package elements;

import org.openqa.selenium.Keys;

/**
 * This class contains support functions for the textbox element.
 * テキストボックス要素のサポート機能。
 */
public class TextBox extends BaseElement {

    public TextBox(String locator) {
        super(locator);
    }

    public void sendKeys(String value) {
        getElement().sendKeys(value);
    }

    public void sendKeys(Keys value) {
        getElement().sendKeys(value);
    }

}
