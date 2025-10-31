package pika.hungt1.dx.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void capture(WebDriver driver, String testName) {
        try {
            // Ép kiểu
            TakesScreenshot ts = (TakesScreenshot) driver;

            // Khởi tạo biến file
            File src = ts.getScreenshotAs(OutputType.FILE);

            // Đặt tên file có thời gian, để tránh trùng
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File destination = new File("screenshots/" + testName + "_" + timestamp + ".png");
            // đuôi png có thể đổi sang đuôi jpg nếu muốn

            destination.getParentFile().mkdirs(); // Tạo thư mục (nếu chưa tồn tại)

            // Copy file đã khởi tạo vào thư mục đích
            Files.copy(src.toPath(), destination.toPath());
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
