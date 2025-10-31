package pika.hungt1.dx.extras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pika.hungt1.dx.core.DriverInitialization;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtil;

import java.util.List;

public class AutomationTesting3 extends DriverInitialization {

    public AutomationTesting3(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        /*
        Lấy tên và giá của sản phẩm

         */
        // testCase1();

        /*
        Kiểm thử chức năng select options
         */
        testCase2();
    }

    private void testCase1() {
        webDriver.get("https://practice.bpbonline.com/index.php");

        // Tìm table
        WebElement table = DriverSyncUtil.waitUntilVisible(
                webDriver,
                By.xpath("//div[@class='contentText']//table"));

        // các dòng
        // nếu viết cả [2] thì cái List<Web...> chả khác gì 1 WE đơn
        // List<WebElement> rows = table.findElements(By.xpath("//tbody/tr[2]"));
        List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));

        // lấy số lượng sản phẩm
        int count = 0;
        String ctx = "";
        for(WebElement row : rows) {
            // lấy sp trên từng dòng
            List<WebElement> products = row.findElements(By.xpath("td"));

            for (WebElement product : products) {
                ctx = product.getText();
                count++;
                //System.out.println(ctx);

                // Bonus : Tách lấy tên sản phẩm
                String[] ctxs = ctx.split("\n");
                String name = ctxs[0];
                float price = Float.parseFloat(ctxs[1].substring(1));

                System.out.println("Product: " + name + " - Price: " + price);

            }
        }

        System.out.printf("Tổng số sản phẩm: %d%n", count);


       // System.out.println(table.getText());
    }

    private void testCase2() {
        noShutdown();
        webDriver.get("https://demo.guru99.com/test/newtours/register.php");

        WebElement selectOption = DriverSyncUtil.waitUntilVisible(
                webDriver,
                By.xpath("//select[@name='country']")
        );

        // Khai báo kiểu đối tượng
        Select select = new Select(selectOption);

        select.selectByIndex(3);
        String ctx = select.getOptions().get(3).getText();

        List<WebElement> elements = select.getOptions();
        for(WebElement element : elements) {
            System.out.println(element.getAttribute("value"));
        }
    }
}
