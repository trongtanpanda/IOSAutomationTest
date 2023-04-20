package tests.pattern3;

import common.RoomType;
import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AddNewPaymentCard.AddNewPaymentCardPage;
import pages.BookingRoom.BookingRoomPage;
import pages.Login.LoginPage;
import pages.MethodOfPayment.MethodOfPaymentPage;
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC02_CanReserveADoubleRoom extends TestBaseHotel {

    @Test()
    @Description("テスト: グループ部屋を予約できる")
    public void TC04() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();

        Logger.info("前提条件: ロギング");
        loginPage.login(TestConstants.EMAIL, TestConstants.PWD);

        String firstMoneyTotal = roomReservationPage.getMoneyTotal();

        Logger.info("1．「チェックインする日」に入力");
        roomReservationPage.inputCheckInDayForSearch(TestConstants.PT3_TC02_DAY_FOR_SEARCH);

        Logger.info("2．シングルボタンを押下");
        roomReservationPage.selectRoomType(RoomType.DOUBLE);

        String firstRoomName = roomReservationPage.getFirstRoomName();

        Logger.info("3．最初の部屋のチェックボックスを押下");
        roomReservationPage.pressCheckBoxOfFirstRoom();

        Logger.verify("1．正しい部屋の番号を表示されること。");
        AssertHelper.checkEqual(bookingRoomPage.getRoomName(), firstRoomName,
                "正しい部屋の番号を表示されません");

        Logger.info("4．「チェックイン」に入力");
        Logger.info("5．「チェックアウト」に入力");
        Logger.info("6．追加ボタンを押下");
        bookingRoomPage.selectDate(TestConstants.PT3_TC02_CHECK_IN_DAY, TestConstants.PT3_TC02_CHECK_OUT_DAY);

        Logger.verify("2．部屋を探す画面のチェックインテキストボックスは「2023/08/15」です");
        AssertHelper.checkEqual("2023/08/15", roomReservationPage.getCheckInDayOfSearchBox(),
                "部屋を探す画面のチェックインテキストボックスは他の日を表示される");

        Logger.info("7．シングルボタンを押下");
        roomReservationPage.selectRoomType(RoomType.DOUBLE);

        Logger.verify("3．最初の部屋のチェックボックスを選んだ");
        AssertHelper.checkTrue(roomReservationPage.isFirstCheckBoxChecked(),
                "最初の部屋のチェックボックスをまだ選びません");

        String secondMoneyTotal = roomReservationPage.getMoneyTotal();

        Logger.verify("4．合計の数が変わること。");
        AssertHelper.checkNotEqual(secondMoneyTotal, firstMoneyTotal,
                "合計の数が変らない");

        Logger.verify("5．予約ボタンは有効です");
        AssertHelper.checkTrue(roomReservationPage.isBookingRoomButtonVisible(),
                "予約ボタンは有効じゃない");

        Logger.info("8. 予約ボタンを押下");
        roomReservationPage.pressBookingRoomButton();

        Logger.verify("6．「支払い方法」画面を表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isMethodOfPaymentPageDisplayed(),
                "支払い方法」画面を表示されません");

        Logger.verify("7．「戻る」ボタンが表示されていること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isBackButtonDisplayed(),
                "「戻る」ボタンが表示されません");

        Logger.verify("8．「予約確定」ボタンが表示され、ステータスがDisableになっている。");
        AssertHelper.checkFalse(methodOfPaymentPage.isPayButtonEnable(),
                "「予約確定」ボタンが表示され、ステータスがEnableです");

        Logger.verify("9．合計とステップ７の合計は同じです");
        AssertHelper.checkEqual(secondMoneyTotal, methodOfPaymentPage.getTotalPayment(),
                "合計とステップ７の合計は同じじゃない");

        Logger.verify("10．ステップ3の部屋を表示されること。");
        AssertHelper.checkEqual(firstRoomName, methodOfPaymentPage.getRoomName(),
                "ステップ3の部屋を表示されません");

        Logger.verify("11.「新しい支払いカードを追加ボタン」が表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isAddNewCardImageDisplayed(),
                "「新しい支払いカードを追加ボタン」が表示されません");

        Logger.verify("12.「新しい支払い方法を追加する」が表示されること。");
        AssertHelper.checkEqual(TestConstants.TC04_MESSAGE_DEFAULT_OF_CARD_NUMBER, methodOfPaymentPage.getCardNumber(),
                "「新しい支払い方法を追加する」が表示されません");

        Logger.verify("13.「Visa、Mastercard,JCB」のロゴが表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isImageVisaDisplayed(),
                "「Visa、Mastercard」のロゴが表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isImageJCBDisplayed(),
                "「JCB」のロゴが表示されること。");

        Logger.info("9．「新しい支払いカードを追加」のボタンを押下");
        methodOfPaymentPage.pressAddNewCardButton();

        Logger.verify("14. 「新しいカードを追加」画面が表示されること。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isAddNewCardTitlePageDisplayed(),
                "「新しいカードを追加」画面が表示されません");
    }
}
