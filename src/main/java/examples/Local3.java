package examples;

import dala.Local;
import util.Box;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Local3 {

    static BlockingQueue<Box> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        @Local Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds
        obj.value = "bar";

        // fails -- queue is a *transfer object* to pass object to another thread
        // NOTE: it is perhaps better to enforce this on the consumer side, ie when another
        // thread calls queue::take
        // is there a good abstraction for such transfer objects ?
        queue.put(obj);

    }
}
