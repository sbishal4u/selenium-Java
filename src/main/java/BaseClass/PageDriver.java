package BaseClass;

import org.openqa.selenium.WebDriver;
public class PageDriver {
    private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static PageDriver instance=null;
    private PageDriver(){

    }
    public static PageDriver getInstance(){
        if (instance == null){
            instance = new PageDriver();
        }
        return instance;
    }
    public  WebDriver getDriver(){
        return webDriverThreadLocal.get();
    }
    public void setDriver(WebDriver driver){
        webDriverThreadLocal.set(driver);
    }
    public static WebDriver getCurrentDriver(){
        return getInstance().getDriver();
    }
}
