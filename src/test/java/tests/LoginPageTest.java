package tests;

import common.Constants;
import common.TestConstants;
import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
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
        User user = User.TANAKA;
        loginPage.login(user);
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
        loginPage.login(User.WRONGPASSWORD);
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
        loginPage.login(User.WRONGEMAIL);
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
        loginPage.login(User.WRONGEMAILANDPASSWORD);
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
        loginPage.login(User.EMAILBLANK);
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
        loginPage.login(User.PASSWORDBLANK);
        Assert.assertTrue(loginPage.isLblErrorMessageContentCorrectly(TestConstants.LBLERRORMESSAGE));
        Assert.assertTrue(loginPage.isErrorMessageContentCorrectly(TestConstants.ERRORMESSAGE));
    }

    @Test()
    @Description("テスト：　ログインを確認する")
    public void TC11() {
        Logger.info("前提条件: 有効なアカウントでログインする");
        LoginPage loginPage = new LoginPage();
        GeneralPage generalPage = new GeneralPage();
        loginPage.login(User.HONDA);
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
        User user = User.YAMAHA;
        loginPage.login(user);
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
