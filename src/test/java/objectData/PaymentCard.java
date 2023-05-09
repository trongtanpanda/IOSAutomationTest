package objectData;

public class PaymentCard {
    String cardName, cardNumber;
    int expiredDate, CVV;

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpiredDate() {
        return expiredDate;
    }

    public int getCVV(){
        return CVV;
    }
    public PaymentCard(String cardName, String cardNumber, int expiredDate, int CVV) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.CVV = CVV;
    }


}
