import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ?
            "princev" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
            "BTxtkGRLs3Xa6YRG92aTy2z3hyREhVRDNIwVYY5qH3WjEamJ1B" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build","Demo for Context Switch");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true); 
            capabilities.setCapability("app", "lt://APP10160202521674154775515472"); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            capabilities.setCapability("autoGrantPermissions",true);
            capabilities.setCapability("automationName","UiAutomator2");

            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver = new AppiumDriver(new URL(hub), capabilities);

            Thread.sleep(7000);

            MobileElement color = (MobileElement) driver.findElementById("webpage");
            
            color.click();
            Thread.sleep(2000);

            driver.findElementById("websiteName").sendKeys("https://mfml.in/api/getInfo");
            Thread.sleep(2000);
            driver.findElementById("findButton").click();
            
            System.out.println(driver.getContextHandles());
            driver.context("WEBVIEW_com.example.QAapp");

            Thread.sleep(2000);

            driver.findElementById("resolution").click();
            Thread.sleep(2000);

            driver.context("NATIVE_APP");
            Thread.sleep(2000);

            driver.findElementById("websiteName").sendKeys("https://www.google.com");
            Thread.sleep(2000);
            driver.findElementById("findButton").click();

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
