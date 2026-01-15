package pika.hungt1.dx.core;

import pika.hungt1.dx.models.Platform;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public abstract class AbstractDriverTestingUniversalNG extends BaseDriver {
    protected Logger logger;
    protected boolean autoShutdown = true;

    public AbstractDriverTestingUniversalNG(Platform platform) {
        super(platform);
        webDriver.manage().window().maximize();
    }

    public AbstractDriverTestingUniversalNG(Platform platform, int durationWait) {
        super(platform, durationWait);
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
