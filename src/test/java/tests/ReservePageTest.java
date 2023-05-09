package tests;

import common.RoomType;
import common.User;
import common.helpers.DateHelper;
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

        Date checkOutDate = DateHelper.plusDaysInDate(new Date(),1);

        Logger.info("1.「チェックアウト日」を押下する");
        Logger.info("2. 明日に選択する");
        Logger.info("3.「閉じる」ボタンを押下する");
        reservePage.pickCheckoutDate(checkOutDate);

        Logger.verify("「yyyy/mm/dd」形式で表示されること");
        Assert.assertTrue(reservePage.isCheckoutDisplayCorrectly(checkOutDate));
    }
    @Test()
    @Description("テスト: 日付ピッカーが表示する")
    public void TC05() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();

        Logger.info("1.「チェックイン日」を 今日に選択する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を押下する");
        Logger.verify("「チェックイン日」の前の選択を許可しないこと");
        Assert.assertTrue(reservePage.isCheckoutShouldNotEqualOrLessThanCheckInDate(checkInDate));
    }
    @Test()
    @Description("テスト: 部屋を探す")
    public void TC06() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,2);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("全てのデータが表示されこと");
        Assert.assertTrue(reservePage.isAllDataDisplayed());
    }

    @Test()
    @Description("テスト: シングル部屋タイプから部屋を探す")
    public void TC07() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,2);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.info("4.「シングル」オプションを押下する");
        reservePage.selectSingle();

        Logger.verify("部屋タイプが「シングル」の部屋のみが表示されこと");
        Assert.assertTrue(reservePage.isListRoomByTypeDisplayCorrectly(RoomType.シングル.name()));
    }

    @Test()
    @Description("テスト: ツイン部屋タイプから部屋を探す")
    public void TC08() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,2);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.info("4.「ツイン」オプションを押下する");
        reservePage.selectTwin();

        Logger.verify("部屋タイプが「ツイン」の部屋のみが表示されこと");
        Assert.assertTrue(reservePage.isListRoomByTypeDisplayCorrectly(RoomType.ツイン.name()));
    }
}