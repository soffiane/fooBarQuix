package com.onepoint;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FooBarQix {

    private static final String QIX = "Qix";
    private static final String BAR = "Bar";
    private static final String FOO = "Foo";

    Logger logger = Logger.getLogger(this.getClass().getName());

    private static String apply(int num) {
        return num % 3 == 0
                ? (num % 5 == 0
                ? (num % 7 == 0 ? FOO + BAR + QIX : FOO + BAR)
                : (num % 7 == 0 ? FOO + QIX : FOO))
                : (num % 5 == 0
                ? (num % 7 == 0 ? BAR + QIX : BAR)
                : (num % 7 == 0 ? QIX : String.valueOf(num)));
    }

    public String compute(String numberToCompute){

        Integer number;
        String result = "";

        try {
            number = Integer.valueOf(numberToCompute);
            result = IntStream.of(number).mapToObj(FooBarQix::apply).collect(Collectors.joining());

            final boolean isDivisible = !result.equals("");

            result += numberToCompute.chars()
                    .mapToObj(c -> (char) c)
                    .map(i -> {
                        return i == '0'
                                ? ("*")
                                : (i == '3'
                                ? (FOO)
                                : (i == '5'
                                ? (BAR)
                                : (i == '7'
                                ? (QIX)
                                : (!isDivisible
                                ? (String.valueOf(i))
                                : ("")))));
                    })
                    .reduce("", (partialString, element) -> partialString + element);

        } catch(NumberFormatException e){
            logger.log(Level.SEVERE,e.getMessage());
        }

        return result;
    }

    public static void main (String... args){
        FooBarQix fooBarQix = new FooBarQix();
        /*for(int i =1 ;i<100;i++){
            System.out.println(fooBarQix.compute(String.valueOf(i)));
        }*/
        System.out.println(fooBarQix.compute(String.valueOf(101)));
        System.out.println(fooBarQix.compute(String.valueOf(303)));
        System.out.println(fooBarQix.compute(String.valueOf(105)));
        System.out.println(fooBarQix.compute(String.valueOf(10101)));

    }
}
