package pika.hungt1.dx;

import pika.hungt1.dx.extras.TutorialsPoint;
import pika.hungt1.dx.models.Platform;
import pika.hungt1.dx.screenshot.ScreenShotsAT;

public class App {
    public static void main( String[] args ) {
//        Exercise6 ex6 = new Exercise6(Platform.EDGE);
//        ex6.handle();

//        TutorialsPoint tutorialsPoint = new TutorialsPoint(Platform.EDGE);
//        tutorialsPoint.handle();

        ScreenShotsAT at = new ScreenShotsAT(Platform.EDGE);
        at.handle();
    }
}
