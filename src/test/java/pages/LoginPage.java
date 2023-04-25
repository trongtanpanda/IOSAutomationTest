package pages;

import common.Constants;
import common.User;
import common.helpers.LocatorFactory;
import elements.Button;
import elements.TextBox;

public class LoginPage {
    protected TextBox txtEmail = new TextBox(LocatorFactory.getLocator("txtEmail"));
    protected TextBox txtPassword = new TextBox(LocatorFactory.getLocator("txtPassword"));
    protected Button btnLogin = new Button(LocatorFactory.getLocator("btnLogin"));


    private void inputEmail(String email) {
        txtEmail.waitForVisibility(Constants.SHORT_TIME);
        txtEmail.sendKeys(email);
    }

    private void inputPassword(String pwd) {
        txtPassword.waitForVisibility(Constants.SHORT_TIME);
        txtPassword.sendKeys(pwd);
    }

    private void pressLoginButton() {
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

    public boolean isEmailAndPasswordTextBoxNull() {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.equals("メールアドレス") || email.isEmpty()) {
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

}

