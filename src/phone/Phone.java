package phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import service.BaseService;
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

	public void setOptionService(String serviceName, OptionService optionService){
		optionServiceList.put(serviceName,optionService);
	}

	public OptionService getOptionService(String serviceName){
		return optionServiceList.get(serviceName);
	}

	public void addCall(Call call) {
		callList.add(call);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Call> getCallList() {
		return callList;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public HashMap<String, OptionService> getOptionServiceList() {
		return optionServiceList;
	}

	public void setMonthlyBasicFee(int monthlyBasicFee) {
		this.monthlyBasicFee = monthlyBasicFee;
	}

	public void setMonthlyCallFee(int monthlyCallFee) {
		this.monthlyCallFee = monthlyCallFee;
	}

	public int getMonthlyBasicFee() {
		return monthlyBasicFee;
	}

	public int getMonthlyCallFee() {
		return monthlyCallFee;
	}
}
