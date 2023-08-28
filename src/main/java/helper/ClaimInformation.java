package helper;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.JavaScriptHelper;
import utility.RandomNames;
import utility.ReadJSonData;

import java.io.File;
import java.io.IOException;

public class ClaimInformation extends BasePage {
    WebDriver driver;
    String TestEmail = "bishal@benekiva.com";
    public ClaimInformation(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }
    String path=System.getProperty("user.dir")+ File.separator +"TestData"+File.separator +"hlc.json";

    public By HomeRepresentative = By.xpath("//input[@id='Funeral_Home_Rep']");
    public By HomeArrangement = By.xpath("//input[@id='name_of_individual_making_funeral_home_arrangements']");
    public By EmailHomeArrangement = By.xpath("//input[@id='email_of_individual_making_funeral_home_arrangements']");
    public By PhoneHomeArrangement = By.xpath("//input[@id='phone_number_of_individual_making_funeral_home_arrangements']");

    @FindBy(xpath = "//span[normalize-space()='CANCEL']")
    WebElement CancelButton;
    public void Enter_Funeral_home_representative() throws IOException, ParseException, InterruptedException {
        String Data = new RandomNames().GenerateRandomName();
        String Phone = new ReadJSonData().Read_the_value_from_json(path,"PhoneNumber");
        WaitForPresenceOfElement(HomeRepresentative,10);
        WaitForPresenceOfElement(HomeArrangement,10);
        type(HomeRepresentative,Data);
        Thread.sleep(3000);
        type(HomeArrangement,Data);
        Thread.sleep(3000);
        type(EmailHomeArrangement,TestEmail);
        Thread.sleep(3000);
        type(PhoneHomeArrangement,Phone);
        Thread.sleep(3000);
    }

}
