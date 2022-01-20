package Driver;

import TestData.Data;
import io.github.bonigarcia.wdm.OperatingSystem;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class Setup {

    public static WebDriver driver;
    private static Properties prop;

    public static void getDriver(String Browser) throws IOException {
        switch (Browser.toUpperCase(Locale.ROOT)) {
            case "CHROME": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
                driver = new ChromeDriver(options);
                break;
            }
            case "BRAVE": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
                driver = new ChromeDriver(options);
                break;
            }
            case "EDGE": {
                WebDriverManager.edgedriver().operatingSystem(OperatingSystem.WIN).setup();
                driver = new EdgeDriver();
                break;
            }
            case "FIREFOX": {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
                driver = new FirefoxDriver(options);
                break;
            }
        }
        driver.get(getUrl());
    }

    public static String getProperty(String property) throws IOException {
        FileInputStream fis = null;
        prop = null;
        try {
            fis = new FileInputStream("src/test/resources/config");
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop.getProperty(property);
    }

    public static String getBrowser() throws IOException {
        return getProperty("browser");
    }
    public static String getUrl() throws IOException {
        return getProperty("URL");
    }
}
