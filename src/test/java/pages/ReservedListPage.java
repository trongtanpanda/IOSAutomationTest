package pages;

import common.Constants;
import common.Room;
import common.SearchType;
import common.helpers.CurrencyHelper;
import common.helpers.DateHelper;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;

public class ReservedListPage extends GeneralPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));

    protected Button btnSearchType = new Button(LocatorFactory.getLocator("btnSearchType"));
    protected Button btnSearchAllRecords = new Button(LocatorFactory.getLocator("btnSearchAllRecords"));
    protected Button btnSearchRange = new Button(LocatorFactory.getLocator("btnSearchRange"));
    protected Label lblRoom = new Label("id=部屋: %s");
    protected Label lblPrice = new Label("id=価格: ¥%s");
    protected Label lblTypeRoom = new Label("id=タイプ: %s");
    protected Label lblBookedDate = new Label("id=予約日: %s");
    protected Label lblPaymentMethod = new Label("id=支払方法: %s");
    protected Label lblCheckinDate = new Label("id=チェックイン日: %s");
    protected Label lblCheckoutDate = new Label("id=チェックアウト日: %s");
    protected Label lblTotalPrice = new Label("id=合計: ¥%s");
    protected Table tblEmptyList = new Table("id=Empty list");


    public void changeSearchType(SearchType searchType){
        if(searchType == SearchType.RANGE){
            btnSearchRange.waitForVisibility(Constants.SHORT_TIME);
            btnSearchRange.click();
        }
        if(searchType == SearchType.ALL_RECORDS) {
            btnSearchAllRecords.waitForVisibility(Constants.SHORT_TIME);
            btnSearchAllRecords.click();
        }
    }

    public boolean isLabelHeaderrReservedListDisPlayed(String label){
        return lblHeader.getText().equals(label);
    }

    public boolean isSearchTypeButtonDisplayed(){
        return btnSearchRange.isDisplayed();
    }

    public void searchReservedList(Date checkin, Date checkout) {
        txtCheckIn.waitForVisibility(Constants.SHORT_TIME);
        txtCheckIn.click();
        selectDate(checkin);
        btnCloseDatePicker.click();
        txtCheckout.waitForVisibility(Constants.SHORT_TIME);
        txtCheckout.click();
        selectDate(checkout);
        btnCloseDatePicker.click();
        btnSearch.click();
    }

    public String getPriceRoom(int price){
        lblPrice.setDynamicValue(price);
        return lblPrice.getText();
    }


    public boolean isRoomMatchReserved(Room room){
        lblRoom.setDynamicValue(room.getRoomName());
        lblTypeRoom.setDynamicValue(room.getRoomType());
        lblPrice.setDynamicValue(CurrencyHelper.currencyConvert(room.getPrice()));
        lblPrice.waitForVisibility(Constants.SHORT_TIME);
        return lblRoom.isDisplayed() && lblTypeRoom.isDisplayed() && lblPrice.isDisplayed();
    }

    public boolean isBookedDateMatch(Date date){
        lblBookedDate.setDynamicValue(DateHelper.dateToString(date));
        return lblBookedDate.isDisplayed();
    }

    public boolean isCheckinAndCheckoutMatch(Date checkin, Date checkout){
        lblCheckinDate.setDynamicValue(DateHelper.dateToString(checkin));
        lblCheckoutDate.setDynamicValue(DateHelper.dateToString(checkout));
        return lblCheckoutDate.isDisplayed() && lblCheckinDate.isDisplayed();
    }

    public boolean isTotalMatch(String total){
        lblTotalPrice.setDynamicValue(total);
        return lblTotalPrice.isDisplayed();
    }

    public boolean isPaymentMethodMatch(String paymentMethod){
        lblPaymentMethod.setDynamicValue(paymentMethod);
        return lblPaymentMethod.isDisplayed();
    }

    public boolean isEmptyList(){
        tblEmptyList.waitForVisibility(Constants.SHORT_TIME);
        return tblEmptyList.isDisplayed();
    }

    public boolean isFirstItemOnList(Room room){
        List<WebElement> allResult =  tblResult.getChildElements(By.xpath("//XCUIElementTypeCell"));
        WebElement element = allResult.get(0).findElement(By.xpath("//XCUIElementTypeStaticText[@name='部屋: "+ room.getRoomName() +"']"));
        return element.isDisplayed();
    }

}
