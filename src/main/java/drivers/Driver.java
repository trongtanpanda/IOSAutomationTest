package drivers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class supports driver functions.
 * ドライバ機能をサポートする。
 */
public class Driver {

    protected static WebElement findElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    protected static List<WebElement> findElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    protected static void navigate(String url) {
        try {
            DriverManager.getDriver().get(url);
        } catch (Exception e) {
			e.printStackTrace();
			throw e;
        }
    }

    protected static void maximizeBrowser() {
        try {
            DriverManager.getDriver().manage().window().maximize();
        } catch (Exception e) {
        }
    }

    protected static void close() {
        try {
            DriverManager.getDriver().close();
        } catch (Exception e) {
			e.printStackTrace();
            throw e;
        }
    }

    protected static void quit() {
        try {
            DriverManager.getDriver().quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    protected static void back(){
        DriverManager.getDriver().navigate().back();
    }
}
