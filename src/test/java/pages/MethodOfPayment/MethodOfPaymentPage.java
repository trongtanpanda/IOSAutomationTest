package pages.MethodOfPayment;

import common.helpers.LocatorFactory;
import elements.BaseElement;
import elements.Button;
import elements.Label;
import pages.GeneralPage;

public class MethodOfPaymentPage extends GeneralPage {
    private final Label lblPaymentTitle = new Label(LocatorFactory.getLocator("lblPaymentTitle"));
    private final Button btnPay = new Button(LocatorFactory.getLocator("btnPay"));
    private final Label lblTotalPayment = new Label(LocatorFactory.getLocator("lblTotalPayment"));
    private final BaseElement imgAddNewCardImage = new BaseElement(LocatorFactory.getLocator("imgAddNewCardImage"));
    private final BaseElement imgImageVisa = new BaseElement(LocatorFactory.getLocator("imgImageVisa"));
    private final BaseElement imgImageJCB = new BaseElement(LocatorFactory.getLocator("imgImageJCB"));
    private final Label lblCardNumber = new Label(LocatorFactory.getLocator("lblCardNumber"));



    public boolean isMethodOfPaymentPageDisplayed() {
        return lblPaymentTitle.isDisplayed();
    }

    public boolean isBackButtonDisplayed() {
        return btnBack.isDisplayed();
    }

    public boolean isPayButtonEnable() {
        return "true".equals(btnPay.getAttribute("enabled"));
    }

    public String getTotalPayment() {
        return lblTotalPayment.getText().trim();
    }

    public boolean isAddNewCardImageDisplayed() {
        return imgAddNewCardImage.isDisplayed();
    }

    public boolean isImageVisaDisplayed() {
        return imgImageVisa.isDisplayed();
    }

    public boolean isImageJCBDisplayed() {
        return imgImageJCB.isDisplayed();
    }

    public String getCardNumber() {
        return lblCardNumber.getText();
    }

    public boolean isLabelAddNewPaymentCardDisplayed(){
        if(getCardNumber().equals(lang.get("PAYMENT_TITLE"))){
            return true;
        }
        return false;
    }

    public boolean isCardPaymentNumberDisplayed(String cardNumber){
        lblCardNumber.waitForDisplay();
        if(lblCardNumber.getText().contains(cardNumber)){
            return true;
        }
        return false;
    }

    public void pressAddNewCardButton() {
        imgAddNewCardImage.waitForDisplay();
        imgAddNewCardImage.click();
    }

    public void pressEditCardButton() {
        imgAddNewCardImage.waitForDisplay();
        imgAddNewCardImage.click();
    }

    public void pressPayment(){
        btnPay.waitForDisplay();
        btnPay.click();
    }
}

