package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.Constants;
import phone.Call;
import phone.Phone;
import service.OptionService;
import util.ExportUtil;
import util.ImportUtil;


/**
 *
 */
public class CalcMonthlyFee {
	public static void main(String[] args) {
		System.out.println(Constants.SOURCE_FILE_PATH + "の読み込みを開始します");

		List<Phone> phoneList;

		try {
			phoneList = ImportUtil.readLog(Constants.SOURCE_FILE_PATH);
		} catch (IOException e) {
			System.out.println("ファイル読み込みエラーが発生しました");
			e.printStackTrace();
			return;
		}

		phoneList = setMonthlyFee(phoneList);

		System.out.println(Constants.DEST_FILE_PATH + "への書き込みを開始します");

		try {
			ExportUtil.writeFee(phoneList , Constants.DEST_FILE_PATH);
		} catch (IOException e) {
			System.out.println("ファイル書き込みエラーが発生しました");
			e.printStackTrace();
			return;
		}

		System.out.println("処理が完了しました");

	}

	/**
	 *@param phoneList 基本料金,通話料金の月額を求める携帯電話のリスト
	 *@return returnPhoneList 基本料金,通話料金の月額情報を含めた携帯電話のリスト
	 */
	private static List<Phone> setMonthlyFee(List<Phone> phoneList) {
		List<Phone> returnPhoneList = new ArrayList<>();
		for(Phone phone :phoneList){
			int monthlyBasicFee = culcMonthlyBasicFee(phone.getBaseService().getBASIC_MONTHLY_CHARGE(), phone.getOptionServiceList());
			phone.setMonthlyBasicFee(monthlyBasicFee);

			int monthlyCallFee = culcMonthlyCallFee(phone.getBaseService().getBASIC_CALL_CHARGE_PER_MINUTES(), phone.getCallList(), phone.getOptionServiceList());
			phone.setMonthlyCallFee(monthlyCallFee);
			returnPhoneList.add(phone);
		}
		return returnPhoneList;
	}


	/**
	 *@param baseMonthlyBasicFee 携帯電話の基本サービスの基本料金
	 *@param optionServiceList 携帯電話が加入しているオプションサービスのHashMap
	 *@return monthlyBasicFee 基本料金月額
	 */
	private static int culcMonthlyBasicFee(int baseMonthlyBasicFee, HashMap<String, OptionService> optionServiceList) {

		//基本サービスの基本料金取得
		int monthlyBasicFee = baseMonthlyBasicFee;

		//オプションサービスごとの基本料金変動を反映
		for (Map.Entry<String, OptionService> optionServiceEntry : optionServiceList.entrySet()) {
			monthlyBasicFee = optionServiceEntry.getValue().changeMonthlyBasicFee(monthlyBasicFee);
		}
		return monthlyBasicFee;
	}


	/**
	 *@param basicCallChargePerMinutes 携帯電話の基本サービスの通話料/分
	 *@param callList 携帯電話の通話のリスト
	 *@param optionServiceList 携帯電話が加入しているオプションサービスのHashMap
	 *@return 通話料金月額
	 */
	private static int culcMonthlyCallFee(int basicCallChargePerMinutes, List<Call> callList, HashMap<String, OptionService> optionServiceList) {
		int monthlyCallFee = 0;

		//通話ごとに通話料計算
		for (Call call : callList) {
			int callFeePerMinutes = basicCallChargePerMinutes;

			if (optionServiceList.get(Constants.SERVICE_NAME_E1) != null) {
				callFeePerMinutes = optionServiceList.get(Constants.SERVICE_NAME_E1)
						.changeCallFeePerMinutes(callFeePerMinutes, call);
			}

			if (optionServiceList.get(Constants.SERVICE_NAME_C1) != null) {
				callFeePerMinutes = optionServiceList.get(Constants.SERVICE_NAME_C1)
						.changeCallFeePerMinutes(callFeePerMinutes, call);
			}

			//通話料金を合計通話料金に加算
			monthlyCallFee += (callFeePerMinutes * call.getCallTime());
		}
		return monthlyCallFee;
	}


}
