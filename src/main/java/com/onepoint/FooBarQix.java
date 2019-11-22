package com.onepoint;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The Foo bar qix class.
 */
public class FooBarQix {

    /**
     * Constants for the String Qix, Foo and Bar.
     */
    private static final String QIX = "Qix";
    private static final String BAR = "Bar";
    private static final String FOO = "Foo";

    /**
     * logger.
     */
    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Compute the input string.
     *
     * @param num             the number to compute in Integer
     * @param numberToCompute the String value to compute
     * @return the computed String
     */
    private static String apply(final int num, final String numberToCompute) {
        String result = "";
        //first we check if the num isDivisible by 3,5 and/or 7
        if (num % 3 == 0) {
            result += FOO;
        }
        if (num % 5 == 0) {
            result += BAR;
        }
        if (num % 7 == 0) {
            result += QIX;
        }

        //if the value to compute is not divisible by 3,5 or 7, we set this boolean to false to indicate that we are going to return the original number
        final boolean isDivisible = !result.isEmpty();
        final boolean containsThreeFiveSeven = numberToCompute.contains("3") || numberToCompute.contains("5") || numberToCompute.contains("7");

        result += numberToCompute.chars()
                .mapToObj(c -> (char) c)
                .map(i -> getString(isDivisible,containsThreeFiveSeven, i))
                .reduce("", (partialString, element) -> partialString + element);

        return result;
    }

    /**
     * Add extra Foo, Bar, Qix string for each occurrence of 3,5 and 7 in num
     * and replace the 0 by * in string.
     *
     * @param isDivisible if the num is divisible by 3,5 and/or 7
     * @param containsThreeFiveSeven
     * @param i           the char of String to compute
     * @return the computed char
     */
    private static String getString(final boolean isDivisible, final boolean containsThreeFiveSeven, final Character i) {
        if(isDivisible || containsThreeFiveSeven){
            if (i == '0') {
                return "*";
            } else if (i == '3') {
                return FOO;
            } else if (i == '5') {
                return BAR;
            } else if (i == '7') {
                return QIX;
            }
        } else {
            if(i == '0'){
                return "*";
            } else return String.valueOf(i);
        }
        return "";
    }

    /**
     * Method to compute string.
     *
     * @param numberToCompute the number to compute
     * @return the computed string
     */
    public String compute(final String numberToCompute) {

        Integer number;
        String result = "";

        try {
            number = Integer.valueOf(numberToCompute);
            result = IntStream.of(number).mapToObj(num -> apply(num, numberToCompute)).collect(Collectors.joining());

        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        return result;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String... args) {
        FooBarQix fooBarQix = new FooBarQix();
        System.out.println(fooBarQix.compute(args[0]));
    }
}
