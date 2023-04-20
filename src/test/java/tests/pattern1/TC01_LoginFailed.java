package tests.pattern1;

import common.TestConstants;
import common.helpers.AssertHelper;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.Login.LoginPage;
import tests.TestBaseHotel;

public class TC01_LoginFailed extends TestBaseHotel {

    @Test()
    @Description("テスト：ロギン失敗")
    public void TC01() {
        LoginPage loginPage = new LoginPage();

        Logger.info("1. 「宿泊予約アプリ」を開く");

        Logger.verify(" メールのテキストボックスが表示されていること。");
        AssertHelper.checkTrue(loginPage.isEmailTextBoxDisplayed(),
                "メールのテキストボックスが表示されていません。");

        Logger.verify(" パスワードのテキストボックスが表示されていること。");
        AssertHelper.checkTrue(loginPage.isPasswordTextBoxDisplayed(),
                "パスワードのテキストボックスが表示されていません。");

        Logger.verify(" メールとパスワードのテキストボックスが空です。");
        AssertHelper.checkTrue(loginPage.isEmailAndPasswordTextBoxNull(),
                "メールとパスワードのテキストボックスが非空です。");

        Logger.info("２．ロギングボタンを押下");
        loginPage.login("","");

        Logger.verify("エラーメッセージ「メールを入力していません!」が表示されること。");
        AssertHelper.checkEqual("メールを入力していません!", loginPage.getErrorMessenger(),
                "エラーメッセージ「メールを入力していません!」が表示されていません。");

        Logger.info("3.「メール」は「abctest」を入力する。");
        Logger.info("4.「パスワード」は「空」を入力する。");
        loginPage.login(TestConstants.TC01_FAILED_DATA, "");

        Logger.verify("エラーメッセージ「パスワードが入力されていません!」が表示されること。");
        AssertHelper.checkEqual("パスワードが入力されていません!", loginPage.getErrorMessenger(),
                "エラーメッセージ「パスワードが入力されていません!」が表示されていません。");

        Logger.info("６.「メール」は「test」入力する。");
        Logger.info("７.「パスワード」は「abctest」を入力する。");
        loginPage.login(TestConstants.EMAIL, TestConstants.TC01_FAILED_DATA);

        Logger.verify("エラーメッセージ「メールアドレスまたはパスワードが正しくありません!」が表示されること。");
        AssertHelper.checkEqual("メールアドレスまたはパスワードが正しくありません!", loginPage.getErrorMessenger(),
                "エラーメッセージ「メールアドレスまたはパスワードが正しくありません!」が表示されていません。");
    }
}

