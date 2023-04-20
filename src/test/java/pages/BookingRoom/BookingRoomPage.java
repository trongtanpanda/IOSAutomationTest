package pages.BookingRoom;

import common.Constants;
import common.helpers.LocatorFactory;
import elements.Button;
import pages.GeneralPage;

public class BookingRoomPage extends GeneralPage {
    protected Button btnAdd = new Button(LocatorFactory.getLocator("btnAdd"));

    private void pressAddButton() {
        btnAdd.waitForVisibility(Constants.SHORT_TIME);
        btnAdd.click();
    }

    public void selectDate(String checkInDay, String checkOutDay) {
        inputCheckInDay(checkInDay);
        inputCheckOutDay(checkOutDay);
        pressAddButton();
    }
}
