import Helpers.GlobalHelpers;
import Pages.Home;
import Pages.Payee;
import Pages.Payments;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Driver.Setup.*;

public class PayeeTest {
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

    @Description("TC1: Verify you can navigate to Payees page using the navigation menu")
    @Test(description = "TC1")
    public void TC1() {
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
    }

    @Description("Verify payee name is a required field")
    @Test
    public void TC3() {
        home.navigateTo("Payees");
        payee.validateAddBlankPayee();
    }

    @Description("TC4: Verify that payees can be sorted by name")
    @Test(description = "TC3")
    public void TC4() {
        List<String> descending, ascending, payeesNames;

        home.navigateTo("Payees");
        payeesNames = payee.getPayeesNames();
        ascending = new ArrayList<>(payeesNames);
        descending = new ArrayList<>(payeesNames);

        Collections.sort(ascending);
        Collections.reverse(descending);
        Assert.assertEquals(payeesNames, ascending);

        payee.clickNameHeader();
        Assert.assertEquals(payee.getPayeesNames(), descending);
    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.get().close();
        }
    }


}


