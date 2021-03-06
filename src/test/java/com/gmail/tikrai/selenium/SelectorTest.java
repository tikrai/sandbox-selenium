package com.gmail.tikrai.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectorTest {
  private WebDriver driver;
  private String attr;

  @BeforeMethod
  public void setUp() {
    String osName = System.getProperty("os.name");
    System.out.println(osName);
    if (osName.equals("Linux")) {
      System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver-v86");
      System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
    } else if (osName.matches("Windows.*")) {
      System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
      System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    } else {
      throw new RuntimeException("Unsupported OS");
    }

//    ChromeOptions options = new ChromeOptions();
//    options.setHeadless(true);
    driver = new ChromeDriver();
//    driver = new FirefoxDriver();
    driver.get("http://demo.guru99.com/test/facebook.html");
  }

  @Test
  public void shouldFindElementById() {
    attr = driver.findElement(By.id("email")).getAttribute("class");
    assertEquals(attr, "inputtext");
  }

  @Test
  public void shouldFindElementByCssSelector() {
    attr = driver.findElement(By.cssSelector("input#email")).getAttribute("class");
    assertEquals(attr, "inputtext");
  }

  @Test
  public void shouldFindElementByFullXpath() {
    String xpath =
        "/html/body/div[2]/div/div/div/div/div[1]/div/div/form/table/tbody/tr[2]/td[1]/input";
    attr = driver.findElement(By.xpath(xpath)).getAttribute("class");
    assertEquals(attr, "inputtext");
  }

  @Test
  public void shouldFindElementByRelativeXpath() {
    String xpath = "//*[@id=\"login_form\"]/table/tbody/tr[2]/td[1]/input";
    attr = driver.findElement(By.xpath(xpath)).getAttribute("class");
    assertEquals(attr, "inputtext");
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
