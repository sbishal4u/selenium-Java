package utility;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import helper.BeneficiaryTab;
import helper.Signature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class UploadDocument extends BasePage {
    WebDriver driver;
    String path=System.getProperty("user.dir")+ File.separator +"TestData"+File.separator +"TestData.pdf";

    public UploadDocument(){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    By FileUpload = By.xpath("//input[@type='file']");
    By SaveButton = By.xpath("//span[contains(text(),'Save')]");

    By MatchThis = By.xpath("(//span[normalize-space()='Match This'])");


    /*
        View Tab
     */

    By ViewButton = By.xpath("(//span[contains(text(),'VIEW')])[1]");
    By ViewClose = By.xpath("//button[@aria-label='Close dialog']/span[1]");
    By CrossIcon = By.xpath("//*[@id='doctracks']/div/div[3]/button[2]/span[1]/mat-icon");

    By SaveButton1 = By.xpath("//div[@class='nav']/div/button/span[1]/mat-icon");

    public void Upload_Test_Document(){
        DocumentUpload(FileUpload);
        WaitForPresenceOfElement(SaveButton,10);
        WaitForPresenceOfElement(MatchThis,10);
    }

    public void Click_match_this() throws InterruptedException {
        int count = getEls(MatchThis).size();
        System.out.println("Total Number of Match This :- " +count);
        for (int i = 1; i <= count; i++){
            driver.findElement(By.xpath("//div[@fxlayoutalign='center']/div["+i+"]/div/div[3]/button")).click();
        }

        click(SaveButton);
        Thread.sleep(10000);
        WaitForPresenceOfElement(new Signature(driver).CustomerSupport,10);
        WaitForPresenceOfElement(new BeneficiaryTab(driver).Beneficiary,10);
    }

    public void ViewButton() throws InterruptedException {
        WaitForPresenceOfElement(ViewButton,10);
        click(ViewButton);
        Thread.sleep(20000);
        WaitForPresenceOfElement(ViewClose,10);
        Thread.sleep(5000);
        ClickElementIsDisplayed(CrossIcon);
        Thread.sleep(5000);
        ClickElementIsDisplayed(SaveButton1);
        //click(SaveButton1);
        Thread.sleep(10000);
    }

}
