package pika.hungt1.dx.extras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.core.DriverInitialization;
import pika.hungt1.dx.utils.DriverSyncUtil;

import java.net.MalformedURLException;
import java.net.URL;

public class AutomationTesting1 extends DriverInitialization {

    public AutomationTesting1(String platform) {
        super(platform);
    }

    /**
     * Logic chứa test case
     */
    @Override
    public void setupTestCases() {
        /*
        Test case 1: Kiểm tra thanh điền tìm kiếm video
         */
        // testCase1();

        /*
        Test case 2: Kiểm tra việc đánh dấu tick vào ô giới tính nữ trên trang đăng ký facebook
         */
        // testCase2();

        /*
        Test case 3: Test id động (id tự sinh ra khi chạy web và ngẫu nhiên
         */
        // testCase3();

        // testCase4();
        testCase5();
    }

    private void testCase1() {
        webDriver.get("https://www.youtube.com/");
        WebElement searchBox = webDriver.findElement(By.xpath("//*[@id=\"center\"]/yt-searchbox/div[1]/form/input"));
        searchBox.sendKeys("OMFG - Hello");
        searchBox.submit();
        noShutdown();
    }

    private void testCase2() {
        webDriver.get("https://www.facebook.com/r.php?locale=en_US");
        // TODO: nếu xpath bắt đầu bằng label thì khó dùng xpath để kiểm thử, khuyến khích cân nhắc sử dụng id thay xpath
        WebElement femaleRadio = webDriver.findElement(By.xpath("//*[@id='sex' and @value='1']"));
        // xpath = "//label[normalize-space()='Female']" <- không nên dùng
        System.out.println(femaleRadio.isSelected());
        femaleRadio.click();
        System.out.println(femaleRadio.isSelected());
        noShutdown();
    }

    private void testCase3() {
        webDriver.get("https://www.facebook.com/");
        // kiểm thử element với id động -> ko thể dùng xpath luôn
        // "//button[@id='u_0_9_y2']" đổi thành
        WebElement loginButton = webDriver.findElement(By.xpath("//button[starts-with(@id, 'u_0_')]"));
        System.out.println(loginButton.getText());
        loginButton.click();
        // sau khi click thì element loginButton không còn tồn tại nữa -> thả lỗi
        WebElement loginButton2 = webDriver.findElement(By.id("loginbutton"));
        System.out.println(loginButton2.getText());
        noShutdown();
    }

    private void testCase4() {
        webDriver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");

        // Lấy phần tử elements và click
        // WebElement elementsButton = webDriver.findElement(By.xpath("//button[normalize-space()='Elements']"));
        WebElement elementsButton = this.findElementByXpath("//button[normalize-space()='Elements']");
        elementsButton.click();

        // Lấy xpath của khung chứa menu...
        WebElement elements = this.findElementByXpath("//ul[@id='navMenus']");

        // Lấy từ textbox
        // WebElement textboxElement = elements.findElement(By.xpath("//div[@id='collapseOne']//li[1]")); viết gọn
        WebElement textboxElement = elements.findElement(By.xpath("//li[1]/a")); // xpath tuyệt đối nếu có 1 dấu /
        logConsole(textboxElement.getText());
    }

    private void testCase5() {
        webDriver.get("https://practice.bpbonline.com");
        WebElement myAccountBtn = DriverSyncUtil.waitUntilClickable(webDriver, By.linkText("My Account"));
        System.out.println(myAccountBtn.getText());
    }
}
