package pika.hungt1.dx.extras;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pika.hungt1.dx.core.DriverInitializationV2;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.util.Set;

public class Windows extends DriverInitializationV2 {
    public Windows(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        // testCase1();
        tc2();
    }

    private void testCase1() {
        webDriver.get("https://the-internet.herokuapp.com/windows");
        DriverSyncUtilV2.waitForPresent(By.xpath("//a[normalize-space()='Click Here']")).click();

        // làm việc trên tất cả cửa sổ windows
        Set<String> allHandleWindows = webDriver.getWindowHandles();
        
        // để truy cập đc từng cửa sổ windows, chuyển tập hợp set sang mảng
        Object[] windows = allHandleWindows.toArray();
        webDriver.switchTo().window(windows[1].toString());
    }

    private void tc2() {
        webDriver.get("https://jqueryui.com/droppable");

        //
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        // cuộn khoảng 200px
        js.executeScript("window.scrollBy(0,200)");

        // chuyển đến iframe
        webDriver.switchTo().frame(webDriver.findElement(By.className("demo-frame")));

        // chọn ele nguồn và đích
        WebElement drap = webDriver.findElement(By.id("draggable"));
        WebElement drop = webDriver.findElement(By.id("droppable"));

        //Tạo action trên đối tượng
        Actions act = new Actions(webDriver);
        act.dragAndDrop(drap, drop).perform();

        setAutoShutdown(false);

//        drop.sendKeys(Keys.DELETE);
    }
}


