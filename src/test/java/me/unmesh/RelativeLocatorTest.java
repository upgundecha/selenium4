package me.unmesh;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class RelativeLocatorTest {

    WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://cookbook.seleniumacademy.com/mobilebmicalculator.html");
    }

    @Test
    public void relativeLocatorTest() {

        // find the height and weight labels using css selector
        WebElement heightLLabel = driver.findElement(By.cssSelector("label[for='heightCMS']"));
        WebElement weightLabel = driver.findElement(By.cssSelector("label[for='weightKg']"));

        // find the height input using toRightOf relative locator
        // input is right of height label
        WebElement heightInput =  driver.findElement(withTagName("input")
                .toRightOf(heightLLabel));

        heightInput.sendKeys("181");

        // find the weight input using combination of below and toRightOf relative locator
        // weight input is below height input and right of weight label
        WebElement weightInput =   driver.findElement(withTagName("input")
                .below(heightInput).toRightOf(weightLabel));

        weightInput.sendKeys("75");

        // find the calculate button which is below the weight label
        WebElement calculateButton = driver.findElement(withTagName("input")
                .below(weightLabel));
        calculateButton.click();

        // find the read only input below calculate button to verify value
        Assert.assertEquals("22.9", driver.findElement(withTagName("input")
                .below(calculateButton)).getAttribute("value"));
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

