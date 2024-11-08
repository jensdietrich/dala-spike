package examples;

import dala.Local;
import util.Box;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Local1 {

    static BlockingQueue<Box> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        @Local Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds
        obj.value = "bar";

    }
}
