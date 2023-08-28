package helper;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ViewCheckList extends BasePage {
    WebDriver driver;

    public ViewCheckList(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    By ViewCHeckList = By.xpath("//div[normalize-space()='VIEW CHECKLIST']");
    //By ViewCHeckList = By.xpath("//div[normalize-space()='VIEW CHECKLIST']");


    public void Go_to_checkList(){
        WaitForPresenceOfElement(ViewCHeckList,10);
        click(ViewCHeckList);
        WaitForPresenceOfElement(new BeneficiaryTab(driver).Beneficiary,10);
        WaitForPresenceOfElement(new Signature(driver).CustomerSupport,10);
    }
}
