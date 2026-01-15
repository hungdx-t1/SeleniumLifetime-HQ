package pika.hungt1.dx.webautotest;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pika.hungt1.dx.core.BaseDriver;
import pika.hungt1.dx.models.Platform;

@SuppressWarnings("NewClassNamingConvention")
public class WebAutomationTest1 extends BaseDriver {

    protected WebAutomationTest1() {
        super(Platform.EDGE);
    }

    @Test
    public void test() {
        try {
            this.waitForPresent(By.xpath("//a[normalize-space()='Signup / Login']"));
            Reporter.log("Đã tìm thấy element thành công");
        } catch (Exception e) {
            Reporter.log("Không tìm thấy element");
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        webDriver.get("https://automationexercise.com/");
    }

    @AfterSuite
    public void afterSuite() {
        webDriver.quit();
    }
}
