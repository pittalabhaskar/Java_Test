
import  org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.lang.*;

public class BrowserFactory {
    private static   Map <String, WebDriver> Drivers = new HashMap <>();
    private static WebDriver driver;
    private static String currentPath = System.getProperty("user.dir");

    public WebDriver getDriver()
    {
        return  driver;
    }
    public static void setDriver(WebDriver value)
    {
        driver = value;
    }

    /***************************************************/
    public static WebDriver initBrowser(String strBrowserName)
    {
        //Get the Libraries Path
        String librariespath = currentPath + "/src/test/java/Libraries/" ;

        //Launch the Browser based on the Browser name
        switch (strBrowserName)
        {
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", librariespath+ "geckodriver.exe");
                driver = new FirefoxDriver();
                Drivers.put("Firefox", driver);
                break;

            case "IE":
                if (driver == null)
                {
                    InternetExplorerOptions options = new InternetExplorerOptions();
                    options.enableNativeEvents();
                    options.introduceFlakinessByIgnoringSecurityDomains();
                    System.setProperty("webdriver.ie.driver", librariespath+ "IEDriverServer.exe");
                    driver = new InternetExplorerDriver(options);
                    Drivers.put("IE", driver);
                }
                break;
            case "Chrome":
                if (driver == null) {
                    ChromeOptions options = new ChromeOptions();
                    //  String strOptions = "user-data-dir=C:\\Users\\" + new com.sun.security.auth.module.NTSystem().getName() + "\\AppData\\Local\\Google\\Chrome\\User Data";
                    // options.addArguments(strOptions);
                    options.addArguments("no-sandbox");
                    System.setProperty("webdriver.chrome.driver", librariespath + "chromedriver.exe");
                    driver = new ChromeDriver(options);
                    Drivers.put("Chrome", driver);
                }
                break;
            case "Headless":
                if (driver == null)
                {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    System.setProperty("webdriver.chrome.driver", librariespath+ "chromedriver.exe");
                    driver = new ChromeDriver(chromeOptions);
                    Drivers.put("Headless", driver);
                }
                break;
        }
        //Set the Timeouts
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.switchTo().defaultContent();
        //Return the Driver
        return driver;
    }

    public static void loadApplication(String strUrl)
    {
        //Load the URL
        driver.navigate().to(strUrl);
    }

    public static void closeAllDrivers()
    {
        //Loop through and Close all the browsers
        for (String key: Drivers.keySet())
        {
            //Drivers.get(key).close();
            Drivers.get(key).quit();
        }
        driver = null;
        Drivers.clear();

    }


    protected void enterText(WebElement objElement,String strText)
    {
        if (strText != null)
        {
            objElement.clear();
            objElement.sendKeys(strText);
            objElement.sendKeys(Keys.TAB);
        }
    }

    protected void typeText(WebElement objElement,String strText)
    {
        if (strText != null)
        {
            objElement.clear();
            objElement.sendKeys(strText);
            // objElement.sendKeys(Keys.TAB);
        }
    }

    public static void selectText(WebElement objElement, String strText)
    {
        if (!strText.equals(""))
        {
            Select  select = new Select (objElement);
            select.selectByVisibleText(strText);
        }
    }

    public static void selectValue(WebElement objElement, String strValue)
    {
        if (!strValue.equals(""))
        {
            Select select = new Select(objElement);
            select.selectByValue(strValue);
        }
    }

    public static void selectIndex(WebElement objElement, int strValue)
    {
        Select select = new Select(objElement);
        select.selectByIndex(strValue);
    }

    protected static int getElementCount(String xpath)    {
        return driver.findElements(By.xpath(xpath)).size();
    }

    protected static List<WebElement> getElements(String xpath)
    {
        return driver.findElements(By.xpath(xpath));
    }

    protected static WebElement getElement(String xpath)
    {
        return driver.findElement(By.xpath(xpath));
    }



}

