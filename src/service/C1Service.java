package service;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import phone.Call;

public class C1Service extends OptionService {

	List<String> availablePhoneNumbers = new ArrayList<>();

	public C1Service(String phoneNumber) {
		availablePhoneNumbers.add(phoneNumber);
		setADD_BASIC_FEE(Constants.ADD_BASIC_FEE_C1);
	}

	public void setAvailablePhoneNumber(String phoneNumber) {
		availablePhoneNumbers.add(phoneNumber);
	}

	public int changeCallFeePerMinutes(int callFeePerMinutes, Call call) {
		for (String availablePhoneNumber : availablePhoneNumbers) {
			if (availablePhoneNumber.equals(call.getTargetPhoneNumber())) {
				return (int) (callFeePerMinutes / Constants.REDUCE_CALL_FEE_C1);
			}
		}
		return callFeePerMinutes;
	}
}