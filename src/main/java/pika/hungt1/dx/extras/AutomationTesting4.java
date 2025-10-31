package pika.hungt1.dx.extras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pika.hungt1.dx.core.DriverInitialization;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class AutomationTesting4 extends DriverInitialization {

    public AutomationTesting4(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        /*
        TC1: Kiểm tra các mặt hàng trên từng danh mục

        B1: Vào trang web https://practice.bpbonline.com/index.php
        B2: Vào Manufacturer, chọn dropdown option
        B3: Chọn Canon
        B4: Kiểm tra xem có mặt hàng không
        B5: Chọn dropdown option, chọn Fox
        B6: Tiếp tục lặp lại các công việc ở bước 5 với các option khác
         */
        testCase1();
    }

    private void testCase1() {
        // B1: vào trang web
        webDriver.get("https://practice.bpbonline.com/index.php");

        // B2: chọn hãng sản xuất
        // Tìm menu facturers dropdown option element
        WebElement manufacturers = webDriver.findElement(By.name("manufacturers_id"));
        Select select = new Select(manufacturers);

        // Lấy tên của các hãng sx
        List<WebElement> manus = select.getOptions();

        // Xóa phần tử đầu tiên
        manus.removeFirst();

        // Lưu tên hãng sản xuất
        List<String> manuNameList = new ArrayList<>();
        for (WebElement manu : manus) {
            manuNameList.add(manu.getText());
        }

        // Duyệt từng string để chọn lấy menu
        for (String manuName : manuNameList) {
            try {
                select.selectByVisibleText(manuName);

                // chờ DOM cũ biến mất (?)
           //     DriverSyncUtil.forElementStale(webDriver, manufacturers);

                // xử lý trạng thái thay đổi (xem lại cái select option)
                manufacturers = webDriver.findElement(By.xpath("//select[@name='manufacturers_id']"));
             //   manufacturers = DriverSyncUtil.waitUntilVisible(webDriver, 10, By.name("manufacturers_id"));
                select = new Select(manufacturers);

                String str = "There are no products available in this category.";

                if(webDriver.getPageSource() == null || webDriver.getPageSource().contains(str)) {
                    System.out.println("Không có sản phẩm nào cho danh mục " + manuName);
                    continue;
                }

                // Chuyển đến lọc
                WebElement table = DriverSyncUtil.waitUntilVisible(
                        webDriver,
                        7,
                        By.xpath("//table[@class='productListingData']")
                );

                List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
                // 1 rows bằng 1 product

                System.out.printf("Danh mục %s%n", manuName);
                for(WebElement product : rows) {
                    // Bỏ cụm từ Buy Now và các dấu cách thừa
                    String text = product.getText().replace("Buy Now", "").trim();

                    if(text.isEmpty()) continue; // bỏ qua dòng trống
                    String[] parts = text.split("\\$");

                    String productName = parts[0].trim();
                    float oldPrice = 0, newPrice = 0;

                    if(parts.length == 2) {
                        // chỉ có 1 giá (giá gốc)
                        newPrice = Float.parseFloat(parts[1].trim());
                        System.out.printf("- %s | Price: %.2f%n", productName, newPrice);
                    } else if (parts.length >= 3) {
                        // có giá cũ và giá mới
                        oldPrice = Float.parseFloat(parts[1].trim());
                        newPrice = Float.parseFloat(parts[2].trim());
                        System.out.printf("- %s | Old: %.2f | New: %.2f%n", productName, oldPrice, newPrice);
                    }
                }
                System.out.println("\n");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "testCase1() error!" , e);
            }
        }
    }
}
