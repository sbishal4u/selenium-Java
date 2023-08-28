package pageObject.FHD;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Wait_Helper_Page;

public class LoginPage extends BasePage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    public By LoginForm = By.xpath("//form[@name='loginForm']");
    public By TextLogin = By.xpath("//h1[normalize-space()='LOGIN TO YOUR ACCOUNT']");
    @FindBy(xpath = "//h1[normalize-space()='LOGIN TO YOUR ACCOUNT']")
    public WebElement TextLoginXpath;
    public By EmailIDXpath = By.xpath("//input[@placeholder='Email']");
    public By PasswordXpath = By.xpath("//input[@placeholder='Password']");
    public By LoginButton = By.xpath("//button[@aria-label='LOGIN']");

    By PaymentOverview = By.xpath("//div[normalize-space()='Payment Overview']");
    By PaymentOverview1 = By.xpath("//div[@class='total-value ng-star-inserted']");

    /*
        Log out from application
     */

    By LogOut = By.xpath("//span[@class='mat-button-wrapper']//div[@fxlayout='row']");
    By TextLogOut = By.xpath("//span[normalize-space()='Sign Out']");


    public void Login_to_application(String username, String password){
        type(EmailIDXpath,username);
        type(PasswordXpath,password);
        click(LoginButton);
        WaitForPresenceOfElement(PaymentOverview,10);
        WaitForPresenceOfElement(PaymentOverview1,10);
        WaitForPresenceOfElement(LogOut,10);
    }

    public void Log_out_from_application(){
        click(LogOut);
        WaitForPresenceOfElement(TextLogOut,10);
        click(TextLogOut);
        WaitForPresenceOfElement(LoginButton,10);
    }

}
