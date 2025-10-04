package pika.hungt1.dx.exercises;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pika.hungt1.dx.core.DriverInitialization;

public class Exercise2 extends DriverInitialization {

    public Exercise2(String plat) {
        super(plat);
    }

    @Override
    public void setupTestCases() {
        tc1();
        tc2();
        try {
            tc3();
        } catch (Exception ignored) {}
    }

    private void tc1() {
        WebDriver drv = new EdgeDriver();
        drv.get("https://daotao.qnu.edu.vn");
        drv.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(drv.getTitle());
        System.out.println(drv.getCurrentUrl());
        drv.close();
    }

    private void tc2() {
        WebDriver drv = new EdgeDriver();
        drv.get("https://www.google.com");
        drv.manage().window().maximize();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) { }

        String title = drv.getTitle();
        boolean check0 = title != null && title.contains("Google");
        if(check0) {
            System.out.println("Trang web hiện tại có tiêu đề là **Google**");
        } else {
            System.out.println("Trang web hiện tại không phải tiêu đề là **Google**");
        }

        String url = drv.getCurrentUrl();
        boolean check = url != null && url.contains("google.co.in"); // tránh nullpointerexception
        if(check) {
            System.out.println("Trang web hiện tại là trang **google.co.in**");
        } else {
            System.out.println("Trang web hiện tại không phải là trang **google.co.in**");
        }
        String pageSource = drv.getPageSource();
        if(pageSource != null) { // tránh nullpointerexception
            int length = pageSource.length();
            System.out.println(pageSource);
            System.out.println("Độ dài nguồn trang: " + length);
        } else {
            System.out.println("Không tìm thấy nguồn trang");
        }

        drv.close();
    }

    public void tc3() throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.google.com");
        Thread.sleep(500);

        driver.manage().window().maximize();
        Thread.sleep(500);

        driver.navigate().to("https://www.facebook.com");
        Thread.sleep(500);
        driver.navigate().back();
        Thread.sleep(500);
        driver.navigate().forward();
        Thread.sleep(500);
        driver.navigate().refresh();
        Thread.sleep(500);
        driver.quit();
    }
}
