package tests;

import common.Room;
import common.TestConstants;
import common.User;
import common.helpers.DateHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ReservePage;

import java.util.Date;

public class PaymentPageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: カード名フィールドを未入力すると。提出できない")
    public void TC21() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        loginPage.login(User.YAMAHA);
        Logger.info("1.「チェックイン日」を「2023/05/15」に設定する");
        Logger.info("2.「チェックアウト日」を「2023/05/20」に設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 5));
        Logger.info("4. 部室名「202」をチェックする");
        reservePage.selectRoomByName(Room.R202.getRoomName());
        Logger.info("5.「予約画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドを未入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールド に 「12/24」を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        paymentPage.prePayemnt(TestConstants.TC21CARD);
        Logger.info("3.「予約を確認する」ボタンを押下");
        paymentPage.payment();
        paymentPage.isErrMsgACNameDisplayed();
    }
}
