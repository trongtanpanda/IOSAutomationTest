package pages;

import common.Constants;
import common.User;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;

import static common.helpers.Utils.sleep;

public class ReservePage {
    protected TextBox txtCheckIn = new TextBox(LocatorFactory.getLocator("txtCheckIn"));
    protected TextBox txtCheckout = new TextBox(LocatorFactory.getLocator("txtCheckOut"));
    protected Button btnSearch = new Button(LocatorFactory.getLocator("btnSearch"));

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));


    private void selectCheckInDate() {
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        sleep(100000);
    }

    public void searchData(){
        selectCheckInDate();
    }

    public String getLblHeader(){
        return lblHeader.getText();
    }

}

