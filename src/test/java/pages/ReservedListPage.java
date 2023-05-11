package pages;

import common.Constants;
import common.Room;
import common.SearchType;
import common.helpers.DateHelper;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;
import net.sf.cglib.core.Local;

import java.util.Date;

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

    public boolean isLabelHeaderrReservedListDisPlayed(String label){
        return lblHeader.getText().equals(label);
    }

    public boolean isSearchTypeButtonDisplayed(){
        return btnSearchType.isDisplayed();
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


    public boolean isRoomExist(Room room){
        lblRoom.setDynamicValue(room.getRoomName());
        lblTypeRoom.setDynamicValue(room.getTypeRoom());
        lblTypeRoom.setDynamicValue(room.getRoomType());
        lblPrice.setDynamicValue(room.getPrice());
        System.out.println(lblPrice.isDisplayed());
        return lblRoom.isDisplayed() && lblTypeRoom.isDisplayed() ;
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

    public boolean isTotalMatch(long total){
        lblTotalPrice.setDynamicValue(total);
        return lblTotalPrice.isDisplayed();
    }

    public boolean isPaymentMethodMatch(String paymentMethod){
        lblPaymentMethod.setDynamicValue(paymentMethod);
        return lblPaymentMethod.isDisplayed();
    }




}
