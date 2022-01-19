import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static Helpers.GlobalHelpers.*;

public class Payments {

    public Payments() {

    }

    private void clickFromAccount(){
        clickObj("//button[@data-monitoring-label='Transfer Form From Chooser']");
    }

    private void clickToAccount(){
        clickObj("//button[@data-monitoring-label='Transfer Form To Chooser']");
    }

    private void searchAccountName(String name)
    {
        setValObj("//input[@data-monitoring-label='Transfer Form Search']",name);
    }

    private void selectAccountName(String name)
    {
        clickObj("//p[text()='"+name+"']");
    }
    public void selectAccount(String fromOrTo, String accountName)
    {
        switch (fromOrTo.toUpperCase(Locale.ROOT)){
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

    public void setAmount(Double amount)
    {
        setValObj("//input[@name='amount']",amount.toString());
    }

    public void clickTransfer()
    {
        clickObj("//button//span[text()='Transfer']");
        validateObj("//span[text()='Transfer successful']");
    }

    public void doTransfer(String from,String to,Double amount)
    {

        selectAccount("From", from);
        selectAccount("To", to);
        setAmount(amount);
        clickTransfer();
    }

}
