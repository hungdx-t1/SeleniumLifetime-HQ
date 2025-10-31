package pika.hungt1.dx.core;

import org.openqa.selenium.WebDriver;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public abstract class DriverInitializationV2 {
    protected WebDriver webDriver;
    protected Logger logger;
    protected boolean autoShutdown = true;

    public DriverInitializationV2(Platform platform) {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.webDriver = platform.createDriver();
        if(webDriver == null) {
            throw new IllegalStateException("Không thể khởi tạo WebDriver cho platform: " + platform);
        }
        DriverSyncUtilV2.init(webDriver);
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

    private void tearDown() {
        if(autoShutdown) {
            this.forceShutdown();
        }
    }

    // Có thể implementate
    protected final void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException ignored) {}
    }

    protected final void setAutoShutdown(boolean shutdown) {
        this.autoShutdown = shutdown;
    }

    protected final void forceShutdown() {
        if(webDriver != null) {
            webDriver.quit();
        }
    }

    protected final Logger getLogger() {
        return this.logger;
    }
}
