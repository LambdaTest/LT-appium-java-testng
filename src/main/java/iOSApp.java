import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import org.testng.annotations.Test;

public class iOSApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-ios" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void iOSApp1(String device, String version, String platform) {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "Java TestNG");
            capabilities.setCapability("name", platform + " " + device + " " + version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", app_id); //Enter your app url
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability("geoLocation", "HK");

            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
            driver = new AppiumDriver(new URL(hub), capabilities);

            WebDriverWait Wait = new WebDriverWait(driver, 30);

            //Changing Locator type from Accessibility ID to Name as in the Proverbial app there is no Accessibility ID Attribute for Any Element
            //Changes the color of the text
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("color"))).click();
            Thread.sleep(1000);

            //Changes the text to "Proverbial"
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Text"))).click();
            Thread.sleep(1000);

            //Toast will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("toast"))).click();
            Thread.sleep(1000);

            //Notification will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("notification"))).click();
            Thread.sleep(4000);

            //Opens the geolocation page
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("geoLocation"))).click();
            Thread.sleep(4000);

            //Takes back
            driver.navigate().back();

            //Takes to speedtest page
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("speedTest"))).click();
            Thread.sleep(4000);
            driver.navigate().back();

            //Opens the browser
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Browser"))).click();
            Thread.sleep(1000);
            MobileElement url = (MobileElement) driver.findElementByName("url");
            url.click();
            url.sendKeys("https://www.lambdatest.com");
            Wait.until(ExpectedConditions.presenceOfElementLocated(By.name("find"))).click();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
