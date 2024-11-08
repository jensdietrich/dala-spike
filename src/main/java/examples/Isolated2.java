package examples;

import dala.Isolated;
import util.Box;

public class Isolated2 {

    public static void main(String[] args) {
        @Isolated Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // fails - no aliasing allowed
        m(obj);
    }

    static void m(Box box) {}
}
