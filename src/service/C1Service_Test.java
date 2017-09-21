package service;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import phone.Call;

public class C1Service_Test {

	@Test
	public void test() throws ParseException {
		C1Service c1Service = new C1Service("090-1111-1111");

		int callFee = c1Service.changeCallFeePerMinutes(20, new Call(null, 0, "090-1111-1111"));
		assertEquals(10, callFee);

		callFee = c1Service.changeCallFeePerMinutes(20, new Call(null, 0, "090-1111-2222"));
		assertEquals(20, callFee);

		int monthlyBasicFee = c1Service.changeMonthlyBasicFee(1000);
		assertEquals(1100, monthlyBasicFee);
	}

	public void BorderTest() throws ParseException {

	}
}
