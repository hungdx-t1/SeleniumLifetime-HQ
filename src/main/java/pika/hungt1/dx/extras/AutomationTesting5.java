package pika.hungt1.dx.extras;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.core.DriverInitializationV2;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.util.Set;

public class AutomationTesting5 extends DriverInitializationV2 {
    public AutomationTesting5(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        testCase1();
    }

    // alert OK
    private void testCase1() {
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement jsAlert = DriverSyncUtilV2.waitForPresent(
                By.xpath("//button[normalize-space()='Click for JS Prompt']")
        );
        jsAlert.click();

        // chuyển đến alert
        Alert alert = webDriver.switchTo().alert();

        // điền trường
        alert.sendKeys("How are you");

        // nhấp vào nút OK
        alert.accept();

        // gợi ý:
//        alert.dismiss(); - chạy khi có alert có 2 nút ok và cancel, dismiss() là nhấn nút cancel
//        alert.getText();
//        alert.sendKeys("..."); - nhập nội dung trong alert, nhớ là thêm alert.accept();

//        if(webDriver.getPageSource().contains("You successfully clicked an alert")) {
//            System.out.println("Alert thanh cong!");
//        } else {
//            System.out.println("Alert khong thanh cong.");
//        }
        // tương tự với dismiss

        if(webDriver.getPageSource().contains("How are you")) {
            System.out.println("Đã xuất hiện chữ");
        } else {
            System.out.println("Không xuất hiện chữ");
        }

    }
}

