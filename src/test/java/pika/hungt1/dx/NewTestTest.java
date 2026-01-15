package pika.hungt1.dx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

public class NewTestTest {
    private WebDriver driver;

    @Test
    public void test() {
        String title = driver.getTitle();
        boolean check0 = title != null && title.contains("Google");
        if(check0) {
            System.out.println("Trang web hiện tại có tiêu đề là **Google**");
            Reporter.log("Yes, Trang web hiện tại có tiêu đề là **Google**");
        } else {
            System.out.println("Trang web hiện tại không phải tiêu đề là **Google**");
            Reporter.log("Trang web hiện tại không phải tiêu đề là **Google**");
        }
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) {
        driver = new EdgeDriver();
        driver.get("https://www.google.com");
        Reporter.log(browser);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}