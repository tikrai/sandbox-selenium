package com.gmail.tikrai.googlesearch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

class GoogleSiteTest {
  private WebDriver driver;
  private String attr;

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver","C:\\java\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    driver = new ChromeDriver();
  }

  @Test
  public void shouldVerifyGoogleSearch() {
    driver.get("http://www.google.com");
    WebDriverWait wait = new WebDriverWait(driver, 5);

    driver.switchTo().frame(0);

    WebElement agree = wait.until(presenceOfElementLocated(By.id("introAgreeButton")));
    agree.click();

    try {
      agree = driver.findElement(By.id("introAgreeButton"));
      fail("Agree button still visible: " + agree);
    } catch (Exception e) {
      // no agree button is available - all ok
    }

    WebElement searchField = driver.findElement(By.cssSelector("input[name='q']"));
    searchField.sendKeys("sql table online");
    searchField.submit();

    WebElement resultCount = driver.findElement(By.id("result-stats"));
    System.out.println(resultCount.getText());
    int i = Integer.parseInt(resultCount.getText().split("[\\D]{2,}")[1].replace(" ", ""));
    System.out.println(i);
    assertThat(i > 10000000, is(true));

    driver.findElement(By.cssSelector("a[aria-label='Page 3']")).click();

    driver.findElements(By.tagName("a")).stream()
        .filter(webElement -> "https://erdplus.com/".equals(webElement.getAttribute("href")))
        .findFirst()
        .orElseThrow(RuntimeException::new)
        .click();

    WebElement logo = wait.until(presenceOfElementLocated(By.cssSelector("img[src='erdplus_logo_large.png']")));
    assertThat(logo.isDisplayed(), is(true));

    WebElement header = driver.findElement(By.xpath("//*[@id=\"root\"]/section/header/header"));
    assertThat(header.getCssValue("background-color"), equalTo("rgba(83, 109, 254, 1)"));

    WebElement text = driver.findElement(By.xpath("//*[contains(text(), 'production')]"));
    assertThat(text.getText().matches(".*2020-05-24.*"), is(true));
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }
}
