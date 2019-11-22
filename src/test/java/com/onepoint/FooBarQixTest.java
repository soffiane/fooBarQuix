package com.onepoint;

import org.junit.Assert;
import org.junit.Test;

public class FooBarQixTest {

    @Test
    public void testFooBarQix() {
        FooBarQix fooBarQix = new FooBarQix();
        Assert.assertEquals("1", fooBarQix.compute("1"));
        Assert.assertEquals("FooFoo", fooBarQix.compute("3"));
        Assert.assertEquals("BarBar", fooBarQix.compute("5"));
        Assert.assertEquals("Foo", fooBarQix.compute("6"));
        Assert.assertEquals("QixQix", fooBarQix.compute("7"));
        Assert.assertEquals("Bar*", fooBarQix.compute("10"));
        Assert.assertEquals("FooBarBar", fooBarQix.compute("15"));
        Assert.assertEquals("FooQix", fooBarQix.compute("21"));
        Assert.assertEquals("FooFooFoo", fooBarQix.compute("33"));
        Assert.assertEquals("FooBar", fooBarQix.compute("51"));
        Assert.assertEquals("BarFoo", fooBarQix.compute("53"));

        Assert.assertEquals("1*1", fooBarQix.compute("101"));
        Assert.assertEquals("FooBarQix*Bar", fooBarQix.compute("105"));
        Assert.assertEquals("FooFoo*Foo", fooBarQix.compute("303"));
        Assert.assertEquals("FooQix**", fooBarQix.compute("10101"));
    }
}