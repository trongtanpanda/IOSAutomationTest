package pages;

import common.Constants;
import common.helpers.LocatorFactory;
import drivers.DriverManager;
import elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.helpers.DateHelper;
import java.util.Date;
import java.util.List;

import static common.helpers.Utils.sleep;

public class ReservePage extends GeneralPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));
    protected RadioButton rbAll = new RadioButton(LocatorFactory.getLocator("rbAll"));
    protected RadioButton rbSingle = new RadioButton(LocatorFactory.getLocator("rbSingle"));
    protected RadioButton rbTwin = new RadioButton(LocatorFactory.getLocator("rbTwin"));
    protected Label lblTotal = new Label(LocatorFactory.getLocator("lblTotal"));
    protected Table tblResult = new Table(LocatorFactory.getLocator("tblResult"));
    protected Button btnGotoPayment = new Button(LocatorFactory.getLocator("btnGotoPayment"));
    protected Button btnOk = new Button(LocatorFactory.getLocator("btnOk"));
    protected Button btnCancel = new Button(LocatorFactory.getLocator("btnCancel"));
    protected Button btnAlertOk = new Button(LocatorFactory.getLocator("btnAlertOk"));
    protected Button btnPayment = new Button(LocatorFactory.getLocator("btnPayment"));

    public void searchData(){
        Date checkin = new Date("2023/05/28");
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        selectDate(checkin);
        btnCloseDatePicker.click();
        Date checkout = new Date("2023/05/30");
        txtCheckout.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        selectDate(checkout);
        btnCloseDatePicker.click();
        rbAll.click();
        btnSearch.click();
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        List<WebElement> list =  tblResult.getChildElements(By.xpath("//XCUIElementTypeButton"));
        System.out.println(DateHelper.distanceBetweenTwoDays(checkout, checkin));
        System.out.println(list.size());
    }

    public void pickCheckInDate(Date checkInDate){
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        selectDate(checkInDate);
        btnCloseDatePicker.click();
    }

    public void pickCheckoutDate(Date checkoutDate){
        txtCheckout.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        selectDate(checkoutDate);
        btnCloseDatePicker.click();
    }

    public void search() {
        btnSearch.waitForVisibility(Constants.SHORT_TIME);
        btnSearch.click();
    }
    public void selectSingle(){
        rbSingle.waitForVisibility(Constants.SHORT_TIME);
        rbSingle.click();
    }
    public void selectTwin(){
        rbTwin.waitForVisibility(Constants.SHORT_TIME);
        rbTwin.click();
    }
    public boolean isCheckInDisplayCorrectly(Date checkinDate){
        String txtCheckInValue = txtCheckIn.getText();
        return txtCheckInValue.equals(DateHelper.dateToString(checkinDate));
    }
    public boolean isCheckoutDisplayCorrectly(Date checkoutDate){
        String txtCheckOutValue = txtCheckout.getText();
        return txtCheckOutValue.equals(DateHelper.dateToString(checkoutDate));
    }

    public boolean isCheckoutShouldNotEqualOrLessThanCheckInDate(Date checkinDate){
        pickCheckoutDate(checkinDate);
        String txtCheckOutValue = txtCheckout.getText();
        Date expectedCheckoutDate = DateHelper.plusDaysInDate(checkinDate, 1);
        return txtCheckOutValue.equals(DateHelper.dateToString(expectedCheckoutDate));
    }
    public boolean isAllDataDisplayed(){
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        List<WebElement> list =  tblResult.getChildElements(By.xpath("//XCUIElementTypeButton"));
        System.out.println(list.size());
        return list.size() == 14;
    }

    public boolean isListRoomByTypeDisplayCorrectly(String type){
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        List<WebElement> listSingleRoom =  tblResult.getChildElements(By.xpath("//XCUIElementTypeStaticText[@name='  タイプ: "+ type +"']"));
        List<WebElement> allResult =  tblResult.getChildElements(By.xpath("//XCUIElementTypeButton"));
        return allResult.size() == listSingleRoom.size();
    }
    public void payment(){
        btnPayment.click();
    }
    public void agreePayment(){
        btnOk.waitForVisibility(Constants.SHORT_TIME);
        btnOk.click();
    }
    public void cancelPayment(){
        btnCancel.waitForVisibility(Constants.SHORT_TIME);
        btnCancel.click();
    }
    public void closeAlert(){
        btnAlertOk.waitForVisibility(Constants.SHORT_TIME);
        btnAlertOk.click();
    }
    public void selectRoomByName(String name){
        WebElement checkBox = DriverManager.getDriver().findElement(By.id(name));
        checkBox.click();

    }

    public void gotoPayment(){
        btnGotoPayment.click();
    }
    public String getLblHeader(){
        return lblHeader.getText();
    }

}
