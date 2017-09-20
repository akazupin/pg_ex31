package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constants.Constants;
import phone.Call;
import phone.Phone;
import service.C1Service;
import service.E1Service;

public class ImportUtil {

	public static List<Phone> readLog(String inputFilePath) throws IOException {

		List<Phone> phoneList = new ArrayList<Phone>();
		Phone phone = null;

		File file = new File(inputFilePath);
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		String lineStr = br.readLine();

		while (lineStr != null) {
			String lineComponent[] = lineStr.split(" ", 0);

			switch (lineComponent[0]) {
			case Constants.SOURCE_PHONE_NUMBER_INFO_HEADER:
				phone = new Phone(lineComponent[1]);
				break;

			case Constants.SOURCE_OPTION_SERVICE_INFO_HEADER:
				phone = setService(phone, lineComponent);
				break;

			case Constants.SOURCE_CALL_LOG_HEADER:
				phone = setCall(phone, lineComponent);
				break;

			case Constants.SOURCE_DELIMITER_HEADER:
				if (phone != null) {
					phoneList.add(phone);
					phone = null;
				}
				break;

			default:
				break;
			}
			lineStr = br.readLine();
		}
		br.close();
		return phoneList;
	}

	private static Phone setService(Phone phone, String[] lineComponent) {
		if (phone == null) {
			return null;
		}

		String serviceName = lineComponent[1];

		switch (serviceName) {
		case Constants.SERVICE_NAME_C1:
			C1Service c1Service = (C1Service) phone.getOptionService(serviceName);
			if (c1Service == null) {
				phone.setOptionService(serviceName,  new C1Service(lineComponent[2]));
				return phone;
			}
			c1Service.setAvailablePhoneNumber(lineComponent[2]);
			phone.setOptionService(serviceName, c1Service);
			return phone;

		case Constants.SERVICE_NAME_E1:
			phone.setOptionService(serviceName, new E1Service());
			return phone;

		default:
			return phone;
		}

	}

	private static Phone setCall(Phone phone, String[] lineComponent) {

		if (phone == null) {
			return null;
		}

		Date callDate;
		try {
			callDate = Constants.timeFormat.parse(lineComponent[1] + " " + lineComponent[2]);
		} catch (ParseException e) {
			e.printStackTrace();
			return phone;
		}

		int callTime = Integer.parseInt(lineComponent[3]);
		phone.addCall(new Call(callDate, callTime, lineComponent[4]));
		return phone;
	}
}
