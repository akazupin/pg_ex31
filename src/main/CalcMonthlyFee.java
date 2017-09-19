package main;

import java.io.IOException;
import java.util.List;

import constants.Constants;
import phone.Phone;
import util.ExportUtil;
import util.ImportUtil;

public class CalcMonthlyFee {
	public static void main(String[] args) {
		System.out.println(Constants.SOURCE_FILE_PATH + "の読み込みを開始します");

		List<Phone> phoneList;

		try {
			phoneList = ImportUtil.readLog();
		} catch (IOException e) {
			System.out.println("ファイル読み込みエラーが発生しました");
			e.printStackTrace();
			return;
		}

		System.out.println(Constants.DEST_FILE_PATH + "への書き込みを開始します");

		try {
			ExportUtil.writeFee(phoneList);
		} catch (IOException e) {
			System.out.println("ファイル書き込みエラーが発生しました");
			e.printStackTrace();
		}

		System.out.println("処理が完了しました");

	}
}
