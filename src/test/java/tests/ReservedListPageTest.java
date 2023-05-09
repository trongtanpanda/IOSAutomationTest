package tests;

import common.SearchType;
import common.TestConstants;
import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservePage;
import pages.ReservedListPage;

import java.time.LocalDate;
import java.util.Date;

public class ReservedListPageTest extends TestBaseIOS{

    @Test()
    @Description("テスト: UIを確認する")
    public void TC15_VerifyThatTitleAndMenuAndSearchOptionisDisplayed() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("Precondition: 有効なアカウントでログイン");
        loginPage.login(User.TANAKA);
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        reservedListPage.showReservedList();
        Assert.assertTrue(reservedListPage.isLabelHeaderrReservedListDisPlayed(TestConstants.LBL_HEADER_RESERVEDLIST));
        Assert.assertTrue(reservedListPage.isMenuButtonDisplayed());
        Assert.assertTrue(reservedListPage.isSearchTypeButtonDisplayed());
    }

    @Test()
    @Description()
    public void TC16_VerifyThatWhenClickReserveRoomOptionTransitionToTheRoomReservationScreen(){
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("Precondition: 有効なアカウントでログイン");
        loginPage.login(User.TANAKA);
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        Logger.info("3. 部屋を予約するオプションを選択する");
        reservedListPage.showReservedList();
        reservedListPage.gotoReservePage();
        Assert.assertEquals(reservePage.getLblHeader(), TestConstants.LBL_HEADER);
        //check them 3 dieu kien
    }

    @Test()
    @Description()
    public void TC17_VerifyThatWhenClickLogoutOptionTransitionToTheLoginScreen(){
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 有効なアカウントでログイン");
        loginPage.login(User.TANAKA);
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        Logger.info("3. ログアウトオプションを選択する");
        reservedListPage.showReservedList();
        reservedListPage.logout();
        Assert.assertTrue(loginPage.isEmailTextBoxDisplayed());
        Assert.assertTrue(loginPage.isPasswordTextBoxDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisPlayed());
        Assert.assertTrue(loginPage.getLblHeader().equals(TestConstants.LBL_HEADER));
    }

    @Test
    @Description()
    public void TC18_VerifyThatTheReservedRoomInformationAndTheRoomInformationDisplayedMatch(){
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン\n         " +
                "2. 本日に部屋を予約する(チェックアウト日:翌日, ２０３号室を選択する, 支払方法:　後払い)");
        loginPage.login(User.HONDA);
//        reservePage.
    }
}
