package common.helpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import drivers.DriverUtils;

/**
 * This class help get data of locator.
 *
 *　ロケーターのデータを取得します。
 */
public class LocatorFactory {

	/**
	 * This method help get data of locator.
	 * default key for browser in window: Browser
	 * default key for browser in app: AppBrowser
	 * @param locatorName: locator name.
	 * @return value of locator.
	 *
	 * ロケーターのデータを取得します。
	 *　ウィンドウにブラウザのデフォルト：Browser
	 * アプリにブラウザのデフォルト：AppBrowser
	 * @引数 locatorName: ロケーター名.
	 * @戻り値 ロケーター
	 */
	public static String getLocator(String locatorName) {
		JSONObject json = null;
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		String[] listClassName = className.split("\\.");
		String simpleClassName = listClassName[listClassName.length - 1].split("_")[0];
		String locatorTestPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
				+ System.getProperty("file.separator") + "test";
		String locatorResourcePath = System.getProperty("file.separator") + "resources"
				+ System.getProperty("file.separator") + "locators" + System.getProperty("file.separator")
				+ simpleClassName + ".json";
		try {
			json = (JSONObject) new JSONParser().parse(
					new BufferedReader(new InputStreamReader(Utils.class.getResourceAsStream(locatorResourcePath))));
		} catch (Exception e) {
			try {
				json = (JSONObject) new JSONParser().parse(new FileReader(locatorTestPath + locatorResourcePath));
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
				throw new IllegalArgumentException(e1);
			}
		}

		JSONArray locators = (JSONArray) ((JSONObject) json.get(locatorName)).get("locator");
		String browserName = DriverUtils.getCurrentDriverProperties().getCapabilities().getBrowserName();
		String driverName = DriverUtils.getDriver().getClass().getSimpleName().replace("Driver", "")+browserName;

		int index = getLocatorIndex(locators, driverName);
		if (index != -1) {
			return getLocatorBaseOnIndex(locators, index);
		} else {
			if (driverName.equalsIgnoreCase("Chrome") || driverName.equalsIgnoreCase("Firefox")
					|| driverName.equalsIgnoreCase("Safari") || driverName.equalsIgnoreCase("RemoteWebChrome") || driverName.equalsIgnoreCase("Chromechrome")) {
				driverName = "Browser";
				index = getLocatorIndex(locators, driverName);
				return getLocatorBaseOnIndex(locators, index);
			}
			if(driverName.equalsIgnoreCase("AndroidChrome") || driverName.equalsIgnoreCase("AndroidFirefox")
					|| driverName.equalsIgnoreCase("IOSSafari")){
				driverName = "AppBrowser";
				index = getLocatorIndex(locators, driverName);
				return getLocatorBaseOnIndex(locators, index);
			}
		}

		throw new IllegalArgumentException(
				"Cant find out driverName: " + driverName + " in list locator of " + locatorName);
	}

	/**
	 * This method help get index of locator.
	 * @param locators:   locator name.
	 * @param driverName: driver name.
	 * @return index of locator by platform.
	 *
	 * ロケーターのインデックスを取得する
	 * @引数 locators: ロケーター名.
	 * @引数 driverName: ドライバー名.
	 * @戻り値 プラットフォームごとのロケーターのインデックス
	 */
	private static int getLocatorIndex(JSONArray locators, String driverName) {
		int index = -1;
		for (int i = 0; i < locators.size(); i++) {
			String[] split = locators.get(i).toString().split(":", 2);
			if (split[0].equalsIgnoreCase(driverName)) {
				return i;
			}
		}
		return index;
	}

	private static String getLocatorBaseOnIndex(JSONArray locators, int index) {
		String[] split = locators.get(index).toString().split(":", 2);
		return split[1];
	}
}
