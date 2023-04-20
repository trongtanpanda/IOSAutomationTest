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
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC06_AddPaymentCardFailed extends TestBaseHotel {
    @Test()
    @Description("テスト：新しいカードを追加して失敗する")
    public void TC06() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();

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

        Logger.info("7. 予約ボタンを押下");
        roomReservationPage.pressBookingRoomButton();

        Logger.info("8.「新しい支払いカードを追加」のボタンを押下");
        methodOfPaymentPage.pressAddNewCardButton();

        Logger.info("9.「カード」名義は「test test test test test test test」を入力する。");
        Logger.info("10．「カード番号」は「４１１１－１１１１－１１１１－１１１」を入力する。");
        addNewPaymentCardPage.addNewCard(TestConstants.TCO6_DATA_PAYMENT);

        Logger.verify("１．「test test test test test t」が表示されること。");
        AssertHelper.checkEqual("test test test test test t",addNewPaymentCardPage.getValueCardName(),"「test test test test test t」が表示されていません。");

        Logger.verify("２．エラーメッセージ「カード番号が正しくありません」が表示される事。");
        AssertHelper.checkEqual("カード番号が正しくありません",addNewPaymentCardPage.getErrorMessenger(),"エラーメッセージ「カード番号が正しくありません」が表示されていません。");

        Logger.info("１１．カード番号のテキストボックスを消去する");
        addNewPaymentCardPage.addNewCard(TestConstants.TCO6_DATA_BLANK_PAYMENT);

        Logger.verify("３．エラーメッセージ「カード番号を入力してください」が表示されていること。");
        AssertHelper.checkEqual("カード番号を入力してください",addNewPaymentCardPage.getErrorMessenger(),"エラーメッセージ「カード番号を入力してください」が表示されていること。ていません。");
    }
}


