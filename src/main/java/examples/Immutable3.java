package examples;

import dala.Immutable;
import util.Box;

public class Immutable3 {

    public static void main(String[] args) {
        @Immutable Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // local alias succeeds
        Box obj2 = obj;

        // fails as object obj2 points to is immutable
        obj2.value = "bar";
    }

}
