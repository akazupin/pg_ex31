package service;

public class BaseService {
	final int BASIC_MONTHLY_CHARGE = 1000;
	final int BASIC_CALL_CHARGE_PER_MINUTES = 20;

	public int getBASIC_MONTHLY_CHARGE() {
		return BASIC_MONTHLY_CHARGE;
	}

	public int getBASIC_CALL_CHARGE_PER_MINUTES() {
		return BASIC_CALL_CHARGE_PER_MINUTES;
	}
}
