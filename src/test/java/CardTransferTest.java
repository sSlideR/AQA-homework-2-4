import LoginProccess.DataHelper;
import LoginProccess.LoginPage;
import LoginProccess.UserPage;
import MoneyTransfer.TransferPage;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import static LoginProccess.UserPage.moneyTransfer;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardTransferTest {

    @BeforeEach
    void openBeforeTest() {
        open("http://localhost:9999/");
    }

    @BeforeEach
    void login() {
        val authInfo = DataHelper.AuthInfo.getAuthInfo();

        val verificationPage = new LoginPage().validLogin(authInfo);
        val userPage = verificationPage.validVerificationCode(authInfo);
        $(withText(userPage.getHeader())).waitUntil(visible, 10000);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldTransferMoneyBetweenCards(int input) {
        val transferPage = moneyTransfer(input);
        val userPage = transferPage.transferMoneyValid();
        $(withText(userPage.getHeader())).waitUntil(visible, 10000);
    }

    @Test
    void shouldntTransferMoreThanExist() {
        val transferPage = moneyTransfer(0);
        transferPage.transferMoneyInvalid();
        $(withText("На Вашем счёте недостаточно средств")).waitUntil(visible, 5000);
    }
}
