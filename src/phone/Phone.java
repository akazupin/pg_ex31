package phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.Constants;
import service.BaseService;
import service.C1Service;
import service.E1Service;
import service.OptionService;

public class Phone {
	private String phoneNumber;
	private BaseService baseService;
	private HashMap<String, OptionService> optionServiceList = new HashMap<String, OptionService>();
	private List<Call> callList = new ArrayList<>();

	private int monthlyBasicFee;
	private int monthlyCallFee;

	public Phone(String phoneNumber) {
		baseService = new BaseService();
		setPhoneNumber(phoneNumber);
	}


	public void setC1Service(String phoneNumber) {
		C1Service c1Service = (C1Service) optionServiceList.get(Constants.SERVICE_NAME_C1);
		if (c1Service == null) {
			optionServiceList.put(Constants.SERVICE_NAME_C1, new C1Service(phoneNumber));
			return;
		}
		c1Service.setAvailablePhoneNumber(phoneNumber);
	}

	public void setE1Service() {
		optionServiceList.put(Constants.SERVICE_NAME_E1, new E1Service());
	}

	public void setOptionService(String serviceName, OptionService optionService){
		optionServiceList.put(serviceName,optionService);
	}

	public OptionService getOptionService(String serviceName){
		return optionServiceList.get(serviceName);
	}

	public void addCall(Call call) {
		callList.add(call);
	}

	private void culcMonthlyBasicFee() {
		monthlyBasicFee = baseService.getBASIC_MONTHLY_CHARGE();
		for (Map.Entry<String, OptionService> optionServiceEntry : optionServiceList.entrySet()) {
			monthlyBasicFee = optionServiceEntry.getValue().changeMonthlyBasicFee(monthlyBasicFee);
		}
	}

	private void culcMonthlyCallFee() {
		monthlyCallFee = 0;
		for (Call call : callList) {
			int callFeePerMinutes = baseService.getBASIC_CALL_CHARGE_PER_MINUTES();

			if (optionServiceList.get(Constants.SERVICE_NAME_E1) != null) {
				callFeePerMinutes = optionServiceList.get(Constants.SERVICE_NAME_E1)
						.changeCallFeePerMinutes(callFeePerMinutes, call);
			}

			if (optionServiceList.get(Constants.SERVICE_NAME_C1) != null) {
				callFeePerMinutes = optionServiceList.get(Constants.SERVICE_NAME_C1)
						.changeCallFeePerMinutes(callFeePerMinutes, call);
			}
			monthlyCallFee = monthlyCallFee + (callFeePerMinutes * call.getCallTime());
		}
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getMonthlyBasicFee() {
		culcMonthlyBasicFee();
		return monthlyBasicFee;
	}

	public int getMonthlyCallFee() {
		culcMonthlyCallFee();
		return monthlyCallFee;
	}
}
