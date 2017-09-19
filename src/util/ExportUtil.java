package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import constants.Constants;
import phone.Phone;

public class ExportUtil {

	public static void writeFee(List<Phone> phoneList) throws IOException {

		File file = new File(Constants.DEST_FILE_PATH);
		FileWriter filewriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);

		for (Phone phone : phoneList) {
			printWriter.print(Constants.DEST_PHONE_NUMBER_INFO_HEADER + " " + phone.getPhoneNumber() + "\n");
			printWriter.print(Constants.DEST_BASIC_FEE_HEADER + " " + phone.getMonthlyBasicFee() + "\n");
			printWriter.print(Constants.DEST_CALL_FEE_HEADER + " " + phone.getMonthlyCallFee() + "\n");
			printWriter.print(Constants.DEST_DELIMITER_HEADER + " " + Constants.DELIMITER + "\n");
		}
		printWriter.close();
	}

}
