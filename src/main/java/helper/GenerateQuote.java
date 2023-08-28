package helper;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utility.ReadJSonData;

import java.io.File;
import java.io.IOException;

public class GenerateQuote extends BasePage {

    String path=System.getProperty("user.dir")+ File.separator +"TestData"+File.separator +"hlc.json";
    WebDriver driver;
    public GenerateQuote(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    By Cause_of_Death = By.xpath("//span[contains(text(),'[None Selected]')]");
    By Panel = By.xpath("//div[@id='causeOfDeath-panel']");

    By TextNatural = By.xpath("//span[normalize-space()='NATURAL']");

    By Date_of_Death = By.xpath("//input[@id='v_datDeath']");

    By Date_of_Birth = By.xpath("//input[@id='userProvidedDOB']");

    By Contact_Number = By.xpath("//input[@id='contract_number']");

    By GenerateQuoteButton = By.xpath("//span[normalize-space()='GENERATE QUOTE']");
    By StartClaim = By.xpath("//span[contains(text(),'START CLAIM')]");
    By CancelButton = By.xpath("//span[contains(text(),'CANCEL')]");


    public void Enter_Cause_of_Death(){
        WaitForPresenceOfElement(GenerateQuoteButton,10);
        click(Cause_of_Death);
        WaitForPresenceOfElement(TextNatural,10);
        for (WebElement el : getEls(Panel)){
            el.click();
        }
    }

    public void Enter_Date_of_Death() throws IOException, ParseException {
        String data = new ReadJSonData().Read_the_value_from_json(path,"Date_of_death");
        type(Date_of_Death,data);
    }

    public void Enter_Date_of_Birth(String dob) throws IOException, ParseException {
        type(Date_of_Birth,dob);
    }
    public void Enter_Contact_Number() throws IOException, ParseException, InterruptedException {
        String data = new ReadJSonData().Read_the_value_from_json(path,"PhoneNumber");
        type(Contact_Number,data);
        click(GenerateQuoteButton);
        WaitForPresenceOfElement(StartClaim,10);
        WaitForPresenceOfElement(CancelButton,10);
    }

    public void Click_Start_Claim(){
        click(StartClaim);
        WaitForPresenceOfElement(new ClaimInformation(driver).HomeRepresentative,10);
        WaitForPresenceOfElement(new ClaimInformation(driver).HomeArrangement,10);
        WaitForPresenceOfElement(new ClaimInformation(driver).EmailHomeArrangement,10);
    }

}
