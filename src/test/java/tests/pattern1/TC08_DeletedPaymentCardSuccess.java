package tests.pattern1;

import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AddNewPaymentCard.AddNewPaymentCardPage;
import pages.BookingRoom.BookingRoomPage;
import pages.EditPaymentCard.EditPaymentCardPage;
import pages.Login.LoginPage;
import pages.MethodOfPayment.MethodOfPaymentPage;
import pages.RoomReservation.RoomReservationPage;
import tests.TestBaseHotel;

public class TC08_DeletedPaymentCardSuccess extends TestBaseHotel {
    @Test()
    @Description("テスト：支払いカードを削除成功。")
    public void TC08() {
        LoginPage loginPage = new LoginPage();
        RoomReservationPage roomReservationPage = new RoomReservationPage();
        BookingRoomPage bookingRoomPage = new BookingRoomPage();
        MethodOfPaymentPage methodOfPaymentPage = new MethodOfPaymentPage();
        AddNewPaymentCardPage addNewPaymentCardPage = new AddNewPaymentCardPage();
        EditPaymentCardPage editPaymentCardPage = new EditPaymentCardPage();

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

        Logger.info("9.「カード」名義は「test」を入力する。");
        Logger.info("10．「カード番号」は「４１１１－１１１１－１１１１－１１１１」を入力する。");
        addNewPaymentCardPage.addNewCard(TestConstants.TCO8_DATA_PAYMENT);

        Logger.info("11．「確認」のボタンを押下。");
        addNewPaymentCardPage.pressConfirmButton();

        Logger.info("12. 「ICONタイプの支払いカード」のボタンを押下。");
        methodOfPaymentPage.pressEditCardButton();

        Logger.verify("１．前提条件のステップ9の支払いカード名義入力したが「カード名義」のテキストボックスで表示されること。");
        AssertHelper.checkEqual(TestConstants.TCO8_DATA_PAYMENT.getCardName(),editPaymentCardPage.getValueCardName(),
                "前提条件のステップ9の支払いカード名義入力したが「カード名義」のテキストボックスで表示されていません。");

        Logger.verify("２．前提条件のステップ10の支払いカード番号入力したが「カード番号」のテキストボックスで表示されること。");
        AssertHelper.checkEqual(TestConstants.TCO8_DATA_PAYMENT.getCardNumber(),editPaymentCardPage.getValueCardNumber(),
                "前提条件のステップ10の支払いカード番号入力したが「カード番号」のテキストボックスで表示されていません。");

        Logger.verify("３．「有効期限:」の月は1月です。");
        AssertHelper.checkTrue(editPaymentCardPage.isSelectBoxMonthDisplayed(),"「有効期限:」の月は1月が表示されていません。");

        Logger.verify("４．「有効期限:」の年は今年＋５年後です。");
        AssertHelper.checkTrue(editPaymentCardPage.isSelectBoxYearDisplayed(),"「有効期限:」の年は今年＋５年後が表示されていません。");

        Logger.verify("５．「戻る」、「確認」、「支払いカードを削除」ボタンが同じ行で表示されること。");
        AssertHelper.checkTrue(editPaymentCardPage.isBackButtonDisplayed(),"「戻る」ボタンが同じ行で表示されていません。");

        AssertHelper.checkTrue(editPaymentCardPage.isConfirmButtonDisplayed(),"「確認」ボタンが同じ行で表示されていません。");

        AssertHelper.checkTrue(editPaymentCardPage.isDeletePaymentCardButtonDisplayed(),"「支払いカードを削除」ボタンが同じ行で表示されていません。");

        Logger.info("13. 「番号」は「４１１１－１１１１－１１１１－１１１1ー２２２２」を入力する。");
        editPaymentCardPage.editPaymentCard(TestConstants.TCO8_DATA_ERROR_1_PAYMENT);

        Logger.verify("６．「４１１１－１１１１－１１１１－１１１１」番号が表示されること。");
        AssertHelper.checkEqual(TestConstants.TCO8_DATA_PAYMENT.getCardNumber(),editPaymentCardPage.getValueCardNumber(),
                    "「４１１１－１１１１－１１１１－１１１１」番号が表示されていません。");

        Logger.info("14. 「番号」は「４１１１－１１１１－１１１１－１１１」を入力する。");
        editPaymentCardPage.editPaymentCard(TestConstants.TCO8_DATA_ERROR_2_PAYMENT);

        Logger.verify("7.　確認ボタンが表示され、ステータスがDisableになられること。");
        AssertHelper.checkFalse(editPaymentCardPage.isStateConfirmButtonEnable(),"確認ボタンが表示され、ステータスがDisableになられていません。");

        Logger.info("15. 「番号」は「空」を入力する。");
        editPaymentCardPage.editPaymentCard(TestConstants.TCO8_DATA_ERROR_3_PAYMENT);

        Logger.verify("7.　確認ボタンが表示され、ステータスがDisableになられること。");
        AssertHelper.checkFalse(editPaymentCardPage.isStateConfirmButtonEnable(),"確認ボタンが表示され、ステータスがDisableになられていません。");

        Logger.info("1６. 「支払いカードを削除」ボタンを押下。");
        editPaymentCardPage.pressDeletePaymentCard();

        Logger.verify("８. 支払い画面が表示されている。");
        AssertHelper.checkTrue(methodOfPaymentPage.isMethodOfPaymentPageDisplayed(),"支払い画面が表示されていません。");

        Logger.verify("９.「新しい支払いカードを追加ボタン」が表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isAddNewCardImageDisplayed(),"「新しい支払いカードを追加ボタン」が表示されていません。");

        Logger.verify("１０.「新しい支払い方法を追加する」が表示されること。");
        AssertHelper.checkTrue(methodOfPaymentPage.isLabelAddNewPaymentCardDisplayed(),"「新しい支払い方法を追加する」が表示されていません。");
    }
}

