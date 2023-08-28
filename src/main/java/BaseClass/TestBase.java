package BaseClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObject.FHD.LoginPage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Locale;

public class TestBase extends BasePage{
    public static WebDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    protected String path=System.getProperty("user.dir")+"\\ExtentReportGenerate\\index.html";


    @Parameters({"browser","url"})
    @BeforeTest
    public void Setup(ITestContext context, @Optional("chrome") String browserName, String Url) throws IOException, ParseException, InterruptedException {
        LaunchBrowser(browserName,Url);
        Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
        String device = capabilities.getBrowserName() + "" + capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
        String author = context.getCurrentXmlTest().getParameter("author");

        extentTest=extentReports.createTest(context.getName());
        extentTest.assignAuthor(author);
        extentTest.assignDevice(device);
    }



    @BeforeSuite
    public void initialiseExtentReports()
    {
        ExtentSparkReporter sparkReporter_all=new ExtentSparkReporter(path);
        sparkReporter_all.config().setReportName("UAT Automation Result");
        sparkReporter_all.config().setDocumentTitle("Test Result");

        extentReports=new ExtentReports();
        extentReports.attachReporter(sparkReporter_all );
        extentReports.setSystemInfo("Version","2.5.6086");
        extentReports.setSystemInfo("QA Engineer","Bishal Shah");
        extentReports.setSystemInfo("Platform",System.getProperty("os.name"));
    }

    @AfterSuite
    public void generateReport() throws IOException {
        extentReports.flush();
        Desktop.getDesktop().browse(new File(path).toURI());
    }

   //@AfterTest
    public void Quit() throws IOException, InterruptedException {
        PageDriver.getCurrentDriver().quit();
    }

    public void LaunchBrowser(String browser,String Url) throws IOException, ParseException, InterruptedException {
        String environment=System.getProperty("os.name");
        System.out.println("Automation Script run on ......"+environment);

        switch (browser.toLowerCase(Locale.ROOT))
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(false);
                options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to Windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.addArguments("--disable-in-process-stack-traces");
                options.addArguments("--disable-logging");
                options.addArguments("--log-level=3");
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            case "safari":
                driver=new SafariDriver();
                break;
            default:
                driver=null;
                break;
        }
        PageDriver.getInstance().setDriver(driver);
        driver.manage().window().maximize();
        driver.get(Url);
        WaitForPresenceOfElement(new LoginPage(driver).TextLogin,10);
        WaitForPresenceOfElement(new LoginPage(driver).LoginButton,10);
        WaitForPresenceOfElement(new LoginPage(driver).LoginForm,10);
    }


    public static String captureScreenshot(String filename){
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File sourcefile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile=new File("./Screenshots/"+filename);
        try {
            FileUtils.copyFile(sourcefile,destFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Screenshot saves successfully");
        return destFile.getAbsolutePath();
    }

    @AfterMethod
    public void CheckStatus(Method m, ITestResult result){
        if (result.getStatus()==ITestResult.FAILURE){
            String screenshotPath=null;
            screenshotPath=captureScreenshot(result.getTestContext().getName()+""+result.getMethod().getMethodName()+".jpg");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus()==ITestResult.SUCCESS) {
            extentTest.pass(result.getMethod().getMethodName() + " is Passed");
        }
        extentTest.assignCategory(m.getAnnotation(Test.class).groups());
    }
}
