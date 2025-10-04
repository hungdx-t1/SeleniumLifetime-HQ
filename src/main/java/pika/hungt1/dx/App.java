package pika.hungt1.dx;

import pika.hungt1.dx.exercises.*;
import pika.hungt1.dx.extras.*;
import pika.hungt1.dx.models.Platform;

public class App {
    public static void main( String[] args ) {
        Exercise3 exercise3 = new Exercise3(Platform.EDGE);
        exercise3.handle();
    }
}
