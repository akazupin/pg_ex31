package service;

import phone.Call;


public abstract class OptionService {

	/**
	 * @author z2150099
	 *
	 */
	public abstract int changeMonthlyBasicFee(int monthlyBasicFee);

	public abstract  int changeCallFeePerMinutes(int callFeePerMinutes, Call call);


}
