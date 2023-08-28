package pageObject.FHD;

import BaseClass.BasePage;
import BaseClass.PageDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utility.RandomNames;

public class MyProfile extends BasePage {
    WebDriver driver;
    public MyProfile(WebDriver driver){
        driver = PageDriver.getCurrentDriver();
        PageFactory.initElements(driver,this);
    }

    By MyProfileArrow = By.xpath("//span[@class='mat-button-wrapper']//div[@fxlayout='row']");
    By TextMyProfile = By.xpath("//span[normalize-space()='My Profile']");
    By MyProfileInfoTab = By.xpath("//div[contains(text(),'My Profile Info')]");

    /*
        My Profile Info
     */

    By FirstName = By.xpath("//input[@data-placeholder='First Name']");
    By LastName = By.xpath("//input[@data-placeholder='Last Name']");

    By Title = By.xpath("//input[@data-placeholder='Title']");

    By SaveButton = By.xpath("//mat-icon[normalize-space()='check_circle']");

    By TextProfile = By.xpath("//p[normalize-space()='Your profile has been updated successfully.']");

    By CloseButton = By.ByClassName.xpath("//span[normalize-space()='CLOSE']");

    public void Go_to_myProfile(){
        click(MyProfileArrow);
        WaitForPresenceOfElement(TextMyProfile,10);
        click(TextMyProfile);
        WaitForPresenceOfElement(MyProfileInfoTab,10);
    }

    public void Go_to_My_Profile_tab(){
        String f_name = new RandomNames().GenerateFirstName();
        String l_name = new RandomNames().GenerateLastName();

        clearTextBox(FirstName);
        type(FirstName,f_name);

        clearTextBox(LastName);
        type(LastName,l_name);

        clearTextBox(Title);
        type(Title, "Mr");

        click(SaveButton);
        WaitForPresenceOfElement(TextProfile,10);
        click(CloseButton);
    }

}
