package pika.hungt1.dx.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleUtil {
    public static void printAll(String... text) {
        for(String s : text) {
            System.out.println(s);
        }
    }

    public static void printAll(List<String> text) {
        for(String s : text) {
            System.out.println(s);
        }
    }

    public static void printErrorLegacy(Throwable th) {
        th.printStackTrace();
    }

    public static void printError(Throwable th, Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getSimpleName());
        logger.log(Level.SEVERE, clazz.getSimpleName() + " error!", th);
    }

    public static void printError(Throwable th, Class<?> clazz, String methodName) {
        Logger logger = Logger.getLogger(clazz.getSimpleName());
        logger.log(Level.SEVERE, clazz.getSimpleName() + "#" + methodName + " error!", th);
    }
}
