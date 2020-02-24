package LoginProccess;

import MoneyTransfer.TransferPage;
import com.codeborne.selenide.ElementsCollection;
import lombok.Value;
import lombok.val;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class UserPage {
    private String header = "Личный кабинет";
    private ElementsCollection accountsList = $$("li.list__item");

    public static TransferPage moneyTransfer(int accountIndex) {
        UserPage userPage = new UserPage();
        ArrayList<Integer> accountsBalances = new ArrayList<>();
        for (int i = 0; i < userPage.getAccountsList().size() ; i++) {
            accountsBalances.add(userPage.getAccountBalance(i));
        }
        userPage.getAccountsList().get(accountIndex).find(withText("Пополнить")).click();
        return new TransferPage(accountIndex, accountsBalances);
    }

    public int getAccountBalance(int accountIndex){
        val accountBalanceString = getAccountsList().get(accountIndex).getText();
        val accountBalanceSub = accountBalanceString.substring(accountBalanceString.indexOf("баланс:") + 8, accountBalanceString.indexOf(" р."));
        val accountBalance = Integer.parseInt(accountBalanceSub);
        return accountBalance;
    }


    public void verifySuccessfulLogin() {
        $(withText(getHeader())).waitUntil(visible, 10000);
    }
}
