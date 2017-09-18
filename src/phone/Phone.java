package phone;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.BaseService;
import service.C1Service;
import service.E1Service;
import service.OptionService;

public class Phone {
	private String phoneNumber;
	private BaseService baseService;
	private HashMap<String, OptionService> optionServiceList = new HashMap<String, OptionService>();
	private List<Call> calls = new ArrayList<>();

	private int monthlyBasicFee;
	private int monthlyCallFee;

	final String SERVICE_NAME_C1 = "C1";
	final String SERVICE_NAME_E1 = "E1";

	public Phone(String phoneNumber) {
		baseService = new BaseService();
		setPhoneNumber(phoneNumber);
	}

	public void setC1Service(String phoneNumber) {
		C1Service c1Service = (C1Service) optionServiceList.get(SERVICE_NAME_C1);
		if (c1Service == null) {
			optionServiceList.put(SERVICE_NAME_C1, new C1Service(phoneNumber));
			return;
		}

		c1Service.setAvailablePhoneNumber(phoneNumber);
	}

	public void setE1Service() {
		optionServiceList.put(SERVICE_NAME_E1, new E1Service());
	}


	public void setCall(Date callDate, int callTime, String targetPhoneNumber) {
		calls.add(new Call(callDate,callTime,targetPhoneNumber));
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void culcMonthlyFee() {
		culcMonthlyBasicFee();
		culcMonthlyCallFee();
	}

	private void culcMonthlyBasicFee() {
		monthlyBasicFee = baseService.getBASIC_MONTHLY_CHARGE();
		for (Map.Entry<String, OptionService> optionServiceEntry :optionServiceList.entrySet()) {
			monthlyBasicFee = optionServiceEntry.getValue().changeMonthlyBasicFee(monthlyBasicFee);
		}
	}

	private void culcMonthlyCallFee() {
		monthlyCallFee = 0;
		for (Call call :calls) {
			int callFeePerMinutes = baseService.getBASIC_CALL_CHARGE_PER_MINUTES();
			if (optionServiceList.get("E1") != null) {
				callFeePerMinutes = optionServiceList.get("E1").changeCallFeePerMinutes(callFeePerMinutes,call);
			}

			if (optionServiceList.get("C1") != null) {
				callFeePerMinutes = optionServiceList.get("C1").changeCallFeePerMinutes(callFeePerMinutes,call);
			}

			monthlyCallFee = monthlyCallFee + (callFeePerMinutes * call.getCallTime());
		}
	}

	public int getMonthlyBasicFee() {
		return monthlyBasicFee;
	}

	public int getMonthlyCallFee() {
		return monthlyCallFee;
	}
}
