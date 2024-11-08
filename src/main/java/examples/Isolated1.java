package examples;

import dala.Isolated;
import util.Box;

public class Isolated1 {

    public static void main(String[] args) {
        @Isolated Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // success, no aliasing allowed but can tolerate in-method aliasing
        // as instrumentation resolved references
        Box m2 = obj;

        // success, can locally mutate
        m2.value = "bar";
    }
}
