package Pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static Helpers.GlobalHelpers.*;

public class Payee {

    public Payee() {

    }

    public void verifyPayeesPage() {
        validateObj("//header[contains(@aria-label,'Payees page')]//span[text()='Payees']");
    }

    private void clickAdd()
    {
        clickObj("//span[text()='Add']");
    }

    private void setPayeeName(String name)
    {
        setValObj("//input[@id='ComboboxInput-apm-name']",name);
    }

    private void selectPayeeName(String name)
    {
        clickObj("//span[text()='"+name.toUpperCase(Locale.ROOT)+"']");
    }

    private void payeeModal_clickAdd()
    {
        clickObj("//button[@class='js-submit Button Button--primary']");

    }

    public void addPayee(String name)
    {
        clickAdd();
        setPayeeName(name);
        selectPayeeName(name);
        payeeModal_clickAdd();
        validateObj("//span[text()='Payee added']");
    }

    public void  addBlankPayee()
    {
        clickAdd();
        setPayeeName("");
        validateObj("//button[@class='js-submit Button Button--primary Button--disabled']");
    }

    public void verifyPayeeInTable(String name)
    {
        validateObj("//ul//span[text()='"+name+"']");
    }

    public List<String> getPayeesNames()
    {
        List<WebElement> test =  getObjects("//span[@class='js-payee-name']");
        List<String> payeeNames = new ArrayList<>();

        for (WebElement w: test) {
            payeeNames.add(w.getText());
        }

        return payeeNames;
    }

    public void clickNameHeader()
    {
        clickObj("//span[text()='Name']");
    }
}
