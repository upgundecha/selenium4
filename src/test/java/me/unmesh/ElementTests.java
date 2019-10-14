package me.unmesh;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.IOException;


public class ElementTests {

    WebDriver driver;

    @Before
    public void setUp()

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://www.google.com/");
    }

    @Test
    public void elementScreenShot() throws IOException {

        // find the Images link on Google Search home page
        WebElement imagesLink = driver.findElement(By.linkText("Images"));

        // take a screenshot of the link element
        File linkScr = imagesLink.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(linkScr, new File("./target/linkScr.png"));

        // find the Search box which is a <form> element
        WebElement searchBox = driver.findElement(By.id("tsf"));

        // take a screenshot of the Search box
        File searchBoxScr = driver.findElement(By.id("tsf"))
                .getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(searchBoxScr, new File("./target/searchBox.png"));

    }

    @Test
    public void elementRect() throws IOException {

        // find the Images link on Google Search home page
        WebElement imagesLink = driver.findElement(By.linkText("Images"));

        // previous getSize() and getLocation() methods
        Dimension elementSize = imagesLink.getSize();

        System.out.println("Element height is " + elementSize.getHeight());
        System.out.println("Element width is " + elementSize.getWidth());

        Point elementLocation = imagesLink.getLocation();

        System.out.println("Element X Pos " + elementLocation.getX());
        System.out.println("Element Y Pos " + elementLocation.getY());


        // new getRect() method
        Rectangle elementRect = imagesLink.getRect();

        System.out.println("Element height is " + elementRect.getHeight());
        System.out.println("Element width is " + elementRect.getWidth());
        System.out.println("Element X Pos " + elementRect.getX());
        System.out.println("Element Y Pos " +  elementRect.getY());

    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

