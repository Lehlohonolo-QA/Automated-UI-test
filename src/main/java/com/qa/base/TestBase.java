package com.qa.base;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.qa.utilities.TestUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static NgWebDriver ngWebDriver;
    public static Properties prop;

    public TestBase() {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/main/java/com/qa/configuration/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialisation() {

        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/firefox/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
        driver.get(prop.getProperty("url"));
    }



}


