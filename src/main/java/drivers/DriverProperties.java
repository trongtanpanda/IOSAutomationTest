package drivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import common.helpers.Utils;

/**
 * This class supports manage the driver's properties
 *　ドライバープロパティの管理をサポートのクラス
 */
public class DriverProperties {
	private URL _url;
	private String _browserUrl;
	private DesiredCapabilities capabilities;

	/**
	 * This method set capabilities from map of data
	 * @return Capabilities
	 *
	 *　データのマップからCapabilitiesを設定する
	 * @戻り値 Capabilities
	 */
	protected DriverProperties(HashMap<String, String> map) {
		try {
			_url = new URL(map.get("hubUrl"));
		} catch (MalformedURLException e) {
		}
		_browserUrl = map.get("browserUrl");
		capabilities = new DesiredCapabilities();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (!entry.getKey().equals("hubUrl") && !entry.getKey().equals("configuration")) {
				setCapability(entry.getKey(), entry.getValue());
			}
		}

		String config = map.get("configuration");
		if (config != null) {
			String envFilePath = "resources/environment/%s.txt";
			Properties props = Utils.readProperties(String.format(envFilePath, config), true);
			for (Map.Entry<Object, Object> entry : props.entrySet()) {
				if (entry.getValue() instanceof String) {
					setCapability(entry.getKey().toString(), entry.getValue().toString());
				}
			}
		}

	}

	/**
	 * This method set new caps.
	 * @param capabilityName: name of capability
	 * @param value: value of capability
	 * @return capabilities
	 *
	 * 新しいCapsを設定する。
	 * @引数 capabilityName: capability名
	 * @引数 value: capability値
	 * @戻り値 capabilities
	 */
	protected void setCapability(String capabilityName, String value) {
		if (value != null) {
			capabilities.setCapability(capabilityName, value);
		}
	}

	/**
	 * This method helps get all capabilities of driver.
	 * @return capabilities
	 *
	 *　全てドライバーのCapabilitiesを取得する。
	 * @戻り値 capabilities
	 */
	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}

	/**
	 * This method helps get platform name of driver.
	 * @return platform name.
	 *
	 *　ドライバーのプラットフォーム名を取得する。
	 * ＠戻り値　プラットフォーム名。
	 */
	protected String getPlatformName() {
		return getCapabilities().getCapability("platformName").toString();
	}

	/**
	 * This method helps get Hub Url of driver.
	 * @return Hub Url.
	 *
	 * ドライバーのHub　Urlを取得する。
	 * ＠戻り値　Hub Url.
	 */
	protected URL getHubUrl() {
		return _url;
	}

	/**
	 * This method helps get Browser Url of driver.
	 * @return Browser Url.
	 *
	 * ドライバーのBrowserUrlを取得する。
	 * ＠戻り値　BrowserUrl
	 */
	protected String getBrowserUrl() {
		return _browserUrl;
	}
}