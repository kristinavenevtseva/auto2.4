package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading2 = $(byText("Пополнение карты"));

    public TransferPage() {
        heading2.shouldBe(visible);
    }

    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromCard = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public int transferToCard(DataHelper.CardInfo cardInfo) {
        amountField.setValue(DataHelper.getRandomAmount());
        var amountValue = getAmount();
        fromCard.setValue(cardInfo.getNumber());
        transferButton.click();
        return amountValue;
    }

    public int getAmount() {
        var text = $(".money-input__value").text().replaceAll("\\s", "");
        return Integer.parseInt(text);
    }
}