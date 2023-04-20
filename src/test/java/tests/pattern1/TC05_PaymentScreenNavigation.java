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

public class TC05_PaymentScreenNavigation extends TestBaseHotel {
    @Test()
    @Description("テスト：「支払いカードを追加」画面を移動する。")
    public void TC05() {
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

        Logger.verify("１．「新しいカードを追加」が表示されること。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isAddNewCardTitlePageDisplayed(),
                "「新しいカードを追加」が表示されていません。");

        Logger.verify("２．「カード名義」のテキストボックスが表示されること。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isTextBoxCardNameDisplayed(), "" +
                "「カード名義」のテキストボックスが表示されていません。");

        Logger.verify("３．「カード番号」のテキストボックスが表示されること。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isTextBoxCardNumberDisplayed(), "" +
                "「カード番号」のテキストボックスが表示されていません。");

        Logger.verify("４．「有効期限:」のLabelと「月」のSpinnerと「年」のSpinnerが同じ行で表示されていること。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isSelectBoxMonthDisplayed(), "" +
                "「有効期限:」のLabelが表示されていません。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isSelectBoxMonthDisplayed(), "" +
                "「月」のSpinnerが表示されていません。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isSelectBoxYearDisplayed(),
                "「年」のSpinnerが表示されていません。");

        Logger.verify("５．「戻る」と「確認」のボタンが同じ行で表示されていること。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isBackButtonDisplayed(),
                "「戻る」のボタンが同じ行で表示されていません。");
        AssertHelper.checkTrue(addNewPaymentCardPage.isConfirmButtonDisplayed(),
                "「確認」のボタンが同じ行で表示されていません。");

        Logger.verify("６．「確認」のボタンが表示され、ステータスがDisableになられること。");
        AssertHelper.checkFalse(addNewPaymentCardPage.isStateConfirmButtonEnable(),
                "「確認」のボタンが表示され、ステータスがDisableになられていません。");

        Logger.info("９．「戻る」ボタンを押下。");
        addNewPaymentCardPage.pressBackPreviousPage();

        Logger.verify("７．「支払い方法」画面が表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isMethodOfPaymentPageDisplayed(),
                "「支払い方法」画面が表示されていません。");
    }
}

