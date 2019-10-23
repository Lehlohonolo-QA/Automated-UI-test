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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.qa.pages.LandingPage.checkIfUserExistsAlready;
import static com.qa.pages.LandingPage.checkIfUserIsAdded;

public class AddUsersUsingExternalDataTest extends TestBase {
    public AddUsersUsingExternalDataTest(){
        super();
    }

    LandingPage landingPage;
    String sheetName="User details";

    @BeforeMethod
    public void setUp() {
        initialisation();
        landingPage = new LandingPage();
    }

    @DataProvider
    public Object[][] getTestData(){
        Object data [][] = TestUtil.getTestData(sheetName);
        return data;
    }


    @Test(dataProvider = "getTestData")
    public void verify_external_user_details_are_unique(String firstName,String lastName, String userName, String password, String customer, String role, String email, String cellPhone) {
        checkIfUserExistsAlready(userName);
        landingPage.clickAdd();
        landingPage.enterUserDetails(firstName,lastName,userName,password,email,cellPhone);
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
