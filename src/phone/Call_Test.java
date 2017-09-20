package phone;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import constants.Constants;

public class Call_Test {
	Call call;
	Date date;
	String dateStr = "2017/09/10 11:21";

	@Before
	public void beforeTest() {
		try {
			date = Constants.timeFormat.parse(dateStr);
		} catch (ParseException e) {
			fail("テストコードのエラー");
			e.printStackTrace();
		}
		call = new Call(date, 10, "090-1111-1111");
	}

	@Test
	public void testCall() {
		assertEquals(dateStr, Constants.timeFormat.format(call.getCallDate()));
		assertEquals(10, call.getCallTime());
		assertEquals("090-1111-1111", call.getTargetPhoneNumber());
	}

}
