package tests;

import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservePage;
import tests.TestBaseIOS;
public class ReservePageTest extends TestBaseIOS {
    LoginPage loginPage = new LoginPage();
    ReservePage reservePage = new ReservePage();
    @Test()
    @Description("テスト: ログイン成功")
    public void TC01() {

        Logger.info("1.  「メール」に入力");
        Logger.info("2．「パスワード」に入力");
        Logger.info("3．ロギングボタンを押下");
        User user = User.TANAKA;
        loginPage.login(user);
        reservePage.searchData();

        //WebElement txtEmail = DriverManager.getDriver().findElement(By.id("email"));
        //txtEmail.sendKeys("tanaka@gmail.com");
    }
}
