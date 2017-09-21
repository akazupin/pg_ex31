package service;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseService_Test {

	@Test
	public void test() {
		BaseService baseService =  new BaseService();
		assertEquals(baseService.getBASIC_MONTHLY_CHARGE() , 1000);
		assertEquals(baseService.getBASIC_CALL_CHARGE_PER_MINUTES() , 20);
	}

}
