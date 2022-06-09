# How to change IP geographic location in TestNG with Appium on [LambdaTest](https://www.lambdatest.com/?utm_source=github&utm_medium=repo&utm_campaign=appium-testNG-geoLocation)

While performing app automation testing with appium on LambdaTest Grid, you may face a scenario where a test that you declared as fail in your local instance may turn out to be completed successfully at LambdaTest. Don't worry though! We understand how imperative it is to flag an app automation test as either "pass" or "fail" depending upon your testing requirement with respect to the validation of expected behaviour. You can refer to sample test repo [here](https://github.com/LambdaTest/LT-appium-java-testng).

# Steps:

You can specify a test as passed or failed by Lambda hooks. The following is an example on how to set test result as passed or failed. If the code reaches exception, then it will be marked as failed, else as passed.

```java
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import java.net.URL;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ?
            "username" : System.getenv("LT_USERNAME"); //Enter your username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ?
            "accessKey" : System.getenv("LT_ACCESS_KEY"); //Enter your accessKey here

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("build","Enter_your_build_name"); //Enter your build name here
                capabilities.setCapability("name","Enter_your_test_name"); //Enter your test name here
                capabilities.setCapability("deviceName", "Galaxy S21 5G");
                capabilities.setCapability("platformVersion","12");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("isRealMobile", true);
                capabilities.setCapability("app", "lt://"); //Enter your app url here

                //ADD GEOLOCATION BASED ON COUNTRY CODE
                capabilities.setCapability("geoLocation", "fr");

            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver = new AppiumDriver(new URL(hub), capabilities);

            MobileElement color = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/color");
            color.click();
            MobileElement text = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/Text");
            text.click();
            MobileElement toast = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/toast");
            toast.click();
            MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
            notification.click();
            Thread.sleep(2000);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
            }
        }
     }
```
## Run your test

```bash
mvn clean install -DsuiteXmlFile=src/test/java/Android.xml
```
# Links:

[LambdaTest Community](http://community.lambdatest.com/)