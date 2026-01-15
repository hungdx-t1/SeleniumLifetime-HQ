package pika.hungt1.dx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class YTTest {

    private WebDriver driver;

    @Test
    public void test() {
        String expectedTitle = "YouTube_12";
        String title = driver.getTitle(); // actual

        Assert.assertEquals(title, expectedTitle);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new EdgeDriver();
        driver.get("https://www.youtube.com");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
