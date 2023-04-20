package tests.pattern2;

import common.RoomType;
import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AddNewPaymentCard.AddNewPaymentCardPage;
import pages.BookedRoom.BookedRoomPage;
import pages.BookingRoom.BookingRoomPage;
import pages.Login.LoginPage;
import pages.MethodOfPayment.MethodOfPaymentPage;
import pages.RoomReservation.RoomReservationPage;
import pages.PaymentSuccess.PaymentSuccessPage;
import tests.TestBaseHotel;

public class TC03_CanBookATwinRoom extends TestBaseHotel {

    @Test()
    @Description("テスト: ツイン部屋の予約ができる")
    public void TC03() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();
        BookedRoomPage bookedRoomPage = new BookedRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();
        PaymentSuccessPage paymentSuccessPage = new PaymentSuccessPage();

        Logger.info("前提条件: ロギング");
        loginPage.login(TestConstants.EMAIL, TestConstants.PWD);

        Logger.info("1.「チェックインする日」に入力");
        roomReservationPage.inputCheckInDayForSearch(TestConstants.PT2_TC03_DAY_FOR_SEARCH);

        Logger.info("2. ツインボタンを押下");
        roomReservationPage.selectRoomType(RoomType.TWIN);

        Logger.info("3. 部屋301のチェックボックスを押下");
        roomReservationPage.findAndPressCheckBoxOfRoom(TestConstants.PT2_TC03_SELECT_ROOM_1);

        Logger.info("4.「チェックイン」に入力");
        Logger.info("5.「チェックアウト」に入力");
        Logger.info("6. 追加ボタンを押下");
        bookingRoomPage.selectDate(TestConstants.PT2_TC03_CHECK_IN_DAY_1, TestConstants.PT2_TC03_CHECK_OUT_DAY_1);

        Logger.info("7. 予約ボタンを押下");
        roomReservationPage.pressBookingRoomButton();

        Logger.info("8.「新しい支払いカードを追加」のボタンを押下");
        methodOfPaymentPage.pressAddNewCardButton();

        Logger.info("9.「カード」名義に入力");
        Logger.info("10．「カード番号」に入力");
        addNewPaymentCardPage.addNewCard(TestConstants.PT2_TCO3_DATA_PAYMENT);

        Logger.info("11．「確認」のボタンを押下。");
        addNewPaymentCardPage.pressConfirmButton();

        Logger.verify("「支払い方法」画面が表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isMethodOfPaymentPageDisplayed(),
                "「支払い方法」画面が表示されません");

        Logger.verify("支払いカード番号「＊＊＊＊＊＊＊＊＊＊＊＊１１１１」が表示されること。");
        AssertHelper.checkEqual("カード番号 - **************4444", paymentSuccessPage.getNumberPaymentCard(),
                "支払いカード番号「＊＊＊＊＊＊＊＊＊＊＊＊１１１１」が表示されません");

        Logger.info("12. 「予約確定」ボタンを押下。");
        methodOfPaymentPage.pressPayment();

        Logger.verify("前提条件のステップ９の「カード名義」が同じ行で表示される事。");
        AssertHelper.checkTrue(paymentSuccessPage.isNamePaymentCardDisplayedCorrectly(TestConstants.PT2_TCO3_DATA_PAYMENT.getCardName()),
                "前提条件のステップ９の「カード名義」が同じ行で表示されていません。");

        Logger.verify("前提条件のステップ１０の「カード番号」が表示される事。");
        AssertHelper.checkEqual("**************4444", paymentSuccessPage.getNumberPaymentCard(),
                "支払いカード番号「＊＊＊＊＊＊＊＊＊＊＊＊１１１１」が表示されません");

        Logger.verify("ステップ3の部屋を表示されること。");
        AssertHelper.checkEqual(TestConstants.PT2_TC03_SELECT_ROOM_1, paymentSuccessPage.getNameRoomInList(),
                "ステップ3の部屋を表示されていません。");

        Logger.info("13. 「部屋の予約に戻る」ボタンを押下。");
        paymentSuccessPage.pressBackToHome();

        Logger.verify("「部屋の予約」画面が表示されている事。");
        AssertHelper.checkTrue(roomReservationPage.isRoomReservationPageDisplayed(),
                "「部屋の予約」画面が表示されていません。");

        Logger.info("14. メニューを押下");
        Logger.info("15. 予約した部屋を押下");
        roomReservationPage.openBookedRoomList(TestConstants.BOOKED_ROOM);

        Logger.info("16．チェックインに入力");
        Logger.info("17．チェックアウトに入力");
        Logger.info("18．探すボタンを押下");
        bookedRoomPage.searchBookedRoom(TestConstants.PT2_TC03_CHECK_IN_DAY_1, TestConstants.PT2_TC03_CHECK_OUT_DAY_1);

        Logger.info(TestConstants.PT2_TC03_SELECT_ROOM_1 + "部屋を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isRoomDisplayed(TestConstants.PT2_TC03_SELECT_ROOM_1),
                TestConstants.PT2_TC03_SELECT_ROOM_1 + "部屋を表示されません");

    }
}
