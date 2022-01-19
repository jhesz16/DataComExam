import Helpers.GlobalHelpers;
import TestData.Data;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test1 {

    private static ChromeDriver driver;
    public static GlobalHelpers helpers;
    public static Home home;
    public static Payee payee;
    public static Payments payments;

    @BeforeMethod
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
        driver = new ChromeDriver(options);
        driver.get(Data.URL);
        helpers = new GlobalHelpers(driver);
        home = new Home();
        payee = new Payee();
        payments = new Payments();
    }

    @Description("TC1: Verify you can navigate to Payees page using the navigation menu")
    @Test
    public static void TC1() {
        home.navigateTo("Payees");
        payee.verifyPayeesPage();
    }

    @Description("TC2: Verify you can add new payee in the Payees page")
    @Test
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
        payee.addBlankPayee();
    }

    @Description("TC4: Verify that payees can be sorted by name")
    @Test
    public void TC4() {
        String payeeName = "TYCO CYLINDER TESTING";
        home.navigateTo("Payees");
        List<String> payeesNames = payee.getPayeesNames();
        List ascending = new ArrayList(payeesNames);
        List descending = new ArrayList(payeesNames);
        Collections.sort(ascending);
        Collections.reverse(descending);
        Assert.assertEquals(payeesNames, ascending);

        payee.clickNameHeader();
        Assert.assertEquals(payee.getPayeesNames(), descending);

    }

    @Description("TC5: Navigate to Payments page")
    @Test
    public void TC5() {
        double transferAmount = 500.00;
        String fromAccountName = "Everyday";
        String toAccountName = "Bills ";

        double fromOldVal = home.getAccountBalance(fromAccountName);
        double toOldVal = home.getAccountBalance(toAccountName);

        home.navigateTo("Payments");
        payments.doTransfer(fromAccountName, toAccountName, transferAmount);

        double fromNewVal = home.getAccountBalance(fromAccountName);
        double toNewVal = home.getAccountBalance(toAccountName);
        Assert.assertEquals(fromOldVal - transferAmount, fromNewVal);
        Assert.assertEquals(toOldVal + transferAmount, toNewVal);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}


