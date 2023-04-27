package pages;

import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;

public class ReservedListPage extends GeneralPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));

    protected Button btnSearchType = new Button(LocatorFactory.getLocator("btnSearchType"));
    protected TextBox txtCheckinDate = new TextBox(LocatorFactory.getLocator("txtCheckinDate"));
    protected TextBox txtCheckoutDate = new TextBox(LocatorFactory.getLocator("txtCheckoutDate"));

    protected Button btnSearch = new Button(LocatorFactory.getLocator("btnSearch"));


}
