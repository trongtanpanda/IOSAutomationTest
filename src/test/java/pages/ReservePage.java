package pages;

import common.Constants;
import common.Room;
import common.helpers.CurrencyHelper;
import common.helpers.DateHelper;
import common.helpers.LocatorFactory;
import drivers.DriverManager;
import drivers.DriverUtils;
import elements.*;
import elements.Button;
import elements.Label;
import objectData.PaymentCard;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;

public class ReservePage extends GeneralPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));
    protected RadioButton rbAll = new RadioButton(LocatorFactory.getLocator("rbAll"));
    protected RadioButton rbSingle = new RadioButton(LocatorFactory.getLocator("rbSingle"));
    protected RadioButton rbTwin = new RadioButton(LocatorFactory.getLocator("rbTwin"));
    protected Label lblTotal = new Label(LocatorFactory.getLocator("lblTotal"));
    protected Table tblResult = new Table(LocatorFactory.getLocator("tblResult"));
    protected Button btnGotoPayment = new Button(LocatorFactory.getLocator("btnGotoPayment"));
    protected Label optPrepay = new Label(LocatorFactory.getLocator("optPrepay"));
    protected Label optPostPaid = new Label(LocatorFactory.getLocator("optPostPaid"));
    protected Button btnPayment = new Button(LocatorFactory.getLocator("btnPayment"));
    protected Button btnOk = new Button(LocatorFactory.getLocator("btnOk"));
    protected Button btnCancel = new Button(LocatorFactory.getLocator("btnCancel"));
    protected Button btnAlertOk = new Button(LocatorFactory.getLocator("btnAlertOk"));
    protected Button btnBack = new Button(LocatorFactory.getLocator("btnBack"));
    protected TextBox txtNameCard = new TextBox(LocatorFactory.getLocator("txtNameCard"));
    protected TextBox txtCardNumber = new TextBox(LocatorFactory.getLocator("txtCardNumber"));
    protected TextBox txtExpiredDate = new TextBox(LocatorFactory.getLocator("txtExpiredDate"));
    protected TextBox txtCVV = new TextBox(LocatorFactory.getLocator("txtCVV"));
    protected Label lblDetailRoomName = new Label(LocatorFactory.getLocator("lblDetailRoomName"));
    protected Label lblDetailFloor = new Label(LocatorFactory.getLocator("lblDetailFloor"));
    protected Label lblDetailPrice = new Label(LocatorFactory.getLocator("lblDetailPrice"));
    protected Label lblDetailRoomType = new Label(LocatorFactory.getLocator("lblDetailRoomType"));
    protected Label lblDetailDescription = new Label(LocatorFactory.getLocator("lblDetailDescription"));
    protected Button btnCloseRoomDetail = new Button(LocatorFactory.getLocator("btnCloseRoomDetail"));
    protected Alert alError = new Alert(LocatorFactory.getLocator("alError"));
    protected Label lblRoomName = new Label("id=部屋: %s");
    protected Label lblRoomType = new Label("id=タイプ: %s");
    protected Label lblRoomPrice = new Label("id=価格: ¥%s");
    protected CheckBox ckbRoom = new CheckBox("id=ckb-%s");

    public void searchData(Date checkin, Date checkout){
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        selectDate(checkin);
        btnCloseDatePicker.click();
        txtCheckout.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        selectDate(checkout);
        btnCloseDatePicker.click();
        rbAll.click();
        btnSearch.click();
        tblResult.waitForVisibility(Constants.SHORT_TIME);
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
    public void selectAll(){
        rbAll.waitForVisibility(Constants.SHORT_TIME);
        rbAll.click();
    }

    public void viewRoomDetailByRoomName(String roomName){
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        WebElement room =  tblResult.getChildElement(By.xpath("//XCUIElementTypeButton[@name='"+ roomName +"']/parent::XCUIElementTypeOther"));
        room.click();
    }

    public void bookRoom(Room room, Date checkInDate, Date checkoutDate){
        pickCheckInDate(checkInDate);
        pickCheckoutDate(checkoutDate);
        search();
        selectRoomByName(room.getRoomName());
        gotoPayment();
        postPaidPayment();
        payment();
        confirmPayment();
        closeDialog();
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
    public boolean isDataDisplayed(){
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        List<WebElement> list =  tblResult.getChildElements(By.xpath("//XCUIElementTypeButton"));
        return list.size() > 0;
    }

    public boolean isListRoomByTypeDisplayCorrectly(String type){
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        List<WebElement> listSingleRoom =  tblResult.getChildElements(By.xpath("//XCUIElementTypeStaticText[@name='  タイプ: "+ type +"']"));
        List<WebElement> allResult =  tblResult.getChildElements(By.xpath("//XCUIElementTypeButton"));
        return allResult.size() == listSingleRoom.size();
    }

    public boolean isRoomDetailDisplayCorrectly(Room room){
        String actualRoomName = lblDetailRoomName.getText();
        String actualRoomType = lblDetailRoomType.getText();
        String actualRoomPrice = lblDetailPrice.getText();
        String actualRoomFloor = lblDetailFloor.getText();
        String actualRoomDescription = lblDetailDescription.getText();
        String expectedRoomName = "部屋: " + room.getRoomName();
        String expectedRoomType = "タイプ: " + room.getRoomType();
        String expectedRoomPrice = "価格: ¥" + CurrencyHelper.currencyConvert(room.getPrice());
        String expectedRoomFloor = "階: " + room.getFloor();
        String expectedRoomDescription = "記述: " + room.getDescription();
        Boolean isInfoCorrectly = expectedRoomType.equals(actualRoomType) && expectedRoomName.equals(actualRoomName) && expectedRoomPrice.equals(actualRoomPrice)
                && expectedRoomFloor.equals(actualRoomFloor) && expectedRoomDescription.equals(actualRoomDescription);
        Boolean isDisplay = lblDetailRoomName.isDisplayed() && lblDetailRoomType.isDisplayed() && lblDetailDescription.isDisplayed()
                && lblDetailFloor.isDisplayed() && lblDetailPrice.isDisplayed();
        return isDisplay && isInfoCorrectly;
    }
    public boolean isRoomDisplayCorrectlyInPaymentPage(Room room){
        lblRoomName.setDynamicValue(room.getRoomName());
        lblRoomType.setDynamicValue(room.getRoomType());
        lblRoomPrice.setDynamicValue(CurrencyHelper.currencyConvert(room.getPrice()));
        return lblRoomName.isDisplayed() && lblRoomType.isDisplayed() && lblRoomPrice.isDisplayed();
    }
    public boolean isRoomDisplayed(Room room){
        lblRoomName.setDynamicValue(room.getRoomName());
        lblRoomType.setDynamicValue(room.getRoomType());
        lblRoomPrice.setDynamicValue(CurrencyHelper.currencyConvert(room.getPrice()));
        return lblRoomName.isDisplayed() && lblRoomType.isDisplayed() && lblRoomPrice.isDisplayed();
    }
    public boolean isErrorPopupDisplayCorrectly(){
        return alError.isDisplayed();
    }

    public boolean isRoomNotExistInTblList(Room room) {
        ckbRoom.setDynamicValue(room.getRoomName());
        WebElement table = DriverManager.getDriver().findElement(By.id("searchList"));
        List<WebElement> matchingElements = table.findElements(By.id("ckb-"+room.getRoomName()));
        return matchingElements.isEmpty();
    }

    public boolean isTotalDisplayCorrectly(Integer total){
        String actualValue = lblTotal.getText();
        String expectedValue = "合計: ¥" + CurrencyHelper.currencyConvert(total);
        return expectedValue.equals(actualValue) && lblTotal.isDisplayed();
    }
    public boolean isCheckedByRoomName(String roomName){
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        WebElement allResult =  tblResult.getChildElement(By.xpath("//XCUIElementTypeImage[@name='ckb-"+ roomName +"']"));
        String checkboxLabel = allResult.getAttribute("label");
        return checkboxLabel.equals("Selected");
    }
    public boolean isPaymentPage(){
        String actualHeader = lblHeader.getText();
        String expectedHeader = "支払い";
        return expectedHeader.equals(actualHeader) && lblHeader.isDisplayed();
    }
    public void payment(){
        btnPayment.click();
    }
    public void confirmPayment(){
        btnOk.waitForVisibility(Constants.SHORT_TIME);
        btnOk.click();
    }
    public void cancelPayment(){
        btnCancel.waitForVisibility(Constants.SHORT_TIME);
        btnCancel.click();
    }
    public void closeDialog(){
        btnAlertOk.waitForVisibility(Constants.SHORT_TIME);
        btnAlertOk.click();
    }
    public void selectRoomByName(String name){
        ckbRoom.setDynamicValue(name);
        WebElement checkBox = DriverManager.getDriver().findElement(By.id("ckb-"+name));
        WebElement table = DriverManager.getDriver().findElement(By.id("searchList"));
        if(checkBox.getAttribute("visible")== "false"){
            DriverUtils.scrollDownToElement(table,checkBox);
        }

        ckbRoom.waitForDisplay(Constants.SHORT_TIME);
        ckbRoom.click();
    }

    public void gotoPayment(){
        btnGotoPayment.click();
    }
    public String getLblHeader(){
        return lblHeader.getText();
    }
    public void backToReservePage(){
        btnBack.waitForVisibility(Constants.SHORT_TIME);
        btnBack.click();
    }
    public void postPaidPayment() {
        optPostPaid.waitForVisibility(Constants.SHORT_TIME);
        optPostPaid.click();
    }

    public void prePayemnt(PaymentCard paymentCard){
        optPrepay.waitForVisibility(Constants.SHORT_TIME);
        optPrepay.click();
        txtExpiredDate.waitForVisibility(Constants.SHORT_TIME);
        txtExpiredDate.sendKeys(paymentCard.getExpiredDate());
        txtNameCard.waitForVisibility(Constants.SHORT_TIME);
        txtNameCard.sendKeys(paymentCard.getCardName());
        txtCardNumber.waitForVisibility(Constants.SHORT_TIME);
        txtCardNumber.sendKeys(paymentCard.getCardNumber());
        txtCVV.waitForVisibility(Constants.SHORT_TIME);
        txtCVV.sendKeys(paymentCard.getCVV());
        txtCVV.sendKeys(Keys.RETURN);
    }

    public String getDetailRoomLabel() {

        return "lbl201.getText()";
    }



}
