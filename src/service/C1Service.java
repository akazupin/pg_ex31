package service;

import java.util.ArrayList;
import java.util.List;

import phone.Call;

public class C1Service extends OptionService {

	List<String> availablePhoneNumbers = new ArrayList<>();
	private final int REDUCE_RATE_C1 = 2;

	public C1Service(String phoneNumber) {
		availablePhoneNumbers.add(phoneNumber);
		ADD_BASIC_FEE = 100;
	}

	public void setAvailablePhoneNumber(String phoneNumber) {
		availablePhoneNumbers.add(phoneNumber);
	}

	public int changeCallFeePerMinutes(int callFeePerMinutes, Call call){
		for (String availablePhoneNumber : availablePhoneNumbers) {
			if (availablePhoneNumber == call.getTargetPhoneNumber()) {
				return callFeePerMinutes/REDUCE_RATE_C1;
			}
		}
		return callFeePerMinutes;
	}
}