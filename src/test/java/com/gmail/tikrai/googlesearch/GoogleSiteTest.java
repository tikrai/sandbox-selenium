package com.gmail.tikrai.googlesearch;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleSiteTest {
  private WebDriver driver;

  @BeforeMethod
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\java\\chromedriver.exe");
    System.setProperty("webdriver.gecko.driver",  "C:\\java\\geckodriver.exe");
//    ChromeOptions options = new ChromeOptions();
//    options.setHeadless(true);

//    driver = new ChromeDriver();
    driver = new FirefoxDriver();
  }

  @Test
  public void shouldVerifyGoogleSearch() {
    driver.get("https://www.google.com");
    WebDriverWait wait = new WebDriverWait(driver, 5);

    driver.switchTo().frame(0);

    WebElement agree = wait.until(presenceOfElementLocated(By.id("introAgreeButton")));
    agree.click();

    driver.switchTo().defaultContent();

    try {
      agree = driver.findElement(By.id("introAgreeButton"));
      fail("Agree button still visible: " + agree);
    } catch (Exception e) {
      // no agree button is available - all ok
    }

    WebElement searchField = driver.findElement(By.cssSelector("input[name='q']"));
    searchField.sendKeys("sql table online");
    searchField.submit();

    WebElement resultCount = wait.until(presenceOfElementLocated(By.id("result-stats")));
    int count = Integer.parseInt(resultCount.getText().split("[\\D]{2,}")[1].replace(" ", ""));
    assertTrue(count > 10000000);

    driver.findElement(By.cssSelector("a[aria-label='Page 3']")).click();

    driver.findElements(By.tagName("a")).stream()
        .filter(webElement -> "https://erdplus.com/".equals(webElement.getAttribute("href")))
        .findFirst()
        .orElseThrow(RuntimeException::new)
        .click();

    WebElement logo = wait.until(presenceOfElementLocated(By.cssSelector("img[src='erdplus_logo_large.png']")));
    assertTrue(logo.isDisplayed());

    WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/section/header/header"));
    assertTrue(header.getCssValue("background-color").matches(".*83, 109, 254.*"));

    WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'production')]"));
    assertTrue(text.getText().matches(".*2020-05-24.*"));
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
}
