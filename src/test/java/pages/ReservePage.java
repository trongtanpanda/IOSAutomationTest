package pages;

import common.Constants;
import common.helpers.LocatorFactory;
import drivers.DriverManager;
import elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.helpers.DateHelper;
import java.util.Date;
import java.util.List;

import static common.helpers.Utils.sleep;

public class ReservePage extends GeneralPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));
    protected RadioButton rbAll = new RadioButton(LocatorFactory.getLocator("rbAll"));
    protected RadioButton rbSingle = new RadioButton(LocatorFactory.getLocator("rbSingle"));
    protected RadioButton rbTwin = new RadioButton(LocatorFactory.getLocator("rbTwin"));
    protected Label lblTotal = new Label(LocatorFactory.getLocator("lblTotal"));
    protected Table tblResult = new Table(LocatorFactory.getLocator("tblResult"));
    protected Button btnGotoPayment = new Button(LocatorFactory.getLocator("btnGotoPayment"));
    public void searchData(){
        Date checkin = new Date("2023/04/28");
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        selectDate(checkin);
        btnCloseDatePicker.click();
        Date checkout = new Date("2023/04/30");
        txtCheckout.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        selectDate(checkout);
        btnCloseDatePicker.click();
        rbAll.click();
        btnSearch.click();
        tblResult.waitForVisibility(Constants.SHORT_TIME);
        List<WebElement> list =  tblResult.getChildElements(By.xpath("//XCUIElementTypeButton"));

        System.out.println(DateHelper.distanceBetweenTwoDays(checkout, checkin));
        System.out.println(list.size());

    }
    public void selectRoom(String name){
        WebElement checkBox = DriverManager.getDriver().findElement(By.id(name));
        checkBox.click();

    }

    public void gotoPayment(){
        btnGotoPayment.click();

    }
    public String getLblHeader(){
        return lblHeader.getText();
    }

}
