package pages;

import common.Constants;
import common.SearchType;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;

import java.util.Date;

public class ReservedListPage extends GeneralPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));

    protected Button btnSearchType = new Button(LocatorFactory.getLocator("btnSearchType"));
    protected TextBox txtCheckinDate = new TextBox(LocatorFactory.getLocator("txtCheckinDate"));
    protected TextBox txtCheckoutDate = new TextBox(LocatorFactory.getLocator("txtCheckoutDate"));

    protected Button btnSearch = new Button(LocatorFactory.getLocator("btnSearch"));

    protected Button btnSearchAllRecords = new Button(LocatorFactory.getLocator("btnSearchAllRecords"));
    protected Button btnSearchRange = new Button(LocatorFactory.getLocator("btnSearchRange"));

    public void changeSearchType(SearchType searchType){
        btnSearchType.waitForVisibility(Constants.SHORT_TIME);
        btnSearchType.click();
        if(searchType == SearchType.RANGE){
            btnSearchRange.waitForVisibility(Constants.SHORT_TIME);
            btnSearchRange.click();
        }
        if(searchType == SearchType.ALL_RECORDS) {
            btnSearchAllRecords.waitForVisibility(Constants.SHORT_TIME);
            btnSearchAllRecords.click();
        }
    }

    public void selectCheckinDate(Date date) {
        txtCheckinDate.click();
        selectDate(date);
    }
}
