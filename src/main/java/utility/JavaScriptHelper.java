package utility;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class JavaScriptHelper extends BasePage {
    WebDriver driver;
    public JavaScriptHelper(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }
    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        System.out.println(script);
        return exe.executeScript(script);
    }
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        System.out.println(script);
        return exe.executeScript(script, args);
    }

    public void scroll_To_Element(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
        System.out.println(element);
    }
    public void scrollToElemetAndClick(WebElement element) {
        scroll_To_Element(element);
        element.click();
        System.out.println(element);
    }

    public void scroll_to_View(By by_Locator) {
        executeScript("arguments[0].scrollIntoView()", by_Locator);
        //System.out.println(element);
    }

    public void scroll_to_View_And_Click(By by_Locator) {
        scroll_to_View(by_Locator);
        click(by_Locator);
        //System.out.println(element);
    }

    public void Scroll(){
        executeScript("window.scrollBy(0,350)","");
    }


}
