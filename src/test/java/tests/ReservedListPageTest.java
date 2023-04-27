package tests;

import common.SearchType;
import common.TestConstants;
import common.User;
import common.helpers.Logger;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ReservedListPage;

import java.time.LocalDate;
import java.util.Date;

public class ReservedListPageTest extends TestBaseIOS{

    @Test()
    @Description("テスト: UIを確認する")
    public void TC15() {
        LoginPage loginPage = new LoginPage();
        ReservedListPage reservedListPage = new ReservedListPage();
        loginPage.login(User.TANAKA);
        reservedListPage.showReservedList();
        reservedListPage.changeSearchType(SearchType.RANGE);
        reservedListPage.selectDate(new Date());
    }
}
