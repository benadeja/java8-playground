package chapter2;

import java.util.Arrays;
import java.util.List;

public class PrettyPrintApples {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());

    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter a) {
        for (Apple apple : inventory) {
            String output = a.accept(apple);
            System.out.println(output);
        }
    }
}
