package service;

import java.sql.Date;

import phone.Call;

public class E1Service extends OptionService {
	private final String START_TIME = "08:00";
	private final String END_TIME = "17:59";
	private final int REDUCE_RATE_E1 = 5;

	public E1Service() {
		ADD_BASIC_FEE = 200;
	}

	public int changeCallFeePerMinutes(int callFeePerMinutes, Call call){
		if (isDiscount(call.getCallDate())) {
			return callFeePerMinutes - REDUCE_RATE_E1;
		}
		return callFeePerMinutes;
	}

	private boolean isDiscount(java.util.Date date) {
		if() {
			return true;
		}

		return false;
	}

}
