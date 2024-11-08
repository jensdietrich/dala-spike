package examples;

import dala.Isolated;
import util.Box;

public class Isolated4 {

    public static void main(String[] args) {
        @Isolated Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds - invocation-local aliasing can be tolerated
        // instrumentation will see actual object
        Box obj2 = obj;

        // fails - but in general aliasing is not permitted
        m(obj2);

    }

    public static void m(Box box) {
        // fails as object is immutable
        box.value = "bar";
    }

}
