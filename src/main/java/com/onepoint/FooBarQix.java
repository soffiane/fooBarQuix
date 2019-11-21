package com.onepoint;


import org.apache.commons.lang3.StringUtils;

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
        String result = "";
        StringBuilder sb = new StringBuilder();
        //first we check if the num isDivisible by 3,5 and/or 7
        if(num % 3 == 0) {
            sb.append(FOO /*+ StringUtils.repeat(FOO, (int) numberToCompute.filter(i -> i == 3).count())*/);
        }
        if(num % 5 == 0) {
            sb.append(BAR /*+ StringUtils.repeat(BAR, (int) numberToCompute.filter(i -> i == 5).count())*/);
        }
        if(num % 7 == 0){
            sb.append(QIX /*+ StringUtils.repeat(QIX, (int) numberToCompute.filter(i -> i == 7).count())*/);
        }

        //then, we add extra Foo, Bar, Qix string for each occurrence of 3,5 and 7 in num
        IntStream.of(Integer.valueOf(numberToCompute)).mapToObj(value -> {
            if (value == 3) {
                sb.append(FOO);
            } else if (value == 5) {
                sb.append(BAR);
            } else if (value == 7) {
                sb.append(QIX);
            }
        };).collect(Collectors.toCollection());
        /*f(num % 3 != 0){
            sb.append(StringUtils.repeat(FOO, (int) numberToCompute.filter(c -> c == '3').count()));
        }
        if(num % 5 != 0){
            sb.append(StringUtils.repeat(BAR, (int) numberToCompute.chars().filter(c -> c == '5').count()));
        }
        if(num % 7 != 0) {
            sb.append(StringUtils.repeat(QIX, (int) numberToCompute.chars().filter(c -> c == '7').count()));
        }*/
        //if num is not divisible by 3,5,7 and does not contain a 3,5 or 7
        //we just return that number
        if(sb.length() == 0) {
            sb.append(num);
        }
        return sb.toString();

        /*if (num % 3 == 0) if (num % 5 == 0) return num % 7 == 0 ? FOO + BAR + QIX : FOO + BAR;
        else return num % 7 == 0 ? FOO + QIX : FOO;
        else if (num % 5 == 0) return num % 7 == 0 ? BAR + QIX : BAR;
        else return num % 7 == 0 ? QIX : String.valueOf(num);*/
    }

    public String compute(String numberToCompute){

        Integer number;
        String result = "";

        try {
            number = Integer.valueOf(numberToCompute);
            result = IntStream.of(number).mapToObj( num -> apply(num,numberToCompute)).collect(Collectors.joining());

            /*final boolean isDivisible = !result.equals("");

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
                    .reduce("", (partialString, element) -> partialString + element);*/

        } catch(NumberFormatException e){
            logger.log(Level.SEVERE,e.getMessage());
        }

        return result;
    }

    public static void main (String... args){
        FooBarQix fooBarQix = new FooBarQix();
        /*for(int i =1 ;i<100;i++){
            System.out.println(fooBarQix.compute(String.valueOf(i)));
        }
        System.out.println(fooBarQix.compute(String.valueOf(101)));
        System.out.println(fooBarQix.compute(String.valueOf(303)));
        System.out.println(fooBarQix.compute(String.valueOf(105)));
        System.out.println(fooBarQix.compute(String.valueOf(10101)));
        System.out.println(fooBarQix.compute(String.valueOf(3)));
        System.out.println(fooBarQix.compute(String.valueOf(5)));
        System.out.println(fooBarQix.compute(String.valueOf(6)));
        System.out.println(fooBarQix.compute(String.valueOf(7)));
        System.out.println(fooBarQix.compute(String.valueOf(9)));
        System.out.println(fooBarQix.compute(String.valueOf(10)));
        System.out.println(fooBarQix.compute(String.valueOf(13)));
        System.out.println(fooBarQix.compute(String.valueOf(15)));
        System.out.println(fooBarQix.compute(String.valueOf(21)));
        System.out.println(fooBarQix.compute(String.valueOf(33)));
        System.out.println(fooBarQix.compute(String.valueOf(51)));*/
        System.out.println(fooBarQix.compute(String.valueOf(53)));

    }
}
