package Pages;

import static Helpers.GlobalHelpers.clickObj;
import static Helpers.GlobalHelpers.getObj;

public class Home {

    public Home() {

    }

    public void goToPayeespage() {
        clickObj("//div[@class='topbar-actions messages']/button");
        clickObj("//a[@href='/client/payees']");
    }

    public void navigateTo(String value) {
        if (value.equalsIgnoreCase("Payments")) {
            value = "Pay or transfer";
        }
        clickObj("//div[@class='topbar-actions messages']/button");
        clickObj("//span[text()='" + value + "']");
    }

    public double getAccountBalance(String accountName) {
        String test = getObj("//div[span[H3[@title='" + accountName + "']]]/span[@class='account-balance']").getText();
        return Double.parseDouble(test.replaceAll(",", ""));
    }
}