package pika.hungt1.dx.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pika.hungt1.dx.core.DriverInitialization;
import pika.hungt1.dx.models.Platform;

public class Exercise5 extends DriverInitialization {

    private static final String URL = "https://automationexercise.com/";

    @SuppressWarnings("unused")
    public Exercise5(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {

    }

    private void login() {
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
        passwordInput.submit();
    }

    private void testCase1() {
        webDriver.get(URL);
        login();

        WebElement addToCartBtn = this.findElementByXpathUntilFound("//body/section/div[@class='container']/div[@class='row']/div[@class='col-sm-9 padding-right']/div[@class='features_items']/div[2]/div[1]/div[1]/div[1]/a[1]");
        addToCartBtn.click();
    }
}
