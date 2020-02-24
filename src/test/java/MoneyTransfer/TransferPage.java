package MoneyTransfer;

import LoginProccess.DataHelper;
import LoginProccess.UserPage;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;


import java.util.ArrayList;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

@Value
public class TransferPage {
    private SelenideElement amountToTransfer = $("input[type='text'][formnovalidate");
    private SelenideElement transferFrom = $("input[type='tel']");
    private SelenideElement transferTo = $("input[type='text'][disabled");
    private SelenideElement buttonToTransfer = $("button[data-test-id='action-transfer']");
    private int accountIndex;
    private ArrayList<Integer> accountBalances;

    public void transferMoneyValid() {
        val amountAvailableToTransfer = accountBalances.get(1 - accountIndex);
        val randomAmountToTransfer = new Random().nextInt(amountAvailableToTransfer) + 1;

        amountToTransfer.setValue(String.valueOf(randomAmountToTransfer));
        val sourceAccount = DataHelper.AuthInfo.getAuthInfo().getAccounts().get(1 - accountIndex);
        transferFrom.setValue(sourceAccount);
        buttonToTransfer.click();

        UserPage userPageAfterTransfer = new UserPage();
        $(withText(userPageAfterTransfer.getHeader())).waitUntil(visible, 5000);

        userPageAfterTransfer.getAccountsList().get(1 - accountIndex).shouldHave(text(String.valueOf(amountAvailableToTransfer - randomAmountToTransfer)));
        userPageAfterTransfer.getAccountsList().get(accountIndex).shouldHave(text(String.valueOf(accountBalances.get(accountIndex) + randomAmountToTransfer)));
    }


    public void transferMoneyInvalid() {
        val amountAvailableToTransfer = accountBalances.get(1);
        val randomInvalidAmountToTransfer = amountAvailableToTransfer + 1 + new Random().nextInt(100000);

        amountToTransfer.setValue(String.valueOf(randomInvalidAmountToTransfer));
        val sourceAccount = DataHelper.AuthInfo.getAuthInfo().getAccounts().get(0);
        transferFrom.setValue(sourceAccount);
        buttonToTransfer.click();
        $(withText("На Вашем счёте недостаточно средств")).waitUntil(visible, 5000);
    }
}
