package pages;

import common.Constants;
import common.helpers.LanguageHelper;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class GeneralPage {
    protected LanguageHelper lang = new LanguageHelper(GeneralPage.class);
    protected Label lblTitle = new Label(LocatorFactory.getLocator("lblTitle"));
    protected Label lblMessage = new Label(LocatorFactory.getLocator("lblMessage"));
    protected Button btnOK = new Button(LocatorFactory.getLocator("btnOK"));
    protected Button btnBack = new Button(LocatorFactory.getLocator("btnBack"));
    protected Button btnClose = new Button(LocatorFactory.getLocator("btnClose"));
    public TextBox txtCheckInDay = new TextBox(LocatorFactory.getLocator("txtCheckInDay"));
    public TextBox txtCheckOutDay = new TextBox(LocatorFactory.getLocator("txtCheckOutDay"));
    protected Label lblInfoRoomName = new Label(LocatorFactory.getLocator("lblInfoRoomName"));
    protected Button btnConfirm = new Button(LocatorFactory.getLocator("btnConfirm"));
    public Label lblRoomName = new Label(LocatorFactory.getLocator("lblRoomName"));

    public boolean isRoomReservationPageDisplayed() {
        lblTitle.setDynamicValue(lang.get("ROOM_RESERVATION"));
        return lblTitle.isDisplayed();
    }

    public boolean isMessageCorrectly(String message){
        return lblMessage.getText().equals(message);
    }

    public void pressOKButton(){
        btnOK.waitForVisibility(Constants.SHORT_TIME);
        btnOK.click();
    }

    public boolean isConfirmButtonDisplayed() {
        btnConfirm.waitForDisplay();
        return btnConfirm.isDisplayed();
    }

    public String getToday() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return currentDate.format(formatter);
    }
    public void pressConfirmButton() {
        btnConfirm.waitForDisplay(Constants.SHORT_TIME);
        btnConfirm.click();
    }
    public boolean isStateConfirmButtonEnable() {
        btnConfirm.waitForDisplay();
        return "true".equals(btnConfirm.getAttribute("enabled"));
    }
    public String getRoomName() {
        return lblRoomName.getText();
    }

    public void inputCheckInDay(String checkInDay) {
        txtCheckInDay.waitForVisibility(Constants.SHORT_TIME);
        txtCheckInDay.sendKeys(checkInDay);
    }

    public void inputCheckOutDay(String checkOutDay) {
        txtCheckOutDay.waitForVisibility(Constants.SHORT_TIME);
        txtCheckOutDay.sendKeys(checkOutDay);
    }

    public void pressBackPreviousPage() {
        btnBack.waitForDisplay();
        btnBack.click();
    }

    public boolean isBackButtonDisplayed() {
        btnBack.waitForDisplay();
        return btnBack.isDisplayed();
    }

    public boolean isRoomDisplayedCorrectly(String roomName){
        return lblInfoRoomName.getText().contains(roomName);
    }

    public void pressCloseButton() {
        btnClose.waitForVisibility(Constants.SHORT_TIME);
        btnClose.click();
    }

    public boolean isCloseButtonDisplayed(){
        return btnClose.isDisplayed();
    }
}
