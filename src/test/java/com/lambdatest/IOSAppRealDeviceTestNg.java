package com.lambdatest;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSAppRealDeviceTestNg {

  private AppiumDriver driver;

  @BeforeMethod
  public void setUp() throws MalformedURLException {
    String username = "";
    String authkey = "";

    String hub = "beta-hub.lambdatest.com/wd/hub";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformVersion","14");
    capabilities.setCapability("platformName","ios");
    capabilities.setCapability("deviceName","iPhone X");
    capabilities.setCapability("build","App_Real_Device");
    capabilities.setCapability("isRealMobile",true);
    capabilities.setCapability("app","lt://APP100162416387155381379880");

    driver = new AppiumDriver(new URL("https://"+username+":"+authkey+"@"+hub),capabilities);
  }

  @Test
  public void firstAppAutomationOnRealDevice(){
    SoftAssert softAssert = new SoftAssert();
    String actualPageTitle = driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"LambdaTest Sample App\"]").getText();
    softAssert.assertTrue(actualPageTitle.contains("LambdaTest Sample App"), "Title is not displayed");

    softAssert.assertTrue(driver.findElementByXPath("(//*[@type='XCUIElementTypeStaticText'])[2]")
        .getText().contains("5 of 5 remaining"), "5 of 5 remaining is not displayed");

    driver.findElementByXPath("(//*[@type='XCUIElementTypeSwitch'])[1]").click();
  }

  @AfterMethod
  public void tearDown(){
    driver.quit();
  }

}
