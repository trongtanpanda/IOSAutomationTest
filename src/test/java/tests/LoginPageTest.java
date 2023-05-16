package tests;

import common.Constants;
import common.TestConstants;
import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import objectData.Account;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GeneralPage;
import pages.LoginPage;
import pages.ReservePage;
import tests.TestBaseIOS;

public class LoginPageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: UIを確認する")
    public void TC01() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Assert.assertTrue(loginPage.isPlaceHolderEmailAndPwExist(TestConstants.PLACEHOLDER_EMAIL, TestConstants.PLACEHOLDER_PASSWORD));
    }

    @Test()
    @Description("テスト: UIを確認する")
    public void TC02(){
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Assert.assertTrue(loginPage.isEmailTextBoxDisplayed());
    }

    @Test()
    @Description("テスト: UIを確認する")
    public void TC03(){
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Assert.assertTrue(loginPage.isPasswordTextBoxDisplayed());
    }

    @Test()
    @Description("テスト: UIを確認する")
    public void TC04(){
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Assert.assertTrue(loginPage.isLoginButtonDisPlayed());
    }

    @Test()
    @Description("テスト: ログイン成功")
    public void TC05() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("1. アプリを開く");
        Logger.info("2. 有効な電子メールを入力する");
        Logger.info("3. 有効なパスワードを入力する");
        Logger.info("4. ロギングボタンを押下");
        Account account = new Account().setAccount(User.TANAKA);
        loginPage.login(account);
        Assert.assertEquals(TestConstants.LBL_HEADER,reservePage.getLblHeader());
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC06() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Logger.info("2. 有効な電子メールを入力する");
        Logger.info("3. 無効なパスワードを入力する");
        Logger.info("4. ロギングボタンを押下");
        Account wrongPassword = new Account().setAccount(User.SUZUKI);
        wrongPassword.setEmail(TestConstants.WRONGPASSWORD);
        loginPage.login(wrongPassword);
        Assert.assertTrue(loginPage.isLblErrorMessageContentCorrectly(TestConstants.LBLERRORMESSAGE));
        Assert.assertTrue(loginPage.isErrorMessageContentCorrectly(TestConstants.ERRORMESSAGE));
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC07() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Logger.info("2. 無効な電子メールを入力する");
        Logger.info("3. 有効なパスワードを入力する");
        Logger.info("4. ロギングボタンを押下");
        Account wrongEmail = new Account().setAccount(User.TANAKA);
        wrongEmail.setEmail(TestConstants.WRONGEMAIL);
        loginPage.login(wrongEmail);
        Assert.assertTrue(loginPage.isLblErrorMessageContentCorrectly(TestConstants.LBLERRORMESSAGE));
        Assert.assertTrue(loginPage.isErrorMessageContentCorrectly(TestConstants.ERRORMESSAGE));
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC08() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Logger.info("2. 無効な電子メールを入力する");
        Logger.info("3. 無効なパスワードを入力する");
        Logger.info("4. ロギングボタンを押下");
        loginPage.login(TestConstants.WRONGEMAILANDPASSWORD);
        Assert.assertTrue(loginPage.isLblErrorMessageContentCorrectly(TestConstants.LBLERRORMESSAGE));
        Assert.assertTrue(loginPage.isErrorMessageContentCorrectly(TestConstants.ERRORMESSAGE));
    }


    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC09() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Logger.info("2. 電子メールを未入力");
        Logger.info("3. 無効なパスワードを入力する");
        Logger.info("4. ロギングボタンを押下");
        Account emailBlank = new Account().setAccount(User.HONDA);
        emailBlank.setEmail(TestConstants.EMAILBLANK);
        loginPage.login(emailBlank);
        Assert.assertTrue(loginPage.isLblErrorMessageContentCorrectly(TestConstants.LBLERRORMESSAGE));
        Assert.assertTrue(loginPage.isErrorMessageContentCorrectly(TestConstants.ERRORMESSAGE));
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC10() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Logger.info("2. 電子メールを入力する");
        Logger.info("3. 無効なパスワードを未入力");
        Logger.info("4. ロギングボタンを押下");
        Account passwordBlank = new Account().setAccount(User.HONDA);
        passwordBlank.setPassword(TestConstants.PASSWORDBLANK);
        loginPage.login(passwordBlank);
        Assert.assertTrue(loginPage.isLblErrorMessageContentCorrectly(TestConstants.LBLERRORMESSAGE));
        Assert.assertTrue(loginPage.isErrorMessageContentCorrectly(TestConstants.ERRORMESSAGE));
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC11() {
        Logger.info("前提条件: 有効なアカウントでログインする");
        LoginPage loginPage = new LoginPage();
        GeneralPage generalPage = new GeneralPage();
        Account honda = new Account().setAccount(User.HONDA);
        loginPage.login(honda);
        Logger.info("1. メニューをクリックする");
        Logger.info("2. ログアウトを選択する");
        generalPage.logout();
        Assert.assertTrue(loginPage.isEmailTextBoxDisplayed());
        Assert.assertTrue(loginPage.isPasswordTextBoxDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisPlayed());
        Assert.assertTrue(loginPage.getLblHeader().equals(TestConstants.LBL_HEADER));
        }

    @Test()
    @Description("テスト: ログイン成功")
    public void TC12() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("1. アプリを開く");
        Logger.info("2. 有効な電子メールを入力する");
        Logger.info("3. 有効なパスワードを入力する");
        Logger.info("4. ロギングボタンを押下");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Assert.assertEquals(TestConstants.LBL_HEADER,reservePage.getLblHeader());
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC13() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Assert.assertTrue(loginPage.isEmailTextBoxDisplayed());
        Assert.assertTrue(loginPage.isPasswordTextBoxDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisPlayed());
        Assert.assertTrue(loginPage.getLblHeader().equals(TestConstants.LBL_HEADER));
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC14() {
        LoginPage loginPage = new LoginPage();
        Logger.info("1. アプリを開く");
        Logger.info("2. パスワードフィールを入力する（例:vision2022)");
        loginPage.inputPassword(TestConstants.TC14PASSWORD);
        Assert.assertTrue(loginPage.getValuePassword().equals(TestConstants.TC14PASSWORDENCRYPTION));
    }


}
