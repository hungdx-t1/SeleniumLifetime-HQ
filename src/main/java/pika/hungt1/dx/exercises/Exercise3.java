package pika.hungt1.dx.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.core.DriverInitialization;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtil;

public class Exercise3 extends DriverInitialization {

    @SuppressWarnings("unused")
    public Exercise3(String plat) {
        super(plat);
    }

    @SuppressWarnings("unused")
    public Exercise3(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        /*
          Test case 1: kiểm tra trang web có title phải là "Google" không
          Các bước thực hiện:
          B1 - Khởi động trình duyệt Edge
          B2 - Vào trang web www.google.com
          B3 - Kiểm tra title của trang web
         */
        // testCase1();

        /*
          Test case 2: ...
          Các bước thực hiện:
          B1 - ...
          B2 - ...
          B3 - ...
         */
        testCase2();
    }

    private void testCase1() {
        // Bước 1, 2
        webDriver.get("https://www.google.com");

        // Bước 3
        String title = webDriver.getTitle();
        if(title != null && title.equals("Google")) { // equals có thể ném NullPointerException
            System.out.println("✅ TC1: Tiêu đề của trang web là Google");
        } else {
            System.out.println("❌ TC1: Tiêu đề của trang web không phải là Google");
        }
    }

    private void testCase2() {
        // Bước 1: Mở link
        webDriver.get("https://www.techlistic.com/p/selenium-practice-form.html");

        // Bước 2: Điền first name và last name
        // WebElement firstNameBox = webDriver.findElement(By.xpath("//input[@name='firstname']"));
        WebElement firstNameBox = DriverSyncUtil.waitUntilVisible(webDriver, By.xpath("//input[@name='firstname']"));
        WebElement lastNameBox = DriverSyncUtil.waitUntilVisible(webDriver, By.xpath("//input[@name='lastname']"));
        firstNameBox.sendKeys("Nguyen");
        lastNameBox.sendKeys("Van A");

        // Bước 3: Chọn giới tính
        WebElement sexRadio = DriverSyncUtil.waitUntilVisible(webDriver, By.xpath("//input[@id='sex-0']"));
        sexRadio.click();

        // Bước 4: Chọn số năm kinh nghiệm
        WebElement yrExperienceRadio =
                DriverSyncUtil.waitUntilVisible(webDriver, By.xpath("//input[@id='exp-6']"));
        yrExperienceRadio.click();

        // Bước 5: Nhập ngày
        WebElement dateBox = DriverSyncUtil.waitUntilVisible(webDriver, By.xpath("//input[@id='datepicker']"));
        dateBox.sendKeys("01/01/1970");

        // Bước 6: Chọn profession
        WebElement profession1 = DriverSyncUtil.waitUntilVisible(webDriver, By.xpath("//input[@id='profession-1']"));
        profession1.click();

        this.noShutdown();
    }
}
