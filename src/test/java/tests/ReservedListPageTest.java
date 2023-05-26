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
    @Description("テスト: タイトル、メニュー、検索オプションがあることを確認する")
    public void TC01() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("Precondition: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.TANAKA));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        reservedListPage.showReservedList();
        Assert.assertTrue(reservedListPage.isLabelHeaderrReservedListDisPlayed(TestConstants.LBL_HEADER_RESERVEDLIST));
        Assert.assertTrue(reservedListPage.isMenuButtonDisplayed());
        Assert.assertTrue(reservedListPage.isSearchTypeButtonDisplayed());
    }

    @Test()
    @Description("テスト：部室を予約するオプションをクリックすると、「部室予約」画面へ遷移することを確認する")
    public void TC02() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("Precondition: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.TANAKA));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        Logger.info("3. 宿泊予約オプションを選択する");
        reservedListPage.showReservedList();
        reservedListPage.gotoReservePage();
        Assert.assertEquals(reservePage.getLblHeader(), TestConstants.LBL_HEADER_RESERVE);
        Assert.assertTrue(reservePage.isMenuButtonDisplayed());
    }

    @Test()
    @Description("テスト：ログアウトオプションをクリックすると、「ロギング」画面へ遷移することを確認する")
    public void TC03() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.TANAKA));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        Logger.info("3. ログアウトオプションを選択する");
        reservedListPage.showReservedList();
        reservedListPage.logout();
        Assert.assertTrue(loginPage.isEmailTextBoxDisplayed());
        Assert.assertTrue(loginPage.isPasswordTextBoxDisplayed());
        Assert.assertTrue(loginPage.isLoginButtonDisPlayed());
        Assert.assertTrue(loginPage.getLblHeader().equals(TestConstants.LBL_HEADER_LOGIN));
    }

    @Test
    @Description("テスト：予約した部室情報と一覧に表示されている部室情報が一致していることを確認する")
    public void TC04() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 本日に宿泊予約(チェックアウト日:翌日, ２０３号室を選択する, 支払方法:　後払い)");
        loginPage.login(new Account().setAccount(User.TANAKA));

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);
        reservePage.searchData(checkInDate, checkoutDate);

        reservePage.selectRoomByName(Room.R301.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        reservePage.showReservedList();
        Assert.assertTrue(reservedListPage.isRoomMatchReserved(Room.R301));
        Assert.assertTrue(reservedListPage.isPaymentMethodMatch(TestConstants.POSTPAID));
        Assert.assertTrue(reservedListPage.isTotalMatch(CurrencyHelper.currencyConvert(DateHelper.distanceBetweenTwoDays(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1)) * Room.R301.getPrice())));
        Assert.assertTrue(reservedListPage.isBookedDateMatch(TestConstants.TODAY));
        Assert.assertTrue(reservedListPage.isCheckinAndCheckoutMatch(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1)));
    }

    @Test
    @Description("テスト：選択した期間内に予約された部室は、予約画面の検索リストで検索できことを確認する")
    public void TC05() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 本日に宿泊予約（チェックアウト日:翌日, ２０１号室を選択する, 支払方法:　後払い)");
        loginPage.login(new Account().setAccount(User.SUZUKI));
        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);
        reservePage.searchData(checkInDate, checkoutDate);

        reservePage.selectRoomByName(Room.R302.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        reservePage.showReservedList();
        Logger.info("3. 検索タイプで特定期間を表示を選択する");
        reservedListPage.changeSearchType(SearchType.RANGE);
        Logger.info("4. チェックイン日フィールドで本日を選択する");
        Logger.info("5. チェックアウト日フィールドで翌日を選択する");
        Logger.info("6. 探すアイコンをクリックする");
        reservedListPage.searchReservedList(checkInDate, checkoutDate);
        Assert.assertTrue(reservedListPage.isRoomMatchReserved(Room.R302));
        Assert.assertTrue(reservedListPage.isPaymentMethodMatch(TestConstants.POSTPAID));
        Assert.assertTrue(reservedListPage.isTotalMatch(CurrencyHelper.currencyConvert(DateHelper.distanceBetweenTwoDays(checkInDate, checkoutDate) * Room.R302.getPrice())));
        Assert.assertTrue(reservedListPage.isBookedDateMatch(TestConstants.TODAY));
        Assert.assertTrue(reservedListPage.isCheckinAndCheckoutMatch(checkInDate, checkoutDate));
    }

    @Test
    @Description("テスト：部室が予約されていない場合、リストは空になることを確認する")
    public void TC06() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.YAMOTO));
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        reservePage.showReservedList();
        Assert.assertTrue(reservedListPage.isEmptyList());
    }

    @Test
    @Description("テスト：最後に予約した部室がリストの一番上に表示されることを確認する")
    public void TC07() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 任意の日付で５宿泊予約");
        loginPage.login(new Account().setAccount(User.YAMOTO));
        Date checkInDate = DateHelper.plusDaysInDate(new Date(),5);
        Date checkoutDate = DateHelper.plusDaysInDate(new Date(),10);
        reservePage.searchData(checkInDate, checkoutDate);
        reservePage.selectRoomByName(Room.R301.getRoomName());
        reservePage.selectRoomByName(Room.R302.getRoomName());
        reservePage.selectRoomByName(Room.R303.getRoomName());
        reservePage.selectRoomByName(Room.R402.getRoomName());
        reservePage.selectRoomByName(Room.R403.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. チェックイン日：翌日を選択する");
        Logger.info("2. チェックアウト日：翌日を選択する");
        Logger.info("3. 検索ボタンをクリックする");
        Date checkInDate2 = DateHelper.plusDaysInDate(new Date(),1);
        Date checkoutDate2 = DateHelper.plusDaysInDate(new Date(),2);
        reservePage.searchData(checkInDate2,checkoutDate2);
        Logger.info("4. 402号室のチェックボックスをクリックする");
        reservePage.selectRoomByName(Room.R304.getRoomName());
        Logger.info("5. 支払い画面へボタンをクリックする");
        reservePage.gotoPayment();
        Logger.info("6. 後払いをを選択する");
        Logger.info("7. 予約するボタンをクリックする、OKボタンをクリックする");
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("8. メニューをクリックする");
        Logger.info("9. 部室を予約したリストを選択する");
        reservePage.showReservedList();
        reservedListPage.isFirstItemOnList(Room.R304);
    }

    @Test
    @Description("テスト:特定期間を表示オプションで、チェックイン日とチェックアウト日を未入力の場合、空のリストが表示されることを確認する")
    public void TC08() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        Logger.info("前提条件: 2. 任意の日付で５宿泊予約");
        loginPage.login(new Account().setAccount(User.YAMAHA));
        Date checkInDate = DateHelper.plusDaysInDate(new Date(),10);
        Date checkoutDate = DateHelper.plusDaysInDate(new Date(),15);
        reservePage.searchData(checkInDate, checkoutDate);
        reservePage.selectRoomByName(Room.R303.getRoomName());
        reservePage.selectRoomByName(Room.R301.getRoomName());
        reservePage.selectRoomByName(Room.R403.getRoomName());
        reservePage.selectRoomByName(Room.R402.getRoomName());
        reservePage.selectRoomByName(Room.R302.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        reservePage.showReservedList();
        Logger.info("3. 特定期間を表示オプションを選択する");
        reservedListPage.changeSearchType(SearchType.RANGE);
        Assert.assertTrue(reservedListPage.isEmptyList());

    }

    @Test
    @Description("テスト:検索間隔に予約期間が含まれていないため、リストは表示されないことを確認する")
    public void TC09() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. 有効なアカウントでログイン");
        loginPage.login(new Account().setAccount(User.HONDA));
        Logger.info("前提条件: 2. 本日に宿泊予約（チェックイン日：翌日、チェックアウト日：明後日、４０３号室を選択する、支払方法；後払い");
        Date checkInDate = DateHelper.plusDaysInDate(new Date(),20);
        Date checkoutDate = DateHelper.plusDaysInDate(new Date(),21);
        reservePage.searchData(checkInDate, checkoutDate);
        reservePage.selectRoomByName(Room.R403.getRoomName());
        reservePage.gotoPayment();
        reservePage.postPaidPayment();
        reservePage.payment();
        reservePage.confirmPayment();
        reservePage.closeDialog();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 予約した部屋リストを選択する");
        reservePage.showReservedList();
        Logger.info("3. 特定期間を表示オプションを選択する");
        reservedListPage.changeSearchType(SearchType.RANGE);
        Logger.info("4. チェックイン日フィールドで明後日を選択する");
        Logger.info("5. チェックアウト日フィールドで３日後を選択する");
        Logger.info("6. 探すアイコンをクリックする");
        Date startDate = DateHelper.plusDaysInDate(new Date(),2);
        Date endDate = DateHelper.plusDaysInDate(new Date(),3);

        reservedListPage.searchReservedList(startDate, endDate);
        Assert.assertTrue(reservedListPage.isEmptyList());
    }

    @Test
    @Description("テスト：予約が成功した場合、ログアウトしても、ログイン後に部室を予約したリストが表示されること")
    public void TC10(){
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件: 1. Yamahaアカウントでログインする");
        loginPage.login(new Account().setAccount(User.YAMAHA));
        Logger.info("前提条件: 2. 本日に宿泊予約（チェックイン日：翌日、チェックアウト日：明後日、２０６号室を選択する、支払方法；後払い");
        Date checkInDate = DateHelper.plusDaysInDate(new Date(),12);
        Date checkoutDate = DateHelper.plusDaysInDate(new Date(),13);
        reservePage.searchData(checkInDate, checkoutDate);
        reservePage.selectRoomByName(Room.R401.getRoomName());
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
        Assert.assertTrue(reservedListPage.isRoomMatchReserved(Room.R401));
        Assert.assertTrue(reservedListPage.isPaymentMethodMatch(TestConstants.POSTPAID));
        Assert.assertTrue(reservedListPage.isTotalMatch(CurrencyHelper.currencyConvert(DateHelper.distanceBetweenTwoDays(checkInDate,checkoutDate) * Room.R401.getPrice())));
        Assert.assertTrue(reservedListPage.isBookedDateMatch(TestConstants.TODAY));
        Assert.assertTrue(reservedListPage.isCheckinAndCheckoutMatch(checkInDate,checkoutDate));

    }

}
