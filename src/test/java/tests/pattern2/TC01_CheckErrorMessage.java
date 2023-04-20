package tests.pattern2;

import common.RoomType;
import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.BookingRoom.BookingRoomPage;
import pages.Login.LoginPage;
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC01_CheckErrorMessage extends TestBaseHotel {
    @Test()
    @Description("テスト: エラーメッセージを表示される")
    public void TC01() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();

        Logger.info("前提条件: ロギング");
        loginPage.login(TestConstants.EMAIL, TestConstants.PWD);

        Logger.info("前提条件: ツインボタンを押下");
        roomReservationPage.selectRoomType(RoomType.TWIN);

        Logger.info("１．最初の部屋のチェックボックスを押下");
        roomReservationPage.pressCheckBoxOfFirstRoom();

        Logger.info("２．「チェックイン」に入力");
        Logger.info("３．追加ボタンを押下");
        bookingRoomPage.selectDate("", bookingRoomPage.getToday());

        Logger.verify("メッセージ「正しい日付を入力してください」を表示されること。");
        AssertHelper.checkTrue(bookingRoomPage.isMessageCorrectly("正しい日付を入力してください"),
                "メッセージ「正しい日付を入力してください」を表示されません。");
        bookingRoomPage.pressOKButton();

        Logger.info("4．「チェックアウト」に入力");
        Logger.info("5．追加ボタンを押下");
        bookingRoomPage.selectDate(bookingRoomPage.getToday(), "");

        Logger.verify("メッセージ「正しい日付を入力してください」を表示されること。");
        AssertHelper.checkTrue(bookingRoomPage.isMessageCorrectly("正しい日付を入力してください"),
                "メッセージ「正しい日付を入力してください」を表示されません。");
        bookingRoomPage.pressOKButton();

        Logger.info("6．「チェックイン」に入力");
        Logger.info("7．「チェックアウト」に入力");
        Logger.info("8．追加ボタンを押下");
        bookingRoomPage.selectDate("", "");

        Logger.verify("メッセージ「正しい日付を入力してください」を表示されること。");
        AssertHelper.checkTrue(bookingRoomPage.isMessageCorrectly("正しい日付を入力してください"),
                "メッセージ「正しい日付を入力してください」を表示されません。");
        bookingRoomPage.pressOKButton();

        Logger.info("9．「チェックイン」に入力");
        Logger.info("10．「チェックアウト」に入力");
        Logger.info("11．追加ボタンを押下");
        bookingRoomPage.selectDate("2023/08/20", "2023/08/15");

        Logger.verify("メッセージ「正しいチェックアウト日を入力してください」を表示されること。");
        AssertHelper.checkTrue(bookingRoomPage.isMessageCorrectly("正しいチェックアウト日を入力してください"),
                "メッセージ「正しいチェックアウト日を入力してください」を表示されません。");
        bookingRoomPage.pressOKButton();

        Logger.info("12．「チェックイン」に入力");
        Logger.info("13．「チェックアウト」に入力");
        Logger.info("14．追加ボタンを押下");
        bookingRoomPage.selectDate("2023/08/20", "2023/08/20");

        Logger.verify("メッセージ「チェックイン日とチェックアウト日を同じにすることはできません」を表示されること。");
        AssertHelper.checkTrue(bookingRoomPage.isMessageCorrectly("チェックイン日とチェックアウト日を同じにすることはできません"),
                "メッセージ「チェックイン日とチェックアウト日を同じにすることはできません」を表示されません。");
        bookingRoomPage.pressOKButton();
    }
}
