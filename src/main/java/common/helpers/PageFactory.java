package common.helpers;

import drivers.DriverUtils;

/**
 * This function helps get corrected PageObject base on platform.
 *
 * プラットフォーム上で修正されたページオブジェクトベースを取得する。
 */
public class PageFactory {

    /**
     * This method helps return corrected PageObject base on platform.
     * @param pageName: name of page object want to check.
     *　プラットフォーム上で修正されたページオブジェクトベースを返します。
     * @引数 pageName: 確認したいページオブジェクトの名前
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> T load(Class pageName) {
        String browserName = DriverUtils.getCurrentDriverProperties().getCapabilities().getBrowserName();
        String driverName = DriverUtils.getDriver().getClass().getSimpleName().replace("Driver", "")+browserName;
        Class<?> classname;
        try {
            // get Page Object by platform.
            classname = Class.forName(pageName.getName() + "_" + driverName);
            return (T) classname.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            try {
                // get Page Object default in no specific Page Object.
                classname = Class.forName(pageName.getName());
                return (T) classname.getDeclaredConstructor().newInstance();
            } catch (Exception e1) {
				e1.printStackTrace();
				throw new IllegalArgumentException(e1);
            }
        }
    }

}
