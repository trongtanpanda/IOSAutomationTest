package pages.EditPaymentCard;

import common.helpers.LocatorFactory;
import elements.Button;
import elements.SelectBox;
import elements.TextBox;
import io.appium.java_client.android.nativekey.AndroidKey;
import objectData.PaymentCard;
import pages.GeneralPage;

public class EditPaymentCardPage extends GeneralPage {
    protected TextBox txtCardNumber = new TextBox(LocatorFactory.getLocator("txtCardNumber"));
    protected TextBox txtCardName = new TextBox(LocatorFactory.getLocator("txtCardName"));
    protected SelectBox spnMonth = new SelectBox(LocatorFactory.getLocator("spnMonth"));
    protected SelectBox spnYear = new SelectBox(LocatorFactory.getLocator("spnYear"));
    protected Button btnDelete = new Button(LocatorFactory.getLocator("btnDelete"));

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

    public String getValueCardNumber() {
        txtCardNumber.waitForDisplay();
        return txtCardNumber.getText().replace("-", "").trim();
    }

    public boolean isTextBoxCardNameDisplayed() {
        txtCardName.waitForDisplay();
        return txtCardName.isDisplayed();
    }


    public boolean isExpirationDateDisplayed() {
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

    public boolean isDeletePaymentCardButtonDisplayed() {
        btnDelete.waitForDisplay();
        return btnDelete.isDisplayed();
    }

    public void pressDeletePaymentCard() {
        btnDelete.waitForDisplay();
        btnDelete.click();
    }

    public void editPaymentCard(PaymentCard paymentCard) {
        try {
            //TextBox Card Name process
            if (!txtCardName.getText().equals(paymentCard.getCardName())) {
                txtCardName.waitForDisplay();
                txtCardName.sendKeys(paymentCard.getCardName());
            }

            //TextBox Card Number process
            if (!txtCardNumber.getText().replace("-", "").equals(paymentCard.getCardNumber())) {
                txtCardNumber.waitForDisplay();
                int lengthText = txtCardNumber.getText().replace("-", "").length();
                txtCardNumber.click();
                for (int i = 0; i < lengthText; i++) {
                    txtCardNumber.sendAndroidKeys(AndroidKey.DEL);
                }
                for (int i = 0; i < paymentCard.getCardNumber().length(); i++) {
                    char c = paymentCard.getCardNumber().charAt(i);
                    String number = Character.toString(c);
                    switch (number) {
                        case "0":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_0);
                            break;
                        case "1":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_1);
                            break;
                        case "2":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_2);
                            break;
                        case "3":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_3);
                            break;
                        case "4":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_4);
                            break;
                        case "5":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_5);
                            break;
                        case "6":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_6);
                            break;
                        case "7":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_7);
                            break;
                        case "8":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_8);
                            break;
                        case "9":
                            txtCardNumber.sendAndroidKeys(AndroidKey.DIGIT_9);
                            break;
                        default:
                            break;
                    }
                }
            }

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
            System.out.print("Can not Edit Payment Card");
        }
    }
}
