package helper;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.RandomNames;

public class Signature extends BasePage {
    WebDriver driver;
    public Signature(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    By SignButton = By.xpath("//span[contains(text(),'Sign')]");
    By SubmitButton = By.xpath("//span[contains(text(),'Submit')]");

    By Canvas = By.xpath("//canvas[starts-with(@class,'ng-tns')]");
    By CheckBox = By.xpath("//span[@class='mat-checkbox-inner-container']");
    By AcceptButton = By.xpath("//span[normalize-space()='ACCEPT']");

    By AttachedDocument = By.xpath("//button[@aria-label='Document attached']");
    By SubmitClaim = By.xpath("//span[normalize-space()='SUBMIT CLAIM']");


    public By ThankYou = By.xpath("//div[starts-with(@class,'message-box')]");
    /*
        Customer Support
     */
    public By CustomerSupport = By.xpath("//div[normalize-space()='CUSTOMER SUPPORT']");
    public By TypeMessage = By.xpath("//textarea[@placeholder='Type your message']");
    public By SendMessage = By.xpath("//mat-icon[normalize-space()='send']");
    public By WaitMessage = By.xpath("//div[starts-with(@class,'bubble message')]");
    public By BackButton = By.xpath("//div[normalize-space()='BACK']");

    /*
        Notification
     */

    By Notification = By.xpath("//mat-icon[normalize-space()='notifications']");
    By Notification1 = By.xpath("//span[normalize-space()='Notifications (Last 7 Days)']");
    By NotificationCloseIcon = By.xpath("//mat-icon[@class='mat-icon notranslate material-icons mat-icon-no-color']");


    public void Signature() throws InterruptedException {
        Thread.sleep(7000);
        click(SignButton);
        WaitForPresenceOfElement(SubmitButton,10);
        WaitForPresenceOfElement(Canvas,10);
        Actions builder = new Actions(PageDriver.getCurrentDriver());
        WebElement canvasElement = PageDriver.getCurrentDriver().findElement(By.xpath("//canvas[starts-with(@class,'ng-tns')]"));
        Action signature= builder.contextClick(canvasElement)
                .clickAndHold()
                .moveToElement(canvasElement,20,-50)
                .moveByOffset(50, 50)
                .moveByOffset(80,-50)
                .moveByOffset(100,50)
                .release(canvasElement)
                .build();
        signature.perform();
        click(SubmitButton);
        Thread.sleep(5000);
        click(CheckBox);
        Thread.sleep(5000);
        click(AcceptButton);
        WaitForPresenceOfElement(AttachedDocument,10);
        WaitForPresenceOfElement(SubmitClaim,10);
        click(SubmitClaim);
        Thread.sleep(20000);
        WaitForPresenceOfElement(ThankYou,10);
    }

    public void Go_CustomerSupport() throws InterruptedException {
        click(CustomerSupport);
        WaitForPresenceOfElement(TypeMessage,10);
        type(TypeMessage,new RandomNames().GenerateRandomName());
        click(SendMessage);
        WaitForPresenceOfElement(WaitMessage,10);
        click(BackButton);
        WaitForPresenceOfElement(ThankYou,10);
        WaitForPresenceOfElement(CustomerSupport,10);
    }

    public void Go_to_Notification(){
        click(Notification);
        WaitForPresenceOfElement(Notification1,10);
        WaitForPresenceOfElement(NotificationCloseIcon,10);
        click(NotificationCloseIcon);
        WaitForPresenceOfElement(NotificationCloseIcon,10);
        WaitForPresenceOfElement(new BeneficiaryTab(driver).Beneficiary,10);
    }
}