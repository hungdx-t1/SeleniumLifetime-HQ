package pika.hungt1.dx;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class DemoTest {

    // chạy từ BeforeSuite -> BeforeTest -> BeforeClass -> BeforeMethod
    // -> Test
    // -> AfterMethod -> AfterClass -> AfterTest -> AfterSuite
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
        Reporter.log("");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 executing");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }
}
