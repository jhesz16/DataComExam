import Helpers.GlobalHelpers;
import Pages.Home;
import Pages.Payee;
import Pages.Payments;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Driver.Setup.*;

public class test2 {
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

   /* @Description("TC1: Verify you can navigate to Payees page using the navigation menu")
    @Test(description = "TC1")
    public static void TC1() {
        home.navigateTo("Payees");
        payee.verifyPayeesPage();
    }

    @Description("TC2: Verify you can add new payee in the Payees page")
    @Test(description = "TC2")
    public void TC2() {
        String payeeName = "TYCO CYLINDER TESTING";
        home.navigateTo("Payees");
        payee.addPayee(payeeName);
        payee.verifyPayeeInTable(payeeName);
    }*/

    @Description("Verify payee name is a required field")
    @Test
    public void TC3() {
        home.navigateTo("Payees");
        payee.validateAddBlankPayee();
    }

    @Description("TC4: Verify that payees can be sorted by name")
    @Test(description = "TC3")
    public void TC4() {
        String payeeName = "TYCO CYLINDER TESTING";
        home.navigateTo("Payees");
        List<String> payeesNames = payee.getPayeesNames();
        List<String> ascending = new ArrayList<>(payeesNames);
        List<String> descending = new ArrayList<>(payeesNames);
        Collections.sort(ascending);
        Collections.reverse(descending);
        Assert.assertEquals(payeesNames, ascending);

        payee.clickNameHeader();
        Assert.assertEquals(payee.getPayeesNames(), descending);
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


