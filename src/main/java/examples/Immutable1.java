package examples;

import dala.Immutable;
import util.Box;

public class Immutable1 {

    public static void main(String[] args) {
        @Immutable Box obj = new Box("foo");
        // now the object pointed to by obj is annotated (not the var)

        // fails as obj is immutable
        obj.value = "bar";
    }
}
