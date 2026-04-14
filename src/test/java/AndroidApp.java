import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : System.getenv("LT_ACCESS_KEY");
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-android" : System.getenv("LT_APP_ID");
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities options = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("w3c", true);
            ltOptions.put("isRealMobile", true);
            ltOptions.put("build", "Java TestNG Build");
            ltOptions.put("name", "Android Test");
            ltOptions.put("visual", true);
            ltOptions.put("devicelog", true);
            ltOptions.put("autoGrantPermissions", true);
            ltOptions.put("platformName", "android");
            ltOptions.put("platformVersion", "15");
            ltOptions.put("deviceName", "Galaxy.*");
            ltOptions.put("app", ",{app_id}");
            options.setCapability("lt:options", ltOptions);


            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";

            System.out.println("USERNAME: " + System.getenv("LT_USERNAME"));
            System.out.println("ACCESS_KEY: " + System.getenv("LT_ACCESS_KEY"));

            driver = new AppiumDriver(new URL(hub), options);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // Changes color to pink
            WebElement color = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/color")));
            color.click();
            Thread.sleep(1000);
            color.click();

            // Changes the text to "Proverbial"
            WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/Text")));
            text.click();

            // Toast will be visible
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/toast")));
            toast.click();

            // Notification will be visible
            WebElement notification = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/notification")));
            notification.click();
            Thread.sleep(2000);

            // Opens the geolocation page
            WebElement geo = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/geoLocation")));
            geo.click();
            Thread.sleep(5000);

            // Takes back to home page
            WebElement home = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Home")));
            home.click();

            // Takes to speed test page
            WebElement speedtest = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/speedTest")));
            speedtest.click();
            Thread.sleep(5000);

            // Back to home page
            WebElement home2 = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Home")));
            home2.click();

            // Opens the browser
            WebElement browser = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Browser")));
            browser.click();

            WebElement url = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/url")));
            url.click();
            url.sendKeys("https://www.lambdatest.com");

            WebElement find = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.lambdatest.proverbial:id/find")));
            find.click();

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}