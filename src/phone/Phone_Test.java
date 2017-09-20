package phone;

import static org.junit.Assert.*;

import org.junit.Test;

import service.BaseService;

public class Phone_Test {

	@Test
	public void testPhone_default() {
		Phone phone = new Phone("090-1111-1111");
		assertEquals("090-1111-1111" , phone.getPhoneNumber());

		BaseService testBaseService = new BaseService();
	}
}
