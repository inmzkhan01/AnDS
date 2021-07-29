package com.kmozaid.java.quizzes;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Lambda {

    /*
     *  var
     *  Supplier<Consumer<?>>
     *  Function<Consumer<?>,?>
     *  Function<Predicate<?>,?>
     *  Consumer<Function<?,?>>
     */

    public void doIt() {
        Supplier<Consumer<?>> v1 = () -> a -> {
        };
    }


}
