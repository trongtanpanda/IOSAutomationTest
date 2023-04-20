package objectData;

public class PaymentCard {
    String cardName,cardNumber,month,year;

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public PaymentCard(String cardName, String cardNumber, String month, String year) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
    }


}
