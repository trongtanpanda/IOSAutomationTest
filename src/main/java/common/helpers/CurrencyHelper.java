package common.helpers;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyHelper {
    public static String currencyConvert(Integer amount) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String currencyString = currencyFormatter.format(amount);
        currencyString = currencyString.replace("$", "").replace(".00", "");
        return currencyString;
    }
}
