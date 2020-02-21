package LoginProccess;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private SelenideElement loginField = $("input[name='login'");
    private SelenideElement passwordField = $("input[name='password'");
    private SelenideElement moveOnButton = $("button[data-test-id='action-login']");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        moveOnButton.click();
        return new VerificationPage();
    }
}
