package pages.PaymentSuccess;

import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import pages.GeneralPage;

public class PaymentSuccessPage extends GeneralPage {
    private final Label lblCardNumber = new Label(LocatorFactory.getLocator("lblCardNumber"));
    private final Label lblCardHolderName = new Label(LocatorFactory.getLocator("lblCardHolderName"));
    private final Button btnBackHome = new Button(LocatorFactory.getLocator("btnBackHome"));
    private final Label lblNameRoomItem = new Label(LocatorFactory.getLocator("lblNameRoomItem"));

    public Boolean isNamePaymentCardDisplayedCorrectly(String nameCard) {
        lblCardHolderName.waitForDisplay();
        return lblCardHolderName.getText().contains(nameCard);
    }

    public String getNumberPaymentCard() {
        lblCardNumber.waitForDisplay();
        return lblCardNumber.getText().trim();
    }

    public String getNameRoomInList() {
        return lblNameRoomItem.getText().trim();
    }

    public void pressBackToHome() {
        btnBackHome.waitForDisplay();
        btnBackHome.click();
    }
}
