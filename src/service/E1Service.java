package service;

import java.text.ParseException;
import java.util.Date;

import constants.Constants;
import phone.Call;

public class E1Service extends OptionService {

	public int changeMonthlyBasicFee(int monthlyBasicFee) {
		return monthlyBasicFee + Constants.ADD_BASIC_FEE_E1;
	}

	// 割引後の価格を返す
	public int changeCallFeePerMinutes(int callFeePerMinutes, Call call) {
		if (isDiscount(call.getCallDate())) {
			return callFeePerMinutes - Constants.REDUCE_CALL_FEE_E1;
		}
		return callFeePerMinutes;
	}

	// 引数の日付が割引適用時間内であればtrueを、それ以外であればfalseを返す
	private boolean isDiscount(Date date) {

		Date startDate;
		Date endDate;
		try {
			startDate = Constants.timeFormat.parse(Constants.dayFormat.format(date) + " " + Constants.E1_START_TIME);
			endDate = Constants.timeFormat.parse(Constants.dayFormat.format(date) + " " + Constants.E1_END_TIME);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		return date.after(startDate) && date.before(endDate);
	}

}
