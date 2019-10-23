package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.pages.LandingPage;
import com.qa.utilities.TestUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class UserListTableTest extends TestBase {
    public UserListTableTest() {
        super();
    }

    TestUtil testUtil;
    LandingPage landingPage;

    @BeforeMethod
    public void setUp() {
        initialisation();
        testUtil = new TestUtil();
        landingPage = new LandingPage();
    }

    @Test
    public void verify_user_list_table_is_visible() throws IOException {
        boolean tableIsPresent = landingPage.validateUserListTable();
        Assert.assertTrue(tableIsPresent);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        try {
            String description = result.getName();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir");
            FileHandler.copy(scrFile, new File(currentDir + "/TestProofs/" + description + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

}





