package service;

import phone.Call;

public abstract class OptionService {
	private int ADD_BASIC_FEE = 0;

	public int changeMonthlyBasicFee(int monthlyBasicFee) {
		return monthlyBasicFee + ADD_BASIC_FEE;
	}

	public int changeCallFeePerMinutes(int callFeePerMinutes, Call call) {
		return callFeePerMinutes;
	}

	public int getADD_BASIC_FEE() {
		return ADD_BASIC_FEE;
	}

	public void setADD_BASIC_FEE(int aDD_BASIC_FEE) {
		ADD_BASIC_FEE = aDD_BASIC_FEE;
	}

}
