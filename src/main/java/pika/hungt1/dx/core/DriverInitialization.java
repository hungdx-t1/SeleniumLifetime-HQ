package pika.hungt1.dx.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.interfaces.IActionLog;
import pika.hungt1.dx.models.Platform;

// import java.time.Duration;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public abstract class DriverInitialization implements IActionLog {

    protected WebDriver webDriver;
    protected final Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    protected boolean autoShutdown = true;

    public DriverInitialization(String platform) {
        this(Platform.parsePlatform(platform));
    }

    public DriverInitialization(Platform platform) {
        this.webDriver = platform.createDriver();
        if(webDriver == null) {
            throw new IllegalStateException("Không thể khởi tạo WebDriver cho platform: " + platform);
        }

        // thiết lập time chờ tối đa 10s cho tất cả code findElement(s)
        // webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
    }

    public void handle() {
        System.out.println("Đang bắt đầu tiến hành test...");
        setupTestCases();
        tearDown();
        System.out.println("Kiểm thử kết thúc.");
    }

    /**
     * Logic chứa test case
     */
    public abstract void setupTestCases();

    @Deprecated
    private void tearDown0() {
        if(autoShutdown && webDriver != null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(this.getClass().getSimpleName() + " has thrown InterruptedException: " + e.getMessage());
            }
            webDriver.quit();
        }
    }

    private void tearDown() {
        this.forceShutdown();
    }

    // Có thể implementate
    protected final void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException ignored) {}
    }

    protected final void noShutdown() {
        this.autoShutdown = false;
    }

    protected final void shutDown() {
        this.autoShutdown = true;
    }

    protected final void forceShutdown() {
        if(webDriver != null) {
            webDriver.quit();
        }
    }

    protected WebElement findElementByXpath(String text) {
        return webDriver.findElement(By.xpath(text));
    }

    protected WebElement findElementById(String text) {
        return webDriver.findElement(By.id(text));
    }

}
