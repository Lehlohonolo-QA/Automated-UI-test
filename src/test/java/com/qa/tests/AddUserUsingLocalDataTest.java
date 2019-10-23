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

import static com.qa.pages.LandingPage.checkIfUserExistsAlready;
import static com.qa.pages.LandingPage.checkIfUserIsAdded;


public class AddUserUsingLocalDataTest extends TestBase {

    public AddUserUsingLocalDataTest() {
        super();
    }

    LandingPage landingPage;
    TestUtil testUtil;

    String firstName = "FName1";
    String lastName = "LName1";
    String userName = "User1";
    String password = "Pass1";
    String customer = "Company AAA";
    String role = "Admin";
    String email = "asmin@mail.com";
    String cell = "08255";

    @BeforeMethod
    public void setUp()  {
        initialisation();
        testUtil= new TestUtil();
        landingPage = new LandingPage();

    }

    @Test
    public void verify_user1_details_are_unique() throws IOException {
        checkIfUserExistsAlready(userName);
        landingPage.clickAdd();
        landingPage.enterUserDetails(firstName, lastName, userName, password, email, cell);
        landingPage.selectRole(role);
        landingPage.setCustomer(customer);
        landingPage.clickSave();
        boolean isUserAdded = checkIfUserIsAdded(userName);
        Assert.assertTrue(isUserAdded);
    }


    @AfterMethod
    public void tearDown(ITestResult result) {

        try {
            String description= result.getName();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir");
            FileHandler.copy(scrFile, new File(currentDir + "/TestProofs/" + description + ".png"));
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

}
