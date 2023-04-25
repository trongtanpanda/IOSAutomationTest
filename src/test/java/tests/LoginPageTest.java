package tests;

import common.TestConstants;
import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservePage;
import tests.TestBaseIOS;

public class LoginPageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: ログイン成功")
    public void TC02() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("1.  「メール」に入力");
        Logger.info("2．「パスワード」に入力");
        Logger.info("3．ロギングボタンを押下");
        User user = User.TANAKA;
        loginPage.login(user);
        Assert.assertEquals(TestConstants.LBL_HEADER,reservePage.getLblHeader());
        //WebElement txtEmail = DriverManager.getDriver().findElement(By.id("email"));
        //txtEmail.sendKeys("tanaka@gmail.com");
    }
}
