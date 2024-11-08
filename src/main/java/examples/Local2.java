package examples;

import dala.Local;
import util.Box;

public class Local2 {

    public static void main(String[] args) throws InterruptedException {

        @Local Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds , thread-local alias
        m(obj);

    }

    public static void m(Box box) {
        // succeeds
        box.value = "bar";
    }
}
