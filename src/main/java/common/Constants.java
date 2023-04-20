package common;

public class Constants {
	private static final String SEPARATOR = System.getProperty("file.separator");

//    public static final String RESOURCES_FOLDER_PATH = USER_DIR + "/resources/".replace("/", SEPARATOR);
	public static final String RUNTIME_SETTINGS_FILE_PATH = "resources/environment/RuntimeSettings.txt".replace("/",
			SEPARATOR);

	// YOUTUBE
	public static final String KEYWORD_SEARCH = "Logigear";
	public static final String URL_YOUTUBE_MOBILE = "https://m.youtube.com";

	// SAFARI
	public static final String URL_YOUTUBE = "https://www.youtube.com";
	public static final String DESCRIPTION_LOGIGEAR="LogiGear has been in the software testing industry for 25 years. Thought leaders in software testing and test automation, based in Silicon Valley, Vietnam and Japan. Makers of the TestArchitect test automation framework. This channel is designed to share our industry knowledge with you to help improve your software testing efforts. Enjoy!";
	public static final String WHAT_NEW_LOGIGEAR = "What's New in TestArchitect 9.0?";

	public static final String URL_GUERRILLAMAIL = "https://www.guerrillamail.com/inbox";
	public static final String DOMAIN = "@sharklasers.com";

	public static int SHORT_TIME = 15;
	public static int MEDIUM_TIME = 25;
	public static int LONG_TIME = 55;

	public static int PRESS_QUICKLY = 100;
	public static int PRESS_MEDIUM = 500;
}
