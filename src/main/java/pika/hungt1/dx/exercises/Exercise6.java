package pika.hungt1.dx.exercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pika.hungt1.dx.config.MyConfig;
import pika.hungt1.dx.core.DriverInitializationV2;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.utils.ConsoleUtil;
import pika.hungt1.dx.utils.DriverSyncUtilV2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise6 extends DriverInitializationV2 {
    public Exercise6(Platform platform) {
        super(platform);
    }

    @Override
    public void setupTestCases() {
        /*
        Test case 1: Kiểm tra xem các môn học có bao nhiêu tín chỉ
         */
        testCase1();
    }

    private void testCase1() {
        // B1: Vào trang web
        webDriver.get("https://tinchi.qnu.edu.vn/");

        // B2: Điền thông tin và nhấn nút đăng nhập
        WebElement userBox = DriverSyncUtilV2.waitUntilVisible(By.name("username"));
        WebElement passBox = DriverSyncUtilV2.waitUntilVisible(By.name("password"));
        userBox.sendKeys(MyConfig.USERNAME);
        passBox.sendKeys(MyConfig.PASSWORD);
        passBox.submit();

        // B3: Tìm nút "Thời khóa biểu" và nhấn vào
        DriverSyncUtilV2.waitUntilClickable(By.xpath("//a[@href='/ThoiKhoaBieu']")).click();

        // B4: Chọn năm học 2025-2026
        WebElement yearSelectorElement = DriverSyncUtilV2.waitUntilClickable(By.name("YearStudy"));
        Select yearSelector = new Select(yearSelectorElement);
        yearSelector.selectByVisibleText("2025-2026");

        // B5: Chọn học kì 1
        WebElement termSelectorElement = DriverSyncUtilV2.waitUntilClickable(By.name("TermID"));
        Select termSelector = new Select(termSelectorElement);
        termSelector.selectByVisibleText("Học kỳ 1");

        // B6: Chọn tuần 2 (không bắt buộc)
        WebElement weekSelectorElement = DriverSyncUtilV2.waitUntilClickable(By.name("Week"));
        Select weekSelector = new Select(weekSelectorElement);
        weekSelector.selectByVisibleText("2");

        // B7: Tìm dropdown chọn tuần hoặc học phần
        WebElement modeSelectorElement = DriverSyncUtilV2.waitUntilClickable(By.name("TypeID"));
        Select modeSelector = new Select(modeSelectorElement);
        modeSelector.selectByIndex(1);

        // B8: Duyệt các thông tin ở trong bảng (test)
        // Tìm table bằng xpath
        By tableLocator = By.xpath("//table[@class='table table-hover']");
        DriverSyncUtilV2.waitUntilVisible(tableLocator); // Đợi table cũ biến mất (nếu có)
        WebElement schedulerTableElement = DriverSyncUtilV2.waitUntilVisible(tableLocator); // Đợi table mới xuất hiện

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // reassign
        modeSelectorElement = DriverSyncUtilV2.waitUntilClickable(By.name("TypeID"));
        modeSelector = new Select(modeSelectorElement);

        // Lấy list các dòng trong table
        List<WebElement> rows = schedulerTableElement.findElements(By.xpath("//tbody/tr"));

        WebElement targetRow = null;
        try {
            for(WebElement row : rows) {
                String contextInRow = row.getText();
                if(contextInRow.contains("Đinh Thị Mỹ Cảnh") && contextInRow.contains("Kiểm thử tự động")) {
                    targetRow = row;
                    // System.out.println(contextInRow + "\n\n\n\n\n");
                    break; // ngưng vòng lặp for
                }
            }

            if(targetRow != null) {
//                List<String> parts = new ArrayList<>(Arrays.asList(targetRow.getText().split(".0 ")));
//                for(String part : parts) {
//                    System.out.println(part);
//                }
//                parts.removeFirst(); // xóa chuỗi đầu tiên
//                String last = parts.getLast();
//                last = last.substring(0, last.length() - 22).trim(); // cắt bỏ ngày tháng
//                last = last.substring(0, last.length() - "Đinh Thị Mỹ Cảnh".length()); // xóa tên
//                parts.set(parts.size() - 1, last); // đặt lại chuỗi cuối cùng thành chuỗi đã cắt (last)
//                for(String part : parts) {
//                    System.out.println(part);
//                }
                int periods = this.countAllPeriods(targetRow.getText());
                System.out.println("Tổng cộng có " + periods + " tiết trong 1 khóa");

            } else {
                System.out.println("Không tìm thấy môn học nào có tên Kiểm thử tự động với giảng viên Đinh Thị Mỹ Cảnh");
            }
        } catch (Exception e) {
            ConsoleUtil.printError(e, Exercise6.class, "testCase1()");
        }
    }

    private int countAllPeriods(String ctx) {
        int periods = 0;

//        String ctx = """
//                4 251105030601 Kiểm thử tự động 4.0 Thứ 3, Tiết: 1-2, Tuần: T2->T16, Phòng: 4T.11.2
//                Thứ 5, Tiết: 8-10, Tuần: T2->T16, Phòng: A1.407
//                Thứ 7, Tiết: 8-9, Tuần: T2->T16, Phòng: 4T.11.2 Đinh Thị Mỹ Cảnh 02/09/2025 13/12/2025
//                """;

        List<String> parts = new ArrayList<>(Arrays.asList(ctx.split(".0 ")));

        parts.removeFirst(); // xóa chuỗi đầu tiên
        String last = parts.getLast(); // lấy chuỗi tuần đi học
        last = last.substring(0, last.length() - 22).trim(); // cắt bỏ ngày tháng
        last = last.substring(0, last.length() - " Đinh Thị Mỹ Cảnh".length()); // xóa tên
        parts.set(parts.size() - 1, last); // đặt lại chuỗi cuối cùng thành chuỗi đã cắt (last)
//        for(String part : parts) {
//            System.out.println(part);
//        }

        // Lúc này chuỗi trở thành
        /*
        Thứ 3, Tiết: 1-2, Tuần: T2->T16, Phòng: 4T.11.2
        Thứ 5, Tiết: 8-10, Tuần: T2->T16, Phòng: A1.407
        Thứ 7, Tiết: 8-9, Tuần: T2->T16, Phòng: 4T.11.2
         */ // 1 part = 1 dòng

        // Phân tách các dòng thành chuỗi
        String[] lines = parts.getLast().split("\n");
        ConsoleUtil.printAll(lines);
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));

        for (String line : lineList) {
            // System.out.println(line);
            // line là "Thứ 3, Tiết: 1-2, Tuần: T2->T16, Phòng: 4T.11.2"
            // Phân tách dấu phẩy
            String[] parts1 = line.split(", ");
            // Lấy phần tử thứ 2 và 3 (tiết và tuần), xóa chữ "Tiết: " và "Tuần: " đi
            String periodsString = parts1[1].substring("Tiết: ".length());
            String weeksString = parts1[2].substring("Tuần: ".length());

            // ConsoleUtil.printAll(periodsString, weeksString);
            /*
            Kết quả
            1-2
            T2->T16
             */

            // lấy tiết đầu và tiết cuối của 1 tuần
            String[] periodsParts = periodsString.split("-");
            int firstPeriod = tryParseInteger(periodsParts[0]);
            int lastPeriod = tryParseInteger(periodsParts[1]);

            // tổng số tiết trong 1 tuần
            int periodsPerWeek = lastPeriod - firstPeriod + 1;

            // lấy tuần đầu và tuần cuối trong 1 học kì
            String[] weekParts = weeksString.substring(1).split("->T"); // xóa chữ T đằng trước
            int firstWeek = tryParseInteger(weekParts[0]);
            int lastWeek = tryParseInteger(weekParts[1]);

            // tổng số tuần trong khóa học
            int totalWeeks = lastWeek - firstWeek + 1;

            periods = periods + periodsPerWeek * totalWeeks;
        }
        return periods;
    }

    private int tryParseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ConsoleUtil.printError(e, Exercise6.class, "tryParseInteger(String)");
        }
        return 0;
    }
}
