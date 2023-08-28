package testCase.FHD;

import BaseClass.TestBase;
import helper.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.FHD.LoginPage;
import utility.UploadDocument;

import java.io.IOException;

public class LoginPageTest extends TestBase {
    @Parameters({"username","password"})
    @Test(priority = 1)
    public void Login_to_application_test(String username,String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login_to_application(username +".com",password);
    }

    @Parameters({"policySearch"})
    @Test(priority = 2)
    public void PolicySearchTab(String policySearch) throws InterruptedException {
        PolicySearch policysearch = new PolicySearch(driver);
        policysearch.SearchThePolicy(policySearch);
        policysearch.Go_to_Action();
    }

    @Parameters({"dob_value1"})
    @Test(priority = 3)
    public void GenerateQuote(String dob_value1) throws IOException, ParseException, InterruptedException {
        GenerateQuote generateQuote = new GenerateQuote(driver);
        generateQuote.Enter_Cause_of_Death();
        generateQuote.Enter_Date_of_Death();
        generateQuote.Enter_Date_of_Birth(dob_value1);
        generateQuote.Enter_Contact_Number();
        generateQuote.Click_Start_Claim();
    }

    @Test(priority = 4)
    public void Go_to_Claim_Information() throws IOException, ParseException, InterruptedException {
        ClaimInformation claimInformation = new ClaimInformation(driver);
        claimInformation.Enter_Funeral_home_representative();
        Signature signature = new Signature(driver);
        signature.Signature();
        signature.Go_CustomerSupport();
        signature.Go_to_Notification();
    }

    @Test(priority = 5)
    public void go_Add_Beneficiary() throws IOException, ParseException, InterruptedException {
        BeneficiaryTab beneficiaryTab = new BeneficiaryTab(driver);
        beneficiaryTab.go_Beneficiary_tab();
        beneficiaryTab.Create_Note();
        beneficiaryTab.CloseIcon();
    }

    @Test(priority = 5)
    public void go_Add_view_check_list() throws IOException, ParseException, InterruptedException {
        ViewCheckList viewCheckList = new ViewCheckList(driver);
        viewCheckList.Go_to_checkList();
    }

    @Test(priority = 6)
    public void upload_document() throws InterruptedException {
        UploadDocument document = new UploadDocument();
        document.Upload_Test_Document();
        document.Click_match_this();
        document.ViewButton();
    }
    @Test(priority = 7)
    public void Log_out_from_application(){
        new LoginPage(driver).Log_out_from_application();
    }

}
