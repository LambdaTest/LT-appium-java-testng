package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class WebRealDeviceTestNg {

  private WebDriver driver;
  private String Status = "failed";

  @BeforeMethod
  public void setup() throws MalformedURLException {
    String username = "";
    String authkey = "";

    String hub = "beta-hub.lambdatest.com/wd/hub";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformVersion","14");
    capabilities.setCapability("platformName","ios");
    capabilities.setCapability("deviceName","iPhone X");
    capabilities.setCapability("build","Web_Real_Device");
    capabilities.setCapability("isRealMobile", true);
    driver = new RemoteWebDriver(new URL("https://"+username + ":" + authkey + "@" + hub),capabilities);
  }

  @Test
  public void firstTest() throws InterruptedException {
    String spanText;
    System.out.println("Loading Url");

    driver.get("https://lambdatest.github.io/sample-todo-app/");

    System.out.println("Checking Box");
    driver.findElement(By.name("li1")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li2")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li4")).click();

    driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
    driver.findElement(By.id("addbutton")).click();

    driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
    driver.findElement(By.id("addbutton")).click();

    driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
    driver.findElement(By.id("addbutton")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li1")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li3")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li7")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li8")).click();
    Thread.sleep(300);

    System.out.println("Entering Text");
    driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");

    driver.findElement(By.id("addbutton")).click();

    System.out.println("Checking Another Box");
    driver.findElement(By.name("li9")).click();

    // Let's also assert that the todo we added is present in the list.

    spanText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[9]/span")).getText();
    Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);
    Status = "passed";
    Thread.sleep(150);

    System.out.println("TestFinished");
  }

  @AfterMethod
  public void tearDown(){
    driver.quit();
  }

}
