package service;

import phone.Call;

public abstract class OptionService {
	int ADD_BASIC_FEE = 0;

	public int changeMonthlyBasicFee(int monthlyBasicFee){
		return monthlyBasicFee + ADD_BASIC_FEE;
	}

	public int changeCallFeePerMinutes(int callFeePerMinutes, Call call){
		return callFeePerMinutes;
	}
}
