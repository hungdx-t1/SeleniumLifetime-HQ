package pika.hungt1.dx.interfaces;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public interface IActionLog {

    default void logConsole(String ctx) {
        Logger.getLogger(getClass().getSimpleName()).log(Level.INFO, ctx);
    }

    default void severeConsole(String ctx) {
        Logger.getLogger(getClass().getSimpleName()).log(Level.SEVERE, ctx);
    }

    default void warnConsole(String ctx) {
        Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, ctx);
    }

    default void defaultPrint(String ctx) {
        System.out.println(ctx);
    }
}
