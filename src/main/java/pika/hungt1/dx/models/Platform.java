package pika.hungt1.dx.models;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public enum Platform {

    EDGE(EdgeDriver.class),
    CHROME(ChromeDriver.class),
    FIREFOX(FirefoxDriver.class);

    private final Class<? extends WebDriver> driverClass;

    private static final Logger logger = Logger.getLogger(Platform.class.getSimpleName());

    Platform(Class<? extends WebDriver> driverClass) {
        this.driverClass = driverClass;
    }

    public Class<? extends WebDriver> getDriverClass() {
        return this.driverClass;
    }

    @Nullable
    public WebDriver createDriver() {
        try {
            return driverClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            logger.severe("Cannot initialize driver " + this.name() + ": " + e.getMessage());
            return null;
        }
    }

    public static Platform parsePlatform(String plat) {
        return switch (plat.toLowerCase()) {
            case "edge" -> Platform.EDGE;
            case "chrome" -> Platform.CHROME;
            case "firefox" -> Platform.FIREFOX;
            default -> throw new RuntimeException("Cannot find platform for " + plat);
        };
    }
}
