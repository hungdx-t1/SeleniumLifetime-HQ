package pika.hungt1.dx.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.core.DriverInitializationV2;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.io.*;

public class ScreenShotsAT extends DriverInitializationV2 {

    public ScreenShotsAT(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        testCase1();
    }

    private void readFile() {
        // Đọc dữ liệu từ file
        File file = new File("data\\data.csv");
        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Không thể đọc file!");
        }
    }

    private void testCase1() {
        webDriver.get("https://practice.bpbonline.com");
        // Đọc dữ liệu từ file
        File file = new File("data\\data.csv");
        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            while(line != null) {
                // Tách username vs password
                String[] parts = line.split(",");
                String email = parts[0];
                String pass = parts[1];

                WebElement myAccElement = DriverSyncUtilV2.waitForPresent(By.xpath("//span[contains(text(),'My Account')]"));
                myAccElement.click();

                WebElement emailEle = DriverSyncUtilV2.waitForPresent(By.name("email_address"));
                emailEle.sendKeys(email);
                WebElement passEle = DriverSyncUtilV2.waitForPresent(By.name("password"));
                passEle.sendKeys(pass);

                passEle.submit();

                String source = webDriver.getPageSource();
//                if(source == null) {
//                    System.err.println("Không tìm thấy source!");
//                    line = bufferedReader.readLine();
//                    continue;
//                }

                if(source.contains("My Account Information")) {
                    System.out.println("Đăng nhập thành công với email " + email);

                    // Chụp màn hình
                    TakesScreenshot ts = (TakesScreenshot) webDriver;
                    File screen = ts.getScreenshotAs(OutputType.FILE);

                    // Đổi tên file
                    String name = "screenshots\\" + email + ".jpg";
                    File file1 = new File(name);
                    file1.getParentFile().mkdirs();
                    FileUtils.copyFile(screen, file1);

                    // đăng xuất
                    DriverSyncUtilV2.waitUntilClickable(By.xpath("//span[contains(text(),'My Account')]")).click();
                    DriverSyncUtilV2.waitUntilClickable(By.linkText("Log Off")).click();
                    DriverSyncUtilV2.waitUntilClickable(By.xpath("//span[contains(text(),'Continue')]")).click();

                    // quay lại trang login
                    DriverSyncUtilV2.waitUntilClickable(By.xpath("//span[contains(text(),'My Account')]")).click();
                } else if(source.contains("No match for E-Mail Address and/or Password")) {
                    System.out.println("Đăng nhập không thành công với email " + email);

                    // Chụp màn hình
                    TakesScreenshot ts = (TakesScreenshot) webDriver;
                    File screen = ts.getScreenshotAs(OutputType.FILE);

                    // Đổi tên file
                    String name = "screenshots\\" + email + ".jpg";
                    FileUtils.copyFile(screen, new File(name));
                }
                // chuyển sang dòng kế
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Không thể đọc file!");
        }
    }
}
