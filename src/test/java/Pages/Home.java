package Pages;

import io.qameta.allure.Step;

import static Helpers.GlobalHelpers.clickObj;
import static Helpers.GlobalHelpers.getObj;

public class Home {

    public Home() {

    }

    @Step("Navigate to")
    public void navigateTo(String value) {
        if (value.equalsIgnoreCase("Payments")) {
            value = "Pay or transfer";
        }
        clickObj("//div[@class='topbar-actions messages']/button");
        clickObj("//span[text()='" + value + "']");
    }

    @Step("Get Account Balance")
    public double getAccountBalance(String accountName) {
        String balance = getObj("//div[span[H3[@title='" + accountName + "']]]/span[@class='account-balance']").getText();
        return Double.parseDouble(balance.replaceAll(",", ""));
    }
}