package tests;

import common.Room;
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

    @Test()
    @Description("テスト: 部屋の詳細")
    public void TC09() {
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

        Room room = Room.R201;
        Logger.info("4. 任意の部屋を押下する");
        reservePage.viewRoomDetailByRoomName(room.getRoomName());

        Logger.verify("部屋タイプが「ツイン」の部屋のみが表示されこと");
        Assert.assertTrue(reservePage.isRoomDetailDisplayCorrectly(room));
    }

    @Test()
    @Description("テスト: 部室を選択しないで予約画面へをクリックする")
    public void TC10() {
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

        Logger.info("4. 「予約画面へ」ボタンを押下する");
        reservePage.gotoPayment();

        Logger.verify("エラーポップアップが表示されこと");
        Assert.assertTrue(reservePage.isErrorPopupDisplayCorrectly());
    }

    @Test()
    @Description("テスト: 部室を選択して予約画面へをクリックする")
    public void TC11() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room = Room.R201;
        Logger.info("4. 部室名「201」をチェックする");
        reservePage.selectRoomByName(room.getRoomName());
        Logger.verify("合計が0円から15000円に変更されこと");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(room.getPrice()));

        Logger.info("5.「予約画面へ」ボタンを押下する");
        reservePage.gotoPayment();

        Logger.verify("支払い」画面 が表示されこと");
        Assert.assertTrue(reservePage.isPaymentPage());
    }

    @Test()
    @Description("テスト: 部室タイプで部室を漉す")
    public void TC12() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room1 = Room.R201;
        Room room2 = Room.R301;
        Logger.info("4. 部室名「201」と「301」をチェックする");
        reservePage.selectRoomByName(room1.getRoomName());
        reservePage.selectRoomByName(room2.getRoomName());

        Logger.verify("合計が0円から27000円に変更されこと,「201」と「301」をチェックすること");
        int total = room1.getPrice() + room2.getPrice();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));

        Logger.info("5.合計が27000円あること,　部室タイプが「シングル」の部室のみが表示されこと、「201」がチェックすること");
        reservePage.selectSingle();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));

        Logger.info("6.合計が27000円あること,　部室タイプが「ツイン」の部室のみが表示されこと、「301」がチェックすること");
        reservePage.selectTwin();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));

        Logger.info("7.合計が27000円あること,　全てのデータが表示されこと、「201」と「301」がチェックすること");
        reservePage.selectAll();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));
    }

    @Test()
    @Description("テスト: 新しい範囲で検索するときにデータを再起動する")
    public void TC13() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room = Room.R201;
        Logger.info("4. 部室名「201」と「301」をチェックする");
        reservePage.selectRoomByName(room.getRoomName());

        Logger.verify("合計が0円から27000円に変更されこと,「201」と「301」をチェックすること");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(room.getPrice()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room.getRoomName()));

        Date newCheckInDate = DateHelper.plusDaysInDate(checkInDate,2);
        Date newCheckoutDate = DateHelper.plusDaysInDate(checkInDate,3);
        Logger.info("5.「チェックイン日」を「2023/05/16」に設定する");
        reservePage.pickCheckInDate(newCheckInDate);
        Logger.info("6.「チェックアウト日」を「2023/05/17」に設定する");
        reservePage.pickCheckoutDate(newCheckoutDate);
        Logger.info("7. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("合計が27000円から0円に変更されこと");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(0));
        Assert.assertFalse(reservePage.isCheckedByRoomName(room.getRoomName()));

    }

    @Test()
    @Description("テスト: 支払う画面が部室予約画面に戻す")
    public void TC14() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        User user = User.TANAKA;
        loginPage.login(user);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room1 = Room.R201;
        Room room2 = Room.R301;
        Logger.info("4. 部室名「201」と「301」をチェックする");
        reservePage.selectRoomByName(room1.getRoomName());
        reservePage.selectRoomByName(room2.getRoomName());

        Logger.info("5.「予約画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        int total = room1.getPrice() + room2.getPrice();
        Logger.verify("合計が0円から27000円に変更されこと,「201」と「301」をチェックすること");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));



    }

}