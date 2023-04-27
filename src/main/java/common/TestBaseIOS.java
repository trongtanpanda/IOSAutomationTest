package common;

import common.helpers.LanguageHelper;
import common.helpers.Logger;
import common.helpers.Utils;
import drivers.DriverUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBaseIOS {

	@Parameters({ "language", "env", "defaultEnv", "shortTime", "mediumTime", "longTime" })
	@BeforeMethod(alwaysRun = true)
	public void beforeTest(String language, String env, String defaultEnv, int shortTime, int mediumTime, int longTime) {
		Utils.configTimeout(shortTime, mediumTime, longTime);
		DriverUtils.useTestEnv(env, defaultEnv);
		LanguageHelper.setTestRunLanguage(language);
		Logger.createLogger();
		Config();
		System.out.println("End test base");
	}

//	@AfterMethod(alwaysRun = true)
//	public void afterTest() {
//		DriverUtils.quit();
//	}
	
	
	public void Config() {
		   DriverUtils.maximizeBrowser();
	}
}
