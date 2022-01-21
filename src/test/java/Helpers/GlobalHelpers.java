package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Driver.Setup.driver;

public class GlobalHelpers {


    public GlobalHelpers() {
    }

    public static void clickObj(String locator) {
        WebElement obj = getObj(locator);
        obj.click();
        try {
            String message = driver.get().switchTo().alert().getText();
            driver.get().switchTo().alert().accept();
        } catch (Exception e) {

        }
        System.out.println(locator + " clicked.");
    }

    public static void clickObj_FailIfWithAlert(String locator) {
        getObj(locator).click();
        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
        try {
            Thread.sleep(3000);
            String message = driver.get().switchTo().alert().getText();
            if (message != "") {
                System.out.println(message);
                assert false;
            }
        } catch (Exception e) {
            System.out.println("PASS");
        }
    }

    public static WebElement getObject(String locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 20);
        WebElement obj = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            obj = driver.get().findElement(By.xpath(locator));
            obj.isDisplayed();
            obj.isEnabled();
        } catch (Exception e) {
        }
        return obj;
    }

    public static WebElement getObjectQuick(String locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 3);
        WebElement obj = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            obj = driver.get().findElement(By.xpath(locator));
            obj.isDisplayed();
            obj.isEnabled();
        } catch (Exception e) {
        }
        return obj;
    }

    public static void hoverObject(String locator) {
        Actions actions = new Actions(driver.get());
        actions.moveToElement(getObj(locator)).build().perform();
        System.out.println(locator + " hovered.");
    }

    public static WebElement getObj(String locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
        WebElement obj = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            obj = driver.get().findElement(By.xpath(locator));
            obj.isDisplayed();
            obj.isEnabled();
        } catch (Exception e) {
            System.out.println("Locator: " + locator + " not found.");
            assert false;
        }

        System.out.println(locator + " is displayed and enabled.");
        return obj;
    }

    public static void waitForObjToDisappear(String locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
        WebElement obj = null;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
        } catch (Exception e) {
            System.out.println("Locator: " + locator + " still found.");
            assert false;
        }


        System.out.println(locator + " is displayed and enabled.");
    }

    public static WebElement getObj_Negative(String locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 10);
        WebElement obj = null;
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
            //obj = Driver.findElement(By.xpath(locator));
            obj.isDisplayed();
            obj.isEnabled();
            System.out.println("Locator: " + locator + " not found.");
            assert false;
        } catch (Exception e) {
        }


        return obj;
    }

    public static void setValObj(String locator, String value) {
        try {
            getObj(locator).clear();
            getObj(locator).sendKeys(value);

        } catch (ElementNotInteractableException e) {
            getObj(locator).click();
            getObj(locator).sendKeys(value);

        }
    }

    public static boolean validateObj(String locator) {
        return getObject(locator).isDisplayed();
    }

    public static void checkIfLoading() {
        String locator = "//div[@class='loading-mask']";
        if (getObjectQuick(locator) == null) {
            return;
        }
        while (validateObj(locator)) {
        }
    }

    public static List<WebElement> getObjects(String locator) {
        WebDriverWait wait = new WebDriverWait(driver.get(), 20);
        List<WebElement> obj = null;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            obj = driver.get().findElements(By.xpath(locator));
        } catch (Exception e) {
        }
        return obj;

    }

}
