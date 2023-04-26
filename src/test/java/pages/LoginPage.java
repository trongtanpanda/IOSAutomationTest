package pages;

import common.Constants;
import common.User;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.Label;
import elements.TextBox;

public class LoginPage {

    protected Label lblHeader = new Label(LocatorFactory.getLocator("lblHeader"));
    protected TextBox txtEmail = new TextBox(LocatorFactory.getLocator("txtEmail"));
    protected TextBox txtPassword = new TextBox(LocatorFactory.getLocator("txtPassword"));
    protected Button btnLogin = new Button(LocatorFactory.getLocator("btnLogin"));
    protected Label ErrorMessage = new Label(LocatorFactory.getLocator("ErrorMessage"));
    protected Label lblErrorMessage = new Label(LocatorFactory.getLocator("lblErrorMessenger"));
    public void inputEmail(String email) {
        txtEmail.waitForVisibility(Constants.SHORT_TIME);
        txtEmail.sendKeys(email);
    }

    public void inputPassword(String pwd) {
        txtPassword.waitForVisibility(Constants.SHORT_TIME);
        txtPassword.sendKeys(pwd);
    }

    public void pressLoginButton() {
        btnLogin.waitForVisibility(Constants.SHORT_TIME);
        btnLogin.click();
    }

    public void login(User user) {
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        pressLoginButton();
    }

    public boolean isEmailTextBoxDisplayed() {
        txtEmail.waitForVisibility();
        return txtEmail.isDisplayed();

    }

    public String getLblHeader(){
        lblHeader.waitForVisibility(Constants.SHORT_TIME);
        return lblHeader.getText();
    }

    public String getValuePassword() {
        txtPassword.waitForVisibility(Constants.SHORT_TIME);
        return txtPassword.getText();
    }

    public boolean isEmailAndPasswordTextBoxNull() {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.equals("メール") || email.isEmpty()) {
            if (password.equals("パスワード") || password.isEmpty()) ;
            {
                return true;
            }
        }
        return false;
    }

    public boolean isPasswordTextBoxDisplayed() {
        txtPassword.waitForVisibility();
        return txtPassword.isDisplayed();

    }

    public boolean isLoginButtonDisPlayed() {
        btnLogin.waitForVisibility();
        return btnLogin.isDisplayed();
    }

    public boolean isPlaceHolderEmailAndPwExist(String email, String pw) {
        return (txtEmail.getText().equals(email) && txtPassword.getText().equals(pw));
    }

    public boolean isErrorMessageContentCorrectly(String message){
        return (ErrorMessage.getText().equals(message));
    }

    public boolean isLblErrorMessageContentCorrectly(String labelMessage){
        return (lblErrorMessage.getText().equals(labelMessage));
    }

}

