package tests.pattern1;

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
import pages.PaymentSuccess.PaymentSuccessPage;
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC10_CheckErrorMessage extends TestBaseHotel {

    @Test()
    @Description("テスト: エラーメッセージを表示される")
    public void TC10() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookedRoomPage bookedRoomPage = new BookedRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();
        PaymentSuccessPage paymentSuccessPage = new PaymentSuccessPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();

        Logger.info("前提条件: ロギング");
        loginPage.login(TestConstants.EMAIL, TestConstants.PWD);

        Logger.info("前提条件: どの部屋でも予約する");
        roomReservationPage.inputCheckInDayForSearch(TestConstants.TC05_DAY_FOR_SEARCH);
        roomReservationPage.pressCheckBoxOfFirstRoom();
        bookingRoomPage.selectDate(TestConstants.TC05_CHECK_IN_DAY, TestConstants.TC05_CHECK_OUT_DAY);
        roomReservationPage.pressBookingRoomButton();
        methodOfPaymentPage.pressAddNewCardButton();
        addNewPaymentCardPage.addNewCard(TestConstants.TCO9_DATA_PAYMENT);
        addNewPaymentCardPage.pressConfirmButton();
        methodOfPaymentPage.pressPayment();
        paymentSuccessPage.pressBackToHome();

        Logger.info("前提条件: メニューを押下");
        Logger.info("前提条件: 予約した部屋を押下");
        roomReservationPage.openBookedRoomList(TestConstants.BOOKED_ROOM);

        Logger.info("１．チェックインに「」入力");
        Logger.info("２．探すボタンを押下");
        bookedRoomPage.searchBookedRoom("", bookedRoomPage.getToday());

        Logger.verify("メッセージ「正しい日付を入力してください」を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isMessageCorrectly("正しい日付を入力してください"),
                "メッセージ「正しい日付を入力してください」を表示されません。");

        bookedRoomPage.pressOKButton();

        Logger.info("3．チェックアウトに入力");
        Logger.info("4．探すボタンを押下");
        bookedRoomPage.searchBookedRoom(bookedRoomPage.getToday(), "");

        Logger.verify("メッセージ「正しい日付を入力してください」を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isMessageCorrectly("正しい日付を入力してください"),
                "メッセージ「正しい日付を入力してください」を表示されません。");

        bookedRoomPage.pressOKButton();

        Logger.info("5．チェックインに入力");
        Logger.info("6．チェックアウトに入力");
        Logger.info("7．探すボタンを押下");
        bookedRoomPage.searchBookedRoom("", "");

        Logger.verify("メッセージ「正しい日付を入力してください」を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isMessageCorrectly("正しい日付を入力してください"),
                "メッセージ「正しい日付を入力してください」を表示されません。");

        bookedRoomPage.pressOKButton();

        Logger.info("8．チェックインに入力");
        Logger.info("9．チェックアウトに入力");
        Logger.info("10．探すボタンを押下");
        bookedRoomPage.searchBookedRoom("2023/04/20", "2023/04/19");

        Logger.verify("メッセージ「正しいチェックアウト日を入力してください」を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isMessageCorrectly("正しいチェックアウト日を入力してください"),
                "メッセージ「正しいチェックアウト日を入力してください」を表示されません。");

        bookedRoomPage.pressOKButton();
    }
}
