package examples;

import dala.Immutable;
import util.Box;

public class Immutable2 {

    public static void main(String[] args) {
        @Immutable Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // succeeds - objects can be aliased
        m(obj);
    }

    public static void m(Box box) {
        // fails as object is immutable
        box.value = "bar";
    }

}
