package pika.hungt1.dx.exam;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pika.hungt1.dx.core.DriverInitializationV2;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.time.Duration;
import java.util.List;

public class ExamTesting1 extends DriverInitializationV2 {

    public ExamTesting1(Platform platform) {
        super(platform);
    }

    /**
     * Đề số 1:
     Cho trang web <a href="https://lib.qnu.edu.vn/">https://lib.qnu.edu.vn/</a> chứa thông tin về thư viện trường ĐHQN.
     Thiết kế kịch bản test để test automation cho các chức năng sau:
     - Tìm kiếm 5 tài liệu số về lĩnh vực Công nghệ thông tin
     - Tính tổng số lượng người truy cập vào trang tính từ tháng 1/2025 đến tháng 10/2025
     */
    @Override
    public void setupTestCases() {

        this.setAutoShutdown(false);
        /*
        Test Case 1: Tìm kiếm 5 tài liệu số về lĩnh vực công nghệ thông tin

        Các bước thực hiện:
        Bước 1: Vào trang web https://lib.qnu.edu.vn/
        Bước 2: Tìm thanh tìm kiếm và điền cụm từ "Công nghệ thông tin"
        Bước 3: Tìm nút radio Tài liệu số và click vào đó
        Bước 4: Nhấp vào ô tìm kiếm
        Bước 5: Kiểm tra các tài liệu số xuất hiện trên màn hình
         */
        testCase1();

        // mở tab mới
        webDriver.switchTo().newWindow(WindowType.TAB);

        /*
        Test Case 2: Tính tổng số lượng người truy cập vào trang tính từ tháng 1/2025 đến tháng 10/2025

        Các bước thực hiện
        Bước 1: Vào trang web https://lib.qnu.edu.vn/
        Bước 2: Cuộn xuống gần cuối trang
        Bước 3: Chọn tháng 1 tại phần thống kê truy cập
        Bước 4: Kiểm tra số người truy cập ở bên phải của phần chọn tháng và năm
        Bước 5: Lặp lại bước 3 và 4 cho đến khi đến tháng 10/2025. Đồng thời tính tổng số lần truy cập trong thời
        gian đó.
         */
        testCase2();
    }

    private void testCase1() {
        System.out.println("===== Test Case 1: =====");

        // Bước 1: Vào trang web https://lib.qnu.edu.vn/
        webDriver.get("https://lib.qnu.edu.vn/");

        // Bước 2: Tìm thanh tìm kiếm và điền cụm từ "Công nghệ thông tin"
        WebElement searchBoxElement = DriverSyncUtilV2.waitUntilVisible(By.xpath("//input[@id='ctl00_phContent_ucPortalCenterRightBoxSearch_txtText']"));
        searchBoxElement.sendKeys("Công nghệ thông tin");

        // Bước 3: Tìm nút radio Tài liệu số và click vào đó
        WebElement radioButton = webDriver.findElement(By.xpath("//label[@for='ctl00_phContent_ucPortalCenterRightBoxSearch_optThuvienso']"));
        radioButton.click();

        // Bước 4: Nhấp vào ô tìm kiếm
        webDriver.findElement(By.id("ctl00_phContent_ucPortalCenterRightBoxSearch_btnPatronLogon")).click();

        // Bước 5: Kiểm tra các tài liệu số xuất hiện trên màn hình
        // tìm bảng chứa các tài liệu
        WebElement documentTable = DriverSyncUtilV2.waitForPresent(By.xpath("//*[@id=\"ctl00_phContent_grvPage\"]/tbody/tr[2]/td/div"));

        // tìm các ô tài liệu và kể vào trong list
//        List<WebElement> documentElements = documentTable.findElements(By.xpath("//tbody/tr"));
        List<WebElement> documentElements = documentTable.findElements(By.className("col-md-6"));
        for (int i = 0; i < Math.min(documentElements.size(), 5); i++) {
            // in các thông tin về từng tài liệu ra màn hình
            WebElement element = documentElements.get(i);
            System.out.println("Tài liệu thứ " + (i + 1) + "\n" + element.getText() + "\n\n");
        }
    }

    private void testCase2() {
        System.out.println("===== Test Case 2: =====");
        int totalVisitors = 0;

        // Bước 1: Vào trang web https://lib.qnu.edu.vn/
        webDriver.get("https://lib.qnu.edu.vn/");

        // Bước 2: Cuộn xuống gần cuối trang
        try {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            // cuộn trang
            js.executeScript("window.scrollBy(0,6000)");
        } catch (Exception ignored) { }

        // Bước 3: Chọn tháng 1 tại phần thống kê truy cập
        // tìm kiếm selector element của tháng
        WebElement monthSelElement = DriverSyncUtilV2.waitUntilClickable(By.xpath("//select[@id='ctl00_phContent_ucPortalLeftStatistic_ddlMonth']"));
        Select monthSelect = new Select(monthSelElement);

        // chọn tháng 1
        monthSelect.selectByIndex(0);
        // kiểm tra số lượng người truy cập
        WebElement visitorsElement = webDriver.findElement(By.id("ctl00_phContent_ucPortalLeftStatistic_lblThongKeLuotTruyCapChiTiet"));

        // in ra màn hình
        String visitorsInMonth = visitorsElement.getText();
        System.out.println("Số người truy cập trong tháng 1: " + visitorsInMonth);

        // xóa dấu chấm trong chuỗi
        visitorsInMonth = visitorsInMonth.replace(".", "");
        // tính tổng
        totalVisitors = totalVisitors + Integer.parseInt(visitorsInMonth);

        // Bước 4 + 5: Lặp lại các bước trên
        for(int i = 1; i <= Math.min(9, monthSelect.getOptions().size()); i++) {
            monthSelect.selectByIndex(i);
            // chờ text thay đổi sau khi select
            // ExamTesting1.waitForTextChange(webDriver, visitorsElement, visitorsInMonth, 5);

            sleep(1000);
            visitorsElement = webDriver.findElement(By.id("ctl00_phContent_ucPortalLeftStatistic_lblThongKeLuotTruyCapChiTiet"));

            visitorsInMonth = visitorsElement.getText();
            System.out.println("Số người truy cập trong tháng " + (i+1) + ": " + visitorsInMonth);

            visitorsInMonth = visitorsInMonth.replace(".", "");
            totalVisitors = totalVisitors + Integer.parseInt(visitorsInMonth);

            // Reassign variable
            monthSelElement = DriverSyncUtilV2.waitUntilClickable(By.xpath("//select[@id='ctl00_phContent_ucPortalLeftStatistic_ddlMonth']"));
            monthSelect = new Select(monthSelElement);
        }

        System.out.printf("Tổng cộng có %d người truy cập trong 10 tháng đầu của 2025.%n", totalVisitors);
    }

    public static void waitForTextChange(WebDriver driver, WebElement element, String oldText, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElement(element, oldText)
        ));
    }
}
