package helper;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PolicySearch extends BasePage {
    WebDriver driver;
    public PolicySearch(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    By PolicySearchTab = By.xpath("//span[normalize-space()='Policy Search']");
    By PolicySearchTextBox = By.xpath("//input[@id='searchInput']");

    By TextActive = By.xpath("//div[contains(text(),'Active')]");

    By ThreeDots = By.xpath("//mat-icon[normalize-space()='more_horiz']");

    By BeginClaim = By.xpath("//span[normalize-space()='Begin Claim']");

    By GenerateQuote = By.xpath("//span[normalize-space()='GENERATE QUOTE']");

    public void SearchThePolicy(String policyNumber){
        click(PolicySearchTab);
        WaitForPresenceOfElement(PolicySearchTextBox,10);
        type(PolicySearchTextBox,policyNumber);
        WaitForPresenceOfElement(TextActive,10);
    }

    public void Go_to_Action() throws InterruptedException {
        click(ThreeDots);
        WaitForPresenceOfElement(BeginClaim,10);
        click(BeginClaim);
        Thread.sleep(10000);
    }






}
