package elements;

public class CheckBox extends BaseElement{
    public CheckBox(String locator) {
        super(locator);
    }

    public boolean isSelected(){
        return getElement().isSelected();
    }

    public void setCheck(boolean check){
        if (check){
            if (!getElement().isSelected()){
                getElement().click();
            }
        }else {
            if (getElement().isSelected()){
                getElement().click();
            }
        }
    }
}
