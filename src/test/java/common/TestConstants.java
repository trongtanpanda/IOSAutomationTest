package common;

import objectData.PaymentCard;

public class TestConstants {
	public static final String CSV_PATH = System.getProperty("user.dir") + "/src/test/resources/data/GoogleKeyword.csv".replace("/", System.getProperty("file.separator"));
	public static final String TEST_DATA_PATH = System.getProperty("user.dir") + "/src/test/resources/data/".replace("/", System.getProperty("file.separator"));
	public static final String CSV_YOUTUBESEARCH = System.getProperty("user.dir") + "/src/test/resources/data/YoutubeData/".replace("/", System.getProperty("file.separator"));
	public static final String CSV_CREATEACCOUNTGOOGLE = System.getProperty("user.dir") + "/src/test/resources/data/CreateAccountGoogleData/".replace("/", System.getProperty("file.separator"));

	public static final String EMAIL = "test";
	public static final String PWD = "admin";

	public static final String BOOKED_ROOM = "予約した部屋";
	public static final String LOGOUT = "ログアウト";

	//Pattern 1
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
	public static final PaymentCard PT2_TCO3_DATA_PAYMENT = new PaymentCard("test","5555555555554444","","");

	//pattern3
	public static final String PT3_TC02_DAY_FOR_SEARCH = "2023/08/15";
	public static final String PT3_TC02_CHECK_IN_DAY = "2023/08/15";
	public static final String PT3_TC02_CHECK_OUT_DAY = "2023/08/17";
	public static final String PT3_TC03_DAY_FOR_SEARCH = "2023/08/27";
	public static final String PT3_TC03_SELECT_ROOM_1 = "201";
	public static final String PT3_TC03_CHECK_IN_DAY_1 = "2023/08/27";
	public static final String PT3_TC03_CHECK_OUT_DAY_1 = "2023/08/29";
	public static final PaymentCard PT3_TCO3_DATA_PAYMENT = new PaymentCard("test","3530111333300000","","");

	public static final PaymentCard TCO7_DATA_PAYMENT = new PaymentCard("test","4111111111111111","","");
	public static final PaymentCard TCO8_DATA_PAYMENT = new PaymentCard("test","4111111111111111","","");
	public static final PaymentCard TCO8_DATA_ERROR_1_PAYMENT = new PaymentCard("test","41111111111111112222","","");
	public static final PaymentCard TCO8_DATA_ERROR_2_PAYMENT = new PaymentCard("test","411111111111111","","");
	public static final PaymentCard TCO8_DATA_ERROR_3_PAYMENT = new PaymentCard("test","","","");
	public static final PaymentCard TCO9_DATA_PAYMENT = new PaymentCard("test","4111111111111111","","");
	public static final PaymentCard TCO6_DATA_PAYMENT = new PaymentCard("test test test test test test test","411111111111111","","");
	public static final PaymentCard TCO6_DATA_BLANK_PAYMENT = new PaymentCard("","","","");
}