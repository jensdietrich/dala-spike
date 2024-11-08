package examples;

import dala.Local;
import util.Box;

public class Local1 {

    public static void main(String[] args) throws InterruptedException {

        @Local Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds
        obj.value = "bar";

    }
}
