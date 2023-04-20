package common.helpers;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import common.Constants;
import drivers.DriverUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;

/**
 * This function help log info to allure report.
 * Allureレポートに情報を記録する。
 */
public class Logger {
	private static final org.apache.log4j.Logger log = LogManager.getLogger(Logger.class.getName());
	private static Boolean screenshotInfo;
	private static Boolean screenshotVerify;

	/**
	 * This method help init Logger.
	 * Set screenshot for Log Info and Log verify.
	 * Logger初期化を作成する。
	 * スクリーンショットのLog Info と Log Verifyを設定する。
	 */
	public static void createLogger() {
		Properties props = Utils.readProperties(Constants.RUNTIME_SETTINGS_FILE_PATH, true);
		try {
			screenshotInfo = "true".equalsIgnoreCase(props.getProperty("screenshotInfo"));
			screenshotVerify = "true".equalsIgnoreCase(props.getProperty("screenshotVerify"));
		} catch (Exception e) {
			screenshotInfo = false;
			screenshotVerify = true;
		}
	}

	/**
	 * This method help log info (step) and attack to Allure.
	 * @param message: message will display in allure.
	 *
	 *　Info（ステップ）をログに記録し、Allureに攻撃する。
	 * @引数 message: Allureにメッセージが表示する.
	 *
	 */
	public static void info(String message) {
		log.info(message);
		if (screenshotInfo) {
			screenshot(message);
		}
		Allure.step(message);
	}

	/**
	 * This method help log verify and attach to Allure
	 * @param message: message will display in allure.
	 *
	 * Verify をログに記録し、Allureに攻撃する。
	 * @引数 message: Allureにメッセージが表示する.
	 */
	public static void verify(String message) {
		message = "VERIFY POINT: " + message;
		log.info(message);
		if (screenshotVerify) {
			screenshot(message);
		}

	}
	
	public static void pass(String message) {
		message = "PASSED: " + message;
		if (screenshotVerify) {
			screenshot(message);
		}
		Allure.step(message, Status.PASSED);
	}
	
	public static void fail(String message) {
		message = "FAILED: " + message;
		if (screenshotVerify) {
			screenshot(message);
		}
		Allure.step(message, Status.FAILED);
	}

	/**
	 * This method help screenshot device.
	 * @param screenshotFileName: name of file screenshot.
	 *
	 * 画面のスクリーンショットを撮影する
	 * @引数 screenshotFileName:　画面のスクリーンショッの名
	 */
	@Attachment(value = "Screenshot", type = "image/png")
	public static void screenshot(String screenshotFileName) {
		ByteArrayInputStream screenshot = new ByteArrayInputStream(
				((TakesScreenshot) DriverUtils.getDriver()).getScreenshotAs(OutputType.BYTES));
		Allure.addAttachment(DriverUtils.getPhysicalDeviceName() + ": " + screenshotFileName, screenshot);
	}
}
