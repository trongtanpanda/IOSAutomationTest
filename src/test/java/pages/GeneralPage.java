package pages;

import common.Constants;
import common.helpers.DateHelper;
import common.helpers.LocatorFactory;
import drivers.DriverManager;
import drivers.DriverUtils;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GeneralPage {
    protected Button btnMenu = new Button(LocatorFactory.getLocator("btnMenu"));
    protected Button btnReserve = new Button(LocatorFactory.getLocator("btnReserve"));
    protected Button btnReservedList = new Button(LocatorFactory.getLocator("btnReservedList"));
    protected Button btnLogout = new Button(LocatorFactory.getLocator("btnLogout"));
    protected Button btnShowYearPicker = new Button(LocatorFactory.getLocator("showYearPicker"));
    protected Button btnHideYearPicker = new Button(LocatorFactory.getLocator("hideYearPicker"));

    public void logout(){
        btnMenu.waitForVisibility(Constants.SHORT_TIME);
        btnMenu.click();
        btnLogout.waitForVisibility(Constants.SHORT_TIME);
        btnLogout.click();
    }

    public void showReservedList() {
        btnMenu.waitForVisibility(Constants.SHORT_TIME);
        btnMenu.click();
        btnReservedList.waitForVisibility(Constants.SHORT_TIME);
        btnReservedList.click();
    }

    public void gotoReservePage() {
        btnMenu.waitForVisibility(Constants.SHORT_TIME);
        btnMenu.click();
        btnReserve.waitForVisibility(Constants.SHORT_TIME);
        btnReserve.click();
    }

    public void selectDate(Date date){
        List<WebElement> values = DriverUtils.findElements(By.xpath("//XCUIElementTypePickerWheel"));
        int gapMonth = DateHelper.getMonthOfDate(date) - DateHelper.monthToNumber(values.get(0).getText());
        int gapYear = DateHelper.getYearOfDate(date) - Integer.parseInt(values.get(1).getText());
        int day = DateHelper.getDayOfDate(date);
        if (gapMonth != 0) {
            String order = gapMonth > 0 ? "next" : "previous";
            HashMap<String, Object> params = new HashMap<>();
            params.put("order", order);
            params.put("offset", 0.15);
            params.put("element", ((RemoteWebElement) values.get(0)).getId());
            for(int i = 0; i<gapMonth; i++){
                DriverManager.getDriver().executeScript("mobile: selectPickerWheelValue", params);
            }
        }
        if (gapYear != 0) {
            String order = gapYear > 0 ? "next" : "previous";
            HashMap<String, Object> params = new HashMap<>();
            params.put("order", order);
            params.put("offset", 0.15);
            params.put("element", ((RemoteWebElement) values.get(1)).getId());
            for(int i = 0; i<gapYear; i++){
                DriverManager.getDriver().executeScript("mobile: selectPickerWheelValue", params);
            }
        }
        btnHideYearPicker.click();
        WebElement dayElm = DriverManager.getDriver().findElement(By.id(Integer.toString(day)));
        dayElm.click();
    }
}
