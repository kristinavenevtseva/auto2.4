package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldSuccessfulTranserFromSecondToFirst() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        var startFirstCardBalance = dashboardPage.getCardBalance(0);
        var startSecondCardBalance = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.validTransferTo(0);
        var cardInfo = DataHelper.getSecondCardInfo();
        var amount = transferPage.transferToCard(cardInfo);

        var expectedFirstCardBalance = startFirstCardBalance + amount;
        var expectedSecondCardBalance = startSecondCardBalance - amount;
        var actualFirstCardBalance = dashboardPage.getCardBalance(0);
        var actualSecondCardBalance = dashboardPage.getCardBalance(1);

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }

    @Test
    void shouldSuccessfulTranserFromFirstToSecond() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        var startFirstCardBalance = dashboardPage.getCardBalance(0);
        var startSecondCardBalance = dashboardPage.getCardBalance(1);
        var transferPage = dashboardPage.validTransferTo(1);
        var cardInfo = DataHelper.getFirstCardInfo();
        var amount = transferPage.transferToCard(cardInfo);

        var expectedFirstCardBalance = startFirstCardBalance - amount;
        var expectedSecondCardBalance = startSecondCardBalance + amount;
        var actualFirstCardBalance = dashboardPage.getCardBalance(0);
        var actualSecondCardBalance = dashboardPage.getCardBalance(1);

        assertEquals(expectedFirstCardBalance, actualFirstCardBalance);
        assertEquals(expectedSecondCardBalance, actualSecondCardBalance);
    }
}
