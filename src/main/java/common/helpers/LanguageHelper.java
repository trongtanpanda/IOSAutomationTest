package common.helpers;

import java.util.Properties;

/**
 * This class used for Japanese or English usage for data.
 *
 *　データの日本語また英語に使用される。
 */
public class LanguageHelper {
	private static String suffix;
	private Properties props;

	public LanguageHelper(String className) {
		loadLanguageProperties(className);
	}

	@SuppressWarnings("rawtypes")
	public LanguageHelper(Class clazz) {
		loadLanguageProperties(clazz.getSimpleName());
	}

	/**
	 * This method load language from language files.
	 * @param className: name of the class want to use.
	 *　
	 * 言語ファイルから読み組む
	 * ＠引数　className：　使用したいクラスの名前
	 */
	private void loadLanguageProperties(String className) {
		String languageDataFilePath = "resources/data/multiLanguage/" + className + suffix;
		props = Utils.readProperties(languageDataFilePath, false);
	}

	/**
	 * This method help set language when running.
	 * @param language: japanese or english.
	 *
	 *　実行時に言語を設定します。
	 * @引数 language: japanese また　english.
	 */
	private static void setSuffix(String language) {
		if (language.equalsIgnoreCase("Japanese")) {
			suffix = "_ja_JP.properties";
		} else {
			suffix = "_en_US.properties";
		}
		System.out.println("Set test run language to " + language);
	}

	public String get(String key) {
		return props.getProperty(key);
	}

	/**
	 * This method help set language when running.
	 * @param language: japanese or english.
	 *
	 * 実行時に言語を設定します。
	 * @引数 language: japanese また　english.
	 */
	public static void setTestRunLanguage(String language) {
		setSuffix(language);
	}
}
