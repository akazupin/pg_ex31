package phone;

import java.util.Date;

public class Call {
	private Date callDate;
	private int callTime;
	private String targetPhoneNumber;

	public Call(Date callDate, int callTime, String targetPhoneNumber) {
		this.setCallDate(callDate);
		this.setCallTime(callTime);
		this.setTargetPhoneNumber(targetPhoneNumber);
	}

	public String getTargetPhoneNumber() {
		return targetPhoneNumber;
	}

	public void setTargetPhoneNumber(String targetPhoneNumber) {
		this.targetPhoneNumber = targetPhoneNumber;
	}

	public int getCallTime() {
		return callTime;
	}

	public void setCallTime(int callTime) {
		this.callTime = callTime;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
}
