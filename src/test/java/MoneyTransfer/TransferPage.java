package MoneyTransfer;

import LoginProccess.DataHelper;
import LoginProccess.UserPage;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;

@Value
public class TransferPage {
    private SelenideElement amountToTransfer = $("input[type='text'][formnovalidate");
    private SelenideElement transferFrom = $("input[type='tel']");
    private SelenideElement transferTo = $("input[type='text'][disabled");
    private SelenideElement buttonToTransfer = $("button[data-test-id='action-transfer']");
    private int accountIndex;

    public UserPage transferMoneyValid() {
        // В идеале должно получать из базы данных текущую сумму и брать меньше или равную ей. Пытался парсить суммы по счетам из личного кабинета через XPath но к сожалению не получилось.
        amountToTransfer.setValue("1000");
        val sourceAccount = DataHelper.AuthInfo.getAuthInfo().getAccounts().get(1 - accountIndex);
        transferFrom.setValue(sourceAccount);
        buttonToTransfer.click();
        return new UserPage();
    }

    public void transferMoneyInvalid() {
        // В идеале должно получать из базы данных текущую сумму и брать больше неё. Пытался парсить суммы по счетам из личного кабинета через XPath но к сожалению не получилось.
        amountToTransfer.setValue("1000000");
        val sourceAccount = DataHelper.AuthInfo.getAuthInfo().getAccounts().get(1 - accountIndex);
        transferFrom.setValue(sourceAccount);
        buttonToTransfer.click();
    }


}
