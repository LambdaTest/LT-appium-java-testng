import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import org.testng.annotations.Test;

public class iOSApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accesskey" : System.getenv("LT_ACCESS_KEY");

    public String gridURL = "@beta-hub.lambdatest.com/wd/hub";

    AppiumDriver driver;


    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void iOSApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build","Proverbial iOS");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            //AppURL (Create from proverbial.ipa sample in project)
            capabilities.setCapability("app", "lt://APP10020211648187346007593"); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability("geoLocation", "HK");

            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver = new AppiumDriver(new URL(hub), capabilities);

            MobileElement color = (MobileElement) driver.findElementByAccessibilityId("Colour");
            //Changes color
            color.click();
            //Back to black color
            color.click();

            MobileElement text = (MobileElement) driver.findElementByAccessibilityId("Text");
            //Changes the text to proverbial
            text.click();

            //toast is visible
            MobileElement toast = (MobileElement) driver.findElementByAccessibilityId("Toast");
            toast.click();

            //notification is visible
            MobileElement notification = (MobileElement) driver.findElementByAccessibilityId("Notification");
            notification.click();

            //Open the geolocation page
            MobileElement geo = (MobileElement) driver.findElementByAccessibilityId("GeoLocation");
            geo.click();
            Thread.sleep(5000);

            //Takes back
            driver.navigate().back();

            //Takes to speed test page
            MobileElement speedtest = (MobileElement) driver.findElementByAccessibilityId("Speed Test");
            speedtest.click();
            Thread.sleep(5000);
            MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("start speed test - connection type multi");
            el10.click();
            Thread.sleep(25000);

            driver.navigate().back();

            //Opens the browser
            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();
            Thread.sleep(3000);

            MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("Search");
            el4.click();
            el4.sendKeys("Lambdatest");

            //((JavascriptExecutor) driver).executeScript("lambda-status=passed");
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
            try{
                driver.quit();
            }catch(Exception e1){
                e.printStackTrace();
            }
        }


    }
}
