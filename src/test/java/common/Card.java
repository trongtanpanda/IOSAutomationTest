package common;

public enum Card {
    VANA("NGUYEN VAN A","1111222233334444","04/26","952"),
    VANB("LY VAN B","2222444466668888","02/29","783"),
    VANC("TRAN VAN C","1111333355557777","10/26","952"),
    VAND("LE VAN D","4444333322221111", "12/24","573");

    public final String cardName, cardNumber, expiredDate, cvv;

    Card(String cardName, String cardNumber, String expiredDate, String cvv){
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.cvv = cvv;
    }

}
