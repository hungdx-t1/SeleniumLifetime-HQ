package pika.hungt1.dx.extras;

import org.openqa.selenium.JavascriptExecutor;
import pika.hungt1.dx.core.DriverInitialization;
import pika.hungt1.dx.models.Platform;

public class AutomationTesting2 extends DriverInitialization {

    public AutomationTesting2(String plat) {
        super(plat);
    }

    public AutomationTesting2(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        webDriver.get("https://www.techlistic.com/p/selenium-practice-form.html");
        try {
            // Khắc phục phần tử bị che khuất
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            // cuộn trang xuống 1200 px;
            js.executeScript("window.scrollBy(0,1200)");
            // click checkbox
            js.executeScript("document.getElementById('profession-1').click()");
        } catch (Exception ignored) { }
    }
}
