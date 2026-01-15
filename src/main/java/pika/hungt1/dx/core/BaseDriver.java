package pika.hungt1.dx.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pika.hungt1.dx.models.Platform;

import java.time.Duration;

@SuppressWarnings("unused")
public abstract class BaseDriver {
    protected WebDriver webDriver;
    protected int DURATION_OF_WAIT_DEFAULT;

    protected BaseDriver(Platform platform) {
        this(platform, 10);
    }

    protected BaseDriver(Platform platform, int durationWait) {
        this.webDriver = platform.createDriver();
        if(webDriver == null) {
            throw new IllegalStateException("Không thể khởi tạo WebDriver cho platform: " + platform);
        }
        this.DURATION_OF_WAIT_DEFAULT = durationWait;
    }

    // Dynamic find element by driver wait methods
    public WebElement waitUntilClickable(By by) {
        return waitUntilClickable(DURATION_OF_WAIT_DEFAULT, by);
    }

    public WebElement waitUntilClickable(int seconds, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitUntilVisible(By by) {
        return waitUntilVisible(DURATION_OF_WAIT_DEFAULT, by);
    }

    public WebElement waitUntilVisible(int seconds, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean waitForInvisible(By by) {
        return waitForInvisible(DURATION_OF_WAIT_DEFAULT, by);
    }

    public boolean waitForInvisible(int seconds, By by) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForPresent(By by) {
        return waitForPresent(DURATION_OF_WAIT_DEFAULT, by);
    }

    public WebElement waitForPresent(int seconds, By by) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean forElementStale(WebElement element) {
        return forElementStale(DURATION_OF_WAIT_DEFAULT, element);
    }

    public boolean forElementStale(int seconds, WebElement element) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.stalenessOf(element));
    }
}
