package Pages;

import io.qameta.allure.Step;

import java.util.Locale;

import static Helpers.GlobalHelpers.*;

public class Payments {

    public Payments() {

    }

    @Step("Click From Account")
    private void clickFromAccount() {
        clickObj("//button[@data-monitoring-label='Transfer Form From Chooser']");
    }

    @Step("Click To Account")
    private void clickToAccount() {
        clickObj("//button[@data-monitoring-label='Transfer Form To Chooser']");
    }

    @Step("Search Account Name")
    private void searchAccountName(String name) {
        setValObj("//input[@data-monitoring-label='Transfer Form Search']", name);
    }

    @Step("Select Account Name")
    private void selectAccountName(String name) {
        clickObj("//p[text()='" + name + "']");
    }

    @Step("Select Account From or To")
    public void selectAccount(String fromOrTo, String accountName) {
        switch (fromOrTo.toUpperCase(Locale.ROOT)) {
            case "TO":
                clickToAccount();
                break;
            case "FROM":
                clickFromAccount();
                break;

        }
        searchAccountName(accountName);
        selectAccountName(accountName);
    }

    @Step("Set Amount")
    public void setAmount(Double amount) {
        setValObj("//input[@name='amount']", amount.toString());
    }

    @Step("Click Transfer")
    public void clickTransfer() {
        clickObj("//button//span[text()='Transfer']");
        validateObj("//span[text()='Transfer successful']");
    }

    @Step("Do a transfer")
    public void doTransfer(String from, String to, Double amount) {

        selectAccount("From", from);
        selectAccount("To", to);
        setAmount(amount);
        clickTransfer();
    }

}
