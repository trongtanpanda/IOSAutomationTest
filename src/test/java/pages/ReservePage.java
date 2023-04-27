package pages;

import common.Constants;
import common.helpers.LocatorFactory;
import elements.*;

import java.util.Date;

import static common.helpers.Utils.sleep;

public class ReservePage extends GeneralPage {


    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));
    protected RadioButton rbAll = new RadioButton(LocatorFactory.getLocator("rbAll"));
    protected RadioButton rbSingle = new RadioButton(LocatorFactory.getLocator("rbSingle"));
    protected RadioButton rbTwin = new RadioButton(LocatorFactory.getLocator("rbTwin"));
    protected Label lblTotal = new Label(LocatorFactory.getLocator("lblTotal"));
//    protected Table tblResult = new Table(LocatorFactory.getLocator("searchList"));
    public void searchData(){
        Date date = new Date("2025/10/31");
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        selectDate(date);
        btnCloseDatePicker.click();
        Date date2 = new Date("2025/11/10");
        txtCheckout.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        selectDate(date2);
        btnCloseDatePicker.click();
        rbSingle.click();
        sleep(1000000);
    }

    public String getLblHeader(){
        return lblHeader.getText();
    }

}
