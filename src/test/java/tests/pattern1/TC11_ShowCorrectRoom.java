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

public class TC11_ShowCorrectRoom extends TestBaseHotel {

    @Test()
    @Description("テスト: 正しい部屋を表示される")
    public void TC11() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();
        BookedRoomPage bookedRoomPage = new BookedRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();
        PaymentSuccessPage paymentSuccessPage = new PaymentSuccessPage();

        Logger.info("前提条件: ロギング");
        loginPage.login(TestConstants.EMAIL, TestConstants.PWD);

        Logger.info("前提条件: どの部屋でも予約する");
        roomReservationPage.findAndPressCheckBoxOfRoom(TestConstants.TC11_SELECT_ROOM_1);
        bookingRoomPage.selectDate(TestConstants.TC11_CHECK_IN_DAY_1, TestConstants.TC11_CHECK_OUT_DAY_1);

        roomReservationPage.findAndPressCheckBoxOfRoom(TestConstants.TC11_SELECT_ROOM_2);
        bookingRoomPage.selectDate(TestConstants.TC11_CHECK_IN_DAY_2, TestConstants.TC11_CHECK_OUT_DAY_2);

        roomReservationPage.pressBookingRoomButton();
        methodOfPaymentPage.pressAddNewCardButton();
        addNewPaymentCardPage.addNewCard(TestConstants.TCO9_DATA_PAYMENT);
        addNewPaymentCardPage.pressConfirmButton();
        methodOfPaymentPage.pressPayment();
        paymentSuccessPage.pressBackToHome();

        Logger.info("前提条件: メニューを押下");
        Logger.info("前提条件: 予約した部屋を押下");
        roomReservationPage.openBookedRoomList(TestConstants.BOOKED_ROOM);

        Logger.info("1．チェックインに入力");
        Logger.info("2．チェックアウトに入力");
        Logger.info("3．探すボタンを押下");
        bookedRoomPage.searchBookedRoom(TestConstants.TC11_CHECK_IN_DAY_1, TestConstants.TC11_CHECK_OUT_DAY_1);

        Logger.verify("1. "+TestConstants.TC11_SELECT_ROOM_1+"部屋を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isRoomDisplayed(TestConstants.TC11_SELECT_ROOM_1),
                TestConstants.TC11_SELECT_ROOM_1+"部屋を表示されません");

        Logger.info("4．チェックインに入力");
        Logger.info("5．チェックアウトに入力");
        Logger.info("6．探すボタンを押下");
        bookedRoomPage.searchBookedRoom(TestConstants.TC11_CHECK_IN_DAY_2, TestConstants.TC11_CHECK_OUT_DAY_2);

        Logger.verify("2. "+TestConstants.TC11_SELECT_ROOM_2+"部屋を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isRoomDisplayed(TestConstants.TC11_SELECT_ROOM_2),
                TestConstants.TC11_SELECT_ROOM_2+"部屋を表示されません");

        Logger.info("7. 全てボタンを押下");
        bookedRoomPage.pressAllButton();

        Logger.verify("3. "+TestConstants.TC11_SELECT_ROOM_1+"部屋と"+TestConstants.TC11_SELECT_ROOM_2+"部屋を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isRoomDisplayed(TestConstants.TC11_SELECT_ROOM_1),
                TestConstants.TC11_SELECT_ROOM_1+"部屋を表示されません");
        AssertHelper.checkTrue(bookedRoomPage.isRoomDisplayed(TestConstants.TC11_SELECT_ROOM_2),
                TestConstants.TC11_SELECT_ROOM_2+"部屋を表示されません");

        String firstRoomName = bookedRoomPage.getFirstRoomName();

        Logger.info("8．最初の部屋の番号をを押下");
        bookedRoomPage.pressFirstRoom();

        Logger.verify("4. 正しい部屋の番号を表示される");
        AssertHelper.checkTrue(bookedRoomPage.isRoomDisplayedCorrectly(firstRoomName));

        Logger.info("9．閉じるボタンを押下");
        bookedRoomPage.pressCloseButton();

        Logger.verify("5. ダイアログを表示されません");
        AssertHelper.checkFalse(bookedRoomPage.isCloseButtonDisplayed(),
                "ダイアログを表示される");

        Logger.info("10. ホームページボタンを押下");
        bookedRoomPage.pressBackButton();

        Logger.verify("6. 「部屋の予約画面」を表示される");
        AssertHelper.checkTrue(roomReservationPage.isRoomReservationPageDisplayed(),
                "「部屋の予約画面」を表示されません");

    }

}
