package tests.pattern1;

import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AddNewPaymentCard.AddNewPaymentCardPage;
import pages.BookingRoom.BookingRoomPage;
import pages.Login.LoginPage;
import pages.MethodOfPayment.MethodOfPaymentPage;
import pages.PaymentSuccess.PaymentSuccessPage;
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC09_AddPaymentCardSuccess extends TestBaseHotel {

    private String nameRoom;

    @Test()
    @Description("テスト：新しいカードを追加して成功する。")
    public void TC09() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();
        PaymentSuccessPage paymentSuccessPage = new PaymentSuccessPage();

        Logger.info("前提条件: ロギング");
        loginPage.login(TestConstants.EMAIL, TestConstants.PWD);

        Logger.info("1．「チェックインする日」に入力");
        roomReservationPage.inputCheckInDayForSearch(TestConstants.TC05_DAY_FOR_SEARCH);

        Logger.info("２．シングルボタンを押下");
        Logger.info("３．最初の部屋のチェックボックスを押下");
        roomReservationPage.pressCheckBoxOfFirstRoom();

        Logger.info("4．「チェックイン」に入力");
        Logger.info("5．「チェックアウト」に入力");
        Logger.info("6．追加ボタンを押下");
        bookingRoomPage.selectDate(TestConstants.TC05_CHECK_IN_DAY, TestConstants.TC05_CHECK_OUT_DAY);

        //Get Name Room
        nameRoom = roomReservationPage.getFirstRoomName();

        Logger.info("7. 予約ボタンを押下");
        roomReservationPage.pressBookingRoomButton();

        Logger.info("8.「新しい支払いカードを追加」のボタンを押下");
        methodOfPaymentPage.pressAddNewCardButton();

        Logger.info("9.「カード」名義は「test」を入力する。");
        Logger.info("10．「カード番号」は「４１１１－１１１１－１１１１－１１１１」を入力する。");
        addNewPaymentCardPage.addNewCard(TestConstants.TCO9_DATA_PAYMENT);

        Logger.info("11．「確認」のボタンを押下。");
        addNewPaymentCardPage.pressConfirmButton();

        Logger.info("12. 「予約確定」ボタンを押下。");
        methodOfPaymentPage.pressPayment();

        Logger.verify("1．前提条件のステップ９の「カード名義」が同じ行で表示される事。");
        AssertHelper.checkTrue(paymentSuccessPage.isNamePaymentCardDisplayedCorrectly(TestConstants.TCO9_DATA_PAYMENT.getCardName()), "前提条件のステップ９の「カード名義」が同じ行で表示されていません。");

        Logger.verify("2．前提条件のステップ１０の「カード番号」が表示される事。");
        AssertHelper.checkEqual("**************1111", paymentSuccessPage.getNumberPaymentCard(),
                "前提条件のステップ１０の「カード番号」が表示されていません。");

        Logger.verify("3．ステップ3の部屋を表示されること。");
        AssertHelper.checkEqual(nameRoom, paymentSuccessPage.getNameRoomInList(), "ステップ3の部屋を表示されていません。");

        Logger.info("13. 「部屋の予約に戻る」ボタンを押下。");
        paymentSuccessPage.pressBackToHome();

        Logger.verify("4.「部屋の予約」画面が表示されている事。");
        AssertHelper.checkTrue(roomReservationPage.isRoomReservationPageDisplayed(), "「部屋の予約」画面が表示されていません。");
    }
}


