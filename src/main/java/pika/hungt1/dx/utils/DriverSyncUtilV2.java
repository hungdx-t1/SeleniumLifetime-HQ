package pika.hungt1.dx.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@SuppressWarnings("unused")
public class DriverSyncUtilV2 {
    private static WebDriver webDriver;

    private static int DURATION_OF_WAIT_DEFAULT = 10;

    private DriverSyncUtilV2() {
        DriverSyncUtilV2.DURATION_OF_WAIT_DEFAULT = 10;
    }

    public DriverSyncUtilV2(int seconds) {
        DriverSyncUtilV2.DURATION_OF_WAIT_DEFAULT = seconds;
    }

    public static void init(WebDriver driver) {
        DriverSyncUtilV2.webDriver = driver;
    }

    public static WebElement waitUntilClickable(By by) {
        return waitUntilClickable(DURATION_OF_WAIT_DEFAULT, by);
    }

    public static WebElement waitUntilClickable(int seconds, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement waitUntilVisible(By by) {
        return waitUntilVisible(DURATION_OF_WAIT_DEFAULT, by);
    }

    public static WebElement waitUntilVisible(int seconds, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean waitForInvisible(By by) {
        return waitForInvisible(DURATION_OF_WAIT_DEFAULT, by);
    }

    public static boolean waitForInvisible(int seconds, By by) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static WebElement waitForPresent(By by) {
        return waitForPresent(DURATION_OF_WAIT_DEFAULT, by);
    }

    public static WebElement waitForPresent(int seconds, By by) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static boolean forElementStale(WebElement element) {
        return forElementStale(DURATION_OF_WAIT_DEFAULT, element);
    }

    public static boolean forElementStale(int seconds, WebElement element) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.stalenessOf(element));
    }
}
