package com.kmozaid.designpatterns.abstractbuilder.example1;

import static com.kmozaid.designpatterns.abstractbuilder.example1.NyPizza.Size.SMALL;
import static com.kmozaid.designpatterns.abstractbuilder.example1.Pizza.Topping.HAM;
import static com.kmozaid.designpatterns.abstractbuilder.example1.Pizza.Topping.ONION;
import static com.kmozaid.designpatterns.abstractbuilder.example1.Pizza.Topping.SAUSAGE;

public class TestMain {

    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM).sauceInside(true).build();

        System.out.println(nyPizza);
        System.out.println(calzone);
    }
}
