package pika.hungt1.dx.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pika.hungt1.dx.core.DriverInitialization;

import java.util.List;

public class Exercise4 extends DriverInitialization {

    public Exercise4(String plat) {
        super(plat);
    }

    /**
     * Logic chứa test case
     */
    @Override
    public void setupTestCases() {
        /*
        Test case 1: Kiểm tra chức năng register có hoạt động đúng không
        B1: Vào trang https://automationexercise.com
        B2: Bấm vào nút Signup / Login
        B3: Điền tên đăng nhập vào trường name trong tab Signup
        B4: Điền email address
        B5: Bấm vào nút Signup
         */
        testCase1();

        /*
        Test case 2: Kiểm thử chức năng đăng nhập có hoạt động đúng không
        B1: Vào trang https://automationexercise.com
        B2: Bấm vào nút Signup / Login
        B3: Điền email vào trường Email Address trong tab Login to your Account
        B4: Điền password
        B5: Bấm vào nút Login
         */
        testCase2();

        /*
        Test case 3: Kiểm thử chức năng đăng xuất có hoạt động đúng không
        Yêu cầu: người dùng đã đăng nhập trước đó
        B1: Bấm vào nút Logout màu đỏ
         */
        testCase3();

        /*
        Test case 4: Kiểm tra sản phẩm và thông tin của sản phẩm trên trang chủ
        Yêu cầu: người dùng đã đăng nhập trước đó
         */
        // testCase4(); // TODO

        /*
        Test case 5: Xác minh chức năng tìm kiếm sản phẩm có hoạt động đúng không
        Yêu cầu: người dùng đã đăng nhập trước đó
        B1:
        B2:
        B3:
         */
        // testCase5(); // TODO

    }

    private void testCase1() {
        webDriver.get("https://automationexercise.com");

        // tìm kiếm nút Signup / Login
        WebElement signupBtn = webDriver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
        signupBtn.click();

        // chuyển đến trang signup/login
        // tìm trường name
        WebElement nameInput = webDriver.findElement(By.xpath("//input[@placeholder='Name']"));
        // tìm trường email
        WebElement emailInput = webDriver.findElement(By.xpath("//input[@data-qa='signup-email']"));

        // Điền thông tin
        nameInput.sendKeys("Hung");
        emailInput.sendKeys("kre12x@gmail.com");

        this.sleep(500);

        emailInput.submit(); // submit form

        // chuyển đến trang điền thông tin
        webDriver.findElement(By.xpath("//input[@id='id_gender1']")).click(); // chọn giới tính nam
        webDriver.findElement(By.id("password")).sendKeys("hung@1000"); // nhập password

        // chọn ngày sinh (cái này hỏi chatgpt)
        WebElement days = webDriver.findElement(By.id("days"));
        Select selectDay = new Select(days);

        Select selectMonth = new Select(webDriver.findElement(By.id("months")));
        Select selectYear = new Select(webDriver.findElement(By.id("years")));

        /*
        / --- Chọn theo 3 cách ---
        selectMonth.selectByIndex(1);     // chọn February (index bắt đầu từ 0)
        selectMonth.selectByValue("3");   // chọn March (theo value)
        selectMonth.selectByVisibleText("May"); // chọn May (theo text hiển thị)
         */

        selectDay.selectByIndex(0);
        selectMonth.selectByIndex(0);
        selectYear.selectByVisibleText("2000"); // chọn năm 2000

        webDriver.findElement(By.id("first_name")).sendKeys("Nguyen"); // điền first name
        webDriver.findElement(By.id("last_name")).sendKeys("Minh Hung"); // điền last name
        webDriver.findElement(By.id("address1")).sendKeys("An Duong Vuong, Nguyen Van Cu");
        // điền địa chỉ

        WebElement country = webDriver.findElement(By.id("country"));
        Select selectCountry = new Select(country);

        selectCountry.selectByVisibleText("Singapore");

        webDriver.findElement(By.id("state")).sendKeys("Nguyen Van Cu");
        webDriver.findElement(By.id("city")).sendKeys("Quy Nhon");
        webDriver.findElement(By.id("zipcode")).sendKeys("56000");
        WebElement finalElement = webDriver.findElement(By.id("mobile_number"));
        finalElement.sendKeys("0912345678");
        sleep(2000);
        finalElement.submit();
    }

    private void testCase2() {
        webDriver.get("https://automationexercise.com");

        // tìm kiếm nút Signup / Login
        WebElement signupBtn = webDriver.findElement(By.xpath("//a[normalize-space()='Signup / Login']"));
        signupBtn.click();

        // chuyển đến trang signup/login
        // tìm trường email
        WebElement emailInput = webDriver.findElement(By.xpath("//input[@data-qa='login-email']"));
        // tìm trường password
        WebElement passwordInput = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));

        // Điền thông tin
        emailInput.sendKeys("kre12x@gmail.com");
        passwordInput.sendKeys("hung@1000");

        sleep(2000);
        passwordInput.submit();
    }

    public void testCase3() {
        sleep(5000);

        WebElement logoutBtn = webDriver.findElement(By.xpath("//a[normalize-space()='Logout']"));
        logoutBtn.click();
        // đã đăng xuất

        sleep(1000);
        List<WebElement> logoutBtns = webDriver.findElements(By.xpath("//a[normalize-space()='Logout']"));
        if (logoutBtns.isEmpty()) {
            logger.info("Nút logout đã biến mất. Đăng xuất thành công");
        } else {
            logger.info("Nút logout vẫn còn hiện!");
        }
    }
}
