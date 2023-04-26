package pages;

import common.Constants;
import common.helpers.LocatorFactory;
import elements.Button;

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
}
