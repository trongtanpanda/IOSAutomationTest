package common.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.google.common.io.Files;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import common.Constants;
import drivers.DriverUtils;
import org.openqa.selenium.JavascriptExecutor;

/**
 * This class contains support functions for the project.
 *
 * プロジェクトのサポート機能が含まれる。
 */
public class Utils {
	@SuppressWarnings("unchecked")
	public static <T> List<T> getCSVData(String csvPath, Class<?> oType) {
		try {
			return new CsvToBeanBuilder<T>(new FileReader(csvPath)).withType((Class<? extends T>) oType).build()
					.parse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * This method get data from csv file.
	 * @param csvFilePath: path to CSV file.
	 * @return list data from csv file.
	 *
	 * csvファイルからデータを取得する。
	 * @引数 csvFilePath: CSVファイルへのパス。
	 * @戻り値 csvファイルからデータをリストアップする。
	 */
	public static List<String[]> getListData(String csvFilePath) {
		CSVReader csvReader;
		try {
			csvReader = new CSVReader(new FileReader(csvFilePath));
			List<String[]> list;
			list = csvReader.readAll();
			csvReader.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * This method helps get data of CSV file.
	 * @param csvPath: path to the csv file to read.
	 * @return list data.
	 *
	 *　CSVファイルのデータを取得する。
	 * @引数 csvPath: 読み込むcsvファイルへのパス。
	 * @戻り値 リストデータ
	 */
	public static Object[] getDataProvider(String csvPath) {
		List<String[]> listData = Utils.getListData(csvPath);
		return listData.toArray(new Object[0]);
	}

	/**
	 * This method helps read properties.
	 * @param filePath:           path to the properties file to read.
	 * @param isExternalResource: if the path is outside the project => isExternalResource ís True.
	 *
	 * プロパティを読み込む。
	 * @引数 filePath:           読み込むプロパティファイルへのパス。
	 * @引数 isExternalResource: パスがプロジェクト外にある場合、isExternalResourceはTrueになります。
	 */
	public static Properties readProperties(String filePath, boolean isExternalResource) {
		InputStream envInput;
		Properties props = new Properties();
		String path = "";

		try {
			envInput = Utils.class.getResourceAsStream(System.getProperty("file.separator") + filePath);
			if (envInput == null) {
				if (isExternalResource) {
					path = System.getProperty("user.dir") + System.getProperty("file.separator");
				} else {
					path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
							+ System.getProperty("file.separator") + "test" + System.getProperty("file.separator");
				}
				envInput = new FileInputStream(path + filePath);
			}

			Reader reader = new InputStreamReader(envInput, StandardCharsets.UTF_8);
			props.load(reader);
		} catch (IOException e) {

		}
		return props;
	}

	public static void sleep(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * This method helps locator find By image.
	 * @param fileName: path to the image file.
	 * @return image in base64.
	 *
	 * 画像でロケーターを探す。
	 * @引数 fileName: 画像ファイルへのパス。
	 * @戻り値 の画像をbase64で表示する。
	 */
	public static String getReferenceImageB64(String fileName) {
		File myFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
				+ System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "resources"
				+ System.getProperty("file.separator") + "locators" + System.getProperty("file.separator") + "img"
				+ System.getProperty("file.separator") + fileName);
		File refImgFile;
		URL refImgUrl;
		try {
			refImgUrl = myFile.toURI().toURL();
			refImgFile = Paths.get(refImgUrl.toURI()).toFile();
			return Base64.getEncoder().encodeToString(Files.toByteArray(refImgFile));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	public static void configTimeout(int shortTime, int mediumTime, int longTime) {
		Constants.SHORT_TIME = shortTime;
		Constants.MEDIUM_TIME = mediumTime;
		Constants.LONG_TIME = longTime;
	}

	/**
	 * This method random text.
	 * @param length: length of text want to random.
	 * @return random text.
	 *
	 * 長さを指定してランダムな文字列を生成する。
	 * @引数 length: 文字列の長さ。
	 * @戻り値: 文字列
	 */
	public static String getRandomAlphaNumericString(int length) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public static String getRandomNumericString(int length) {
		String AlphaNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	/**
	 * This method use JaveScript scroll to top
	 * JavaScriptで端末の画面をトップにスクロールする
	 */
	public static void scrollTopByJS(){
		executeJS("window.scrollTo(0, 0)");
	}

	/**
	 * This method use JaveScript scroll to bottom
	 * JavaScriptで端末の画面をボトムにスクロールする
	 */
	public static void scrollBottomByJS(){
		executeJS("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * This method execute JavaScript
	 * @param script javascript
	 *
	 * JavaScriptを実行する。
	 * @引数 script JavaScript。
	 */
	public static void executeJS(String script){
		JavascriptExecutor jse = DriverUtils.getDriver();
		jse.executeScript(script);
	}

}