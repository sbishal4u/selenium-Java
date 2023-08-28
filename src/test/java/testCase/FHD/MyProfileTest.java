package testCase.FHD;

import BaseClass.TestBase;
import org.testng.annotations.Test;
import pageObject.FHD.LoginPage;
import pageObject.FHD.MyProfile;

public class MyProfileTest extends TestBase {

    @Test
    public void Enter_Detail_My_Profile() throws InterruptedException {
        MyProfile myProfile = new MyProfile(driver);
        myProfile.Go_to_myProfile();
        myProfile.Go_to_My_Profile_tab();
        new LoginPage(driver).Log_out_from_application();
        Thread.sleep(5000);
    }
}
