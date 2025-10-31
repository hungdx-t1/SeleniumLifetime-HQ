package pika.hungt1.dx.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@SuppressWarnings("unused")
public class DriverSyncUtil {

    private static int DURATION_OF_WAIT_DEFAULT = 10;

    public DriverSyncUtil() {
        DriverSyncUtil.DURATION_OF_WAIT_DEFAULT = 10;
    }

    public DriverSyncUtil(int seconds) {
        DriverSyncUtil.DURATION_OF_WAIT_DEFAULT = seconds;
    }

    public static WebElement waitUntilClickable(WebDriver driver, By by) {
        return waitUntilClickable(driver, DURATION_OF_WAIT_DEFAULT, by);
    }

    public static WebElement waitUntilClickable(WebDriver driver, int seconds, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement waitUntilVisible(WebDriver driver, By by) {
        return waitUntilVisible(driver, DURATION_OF_WAIT_DEFAULT, by);
    }

    public static WebElement waitUntilVisible(WebDriver driver, int seconds, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean waitForInvisible(WebDriver driver, By by) {
        return waitForInvisible(driver, DURATION_OF_WAIT_DEFAULT, by);
    }

    public static boolean waitForInvisible(WebDriver driver, int seconds, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static WebElement waitForPresent(WebDriver driver, By by) {
        return waitForPresent(driver, DURATION_OF_WAIT_DEFAULT, by);
    }

    public static WebElement waitForPresent(WebDriver driver, int seconds, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static boolean forElementStale(WebDriver driver, WebElement element) {
        return forElementStale(driver, DURATION_OF_WAIT_DEFAULT, element);
    }

    public static boolean forElementStale(WebDriver driver, int seconds, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.stalenessOf(element));
    }
}
