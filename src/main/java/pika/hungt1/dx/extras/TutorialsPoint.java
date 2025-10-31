package pika.hungt1.dx.extras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.core.DriverInitializationV2;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.util.Set;

public class TutorialsPoint extends DriverInitializationV2 {

    public TutorialsPoint(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        //testCase1();
        testCase2();
    }

    /*
    Test Case 1: Kiểm thử nút new tab trong trang Browser Windows

    Bước 1: Vào trang web https://www.tutorialspoint.com/selenium/practice/browser-windows.php
    Bước 2: Click vào nút New Tab
     */
    private void testCase1() {
        webDriver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        // Tìm element có nút New Tab
        WebElement newTabBtn =
                DriverSyncUtilV2
                .waitUntilClickable(By.xpath("//button[normalize-space()='New Tab']"));
        newTabBtn.click();

        // lấy tất cả các cửa sổ trên trình duyệt
        Set<String> allHandleWindows = webDriver.getWindowHandles();

        // chuyển sang mảng
        Object[] windows = allHandleWindows.toArray();

        // chuyển đến cửa sổ đầu tiên
        webDriver.switchTo().window(windows[0].toString());

        sleep(2000);
    }

    /*
    Test Case 2: Kiểm thử nút new tab trong trang Browser Windows

    Bước 1: Vào trang web https://www.tutorialspoint.com/selenium/practice/browser-windows.php
    Bước 2: Click vào nút New Window
     */
    private void testCase2() {
        webDriver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        // Lưu lại ID của cửa sổ hiện tại
        String mainWindow = webDriver.getWindowHandle();

        // Click nút "New Window"
        DriverSyncUtilV2.waitUntilClickable(By.xpath("//button[normalize-space()='New Window']")).click();

        // Lấy tất cả các cửa sổ đang mở
        Set<String> allWindows = webDriver.getWindowHandles();

        // Duyệt qua từng cửa sổ
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(mainWindow)) {
                // Nếu khác mainWindow => switch sang cửa sổ mới
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        // Kiểm tra nội dung cửa sổ mới (demo)
        System.out.println("Title of new window: " + webDriver.getTitle());
        System.out.println("URL of new window: " + webDriver.getCurrentUrl());

        // Chờ 2s để quan sát
        sleep(2000);

        // Đóng cửa sổ mới
        webDriver.close();

        // Quay lại cửa sổ chính
        webDriver.switchTo().window(mainWindow);

        sleep(1000);
    }
}
