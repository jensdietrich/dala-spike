package examples;

import dala.Immutable;
import util.Box;

public class Immutable3 {

    public static void main(String[] args) {
        @Immutable
        Box obj = new Box("foo");

        // now the object pointed to by obj is annotated (not the var)

        Box obj2 = obj;

        // MUST FAIL
        obj2.value = "bar";
    }

}
