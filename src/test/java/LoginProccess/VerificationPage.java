package LoginProccess;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement verificationCodeField = $("input[name='code'");
    private SelenideElement moveOnButton = $("button[data-test-id='action-verify']");

    public UserPage validVerificationCode(DataHelper.AuthInfo info) {
        verificationCodeField.setValue(DataHelper.VerificationCode.getVerificationCodeFore(info).getCode());
        moveOnButton.click();
        return new UserPage();
    }

}
