package service;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import constants.Constants;
import phone.Call;

public class E1Service_Test {

	@Test
	public void test() throws ParseException {
		E1Service e1Service = new E1Service();
		int callFee = e1Service.changeCallFeePerMinutes(20, new Call(Constants.timeFormat.parse("2099/12/22 15:00"), 10, ""));
		assertEquals(15, callFee);

		callFee = e1Service.changeCallFeePerMinutes(10, new Call(Constants.timeFormat.parse("2099/12/22 15:00"), 10, ""));
		assertEquals(5, callFee);

		int monthlyBasicFee = e1Service.changeMonthlyBasicFee(1000);
		assertEquals(1200, monthlyBasicFee);
	}

	public void BorderTest() throws ParseException {
		E1Service e1Service = new E1Service();
		int callFee = e1Service.changeCallFeePerMinutes(20, new Call(Constants.timeFormat.parse("2099/12/22 17:59"), 10, ""));
		assertEquals(15, callFee);

		callFee = e1Service.changeCallFeePerMinutes(20, new Call(Constants.timeFormat.parse("2099/12/22 18:00"), 10, ""));
		assertEquals(20, callFee);

		callFee = e1Service.changeCallFeePerMinutes(20, new Call(Constants.timeFormat.parse("2099/12/22 08:01"), 10, ""));
		assertEquals(15, callFee);

		callFee = e1Service.changeCallFeePerMinutes(20, new Call(Constants.timeFormat.parse("2099/12/22 18:00"), 10, ""));
		assertEquals(20, callFee);
	}

}
