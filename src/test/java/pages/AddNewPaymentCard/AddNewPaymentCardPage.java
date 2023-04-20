package pages.AddNewPaymentCard;

import objectData.PaymentCard;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.SelectBox;
import elements.TextBox;
import pages.GeneralPage;

public class AddNewPaymentCardPage extends GeneralPage {
    protected TextBox txtCardNumber = new TextBox(LocatorFactory.getLocator("txtCardNumber"));
    protected TextBox txtCardName = new TextBox(LocatorFactory.getLocator("txtCardName"));
    protected SelectBox spnMonth = new SelectBox(LocatorFactory.getLocator("spnMonth"));
    protected SelectBox spnYear = new SelectBox(LocatorFactory.getLocator("spnYear"));
    protected Label lblErrorMessenger = new Label(LocatorFactory.getLocator("lblErrorMessenger"));

    public boolean isAddNewCardTitlePageDisplayed() {
        lblTitle.setDynamicValue(lang.get("TITLE_ADD_NEW_CARD"));
        return lblTitle.isDisplayed();
    }

    public boolean isTextBoxCardNumberDisplayed() {
        txtCardNumber.waitForDisplay();
        return txtCardNumber.isDisplayed();
    }

    public String getValueCardName() {
        txtCardName.waitForDisplay();
        return txtCardName.getText().trim();
    }

    public boolean isTextBoxCardNameDisplayed() {
        txtCardName.waitForDisplay();
        return txtCardName.isDisplayed();
    }



    public boolean isExpirationDateDisplayed(){
        lblTitle.setDynamicValue(lang.get("LABEL_EXPIRATION_DATE"));
        return lblTitle.isDisplayed();
    }


    public boolean isSelectBoxMonthDisplayed() {
        spnMonth.waitForDisplay();
        return spnMonth.isDisplayed();
    }

    public boolean isSelectBoxYearDisplayed() {
        spnYear.waitForDisplay();
        return spnYear.isDisplayed();
    }

    public String getErrorMessenger(){
        lblErrorMessenger.waitForDisplay();
        return lblErrorMessenger.getText().trim();
    }

    public void addNewCard(PaymentCard paymentCard) {
        try {
            //TextBox Card Name process
            txtCardName.waitForDisplay();
            txtCardName.sendKeys(paymentCard.getCardName());

            //TextBox Card Number process
            txtCardNumber.waitForDisplay();
            txtCardNumber.sendKeys(paymentCard.getCardNumber());

            //Month process
            spnMonth.waitForDisplay();
            String month = paymentCard.getMonth();
            if (!month.isEmpty()) {
                spnMonth.selectByText(month);
            }

            //Year process
            spnYear.waitForDisplay();
            String year = paymentCard.getMonth();
            if (!year.isEmpty()) {
                spnYear.selectByText(year);
            }

        } catch (Exception E) {
            System.out.print("Can not add new Card");
        }
    }
}
