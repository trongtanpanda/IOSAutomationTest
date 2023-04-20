package pages.RoomReservation;

import common.Constants;
import common.RoomType;
import common.helpers.Direction;
import common.helpers.LocatorFactory;
import drivers.DriverUtils;
import elements.BaseElement;
import elements.Button;
import elements.CheckBox;
import elements.Label;
import pages.GeneralPage;

public class RoomReservationPage extends GeneralPage {
    private final CheckBox ckbFirstRoom = new CheckBox(LocatorFactory.getLocator("ckbFirstRoom"));
    private final CheckBox chkCheckBoxOfRoom = new CheckBox(LocatorFactory.getLocator("chkCheckBoxOfRoom"));
    private final BaseElement rdoAll = new BaseElement(LocatorFactory.getLocator("rdoAll"));
    private final BaseElement rdoSingle = new BaseElement(LocatorFactory.getLocator("rdoSingle"));
    private final BaseElement rdoTwin = new BaseElement(LocatorFactory.getLocator("rdoTwin"));
    private final BaseElement rdoDouble = new BaseElement(LocatorFactory.getLocator("rdoDouble"));
    private final Label lblFirstRoomName = new Label(LocatorFactory.getLocator("lblFirstRoomName"));
    private final Label lblMoneyTotal = new Label(LocatorFactory.getLocator("lblMoneyTotal"));
    private final Button btnBookingRoom = new Button(LocatorFactory.getLocator("btnBookingRoom"));
    private final Label lblMenuItem = new Label(LocatorFactory.getLocator("lblMenuItem"));
    private final Button btnMenu = new Button(LocatorFactory.getLocator("btnMenu"));

    public void pressCheckBoxOfFirstRoom() {
        ckbFirstRoom.waitForVisibility(Constants.SHORT_TIME);
        ckbFirstRoom.setCheck(true);
    }

    public String getFirstRoomName() {
        lblFirstRoomName.waitForVisibility(Constants.SHORT_TIME);
        return lblFirstRoomName.getText().trim();
    }

    public void inputCheckInDayForSearch(String day) {
        txtCheckInDay.waitForVisibility(Constants.SHORT_TIME);
        txtCheckInDay.sendKeys(day);
    }

    public void selectRoomType(RoomType roomType) {
        switch (roomType) {
            case ALL -> rdoAll.click();
            case SINGLE -> rdoSingle.click();
            case TWIN -> rdoTwin.click();
            case DOUBLE -> rdoDouble.click();
        }
    }

    public String getCheckInDayOfSearchBox() {
        txtCheckInDay.waitForVisibility();
        return txtCheckInDay.getText().trim();
    }

    public boolean isFirstCheckBoxChecked() {
        return "true".equals(ckbFirstRoom.getAttribute("checked"));
    }

    public String getMoneyTotal() {
        lblMoneyTotal.waitForVisibility();
        return lblMoneyTotal.getText().trim();
    }

    public boolean isBookingRoomButtonVisible() {
        btnBookingRoom.waitForVisibility();
        return "true".equals(btnBookingRoom.getAttribute("enabled"));
    }

    public void pressBookingRoomButton() {
        btnBookingRoom.waitForVisibility();
        btnBookingRoom.click();
    }

    public void pressMenuButton() {
        btnMenu.waitForVisibility();
        btnMenu.click();
    }

    public void selectMenu(String menu) {
        lblMenuItem.setDynamicValue(menu);
        lblMenuItem.click();
    }

    public void openBookedRoomList(String menu) {
        pressMenuButton();
        selectMenu(menu);
    }

    public void findAndPressCheckBoxOfRoom(String roomName){
        chkCheckBoxOfRoom.setDynamicValue(roomName);
        if (!chkCheckBoxOfRoom.isDisplayed()){
            DriverUtils.swipeScreen(Direction.UP);
        }
        chkCheckBoxOfRoom.setCheck(true);
    }
}
