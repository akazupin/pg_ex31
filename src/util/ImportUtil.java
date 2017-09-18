package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import phone.Phone;

public class ImportUtil {

	final static String SOURCE_FILE_PATH = "data/record.log";
	final static String DEST_FILE_PATH = "data/invoice.dat";

	//読み込みファイルの各行のヘッダ番号
	final static String PHONE_NUMBER_INFO_HEADER = "1";
	final static String OPTION_SERVICE_INFO_HEADER = "2";
	final static String COMMUNICATION_LOG_HEADER = "5";
	final static String DELIMITER_HEADER = "9";

	final static String SERVICE_NAME_C1 = "C1";
	final static String SERVICE_NAME_E1 = "E1";
    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");



	@SuppressWarnings("resource")
	static ArrayList<Phone> readLog() throws IOException {
		File file = new File(SOURCE_FILE_PATH);
		FileReader filereader = new FileReader(file);
		BufferedReader br = new BufferedReader(filereader);
		String lineStr = br.readLine();

		ArrayList<Phone> phoneList = new ArrayList<Phone>();
		Phone phone = null;

		while (lineStr != null) {
			String lineComponent[] = lineStr.split(" ",0);

			switch (lineComponent[0]) {
			case PHONE_NUMBER_INFO_HEADER:
				phone = new Phone(lineComponent[1]);
				break;

			case OPTION_SERVICE_INFO_HEADER:
				if(lineComponent[1] == SERVICE_NAME_C1) {
					phone.setC1Service(lineComponent[2]);
				}
				if(lineComponent[1] == SERVICE_NAME_E1) {
					phone.setE1Service();
				}
				break;

			case COMMUNICATION_LOG_HEADER:
				Date callDate;
				try {
					callDate = sdf.parse(lineComponent[1] + " " + lineComponent[2]);
				} catch (ParseException e) {
					e.printStackTrace();
					break;
				}
				int callTime = Integer.parseInt(lineComponent[3]);
				phone.setCall(callDate,callTime,lineComponent[4]);
				break;

			case DELIMITER_HEADER:
				phoneList.add(phone);
				break;

			default:
				break;
			}

			lineStr = br.readLine();
		}
		return phoneList;
	}
}
