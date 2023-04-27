package pages;

import common.Constants;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;
import java.util.Date;

public class ReservePage extends GeneralPage {
    protected TextBox txtCheckIn = new TextBox(LocatorFactory.getLocator("txtCheckIn"));
    protected TextBox txtCheckout = new TextBox(LocatorFactory.getLocator("txtCheckOut"));
    protected Button btnSearch = new Button(LocatorFactory.getLocator("btnSearch"));
    protected Button btnCloseCheckInPicker = new Button(LocatorFactory.getLocator("btnCloseCheckInPicker"));
    protected Button btnCloseCheckOutPicker = new Button(LocatorFactory.getLocator("btnCloseCheckOutPicker"));
    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));



    public void searchData(){
        Date date = new Date("2025/10/31");
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        btnShowYearPicker.waitForVisibility(Constants.SHORT_TIME);
        btnShowYearPicker.click();
        selectDate(date);
    }

    public String getLblHeader(){
        return lblHeader.getText();
    }

}
