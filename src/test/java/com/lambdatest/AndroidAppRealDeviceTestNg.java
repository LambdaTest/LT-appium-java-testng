package com.lambdatest;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidAppRealDeviceTestNg {

  private AppiumDriver driver;

  @BeforeMethod
  public void setUp() throws MalformedURLException {
    String username = "";
    String authkey = "";

    String hub = "beta-hub.lambdatest.com/wd/hub";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformVersion","11");
    capabilities.setCapability("platformName","android");
    capabilities.setCapability("deviceName","OnePlus 8");
    capabilities.setCapability("build","App_Real_Device");
    capabilities.setCapability("isRealMobile",true);
    capabilities.setCapability("app","lt://APP10016241638728418334196");

    driver = new AppiumDriver(new URL("https://"+username+":"+authkey+"@"+hub),capabilities);
  }

  @Test
  public void firstAppAutomationOnRealDevice(){
    SoftAssert softAssert = new SoftAssert();
    String actualPageTitle = driver.findElementByXPath("(//*[@package='io.gonative.android.kzlnwx'])[12]").getText();
    softAssert.assertTrue(actualPageTitle.contains("LambdaTest Sample App"), "Title is not displayed");

    softAssert.assertTrue(driver.findElementByXPath("(//*[@package='io.gonative.android.kzlnwx'])[13]")
      .getText().contains("5 of 5 remaining"), "5 of 5 remaining is not displayed");

    driver.findElementByXPath("(//*[@class='android.widget.CheckBox'])[1]").click();
  }

  @AfterMethod
  public void tearDown(){
    driver.quit();
  }

}
