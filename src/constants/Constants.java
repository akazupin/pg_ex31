package constants;

import java.text.SimpleDateFormat;

public class Constants {
	private Constants() {
	}

	// 読み込み・書き出しファイルのパス
	public static final String SOURCE_FILE_PATH = "data/record.log";
	public static final String DEST_FILE_PATH = "data/invoice.dat";

	// 読み込みファイルの各行のヘッダ番号
	public static final String SOURCE_PHONE_NUMBER_INFO_HEADER = "1";
	public static final String SOURCE_OPTION_SERVICE_INFO_HEADER = "2";
	public static final String SOURCE_CALL_LOG_HEADER = "5";
	public static final String SOURCE_DELIMITER_HEADER = "9";

	// 書き込みファイルの各行のヘッダ番号

	public static final String DEST_PHONE_NUMBER_INFO_HEADER = "1";
	public static final String DEST_BASIC_FEE_HEADER = "5";
	public static final String DEST_CALL_FEE_HEADER = "7";
	public static final String DEST_DELIMITER_HEADER = "9";

	// 書き出しファイルの区切り行
	public static final String DELIMITER = "====================";

	// 日付フォーマット
	public static final SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	// オプションサービス名
	public static final String SERVICE_NAME_C1 = "C1";
	public static final String SERVICE_NAME_E1 = "E1";

	// オプションサービス基本料金値上げ額
	public static final int ADD_BASIC_FEE_C1 = 100;
	public static final int ADD_BASIC_FEE_E1 = 200;

	// オプションサービス通話料金値引き額
	public static final int REDUCE_CALL_FEE_C1 = 2;
	public static final int REDUCE_CALL_FEE_E1 = 5;

	// E1サービス適用時間
	public static final String E1_START_TIME = "08:00";
	public static final String E1_END_TIME = "17:59";
}
