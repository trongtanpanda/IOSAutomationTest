package elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Constants;
import common.helpers.Direction;
import common.helpers.Utils;
import drivers.DriverUtils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

/**
 * This class contains support functions for the element.
 * エレメント要素のサポート機能。
 */
public class BaseElement {
    protected By _by;
    protected String _locator;
    protected String _dynamicLocator;

    public BaseElement(String locator) {
        this._locator = locator;
        this._dynamicLocator = locator;
        this._by = getByLocator();
    }

    /**
     * This method helps define By of locator.
     * ロケーターのByを定義する。
     */
    public By getByLocator() {
        String body = this._locator.split("=", 2)[1].trim();
        String type = this._locator.split("=", 2)[0].trim();

        return switch (type) {
            case "id" -> By.id(body);
            case "xpath" -> By.xpath(body);
            case "name" -> By.name(body);
            case "classChain" -> MobileBy.iOSClassChain(body);
            case "img" -> MobileBy.image(Utils.getReferenceImageB64(body));
            default -> By.xpath(this._locator);
        };
    }


	/**
	 * This method set dynamic string to locator
	 * input %s inside locator string
     * @param args = aaa
     * xpath = //*[@id='%s']
     * @return xpath = //*[@id='aaa']
     *
     * 動的な文字列のロケータ
     * ロケータ文字列内の入力 %s
     * @引数 args = aaa　（テキスト）
     * xpath = //*[@id='%s']
     * @戻り値 xpath = //*[@id='aaa']
	 */
    public void setDynamicValue(Object... args) {
        this._locator = String.format(this._dynamicLocator, args);
        this._by = this.getByLocator();
    }

    public WebElement getElement() {
        return DriverUtils.findElement(this._by);
    }

    public List<WebElement> getElements() {
        return DriverUtils.findElements(this._by);
    }

    public String getText() {
        return this.getElement().getText();
    }

    public String getAttribute(String attributeName) {
        return this.getElement().getAttribute(attributeName);
    }

    public boolean isDisplayed() {
        try {
            return this.getElement().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForDisplay() {
        this.waitForDisplay(Constants.SHORT_TIME);
    }

    public void waitForDisplay(int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverUtils.getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(this._by));
    }

    public void waitForVisibility() {
        this.waitForVisibility(Constants.SHORT_TIME);
    }

    public void waitForVisibility(int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverUtils.getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(this._by));
    }
    

    public void waitForInvisibility() {
        this.waitForInvisibility(Constants.SHORT_TIME);
    }

    public void waitForInvisibility(int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverUtils.getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(this._by));
    }

    public void click() {
        getElement().click();
    }
    public List<WebElement> getChildElements(By by) {
        return getElement().findElements(by);
    }


    public WebElement getChildElement(By by) {
        return getElement().findElement(by);
    }
    /**
     * This method helps to scroll to the element until the element is displayed.
     * 表示されるまでスクロールします。
     *　
     * @param direction: Direction want to swipe.
     * @引数 direction: 方向をスワイプしたい。
     */
    public void scrollByDirection(Direction direction) {
        boolean isFoundElement = this.isDisplayed();
        if (isFoundElement) return;
        int i = 15;
        while (i-- > 0) {
            if (direction == Direction.UP) {
                DriverUtils.swipeScreen(Direction.UP);
            } else if (direction == Direction.DOWN) {
                DriverUtils.swipeScreen(Direction.DOWN);
            }
            if (this.isDisplayed()) {
                return;
            }
        }
    }

    /**
     * This method send key to Android device
     *
     *Androidデバイスにキーを入力する
     */
    public void sendAndroidKeys(AndroidKey key) {
        ((PressesKey) DriverUtils.getDriver()).pressKey(new KeyEvent(key));
    }

    /**
     * This method send key to IOS device
     *
     * IOSデバイスにキーを入力する
     */
    @SuppressWarnings("deprecation")
	public void sendIOSKeys(Keys key) {
        DriverUtils.getIOSDriver().getKeyboard().pressKey(key);
    }


    /**
     * This method capture image of locator and export to root folder, support for check image feature
     * ロケーターの画像をキャプチャしてルートフォルダにエクスポート、画像チェック機能のサポートする
     */
    public void captureElement() {
        File srcFile = this.getElement().getScreenshotAs(OutputType.FILE);
        File targetFile = new File("myTest.png");
        try {
            FileUtils.copyFile(srcFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(targetFile.getAbsolutePath());
    }
}
