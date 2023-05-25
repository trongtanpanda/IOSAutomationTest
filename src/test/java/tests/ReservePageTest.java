package tests;

import common.Room;
import common.RoomType;
import common.User;
import common.helpers.DateHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import jdk.jfr.Name;
import objectData.Account;
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
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();

        Logger.info("1.「チェックイン日」を押下する");
        Logger.info("2. 本日を選択する (ex: 2023/05/10)");
        Logger.info("3.「閉じる」ボタンを押下する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.verify("「チェックイン日」フィールドの値は本日「2023/05/10」こと,「yyyy/mm/dd」形式で表示されること");
        Assert.assertTrue(reservePage.isCheckInDisplayCorrectly(checkInDate));
    }

    @Test()
    @Description("テスト: [チェックアウト日] 日付ピッカーが表示する")
    public void TC04() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkOutDate = DateHelper.plusDaysInDate(new Date(),1);

        Logger.info("1.「チェックアウト日」を押下する");
        Logger.info("2. 翌日を選択する (ex: 2023/05/11)");
        Logger.info("3.「閉じる」ボタンを押下する");
        reservePage.pickCheckoutDate(checkOutDate);


        Logger.verify("「チェックアウト日」フィールドの値は翌日「2023/05/11」こと,「yyyy/mm/dd」形式で表示されること");
        Assert.assertTrue(reservePage.isCheckoutDisplayCorrectly(checkOutDate));
    }

    @Test()
    @Description("テスト: 日付ピッカーが表示する")
    public void TC05() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();

        Logger.info("1「チェックイン日」で本日（ex: 2023/10/12）に選択する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」を押下する");
        Logger.verify("翌日（ex: 2023/20/13）の前の選択を許可しないこと");
        Assert.assertTrue(reservePage.isCheckoutShouldNotEqualOrLessThanCheckInDate(checkInDate));
    }

    @Test()
    @Description("テスト: 部屋を探す")
    public void TC06() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("データが表示されこと");
        Assert.assertTrue(reservePage.isDataDisplayed());
    }

    @Test()
    @Description("テスト: シングル部屋タイプから部屋を探す")
    public void TC07() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.info("4.「シングル」オプションを押下する");
        reservePage.selectSingle();

        Logger.verify("部屋タイプが「シングル」の部屋のみが表示されこと");
        Assert.assertTrue(reservePage.isListRoomByTypeDisplayCorrectly(RoomType.SINGLE.getType()));
    }

    @Test()
    @Description("テスト: ツイン部屋タイプから部屋を探す")
    public void TC08() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.info("4.「ツイン」オプションを押下する");
        reservePage.selectTwin();

        Logger.verify("部屋タイプが「ツイン」の部屋のみが表示されこと");
        Assert.assertTrue(reservePage.isListRoomByTypeDisplayCorrectly(RoomType.TWIN.getType()));
    }

    @Test()
    @Description("テスト: 部屋の詳細")
    public void TC09() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
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
    @Description("テスト: 部室を選択しないで支払い画面へをクリックする")
    public void TC10() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.info("4. 「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();

        Logger.verify("エラーポップアップが表示されこと");
        Assert.assertTrue(reservePage.isErrorPopupDisplayCorrectly());
    }

    @Test()
    @Description("テスト: 部室を選択して支払い画面へをクリックする")
    public void TC11() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();

        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room = Room.R201;
        Logger.info("4. 部室名「201」をチェックする");
        reservePage.selectRoomByName(room.getRoomName());
        Logger.verify("合計が0円から15000円に変更されこと");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(room.getPrice()));

        Logger.info("5.「支払い画面へ」ボタンを押下する");
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
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room1 = Room.R201;
        Room room2 = Room.R401;
        Logger.info("4. 部室名「201」と「401」をチェックする");
        reservePage.selectRoomByName(room1.getRoomName());
        reservePage.selectRoomByName(room2.getRoomName());

        Logger.verify("合計が0円から27000円に変更されこと,「201」と「401」をチェックすること");
        int total = room1.getPrice() + room2.getPrice();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));

        Logger.info("5.合計が23000円あること,　部室タイプが「シングル」の部室のみが表示されこと、「201」がチェックすること");
        reservePage.selectSingle();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));

        Logger.info("6.合計が23000円あること,　部室タイプが「ツイン」の部室のみが表示されこと、「401」がチェックすること");
        reservePage.selectTwin();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));

        Logger.info("7.合計が23000円あること,　全てのデータが表示されこと、「201」と「401」がチェックすること");
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
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room1 = Room.R201;
        Room room2 = Room.R401;
        Logger.info("4. 部室名「201」と「401」をチェックする");
        reservePage.selectRoomByName(room1.getRoomName());
        reservePage.selectRoomByName(room2.getRoomName());
        Logger.verify("合計が0円から23000円に変更されこと,「201」と「401」をチェックすること");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(room1.getPrice()+room2.getPrice()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));

        Date newCheckInDate = DateHelper.plusDaysInDate(checkInDate,2);
        Date newCheckoutDate = DateHelper.plusDaysInDate(checkInDate,3);
        Logger.info("5.「チェックイン日」で2日後を設定する");
        reservePage.pickCheckInDate(newCheckInDate);
        Logger.info("6.「チェックアウト日」で3日後を設定する");
        reservePage.pickCheckoutDate(newCheckoutDate);
        Logger.info("7. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("合計が23000円から0円に変更されこと");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(0));
        Assert.assertFalse(reservePage.isCheckedByRoomName(room1.getRoomName()));
        Assert.assertFalse(reservePage.isCheckedByRoomName(room2.getRoomName()));
    }

    @Test()
    @Description("テスト: 支払う画面が部室予約画面に戻す")
    public void TC14() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room1 = Room.R201;
        Room room2 = Room.R401;
        Logger.info("4. 部室名「201」と「401」をチェックする");
        reservePage.selectRoomByName(room1.getRoomName());
        reservePage.selectRoomByName(room2.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        int total = room1.getPrice() + room2.getPrice();
        Logger.verify("合計が0円から23000円に変更されこと,「201」と「401」をチェックすること");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isRoomDisplayCorrectlyInPaymentPage(room1));
        Assert.assertTrue(reservePage.isRoomDisplayCorrectlyInPaymentPage(room2));

        Logger.info("６.「戻る」ボタンを押下する");
        reservePage.backToReservePage();
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room1.getRoomName()));
        Assert.assertTrue(reservePage.isCheckedByRoomName(room2.getRoomName()));

    }

    @Test()
    @Description("テスト: 後払いで提出する")
    public void TC15() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);

        Date checkInDate = new Date();
        Date checkoutDate = DateHelper.plusDaysInDate(checkInDate,1);

        Logger.info("1.「チェックイン日」で本日を設定する");
        reservePage.pickCheckInDate(checkInDate);

        Logger.info("2.「チェックアウト日」で翌日を設定する\n");
        reservePage.pickCheckoutDate(checkoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Room room1 = Room.R201;
        Room room2 = Room.R401;
        Logger.info("4. 部室名「201」と「401」をチェックする");
        reservePage.selectRoomByName(room1.getRoomName());
        reservePage.selectRoomByName(room2.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        int total = room1.getPrice() + room2.getPrice();
        Logger.verify("合計が0円から27000円に変更されこと,「201」と「401」をチェックすること");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(total));
        Assert.assertTrue(reservePage.isRoomDisplayCorrectlyInPaymentPage(room1));
        Assert.assertTrue(reservePage.isRoomDisplayCorrectlyInPaymentPage(room2));

        Logger.info("6.「後払い」オプションを押下する");
        reservePage.postPaidPayment();

        Logger.info("7.「予約する」ボタンを押下する");
        reservePage.payment();

        Logger.info("8.「OK」ボタンを押下する");
        reservePage.confirmPayment();

        Logger.info("9.「OK」ボタンを押下する");
        reservePage.closeDialog();

        Logger.verify("手順9確認時、「部室予約」画面が表示されこと");

        Logger.verify("「チェックイン日」が本日表示されこと");
        Assert.assertTrue(reservePage.isCheckInDisplayCorrectly(checkInDate));

        Logger.verify("「チェックアウト日」が翌日表示されこと");
        Assert.assertTrue(reservePage.isCheckoutDisplayCorrectly(checkoutDate));

        Logger.verify("部室「201」と「401」が消えること");
        Assert.assertFalse(reservePage.isRoomDisplayed(room1));
        Assert.assertFalse(reservePage.isRoomDisplayed(room2));

        Logger.verify("合計が0円あること");
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(0));
    }

    @Test()
    @Description("テスト: 部室を予約した時間の以外、残りの時間を探すと、全てのデータが表示される")
    public void TC16() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);
        Date today = new Date();
        Date checkInDate = DateHelper.plusDaysInDate(today,6);
        Date checkoutDate = DateHelper.plusDaysInDate(today,10);
        Room room = Room.R202;
        Logger.info("６日後(ex:2023/05/06)から10日後 (ex: 2023/05/10)まで、部室の202を予約した");
        reservePage.bookRoom(room, checkInDate, checkoutDate);

        Date expectCheckInDate =  DateHelper.plusDaysInDate(today,1);
        Date expectCheckoutDate =  DateHelper.plusDaysInDate(today,6);
        Logger.info("1.「チェックイン日」で1日後(ex: 2023/05/01)を設定する");
        reservePage.pickCheckInDate(expectCheckInDate);

        Logger.info("2.「チェックアウト日」で ６日後(ex:2023/05/06)を設定する");
        reservePage.pickCheckoutDate(expectCheckoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("部室「202」が表示されこと");
        Assert.assertTrue(reservePage.isRoomDisplayed(room));
    }

    @Test()
    @Description("テスト: 部室を予約した時間の以外、残りの時間を探すと、全てのデータが表示される")
    public void TC17() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);
        Date today = new Date();
        Date checkInDate = DateHelper.plusDaysInDate(today,6);
        Date checkoutDate = DateHelper.plusDaysInDate(today,10);
        Room room = Room.R203;
        Logger.info("[2023/05/06]から [2023/05/10]まで、部室の203を予約した");
        reservePage.bookRoom(room, checkInDate, checkoutDate);

        Date expectCheckInDate =  DateHelper.plusDaysInDate(today,10);
        Date expectCheckoutDate =  DateHelper.plusDaysInDate(today,20);
        Logger.info("1.「チェックイン日」で10日後(ex:2023/05/10)を設定する");
        reservePage.pickCheckInDate(expectCheckInDate);

        Logger.info("2.「チェックアウト日」で20日後(ex:2023/05/20)を設定する");
        reservePage.pickCheckoutDate(expectCheckoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("部室「203」が表示されこと");
        Assert.assertTrue(reservePage.isRoomDisplayed(room));
    }

    @Test()
    @Description("テスト: 予約された部室が含まれる期間の検索が行われた場合、予約済みの部室は空室リストに表示されないようになります。")
    public void TC18() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);
        Date today = new Date();
        Date checkInDate = DateHelper.plusDaysInDate(today,6);
        Date checkoutDate = DateHelper.plusDaysInDate(today,10);
        Room room = Room.R204;
        Logger.info("[2023/05/06]から [2023/05/10]まで、部室の204を予約した");
        reservePage.bookRoom(room, checkInDate, checkoutDate);

        Date expectCheckInDate =  DateHelper.plusDaysInDate(today,1);
        Date expectCheckoutDate =  DateHelper.plusDaysInDate(today,8);
        Logger.info("1.「チェックイン日」で1日後(ex:2023/05/01)を設定する");
        reservePage.pickCheckInDate(expectCheckInDate);

        Logger.info("2.「チェックアウト日」で8日後(ex:2023/05/08)を設定する");
        reservePage.pickCheckoutDate(expectCheckoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("部室「204」が不表示されこと");
        Assert.assertFalse(reservePage.isRoomDisplayed(room));
    }
    @Test()
    @Description("テスト: 予約された部室が含まれる期間の検索が行われた場合、予約済みの部室は空室リストに表示されないようになります。")
    public void TC19() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);
        Date today = new Date();
        Date checkInDate = DateHelper.plusDaysInDate(today,6);
        Date checkoutDate = DateHelper.plusDaysInDate(today,10);
        Room room = Room.R205;
        Logger.info("[2023/05/06]から [2023/05/10]まで、部室の205を予約した");
        reservePage.bookRoom(room, checkInDate, checkoutDate);

        Date expectCheckInDate =  DateHelper.plusDaysInDate(today,7);
        Date expectCheckoutDate =  DateHelper.plusDaysInDate(today,30);
        Logger.info("1.「チェックイン日」で7日後(ex:2023/05/07)に設定");
        reservePage.pickCheckInDate(expectCheckInDate);

        Logger.info("2.「チェックアウト日」で30日後(ex:2023/05/30)に設定");
        reservePage.pickCheckoutDate(expectCheckoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("部室「205」が不表示されこと");
        Assert.assertFalse(reservePage.isRoomDisplayed(room));
    }
    @Test()
    @Name("予約された部室が含まれる期間の検索が行われた場合、予約済みの部室は空室リストに表示されないようになります。")
    @Description("テスト: 予約された部室が含まれる期間の検索が行われた場合、予約済みの部室は空室リストに表示されないようになります。")
    public void TC20() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        Logger.info("前提条件");
        Logger.info("ユーザーがログインしました");
        Account tanaka = new Account().setAccount(User.TANAKA);
        loginPage.login(tanaka);
        Date today = new Date();
        Date checkInDate = DateHelper.plusDaysInDate(today,6);
        Date checkoutDate = DateHelper.plusDaysInDate(today,10);
        Room room = Room.R206;
        Logger.info("[2023/05/06]から [2023/05/10]まで、部室の206を予約した");
        reservePage.bookRoom(room, checkInDate, checkoutDate);

        Date expectCheckInDate =  DateHelper.plusDaysInDate(today,1);
        Date expectCheckoutDate =  DateHelper.plusDaysInDate(today,30);
        Logger.info("1.「チェックイン日」で1日後(ex:2023/05/01)を設定する");
        reservePage.pickCheckInDate(expectCheckInDate);

        Logger.info("2.「チェックアウト日」で30日後(ex:2023/05/30)を設定する");
        reservePage.pickCheckoutDate(expectCheckoutDate);

        Logger.info("3. 検索ボタンを押下する");
        reservePage.search();

        Logger.verify("部室「206」が不表示されこと");
        Assert.assertFalse(reservePage.isRoomDisplayed(room));
    }

}