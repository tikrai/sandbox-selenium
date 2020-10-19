package com.gmail.tikrai.selenium;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class SelectorTest {
  private WebDriver driver;
  private String attr;

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver","C:\\java\\chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.setHeadless(true);
    driver = new ChromeDriver();
    driver.get("http://demo.guru99.com/test/facebook.html");
  }

  @Test
  public void shouldFindElementById() {
    attr = driver.findElement(By.id("email")).getAttribute("class");
    assertThat(attr, equalTo("inputtext"));
  }

  @Test
  public void shouldFindElementByCssSelector() {
    attr = driver.findElement(By.cssSelector("input#email")).getAttribute("class");
    assertThat(attr, equalTo("inputtext"));
  }

  @Test
  public void shouldFindElementByFullXpath() {
    String xpath =
        "/html/body/div[2]/div/div/div/div/div[1]/div/div/form/table/tbody/tr[2]/td[1]/input";
    attr = driver.findElement(By.xpath(xpath)).getAttribute("class");
    assertThat(attr, equalTo("inputtext"));
  }

  @Test
  public void shouldFindElementByRelativeXpath() {
    String xpath = "//*[@id=\"login_form\"]/table/tbody/tr[2]/td[1]/input";
    attr = driver.findElement(By.xpath(xpath)).getAttribute("class");
    assertThat(attr, equalTo("inputtext"));
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }
}
