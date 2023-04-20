package pages.BookedRoom;

import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import pages.GeneralPage;

public class BookedRoomPage extends GeneralPage {
    protected Button btnSearch = new Button(LocatorFactory.getLocator("btnSearch"));
    protected Label lblRoomNameDynamic = new Label(LocatorFactory.getLocator("lblRoomNameDynamic"));
    protected Button btnAll = new Button(LocatorFactory.getLocator("btnAll"));
    protected Button btnBackHome = new Button(LocatorFactory.getLocator("btnBackHome"));
    protected Label lblFirstRoom = new Label(LocatorFactory.getLocator("lblFirstRoom"));

    private void pressSearchButton(){
        btnSearch.waitForVisibility();
        btnSearch.click();
    }

    public void searchBookedRoom(String checkInDay, String checkOutDay){
        inputCheckInDay(checkInDay);
        inputCheckOutDay(checkOutDay);
        pressSearchButton();
    }

    public boolean isRoomDisplayed(String roomName){
        lblRoomNameDynamic.setDynamicValue(roomName);
        return lblRoomNameDynamic.isDisplayed();
    }

    public void pressAllButton(){
        btnAll.waitForVisibility();
        btnAll.click();
    }

    public String getFirstRoomName(){
        return lblFirstRoom.getText().trim();
    }

    public void pressFirstRoom(){
        lblFirstRoom.waitForVisibility();
        lblFirstRoom.click();
    }

    public void pressBackButton(){
        btnBackHome.waitForVisibility();
        btnBackHome.click();
    }
}
