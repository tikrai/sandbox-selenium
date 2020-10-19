package com.gmail.tikrai;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
  public static void main(String[] args) {
    // declaration and instantiation of objects/variables
    System.setProperty("webdriver.chrome.driver","C:\\java\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();

//        String expectedTitle = "Welcome: Mercury Tours";
//
//        // launch Fire fox and direct it to the Base URL
//        driver.get("http://demo.guru99.com/test/newtours/");
//
//        // get the actual value of the title
//        String actualTitle = driver.getTitle();
//
//        /*
//         * compare the actual title of the page with the expected one and print
//         * the result as "Passed" or "Failed"
//         */
//        if (actualTitle.contentEquals(expectedTitle)){
//            System.out.println("Test Passed!");
//        } else {
//            System.out.println("Test Failed");
//        }

    driver.get("http://demo.guru99.com/test/facebook.html");
    WebElement element = driver.findElement(By.id("email"));
    String tagName = element.getAttribute("class");
    System.out.println(tagName);

    WebElement element2 = driver.findElement(By.cssSelector("input#email"));
    String tagName2 = element2.getAttribute("class");
    System.out.println(tagName2);

    WebElement element3 = driver.findElement(By.cssSelector("input#email"));
    System.out.println(element3.getAttribute("class"));

    driver.close(); //close chrome
  }


}
