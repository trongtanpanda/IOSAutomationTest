package tests.pattern1;

import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.LocatorFactory;
import common.helpers.Logger;
import drivers.DriverManager;
import elements.TextBox;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC02_LoginSuccess extends TestBaseHotel {

    @Test()
    @Description("テスト: ログイン成功")
    public void TC02() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();

        Logger.info("1.  「メール」に入力");
        Logger.info("2．「パスワード」に入力");
        Logger.info("3．ロギングボタンを押下");
        WebElement txtEmail = DriverManager.getDriver().findElement(By.id("email"));
        txtEmail.sendKeys("tanaka@gmail.com");
    }
}
