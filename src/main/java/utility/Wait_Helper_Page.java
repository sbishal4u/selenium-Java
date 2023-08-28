package utility;

import BaseClass.PageDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait_Helper_Page {
    WebDriver driver;
    WebDriverWait webDriverWait;


    public Wait_Helper_Page(WebDriver driver, int timeout){
        driver = PageDriver.getCurrentDriver();
        webDriverWait=new WebDriverWait(driver, Duration.ofMinutes(timeout));
    }

    public void Go_to_Wait_for_Element(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

        boolean status=element.isDisplayed();
        if (status){
            System.out.println("Element is Displayed:-"+element.getText());
        }
        else {
            System.out.println("Element is not displayed: "+element.getText());
        }

    }
}
