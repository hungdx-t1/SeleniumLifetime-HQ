package pika.hungt1.dx.exercises;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pika.hungt1.dx.core.DriverInitialization;

public class Exercise1 extends DriverInitialization {

    public Exercise1(String plat) {
        super(plat);
    }

    @Override
    public void setupTestCases() {
        testCase1();
    }

    private void testCase1() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.com");

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            logger.warning("Thread sleep error! " + e.getMessage());
        }

        System.out.println(driver.getTitle());

        driver.navigate().to("http://practice.bpbonline.com");
        System.out.println(driver.getTitle());

        driver.navigate().to("https://www.tutorialspoint.com/selenium/selenium_locators.htm");
        System.out.println(driver.getTitle());

        driver.quit();
    }
}
