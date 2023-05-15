package pages;

import common.Constants;
import common.TestConstants;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;
import objectData.PaymentCard;
import org.openqa.selenium.Keys;

import static common.helpers.Utils.sleep;

public class PaymentPage extends GeneralPage {

    protected Label optPrepay = new Label(LocatorFactory.getLocator("optPrepay"));
    protected Label optPostPaid = new Label(LocatorFactory.getLocator("optPostPaid"));
    protected Button btnPayment = new Button(LocatorFactory.getLocator("btnPayment"));
    protected Button btnOk = new Button(LocatorFactory.getLocator("btnOk"));
    protected Button btnCancel = new Button(LocatorFactory.getLocator("btnCancel"));
    protected Button btnAlertOk = new Button(LocatorFactory.getLocator("btnAlertOk"));
    protected Button btnBack = new Button(LocatorFactory.getLocator("btnBack"));
    protected TextBox txtNameCard = new TextBox(LocatorFactory.getLocator("txtNameCard"));
    protected TextBox txtCardNumber = new TextBox(LocatorFactory.getLocator("txtCardNumber"));
    protected TextBox txtExpiredDate = new TextBox(LocatorFactory.getLocator("txtExpiredDate"));
    protected TextBox txtCVV = new TextBox(LocatorFactory.getLocator("txtCVV"));
    protected Label errMsgACName = new Label(LocatorFactory.getLocator("errMsgACName"));
    protected Label errMsgACNumber = new Label(LocatorFactory.getLocator("errMsgACNumber"));
    protected Label errMsgExpiredDate = new Label(LocatorFactory.getLocator("errMsgExpiredDate"));
    protected Label errMsgSecureCode = new Label(LocatorFactory.getLocator("errMsgSecureCode"));

    public void backToReservePage() {
        btnBack.waitForVisibility(Constants.SHORT_TIME);
        btnBack.click();
    }

    public void postPaidPayment() {
        optPostPaid.waitForVisibility(Constants.SHORT_TIME);
        optPostPaid.click();
    }

    public void prePayemnt(PaymentCard paymentCard) {
        optPrepay.waitForVisibility(Constants.SHORT_TIME);
        optPrepay.click();

        txtNameCard.waitForVisibility(Constants.SHORT_TIME);
        txtNameCard.sendKeys(paymentCard.getCardName());
        txtCardNumber.waitForVisibility(Constants.SHORT_TIME);
        txtCardNumber.sendKeys(paymentCard.getCardNumber());
        txtExpiredDate.waitForVisibility(Constants.LONG_TIME);
        txtExpiredDate.sendIOSKeys(Keys.RETURN);
        txtExpiredDate.click();
        txtExpiredDate.sendKeys(String.valueOf(1));
        txtCVV.waitForVisibility(Constants.SHORT_TIME);
        txtCVV.sendKeys(String.valueOf(paymentCard.getCVV()));
        txtCVV.sendKeys(Keys.RETURN);
    }

    public void payment() {
        btnPayment.click();
    }

    public void confirmPayment() {
        btnOk.waitForVisibility(Constants.SHORT_TIME);
        btnOk.click();
    }

    public void cancelPayment() {
        btnCancel.waitForVisibility(Constants.SHORT_TIME);
        btnCancel.click();
    }

    public void closeDialog() {
        btnAlertOk.waitForVisibility(Constants.SHORT_TIME);
        btnAlertOk.click();
    }

    public boolean isErrMsgACNameDisplayed() {
        errMsgACName.waitForVisibility(Constants.SHORT_TIME);
        return errMsgACName.isDisplayed();
    }

    public boolean isErrMsgACNumberDisplayed() {
        errMsgACNumber.waitForVisibility(Constants.SHORT_TIME);
        return errMsgACNumber.isDisplayed();
    }

    public boolean isErrMsgExpiredDateDisplayed(){
        errMsgExpiredDate.waitForVisibility(Constants.SHORT_TIME);
        return errMsgExpiredDate.isDisplayed();
    }

    public boolean isErrMsgSecureCodeDisplayed(){
        errMsgSecureCode.waitForVisibility(Constants.SHORT_TIME);
        return errMsgSecureCode.isDisplayed();
    }
}
