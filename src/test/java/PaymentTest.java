import Helpers.GlobalHelpers;
import Pages.Home;
import Pages.Payee;
import Pages.Payments;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static Driver.Setup.*;

public class PaymentTest {
    public static GlobalHelpers helpers;
    public static Home home;
    public static Payee payee;
    public static Payments payments;

    @BeforeMethod
    public void setupClass() throws IOException {
        getDriver(getBrowser());
        home = new Home();
        payee = new Payee();
        payments = new Payments();
        helpers = new GlobalHelpers();
    }


    @Description("TC5: Navigate to Payments page")
    @Test(description = "TC5")
    public void TC5() {
        double transferAmount = 500.00;
        String fromAccountName = "Everyday";
        String toAccountName = "Bills ";
        double fromOldVal, toOldVal, fromNewVal, toNewVal;

        fromOldVal = home.getAccountBalance(fromAccountName);
        toOldVal = home.getAccountBalance(toAccountName);

        home.navigateTo("Payments");
        payments.doTransfer(fromAccountName, toAccountName, transferAmount);

        fromNewVal = home.getAccountBalance(fromAccountName);
        toNewVal = home.getAccountBalance(toAccountName);
        Assert.assertEquals(fromOldVal - transferAmount, fromNewVal);
        Assert.assertEquals(toOldVal + transferAmount, toNewVal);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.close();
        }
    }


}


