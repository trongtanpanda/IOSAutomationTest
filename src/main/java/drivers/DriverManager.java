package drivers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import common.Constants;
import common.helpers.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class supports manage driver.
 * ドライバ機能をサポートする。
 */
public class DriverManager {
	private static HashMap<String, DriverProperties> driverPropertiesMap = new HashMap<>();
	private static ThreadLocal<DriverProperties> _driverProperties = new ThreadLocal<>();
	private static HashMap<String, RemoteWebDriver> driverMap = new HashMap<>();
	private static ThreadLocal<String> driverKey = new ThreadLocal<>();
	private static ThreadLocal<String> subDriverKey = new ThreadLocal<>();

	private static void loadDriverProperties(HashMap<String, String> map) {
		try {
			_driverProperties.set(new DriverProperties(map));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This method helps to load all platform capabilities in env and env key file.
	 * @param testEnv: name of file Env.
	 * @param envKey: name of driver.
	 *
	 * envおよびenvキーファイルにすべてのプラットフォーム機能をロードするのに役立つ。
	 * @引数 testEnv: Envファイル名.
	 * @引数 envKey: ドライバ名.
	 */
	protected static void loadProperties(String testEnv, String envKey) {
		String envFilePath = "resources/environment/%s.txt";
		Properties props = Utils.readProperties(String.format(envFilePath, testEnv), true);
		String envName = props.getProperty(envKey);
		props = Utils.readProperties(String.format(envFilePath, envName), true);
		HashMap<String, String> propertiesMap = new HashMap<>();
		for (Map.Entry<Object, Object> entry : props.entrySet()) {
			if (entry.getValue() instanceof String) {
				propertiesMap.put((String) entry.getKey(), (String) entry.getValue());
			}
		}
		loadDriverProperties(propertiesMap);
	}

	protected static void setCapability(String capabilityName, String value) {
		_driverProperties.get().setCapability(capabilityName, value);
	}

	public static RemoteWebDriver getDriver() {
		return driverMap.get(driverKey.get());
	}

	protected static RemoteWebDriver getDriver(String driverKey) {
		try {
			return driverMap.get(Thread.currentThread().getId() + "." + driverKey);
		} catch (Exception e) {
			System.out.println("There is no driver name " + driverKey);
			return null;
		}
	}

	protected static String getDriverName() {
		return driverKey.get();
	}

	@SuppressWarnings("unchecked")
	protected static AndroidDriver<MobileElement> getAndroidDriver() {
		return (AndroidDriver<MobileElement>) driverMap.get(driverKey.get());
	}

	@SuppressWarnings("unchecked")
	protected static IOSDriver<MobileElement> getIOSDriver() {
		return (IOSDriver<MobileElement>) driverMap.get(driverKey.get());
	}

	@SuppressWarnings("unchecked")
	protected static AppiumDriver<MobileElement> getAppiumDriver() {
		return (AppiumDriver<MobileElement>) driverMap.get(driverKey.get());
	}

	private static void set(RemoteWebDriver driver) {
		driverKey.set(Thread.currentThread().getId() + "." + subDriverKey.get());
		driverMap.put(driverKey.get(), driver);
	}

	protected static void createDriver(String testEnv, String driverKey) {
		subDriverKey.set(driverKey);
		initDriver(testEnv, driverKey);
	}

	/**
	 * This method help create new driver. Set this driver to current driver.
	 * @param testEnv:   name of file Env.
	 * @param driverKey: name of driver.
	 *
	 *　新ドライバーを作成し、 このドライバーを現在のドライバーに設定する。
	 * @引数 testEnv:   Envファイル名.
	 * @引数 driverKey: ドライバ名.
	 */
	private static synchronized void initDriver(String testEnv, String driverKey) {
		loadProperties(testEnv, driverKey);
		set(DriverFactory.initDriver(_driverProperties.get()));
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (_driverProperties.get().getPlatformName().equalsIgnoreCase("Chrome")
				|| _driverProperties.get().getPlatformName().equalsIgnoreCase("Firefox")
				|| _driverProperties.get().getPlatformName().equalsIgnoreCase("Safari")) {
			String browserUrl = _driverProperties.get().getBrowserUrl();
			if (browserUrl != null && !browserUrl.equals("")) {
				getDriver().get(browserUrl);
			}
		}
	}

	protected static void switchDriver(String testEnv, String driverName) {
		subDriverKey.set(driverName);
		loadProperties(testEnv, driverName);
		driverKey.set(Thread.currentThread().getId() + "." + subDriverKey.get());
	}

	protected static DriverProperties getDriverProperties(String driverName) {
		return driverPropertiesMap.get(driverName);
	}

	protected static DriverProperties getCurrentDriverProperties() {
		return _driverProperties.get();
	}

	/**
	 * This method helps to remove all the drivers that have been created.
	 * すべての作成ドライバーを削除する。
	 */
	protected synchronized static void quitAllDriver() {
		for (var driverName : driverMap.keySet().toArray()) {
			if (driverName.toString().contains(String.valueOf(Thread.currentThread().getId()))) {
				driverMap.get(driverName.toString()).quit();
				driverMap.remove(driverName.toString());
			}
		}
	}

	protected static void quit() {
		getDriver().quit();
		driverMap.remove(getDriverName().toString());
	}
}

/**
 * This class helps to initialize the correct driver and platform.
 * 正しいドライバーとプラットフォームを初期化する。
 */
class DriverFactory {
	/**
	 * This method helps to initialize the driver by platform in 2 modes (local or remote).
	 * @return: new driver
	 *
	 * 2つのモード（ローカルまたはリモート）でプラットフォームごとにドライバーを初期化する。
	 * @戻り値: 新しいドライバー
	 */
	protected static RemoteWebDriver initDriver(DriverProperties properties) {
		String runMode = Utils.readProperties(Constants.RUNTIME_SETTINGS_FILE_PATH, true).getProperty("runMode");
		RemoteWebDriver driver;
		if (runMode.equalsIgnoreCase("remote")) {
			switch (properties.getPlatformName().toUpperCase()) {
			case "ANDROID" -> driver = new AndroidDriver<MobileElement>(properties.getHubUrl(),
					properties.getCapabilities());
			case "IOS" -> driver = new IOSDriver<>(properties.getHubUrl(), properties.getCapabilities());
			case "FIREFOX" -> {
				WebDriverManager.firefoxdriver().setup();
				driver = new RemoteWebDriver(properties.getHubUrl(), properties.getCapabilities());
			}
			case "SAFARI" -> {
				WebDriverManager.safaridriver().setup();
				driver = new RemoteWebDriver(properties.getHubUrl(), properties.getCapabilities());
			}
			default -> {
				driver = new RemoteWebDriver(properties.getHubUrl(), properties.getCapabilities());
			}
			}
		} else { // runMode = local
			switch (properties.getPlatformName().toUpperCase()) {
			case "ANDROID" -> driver = new AndroidDriver<MobileElement>(properties.getHubUrl(),
					properties.getCapabilities());
			case "IOS" -> driver = new IOSDriver<>(properties.getHubUrl(), properties.getCapabilities());
			case "FIREFOX" -> {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("private");
				driver = new FirefoxDriver();
			}
			case "SAFARI" -> {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			}
			default -> {
				try {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("incognito");
					driver = new ChromeDriver(options);
				}catch (Exception e){
					throw e;
				}


			}
			}
		}
		return driver;
	}
}