package tests;

import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservePage;

import static common.helpers.Utils.sleep;

public class ReservePageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: ログイン成功")
    public void TC01() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("1.  「メール」に入力");
        Logger.info("2．「パスワード」に入力");
        Logger.info("3．ロギングボタンを押下");
        User user = User.TANAKA;
        loginPage.login(user);
//        reservePage.searchData();
        reservePage.selectRoomByName("401");
        reservePage.gotoPayment();
        reservePage.payment();
//        reservePage.agreePayment();
        reservePage.closeDialog();
        sleep(10000);
        //WebElement txtEmail = DriverManager.getDriver().findElement(By.id("email"));
        //txtEmail.sendKeys("tanaka@gmail.com");
    }
}
