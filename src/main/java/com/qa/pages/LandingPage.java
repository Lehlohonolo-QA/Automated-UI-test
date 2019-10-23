package com.qa.pages;

import com.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LandingPage extends TestBase {

    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    String winHandleBefore = driver.getWindowHandle();

    @FindBy(xpath = "//table[@table-title='Smart Table example']")
    WebElement table;

    @FindBy(xpath = "//button[@class='btn btn-link pull-right' and @type='add']")
    WebElement addButton;

    @FindBy(name = "FirstName")
    WebElement firstName;

    @FindBy(name = "LastName")
    WebElement lastName;

    @FindBy(name = "UserName")
    WebElement userName;

    @FindBy(name = "Password")
    WebElement password;

    @FindBy(xpath = "//input[@name='optionsRadios' and @value='15' and @type='radio']")
    WebElement companyAAA;

    @FindBy(xpath = "//input[@name='optionsRadios' and @value='16' and @type='radio']")
    WebElement companyBBB;

    @FindBy(name = "RoleId")
    WebElement role;

    @FindBy(name = "Email")
    WebElement email;

    @FindBy(name = "Mobilephone")
    WebElement cellPhone;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement save;

    @FindBy(xpath = "//button[contains(text(),'Close')]")
    WebElement close;


    public void clickAdd() {
        addButton.click();
        ngWebDriver.waitForAngularRequestsToFinish();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public boolean validateUserListTable() {
        return table.isDisplayed();
    }

    public void setCustomer(String customer) {
        if (customer.equals("Company AAA")) {
            companyAAA.click();
        } else if (customer.equals("Company BBB")) {
            companyBBB.click();
        } else
            System.out.println("Please select a customer");
    }

    public void selectRole(String value) {
        Select option = new Select(role);
        option.selectByVisibleText(value);
    }

    public void clickSave() {
        save.click();
        driver.switchTo().window(winHandleBefore);
    }

    public void clickClose() {
        close.click();
        driver.switchTo().window(winHandleBefore);
    }


    public void enterUserDetails(String fName, String lName, String uName, String pWord, String eMail, String cell) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        userName.sendKeys(uName);
        password.sendKeys(pWord);
        email.sendKeys(eMail);
        cellPhone.sendKeys(cell);
    }

    public static void checkIfUserExistsAlready(String userName) {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='smart-table-data-row ng-scope']"));
        for (int i = 1; i < rows.size(); i++) {
            String rowElement = rows.get(i).findElement(By.xpath("/html/body/table/tbody/tr[" + i + "]/td[3]")).getText();

            if (rowElement.matches(userName)) {

                System.out.println("User name already exists");
                driver.findElement(By.xpath("/html/body/table/tbody/tr[" + i + "]/td[11]/button")).click();
            }
        }
    }


    public static boolean checkIfUserIsAdded(String userName) {
        boolean isAdded= false;
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='smart-table-data-row ng-scope']"));
        for (int i = 1; i < rows.size(); i++) {
            String rowElement = rows.get(i).findElement(By.xpath("/html/body/table/tbody/tr[" + i + "]/td[3]")).getText();
            System.out.println(rowElement);
            if (rowElement.matches(userName)) {
                isAdded= true;
                System.out.println("/******* "+"- User has been added to the list -" +"*******/");
                System.out.println(rowElement);
            }

        }
        return isAdded;
    }

    }
