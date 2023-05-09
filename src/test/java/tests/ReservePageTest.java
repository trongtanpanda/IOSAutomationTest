package tests;

import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservePage;

import java.util.Date;

import static common.helpers.Utils.sleep;

public class ReservePageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: [チェックイン日] 日付ピッカーが表示する")
    public void TC03() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();

        Logger.info("1.「チェックイン日」を押下する");
        Logger.info("2. 今日に選択する");
        Logger.info("3.「閉じる」ボタンを押下する");
        reservePage.pickCheckInDate(checkInDate);
        Logger.verify("「yyyy/mm/dd」形式で表示されること");
        Assert.assertTrue(reservePage.isCheckInDisplayCorrectly(checkInDate));
    }
    @Test()
    @Description("テスト: [チェックアウト日] 日付ピッカーが表示する")
    public void TC04() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkOutDate = new Date().getTime() + 24*60*60;

        Logger.info("1.「チェックアウト日」を押下する");
        Logger.info("2. 明日に選択する");
        Logger.info("3.「閉じる」ボタンを押下する");
        reservePage.pickCheckInDate(checkOutDate);
        Logger.verify("「yyyy/mm/dd」形式で表示されること");
        Assert.assertTrue(reservePage.isCheckoutDisplayCorrectly(checkOutDate));
    }
}
