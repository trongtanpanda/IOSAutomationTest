package drivers;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import elements.BaseElement;
import elements.Table;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import common.helpers.Direction;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * This class contains support functions for the driver.
 * ドライバのサポート機能を含むクラス
 */
public class DriverUtils {

    //region Utilities for DiverManager
    public static void loadProperties(String testEnv, String envNumber) {
        DriverManager.loadProperties(testEnv, envNumber);
    }

    public static DriverProperties getCurrentDriverProperties() {
        return DriverManager.getCurrentDriverProperties();
    }

    public static String getPlatformName(String driverName) {
        return DriverManager.getDriverProperties(driverName).getPlatformName();
    }

    public static String getCurrentPlatformName() {
        return DriverManager.getCurrentDriverProperties().getPlatformName();
    }

    public static void setCapability(String capabilityName, String value) {
        DriverManager.setCapability(capabilityName, value);
    }


    public static RemoteWebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static String getDriverName() {
        return DriverManager.getDriverName();
    }

    public static RemoteWebDriver getDriver(String driverName) {
        return DriverManager.getDriver(driverName);
    }

    /**
     * This method help start appActivity of app need test.
     * @param appPackage: app package of app need test.
     * @param appActivity: app activity of app need test.
     * If driver already exist => create new driver else switch to driver.
     *
     *　アプリのappActivityのテスト必要です。
     * @引数 appPackage:　アプリのアプリパッケージはテスト必要
     * @引数 appActivity: アプリのアプリアクティビティはテスト必要
     * ドライバがすでに存在する場合は、新しいドライバを作成する。そうでない場合は、ドライバに切り替える。
     */
    public static void startActivity(String appPackage, String appActivity) {
        DriverManager.getAndroidDriver().startActivity(new Activity(appPackage, appActivity));

    }

    public static void rotate() {
        DeviceRotation rotation = new DeviceRotation(90, 90, 90);
        DriverManager.getIOSDriver().rotate(rotation);
    }

    /**
     * This method help create or switch driver.
     * If driver already exist => create new driver else switch to driver.
     *
     *　ドライバーを作成または切り替える。
     *  ドライバーが既に存在する場合は、新しいドライバーを作成する。それ以外の場合は、ドライバーに切り替える。
     */
    public static void useTestEnv(String testEnv, String driverKey) {
        if (getDriver(driverKey) != null) {
            DriverManager.switchDriver(testEnv, driverKey);
        } else {
            DriverManager.createDriver(testEnv, driverKey);
        }
    }

    public static void quitAllDriver() {
        DriverManager.quitAllDriver();
    }
  
    public static WebElement findElement(By by) {
        return Driver.findElement(by);
    }

    public static List<WebElement> findElements(By by) {
        return Driver.findElements(by);
    }

    public static void navigate(String url) {
        Driver.navigate(url);
    }

    public static void maximizeBrowser() {
        Driver.maximizeBrowser();
    }

    public static void close() {
        Driver.close();
    }

    public static void quit() {
        DriverManager.quit();
    }


    public void navigate(String... args) {
        if (DriverUtils.getCurrentPlatformName().equalsIgnoreCase("Android")) {
            DriverUtils.startActivity(args[0], args[1]);
        } else {
            DriverUtils.navigate(args[0]);
        }
    }

    /**
     * This method help swipe screen.
     * @param dir: Direction want to swipe.
     *
     *　端末の画面をスクロールする
     *　@引数 dir: スワイプしたい方向。
     */
    @SuppressWarnings("rawtypes")
	public static void swipeScreen(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 1000; // ms

        final int PRESS_TIME = 1000; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption<?> pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = getDriver().manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction((PerformsTouchActions) getDriver())
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    /**
     * This method help swipe screen.
     * @param dir: Direction want to swipe.
     * @param pressTime: speed swipe (Use Constants.PRESS_QUICKLY, Constants.PRESS_MEDIUM)
     *
     * 端末の画面をスクロールする
     * @引数 dir: スワイプしたい方向。
     * @引数 pressTime: スピードスワイプ(Constants.PRESS_QUICKLY, Constants.PRESS_MEDIUM)
     */
    @SuppressWarnings("rawtypes")
    public static void swipeScreenQuickly(Direction dir, int pressTime) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 500; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption<?> pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = getDriver().manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction((PerformsTouchActions) getDriver())
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public static void swipeElementToLeft(BaseElement element){
        Dimension dims = getDriver().manage().window().getSize();
        Point location = element.getElement().getLocation();
        int y1 = (int) (location.getY() + location.getY() * 0.1);
        int t = 1000;
        swipe(dims.getWidth()/2, y1, 0, y1, t);
    }
    public static void swipeElementToRight(BaseElement element){
        // init screen variables
        Dimension dims = getDriver().manage().window().getSize();
        Point location = element.getElement().getLocation();
        int y1 = (int) (location.getY() + location.getY() * 0.1);
        int t = 1000;
        if (DriverUtils.getCurrentDriverProperties().getPlatformName().toUpperCase().equals("IOS")){
            swipe(dims.getWidth()/2, y1, dims.getWidth(), y1, t);
        }else{
            swipe(0, y1, dims.getWidth()/2, y1, t);
        }

    }

    public static void scrollDownToElement(WebElement listView, WebElement element){

            TouchAction action = new TouchAction(DriverUtils.getIOSDriver());
            action.longPress(LongPressOptions.longPressOptions()
                            .withElement(ElementOption.element(listView))
                            )
                    .moveTo(ElementOption.element(element))
                    .release()
                    .perform();
    }

    private static void scrollToListElement(AppiumDriver driver, Table listView, WebElement element) {

    }



    private static boolean isElementVisible(WebElement element) {
        System.out.println(element.getAttribute("visible").equals("true"));
        return element.getAttribute("visible").equals("true");
    }
    @SuppressWarnings("rawtypes")
	public static void swipe(int startX, int startY, int endX, int endY, int millisWait) {
        new TouchAction((PerformsTouchActions) getDriver()).press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(millisWait)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }

    /**
     * This method helps to get the name of the device declared in the .txt file
     * @return Name of device
     *
     *　.txtファイルで宣言のデバイス名前を取得します
     * ＠戻り値　デバイスの名前
     */
    public static String getPhysicalDeviceName() {
        DriverProperties properties = getCurrentDriverProperties();
        String deviceName = (String) properties.getCapabilities().getCapability("deviceName");
        if (deviceName == null) {
            deviceName = (String) properties.getPlatformName();
        }
        return deviceName;
    }

    public static IOSDriver<MobileElement> getIOSDriver() {
        return DriverManager.getIOSDriver();
    }

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        return DriverManager.getAndroidDriver();
    }

    public static AppiumDriver<MobileElement> getAppiumDriver() {
        return DriverManager.getAppiumDriver();
    }

    public static void backBrowserWindow(){
        Driver.back();
    }

    public static void reloadPageBrowser(){
        DriverManager.getDriver().navigate().refresh();
    }

    @SuppressWarnings("rawtypes")
	public static void clickLocation(int x, int y){
        new TouchAction((PerformsTouchActions) getDriver()).tap(PointOption.point(x, y)).perform();
    }

    public static void launchApp(String bundlId) {
        HashMap<String, Object> args = new HashMap<String, Object>();
        args.put("bundleId", bundlId);
        ((RemoteWebDriver) DriverUtils.getDriver()).executeScript("mobile: launchApp", args);
    }

    public static void activeApp(String bundleID) {
        AppiumDriver driver = (AppiumDriver) DriverUtils.getDriver();
        driver.activateApp(bundleID);
    }
}
