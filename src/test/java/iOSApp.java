import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
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
//            UiAutomator2Options options = new UiAutomator2Options();
//

            // LambdaTest Specific Options
            DesiredCapabilities options = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("w3c", true);
            ltOptions.put("isRealMobile", true);
            ltOptions.put("build", "Java TestNG iPad Build");
            ltOptions.put("name", "iOS iPad Test");
            ltOptions.put("visual", true);
            ltOptions.put("devicelog", true);
            ltOptions.put("platformName", "ios");
            ltOptions.put("platformVersion", "17");
            ltOptions.put("deviceName", "iPhone.*");
            ltOptions.put("app", "<your_app_id>");
            options.setCapability("lt:options", ltOptions);

            String hub = "https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub";

            System.out.println("USERNAME: " + System.getenv("LT_USERNAME"));
            System.out.println("ACCESS_KEY: " + System.getenv("LT_ACCESS_KEY"));

            driver = new IOSDriver(new URL(hub), options);


            WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            //Changes the color of the text
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("color"))).click();
            Thread.sleep(1000);

            //Changes the text to "Proverbial"
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Text"))).click();
            Thread.sleep(1000);

            //Toast will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("toast"))).click();
            Thread.sleep(1000);

            //Notification will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("notification"))).click();
            Thread.sleep(8000);

            //Opens the geolocation page
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("geoLocation"))).click();
            Thread.sleep(4000);

            //Takes back
            driver.navigate().back();

            //Takes to speedtest page
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("speedTest"))).click();
            Thread.sleep(4000);
            driver.navigate().back();

            //Opens the browser
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Browser"))).click();
            Thread.sleep(1000);

            WebElement url = Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("url")));
            Thread.sleep(1000);

            url.click();
            url.sendKeys("https://www.lambdatest.com");
            Wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("find"))).click();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}