package tests;

import common.Card;
import common.Room;
import common.TestConstants;
import common.User;
import common.helpers.DateHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import objectData.Account;
import objectData.PaymentCard;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PaymentPage;
import pages.ReservePage;
import pages.ReservedListPage;

import java.util.Date;

public class PaymentPageTest extends TestBaseIOS {

    @Test()
    @Description("テスト: カード番号フィールドを未入力すると。提出できない")
    public void TC22() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド にを未入力する");
        Logger.info("2.3「有効期限」フィールド に 「12/24」を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardNumber("");
        card.setExpiredDate("12/24");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgACNumberDisplayed());
    }

    @Test()
    @Description("テスト: カード名フィールドを未入力すると。提出できない")
    public void TC21() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドを未入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールド に 「12/24」を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANB);
        card.setCardName("");
        card.setExpiredDate("12/24");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgACNameDisplayed());
    }

    @Test()
    @Description("テスト: カード番号の形式が間違って、カード番号が 16 桁の数字足らないで,提出できません")
    public void TC23() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555 6」を入力する");
        Logger.info("2.3「有効期限」フィールド に 「12/24」を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("22223333444455556");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isWrongAccountDialogDisplayed());
    }

    @Test()
    @Description("テスト: カード名フィールドを未入力すると。提出できない")
    public void TC24() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 555」を入力する");
        Logger.info("2.3「有効期限」フィールド に 「12/24」を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("222233334444555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgACNumberDisplayed());
    }

    @Test()
    @Description("テスト: カード番号の形式が間違って、カード番号が 16 桁で足らないで,提出できません")
    public void TC25() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 abc2 5555」を入力する");
        Logger.info("2.3「有効期限」フィールド に 「12/24」を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("22223333abc25555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgACNumberDisplayed());
    }

    @Test()
    @Description("テスト: 有効期限を未入力すると、提出できない")
    public void TC26() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールド を未入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("");
        card.setCardNumber("2222333344445555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgExpiredDateDisplayed());
    }

    @Test()
    @Description("テスト: 「有効期限」の年は2桁の数字でなければならない")
    public void TC27() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「12/2」 を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/2");
        card.setCardNumber("2222333344445555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgExpiredDateDisplayed());
    }

    @Test()
    @Description("テスト: 「「有効期限」の年は2桁の数字でなければならない")
    public void TC28() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「ab/24」 を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("ab/24");
        card.setCardNumber("2222333344445555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgExpiredDateDisplayed());
    }

    @Test()
    @Description("テスト: 「有効期限」の年は2桁でなければならない")
    public void TC29() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「12/ab」 を入力する");
        Logger.info("2.4 「cvv」フィールド に「333」を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/ab");
        card.setCardNumber("2222333344445555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgExpiredDateDisplayed());
    }

    @Test()
    @Description("テスト: CVVを入力しなければならない")
    public void TC30() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「12/24」 を入力する");
        Logger.info("2.4 「cvv」フィールド を未入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("2222333344445555");
        card.setCVV("");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgSecureCodeDisplayed());
    }

    @Test()
    @Description("テスト: CVVフィールドは３桁でなければならない")
    public void TC31() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「12/24」 を入力する");
        Logger.info("2.4 「cvv」フィールドに「33」 を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("2222333344445555");
        card.setCVV("33");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgSecureCodeDisplayed());
    }

    @Test()
    @Description("テスト: CVVフィールドは３桁でなければならない")
    public void TC32() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「12/24」 を入力する");
        Logger.info("2.4 「cvv」フィールドに「abc」 を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("2222333344445555");
        card.setCVV("abc");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isErrMsgSecureCodeDisplayed());
    }

    @Test()
    @Description("テスト: 無効なアカウントで提出できない")
    public void TC33() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「LY VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「2222 3333 4444 5555」を入力する");
        Logger.info("2.3「有効期限」フィールドに「12/24」 を入力する");
        Logger.info("2.4 「cvv」フィールドに「333」 を入力する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        card.setCardName("LY VAN C");
        card.setExpiredDate("12/24");
        card.setCardNumber("2222333344445555");
        card.setCVV("333");
        paymentPage.prePayemnt(card);
        Logger.info("3.「予約する」ボタンを押下");
        paymentPage.payment();
        Assert.assertTrue(paymentPage.isWrongAccountDialogDisplayed());
    }

    @Test()
    @Description("テスト: 有効な銀行口座で成功を提出できる")
    public void TC34() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「404」をチェックする");
        reservePage.selectRoomByName(Room.R404.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1.「前払い」オプションを押下する");
        Logger.info("2. 値を入力すろ");
        Logger.info("2.1 「カード名」フィールドに「TRAN VAN C」を入力する");
        Logger.info("2.2 「カード番号」フィールド に「1111 3333 5555 7777」を入力する");
        Logger.info("2.3「有効期限」フィールドに「10/26」 を入力する");
        Logger.info("2.4 「cvv」フィールドに「952」 を入力する");

        Logger.info("3.「予約する」ボタンを押下する");
        PaymentCard card = new PaymentCard().setPaymentCard(Card.VANC);
        paymentPage.prePayemnt(card);
        paymentPage.payment();
        Logger.info("4. 「OK」ボタンを押下する");
        paymentPage.confirmPayment();
        Logger.info("5. 「OK」ボタンを押下する");
        paymentPage.closeDialog();
        Assert.assertTrue(reservePage.isCheckInDisplayCorrectly(new Date()));
        Assert.assertTrue(reservePage.isCheckoutDisplayCorrectly(DateHelper.plusDaysInDate(new Date(), 1)));
        Assert.assertTrue(reservePage.isTotalDisplayCorrectly(0));
        Logger.info("6. メニューアイコンをクリックする");
        Logger.info("７．「部室を予約したリスト」メニュー項目 をクリックする");
        reservePage.showReservedList();
        Assert.assertTrue(reservedListPage.isRoomMatchReserved(Room.R404));
    }

    @Test()
    @Description("テスト: メニューをクリックした後、「予約する」ボタンはアクションを実行できない")
    public void TC35() {
        LoginPage loginPage = new LoginPage();
        ReservePage reservePage = new ReservePage();
        PaymentPage paymentPage = new PaymentPage();
        Logger.info("前提条件： ユーザーがログインしました");
        Account yamaha = new Account().setAccount(User.YAMAHA);
        loginPage.login(yamaha);
        Logger.info("1.「チェックイン日」で本日を設定する");
        Logger.info("2.「チェックアウト日」で翌日を設定する");
        Logger.info("3. 検索ボタンを押下する");
        reservePage.searchData(TestConstants.TODAY, DateHelper.plusDaysInDate(TestConstants.TODAY, 1));
        Logger.info("4. 部室名「202」をチェックする");
        reservePage.selectRoomByName(Room.R202.getRoomName());
        Logger.info("5.「支払い画面へ」ボタンを押下する");
        reservePage.gotoPayment();
        Logger.info("1. メニューをクリックする");
        Logger.info("2. 「予約する」ボタンを押下する");
        paymentPage.clickMenuButton();
        paymentPage.paymentTap();
        Assert.assertTrue(paymentPage.isAlertConfirmPaymentDisplayed());
    }
}
