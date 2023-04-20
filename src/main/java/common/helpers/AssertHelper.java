package common.helpers;

import org.testng.Assert;

/**
 * This class supports verifying check point, screenshots for steps and failed check points.
 * チェックポイントの検証、ステップのスクリーンショット、および失敗したチェックポイントをサポートする。
 *
 */
public class AssertHelper {

    public static void checkEqual(Object expected, Object actual, String message) {
        Logger.screenshot(message);
        Assert.assertEquals(expected, actual, message);
    }

    public static void checkEqual(Object expected, Object actual) {
        Logger.screenshot("screenshot");
        Assert.assertEquals(expected, actual);
    }

    public static void checkTrue(boolean actual, String message) {
        //Logger.screenshot(message);
        Assert.assertTrue(actual, message);
    }

    public static void checkTrue(boolean actual) {
        Logger.screenshot("screenshot");
        Assert.assertTrue(actual);
    }

    public static void checkFalse(boolean actual, String message) {
        Logger.screenshot(message);
        Assert.assertFalse(actual, message);
    }

    public static void checkFalse(boolean actual) {
        Logger.screenshot("screenshot");
        Assert.assertFalse(actual);
    }

    public static void checkNotEqual(Object expected, Object actual, String message) {
        Logger.screenshot(message);
        Assert.assertNotEquals(expected, actual, message);
    }

    public static void checkNotEqual(Object expected, Object actual) {
        Logger.screenshot("screenshot");
        Assert.assertNotEquals(expected, actual);
    }
}
