package examples;

import dala.Isolated;
import util.Box;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Isolated3 {

    static BlockingQueue<Box> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        @Isolated Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds, mutating is ok as long as the thread owns the object
        obj.value = "bar";

        // succeeds, puts object in transfer state
        queue.put(obj);

        // fails, now control has been passed to another thread
        // so this thread cannot mutate this anymore
        obj.value = "bar2";
    }

}
