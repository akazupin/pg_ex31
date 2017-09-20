package main;

import java.io.IOException;
import java.util.ArrayList;
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
	 *@return 基本料金,通話料金の月額情報を含めた携帯電話のリスト
	 */
	private static List<Phone> setMonthlyFee(List<Phone> phoneList) {
		List<Phone> returnPhoneList = new ArrayList<>();
		for(Phone phone :phoneList){
			phone.setMonthlyBasicFee(culcMonthlyBasicFee(phone));
			phone.setMonthlyCallFee(culcMonthlyCallFee(phone));
			returnPhoneList.add(phone);
		}
		return returnPhoneList;
	}


	/**
	 *@param phone 基本料金を求める携帯電話
	 *@return 基本料金月額
	 */
	private static int culcMonthlyBasicFee(Phone phone) {

		//基本サービスの基本料金取得
		int monthlyBasicFee = phone.getBaseService().getBASIC_MONTHLY_CHARGE();

		//オプションサービスごとの基本料金変動を反映
		for (Map.Entry<String, OptionService> optionServiceEntry : phone.getOptionServiceList().entrySet()) {
			monthlyBasicFee = optionServiceEntry.getValue().changeMonthlyBasicFee(monthlyBasicFee);
		}
		return monthlyBasicFee;
	}


	/**
	 *@param phone 通話料金を求める携帯電話
	 *@return 通話料金月額
	 */
	private static int culcMonthlyCallFee(Phone phone) {
		int monthlyCallFee = 0;

		//通話ごとに通話料計算
		for (Call call : phone.getCallList()) {
			int callFeePerMinutes = phone.getBaseService().getBASIC_CALL_CHARGE_PER_MINUTES();

			if (phone.getOptionService(Constants.SERVICE_NAME_E1) != null) {
				callFeePerMinutes = phone.getOptionService(Constants.SERVICE_NAME_E1)
						.changeCallFeePerMinutes(callFeePerMinutes, call);
			}

			if (phone.getOptionService(Constants.SERVICE_NAME_C1) != null) {
				callFeePerMinutes = phone.getOptionService(Constants.SERVICE_NAME_C1)
						.changeCallFeePerMinutes(callFeePerMinutes, call);
			}

			//通話料金を合計通話料金に加算
			monthlyCallFee += (callFeePerMinutes * call.getCallTime());
		}
		return monthlyCallFee;
	}


}
