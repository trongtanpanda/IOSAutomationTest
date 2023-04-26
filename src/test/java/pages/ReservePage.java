package pages;

import common.Constants;
import common.helpers.DateHelper;
import common.helpers.LocatorFactory;
import drivers.DriverManager;
import drivers.DriverUtils;
import elements.Button;
import elements.DatePicker;
import elements.Label;
import elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;


import java.text.ParseException;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static common.helpers.Utils.sleep;

public class ReservePage extends GeneralPage {
    protected TextBox txtCheckIn = new TextBox(LocatorFactory.getLocator("txtCheckIn"));
    protected TextBox txtCheckout = new TextBox(LocatorFactory.getLocator("txtCheckOut"));
    protected Button btnSearch = new Button(LocatorFactory.getLocator("btnSearch"));
    protected Button btnCloseCheckInPicker = new Button(LocatorFactory.getLocator("btnCloseCheckInPicker"));
    protected Button btnCloseCheckOutPicker = new Button(LocatorFactory.getLocator("btnCloseCheckOutPicker"));
    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));

    protected DatePicker dpCheckIn = new DatePicker(LocatorFactory.getLocator("checkInPicker"));
    private void selectCheckInDate(Date date){
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        btnShowYearPicker.waitForVisibility(Constants.SHORT_TIME);
        btnShowYearPicker.click();
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

    public void searchData(){
        Date date = new Date("2025/10/31");
        selectCheckInDate(date);
    }

    public String getLblHeader(){
        return lblHeader.getText();
    }

}

