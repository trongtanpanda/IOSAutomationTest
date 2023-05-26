package common;

import objectData.Account;

import java.util.Date;

public class TestConstants {
	public static final String CSV_PATH = System.getProperty("user.dir") + "/src/test/resources/data/GoogleKeyword.csv".replace("/", System.getProperty("file.separator"));
	public static final String TEST_DATA_PATH = System.getProperty("user.dir") + "/src/test/resources/data/".replace("/", System.getProperty("file.separator"));
	public static final String CSV_YOUTUBESEARCH = System.getProperty("user.dir") + "/src/test/resources/data/YoutubeData/".replace("/", System.getProperty("file.separator"));
	public static final String CSV_CREATEACCOUNTGOOGLE = System.getProperty("user.dir") + "/src/test/resources/data/CreateAccountGoogleData/".replace("/", System.getProperty("file.separator"));
	public static final String LBL_HEADER_RESERVEDLIST = "予約した部屋リスト";
	public static final String EMAIL = "test";
	public static final String PWD = "admin";
	public static final String WRONGEMAIL = "Hokane.edogawa@gmail.com";
	public static final String WRONGPASSWORD = "Wrongpassword";
	public static final String EMAILBLANK = "";
	public static final String PASSWORDBLANK = "";
	public static final Account WRONGEMAILANDPASSWORD = new Account( "tomioka.giyu@gmail.com", "wrongpassword");

	public static final String BOOKED_ROOM = "予約した部屋";
	public static final String LOGOUT = "ログアウト";
	public static final String LBL_HEADER_RESERVE = "部屋の予約";
	public static final String LBL_HEADER_LOGIN = "宿泊予約";

	public static final String PLACEHOLDER_EMAIL = "メールアドレス";
	public static final String PLACEHOLDER_PASSWORD = "パスワード";

	public static final String ERRORMESSAGE = "有効なアカウントを入力してください";
	public static final String LBLERRORMESSAGE = "アカウントまたはパスワードが無効です";

	public static final Date TODAY = new Date();
	public static final String ERRTITLEDIALOG= "失敗";
	public static final String ERRMSGDIALOG = "クレジットカード情報が誤っています。入力内容をご確認ください";
	public static final String SUCCESSTITLEDIALOG = "予約が完了しました";
	public static final String SUCCESSMSGDIALOG = "予約が成功したことを確認する";
	public static final String ERRMSGACNAMECARD = "カード所有者の名前は大文字にする必要があります";
	public static final String ERRMSGACNUMBER = "カード番号は16桁である必要があります";
	public static final String ERRMSGEXPIREDDATE = "カードの有効期限が正しくありません";
	public static final String ERRMSGSECURECODE = "3桁のセキュリティコードを入力してください";
	public static final String TC14PASSWORD = "vision2022";
	public static final String TC14PASSWORDENCRYPTION = "••••••••••";

	public static final String TC01_FAILED_DATA = "abctest";
	//TC04
	public static final String TC04_DAY_FOR_SEARCH = "2023/08/15";
	public static final String TC04_CHECK_IN_DAY = "2023/08/15";
	public static final String TC04_CHECK_OUT_DAY = "2023/08/17";

	public static final String TC05_DAY_FOR_SEARCH = "2023/08/15";
	public static final String TC05_CHECK_IN_DAY = "2023/08/15";
	public static final String TC05_CHECK_OUT_DAY = "2023/08/17";
	public static final String TC04_MESSAGE_DEFAULT_OF_CARD_NUMBER = "新しい支払い方法を追加する";

	public static final String TC11_SELECT_ROOM_1 = "105";
	public static final String TC11_SELECT_ROOM_2 = "106";
	public static final String TC11_CHECK_IN_DAY_1 = "2023/08/19";
	public static final String TC11_CHECK_OUT_DAY_1 = "2023/08/20";
	public static final String TC11_CHECK_IN_DAY_2 = "2023/08/22";
	public static final String TC11_CHECK_OUT_DAY_2 = "2023/08/23";

	//pattern2
	public static final String PT2_TC02_DAY_FOR_SEARCH = "2023/08/15";
	public static final String PT2_TC02_CHECK_IN_DAY = "2023/08/15";
	public static final String PT2_TC02_CHECK_OUT_DAY = "2023/08/17";
	public static final String PT2_TC03_DAY_FOR_SEARCH = "2023/08/23";
	public static final String PT2_TC03_SELECT_ROOM_1 = "301";
	public static final String PT2_TC03_CHECK_IN_DAY_1 = "2023/08/23";
	public static final String PT2_TC03_CHECK_OUT_DAY_1 = "2023/08/25";
	//pattern3
	public static final String PT3_TC02_DAY_FOR_SEARCH = "2023/08/15";
	public static final String PT3_TC02_CHECK_IN_DAY = "2023/08/15";
	public static final String PT3_TC02_CHECK_OUT_DAY = "2023/08/17";
	public static final String PT3_TC03_DAY_FOR_SEARCH = "2023/08/27";
	public static final String PT3_TC03_SELECT_ROOM_1 = "201";
	public static final String PT3_TC03_CHECK_IN_DAY_1 = "2023/08/27";
	public static final String PT3_TC03_CHECK_OUT_DAY_1 = "2023/08/29";
	public static final String PREPAYMENT = "事前決済";
	public static final String POSTPAID = "後払い";

}