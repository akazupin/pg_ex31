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
import phone.Phone;

public class ImportUtil {

	public static List<Phone> readLog() throws IOException {
		File file = new File(Constants.SOURCE_FILE_PATH);
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		String lineStr = br.readLine();

		List<Phone> phoneList = new ArrayList<Phone>();
		Phone phone = null;

		while (lineStr != null) {
			String lineComponent[] = lineStr.split(" ", 0);

			switch (lineComponent[0]) {
			case Constants.SOURCE_PHONE_NUMBER_INFO_HEADER:
				phone = new Phone(lineComponent[1]);
				break;

			case Constants.SOURCE_OPTION_SERVICE_INFO_HEADER:
				if (phone == null) {
					break;
				}
				if (lineComponent[1].equals(Constants.SERVICE_NAME_C1)) {
					phone.setC1Service(lineComponent[2]);
				}
				if (lineComponent[1].equals(Constants.SERVICE_NAME_E1)) {
					phone.setE1Service();
				}
				break;

			case Constants.SOURCE_CALL_LOG_HEADER:
				if (phone == null) {
					break;
				}
				Date callDate;
				try {
					callDate = Constants.timeFormat.parse(lineComponent[1] + " " + lineComponent[2]);
				} catch (ParseException e) {
					e.printStackTrace();
					break;
				}
				int callTime = Integer.parseInt(lineComponent[3]);
				phone.setCall(callDate, callTime, lineComponent[4]);
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
}
