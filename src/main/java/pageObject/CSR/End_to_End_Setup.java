package pageObject.CSR;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class End_to_End_Setup extends BasePage {
    WebDriver driver;
    public End_to_End_Setup(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

}
