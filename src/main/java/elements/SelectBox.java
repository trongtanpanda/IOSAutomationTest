package elements;

import org.openqa.selenium.support.ui.Select;

/**
 * This class contains support functions for the selectbox element.
 * 選択ボックス要素のサポート機能。
 */
public class SelectBox extends BaseElement {

    public SelectBox(String locator) {
        super(locator);
    }

    public void selectByText(String option){
        new Select(getElement()).selectByVisibleText(option);
    }
}
