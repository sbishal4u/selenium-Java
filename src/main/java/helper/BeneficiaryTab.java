package helper;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.ReadJSonData;

import java.io.File;
import java.io.IOException;

public class BeneficiaryTab extends BasePage {
    String path=System.getProperty("user.dir")+ File.separator +"TestData"+File.separator +"hlc.json";
    WebDriver driver;
    public BeneficiaryTab(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    public By Beneficiary = By.xpath("//div[normalize-space()='BENEFICIARIES']");
    By Estate = By.xpath("//mat-row[@role='row']/mat-cell[1]/a");
    By Note = By.xpath("//div[contains(text(),'Notes')]");
    By AddNewNote = By.xpath("//button[@aria-label='Add Note']");
    By SubmitButton = By.xpath("//button[@aria-label='Close']//span[@class='mat-button-wrapper']");

    By Notes = By.xpath("//div[@class='fr-element fr-view']");

    /*
        Close Icon
     */

    By CloseIcon = By.xpath("//button[@aria-label='Close dialog']/span[1]");
    By CloseIcon1 = By.xpath("//*[@id='forms']/mat-toolbar/button/span[1]/mat-icon");

    public void go_Beneficiary_tab(){
        WaitForPresenceOfElement(Beneficiary,10);
        click(Beneficiary);
        WaitForPresenceOfElement(Estate,10);
    }

    public void Create_Note() throws InterruptedException, IOException, ParseException {
        click(Estate);
        WaitForPresenceOfElement(Note,10);
        click(Note);
        WaitForPresenceOfElement(AddNewNote,10);
        click(AddNewNote);
        Thread.sleep(5000);
        String TestData = new ReadJSonData().Read_the_value_from_json(path,"Chat");
        click(Notes);
        Thread.sleep(3000);
        type(Notes,TestData);
        click(SubmitButton);
        Thread.sleep(5000);
    }

    public void CloseIcon() throws InterruptedException {
        WaitForPresenceOfElement(CloseIcon,10);
        click(CloseIcon);
        WaitForPresenceOfElement(CloseIcon1,10);
        click(CloseIcon1);

    }






}
