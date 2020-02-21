package LoginProccess;

import MoneyTransfer.TransferPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Value;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Value
public class UserPage {
    private String header;
    private ElementsCollection account = $$("li.list__item");

    public UserPage() {
        this.header = "Личный кабинет";
    }

    public static TransferPage moneyTransfer (int accountIndex) {
        UserPage userPage = new UserPage();
        userPage.getAccount().get(accountIndex).find(withText("Пополнить")).click();
        return new TransferPage(accountIndex);
    }
}
