package com.onepoint;


import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FooBarQix {

    private static final String QIX = "Qix";
    private static final String BAR = "Bar";
    private static final String FOO = "Foo";

    Logger logger = Logger.getLogger(this.getClass().getName());

    private static String apply(int num, String numberToCompute) {
        StringBuilder sb = new StringBuilder();
        //first we check if the num isDivisible by 3,5 and/or 7
        if (num % 3 == 0) {
            sb.append(FOO);
        }
        if (num % 5 == 0) {
            sb.append(BAR);
        }
        if (num % 7 == 0) {
            sb.append(QIX);
        }

        //then, we add extra Foo, Bar, Qix string for each occurrence of 3,5 and 7 in num
        numberToCompute.chars().forEach(c -> {
            if ((char) c == '3') {
                sb.append(FOO);
            } else if ((char) c == '5') {
                sb.append(BAR);
            } else if ((char) c == '7') {
                sb.append(QIX);
            }
        });
        //if num is not divisible by 3,5,7 and does not contain a 3,5 or 7
        //we just return that number
        if (sb.length() == 0) {
            sb.append(num);
        }
        //step 2 : replace 0 by *
        return sb.toString();
    }

    public String compute(String numberToCompute) {

        Integer number;
        String result = "";

        try {
            number = Integer.valueOf(numberToCompute);
            result = IntStream.of(number).mapToObj(num -> apply(num, numberToCompute)).collect(Collectors.joining());

            //STEP 2 : replace 0 in number by *
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

        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return result;
    }

    public static void main(String... args) {
        FooBarQix fooBarQix = new FooBarQix();
        /*for (int i = 1; i < 100; i++) {
            System.out.println(fooBarQix.compute(String.valueOf(i)));
        }*/
        System.out.println(fooBarQix.compute(String.valueOf(101)));
        System.out.println(fooBarQix.compute(String.valueOf(303)));
        System.out.println(fooBarQix.compute(String.valueOf(105)));
        System.out.println(fooBarQix.compute(String.valueOf(10101)));
        System.out.println(fooBarQix.compute(String.valueOf(10)));
        System.out.println(fooBarQix.compute(String.valueOf(20)));
        System.out.println(fooBarQix.compute(String.valueOf(30)));
        System.out.println(fooBarQix.compute(String.valueOf(40)));
        System.out.println(fooBarQix.compute(String.valueOf(50)));
        System.out.println(fooBarQix.compute(String.valueOf(60)));
        System.out.println(fooBarQix.compute(String.valueOf(70)));
        System.out.println(fooBarQix.compute(String.valueOf(80)));
        System.out.println(fooBarQix.compute(String.valueOf(90)));
        System.out.println(fooBarQix.compute(String.valueOf(100)));
    }
}
