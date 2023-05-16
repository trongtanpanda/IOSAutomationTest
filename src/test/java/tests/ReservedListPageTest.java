package tests;

import common.SearchType;
import common.Room;
import common.TestConstants;
import common.User;
import common.helpers.CurrencyHelper;
import common.helpers.DateHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import objectData.Account;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservePage;
import pages.ReservedListPage;

import java.util.Date;

public class ReservedListPageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: UIを確認する")
    public void TC01_VerifyThatTitleAndMenuAndSearchOptionisDisplayed() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("Precondition: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.TANAKA));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        reservedListPage.showReservedList();
        Assert.assertTrue(reservedListPage.isLabelHeaderrReservedListDisPlayed(TestConstants.LBL_HEADER_RESERVEDLIST));
        Assert.assertTrue(reservedListPage.isMenuButtonDisplayed());
        Assert.assertTrue(reservedListPage.isSearchTypeButtonDisplayed());
    }

    @Test()
    @Description()
    public void TC02_VerifyThatWhenClickReserveRoomOptionTransitionToTheRoomReservationScreen() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("Precondition: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.TANAKA));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        Logger.info("3. 部屋を予約するオプションを選択する");
        reservedListPage.showReservedList();
        reservedListPage.gotoReservePage();
        Assert.assertEquals(reservePage.getLblHeader(), TestConstants.LBL_HEADER);
        Assert.assertTrue(reservePage.isMenuButtonDisplayed());
    }

    @Test()
    @Description()
    public void TC03_VerifyThatWhenClickLogoutOptionTransitionToTheLoginScreen() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.TANAKA));
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
    public void TC04_VerifyThatTheReservedRoomInformationAndTheRoomInformationDisplayedMatch() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 本日に部屋を予約する(チェックアウト日:翌日, ２０３号室を選択する, 支払方法:　後払い)");
        loginPage.login(new Account().setAccount(User.YAMAHA));
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        reservePage.selectRoomByName(Room.R203.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        reservePage.showReservedList();
        Assert.assertTrue(reservedListPage.isRoomExist(Room.R203));
        Assert.assertTrue(reservedListPage.isPaymentMethodMatch(TestConstants.POSTPAID));
        Assert.assertTrue(reservedListPage.isTotalMatch(CurrencyHelper.currencyConvert(DateHelper.distanceBetweenTwoDays(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1)) * Room.R203.getPrice())));
        Assert.assertTrue(reservedListPage.isBookedDateMatch(TestConstants.TODAY));
        Assert.assertTrue(reservedListPage.isCheckinAndCheckoutMatch(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1)));
    }

    @Test
    @Description()
    public void TC05() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 本日に部屋を予約する（チェックアウト日:翌日, ２０１号室を選択する, 支払方法:　後払い)");
        loginPage.login(new Account().setAccount(User.HONDA));
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        reservePage.selectRoomByName(Room.R201.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        reservePage.showReservedList();
        Logger.info("3. 検索タイプで検索範囲を選択する");
        reservedListPage.changeSearchType(SearchType.RANGE);
        Logger.info("4. チェックイン日フィールドで本日を選択する");
        Logger.info("5. チェックアウト日フィールドで翌日を選択する");
        Logger.info("6. 探すアイコンをクリックする");
        reservedListPage.searchReservedList(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Assert.assertTrue(reservedListPage.isRoomExist(Room.R201));
        Assert.assertTrue(reservedListPage.isPaymentMethodMatch(TestConstants.POSTPAID));
        Assert.assertTrue(reservedListPage.isTotalMatch(CurrencyHelper.currencyConvert(DateHelper.distanceBetweenTwoDays(TestConstants.TODAY, DateHelper.plusDaysInDate(new Date(), 1)) * Room.R201.getPrice())));
        Assert.assertTrue(reservedListPage.isBookedDateMatch(TestConstants.TODAY));
        Assert.assertTrue(reservedListPage.isCheckinAndCheckoutMatch(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1)));
    }

    @Test
    @Description()
    public void TC06() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.SUZUKI));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        reservePage.showReservedList();
        Assert.assertTrue(reservedListPage.isEmptyList());
    }

    @Test
    @Description()
    public void TC07() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 任意の日付で５部屋を予約する");
        loginPage.login(new Account().setAccount(User.SUZUKI));
        reservePage.searchData(DateHelper.plusDaysInDate(new Date(), 5), DateHelper.plusDaysInDate(DateHelper.plusDaysInDate(new Date(), 5), 5));
        reservePage.selectRoomByName(Room.R303.getRoomName());
        reservePage.selectRoomByName(Room.R301.getRoomName());
        reservePage.selectRoomByName(Room.R204.getRoomName());
        reservePage.selectRoomByName(Room.R206.getRoomName());
        reservePage.selectRoomByName(Room.R302.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. チェックイン日：翌日を選択する");
        Logger.info("2. 検索ボタンをクリックする");
        reservePage.searchData(DateHelper.plusDaysInDate(new Date(), 1), DateHelper.plusDaysInDate(new Date(), 2));
        Logger.info("3. 402号室のチェックボックスをクリックする");
        reservePage.selectRoomByName(Room.R402.getRoomName());
        Logger.info("4. 予約画面へボタンをクリックする");
        reservePage.gotoPayment();
        Logger.info("5. 後払いをを選択する");
        Logger.info("6. 予約を確認するボタンをクリックする、OKボタンをクリックする");
        reservePage.postPaidPayment();
        Logger.info("7. メニューをクリックする");
        Logger.info("8. 部室を予約したリストを選択する");
        reservePage.showReservedList();
    }

    @Test
    @Description()
    public void TC08() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 任意の日付で５部屋を予約する");
        loginPage.login(new Account().setAccount(User.SUZUKI));
        reservePage.searchData(DateHelper.plusDaysInDate(new Date(), 5), DateHelper.plusDaysInDate(DateHelper.plusDaysInDate(new Date(), 5), 5));
        reservePage.selectRoomByName(Room.R203.getRoomName());
        reservePage.selectRoomByName(Room.R303.getRoomName());
        reservePage.selectRoomByName(Room.R403.getRoomName());
        reservePage.selectRoomByName(Room.R206.getRoomName());
        reservePage.selectRoomByName(Room.R302.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        reservePage.showReservedList();
        Logger.info("3. 検索範囲オプションを選択する");
        reservedListPage.changeSearchType(SearchType.RANGE);
        Assert.assertTrue(reservedListPage.isEmptyList());

    }

    @Test
    @Description()
    public void TC09() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.YAMOTO));
        Logger.info("前提条件: 2. 本日に部屋を予約する（チェックイン日：翌日、チェックアウト日：明後日、４０３号室を選択する、支払方法；後払い");
        reservePage.searchData(DateHelper.plusDaysInDate(new Date(), 1), DateHelper.plusDaysInDate(DateHelper.plusDaysInDate(new Date(), 1), 1));
        reservePage.selectRoomByName(Room.R403.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 部屋を予約したリストを選択する");
        reservePage.showReservedList();
        Logger.info("3. 検索範囲オプションを選択する");
        reservedListPage.changeSearchType(SearchType.RANGE);
        Logger.info("4. チェックイン日フィールドで明後日を選択する");
        Logger.info("5. チェックアウト日フィールドで３日後を選択する");
        Logger.info("6. 探すアイコンをクリックする");
        reservedListPage.searchReservedList(DateHelper.plusDaysInDate(new Date(), 2), DateHelper.plusDaysInDate(new Date(), 3));
        Assert.assertTrue(reservedListPage.isEmptyList());
    }

    @Test
    @Description()
    public void TC10(){
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. Yamahaアカウントでログインする");
        loginPage.login(new Account().setAccount(User.YAMAHA));
        Logger.info("前提条件: 2. 本日に部屋を予約する（チェックイン日：翌日、チェックアウト日：明後日、２０６号室を選択する、支払方法；後払い");
        reservePage.searchData(DateHelper.plusDaysInDate(new Date(), 1), DateHelper.plusDaysInDate(DateHelper.plusDaysInDate(new Date(), 1), 1));
        reservePage.selectRoomByName(Room.R206.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. ログアウトする");
        reservePage.logout();
        Logger.info("2. Yamadaアカウントでログインする");
        loginPage.login(new Account().setAccount(User.YAMAHA));
        Logger.info("3. メニューをクリックする");
        Logger.info("4. 部屋をを予約したリストを選択する");
        reservePage.showReservedList();
        Assert.assertTrue(reservedListPage.isRoomExist(Room.R206));
        Assert.assertTrue(reservedListPage.isPaymentMethodMatch(TestConstants.POSTPAID));
        Assert.assertTrue(reservedListPage.isTotalMatch(CurrencyHelper.currencyConvert(DateHelper.distanceBetweenTwoDays(DateHelper.plusDaysInDate(TestConstants.TODAY, 1), DateHelper.plusDaysInDate(TestConstants.TODAY, 2)) * Room.R206.getPrice())));
        Assert.assertTrue(reservedListPage.isBookedDateMatch(TestConstants.TODAY));
        Assert.assertTrue(reservedListPage.isCheckinAndCheckoutMatch(DateHelper.plusDaysInDate(TestConstants.TODAY, 1), DateHelper.plusDaysInDate(TestConstants.TODAY, 2)));

    }

}
