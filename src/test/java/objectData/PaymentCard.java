package objectData;

import common.Card;

public class PaymentCard {
    String cardName, cardNumber, expiredDate, CVV;

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getCVV(){
        return CVV;
    }
    public PaymentCard(){

    }
    public PaymentCard(String cardName, String cardNumber, String expiredDate, String CVV) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.CVV = CVV;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public PaymentCard setPaymentCard(Card card){
        PaymentCard pmc = new PaymentCard(card.cardName, card.cardNumber, card.expiredDate, card.cvv);
        return pmc;
    }

}
