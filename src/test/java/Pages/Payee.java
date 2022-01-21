package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static Helpers.GlobalHelpers.*;

public class Payee {

    public Payee() {

    }

    @Step("Verify Payee Page")
    public void verifyPayeesPage() {
        validateObj("//header[contains(@aria-label,'Payees page')]//span[text()='Payees']");
    }
    @Step("Click Add Payee")
    private void clickAdd() {
        clickObj("//span[text()='Add']");
    }

    @Step("Set Payee Name")
    private void setPayeeName(String name) {
        setValObj("//input[@id='ComboboxInput-apm-name']", name);
    }

    @Step("Select Payee Name")
    private void selectPayeeName(String name) {
        clickObj("//span[text()='" + name.toUpperCase(Locale.ROOT) + "']");
    }

    @Step("Click Add in Payee Modal")
    private void payeeModal_clickAdd() {
        clickObj("//button[@class='js-submit Button Button--primary']");
    }

    @Step("Add Payee")
    public void addPayee(String name) {
        clickAdd();
        setPayeeName(name);
        selectPayeeName(name);
        payeeModal_clickAdd();
        validateObj("//span[text()='Payee added']");
    }

    @Step("Validate Add Blank Payee")
    public void validateAddBlankPayee() {
        clickAdd();
        setPayeeName("");
        validateObj("//button[@class='js-submit Button Button--primary Button--disabled']");
    }

    @Step("verify Payee In Table")
    public void verifyPayeeInTable(String name) {
        validateObj("//ul//span[text()='" + name + "']");
    }

    @Step("Get Payee Name")
    public List<String> getPayeesNames() {
        List<WebElement> test = getObjects("//span[@class='js-payee-name']");
        List<String> payeeNames = new ArrayList<>();

        for (WebElement w : test) {
            payeeNames.add(w.getText());
        }

        return payeeNames;
    }

    @Step("Click 'Name' Header")
    public void clickNameHeader() {
        clickObj("//span[text()='Name']");
    }
}
