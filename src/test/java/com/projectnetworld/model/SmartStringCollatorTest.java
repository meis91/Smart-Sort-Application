package com.projectnetworld.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SmartStringCollatorTest {
    SmartStringCollator smartStringCollator = new SmartStringCollator(Locale.getDefault());
    public static Stream<Arguments> parameter (){
        return Stream.of(
                Arguments.of(0, " ", " "),
                Arguments.of(0, " ", "  "),
                Arguments.of(0, "-", "-"),
                Arguments.of(0, "1", "1"),
                Arguments.of(0, "x", "x"),
                Arguments.of(0, "foo", "foo"),
                Arguments.of(0, "foo", "Foo"),
                Arguments.of(-1, " ", "-"),
                Arguments.of(-1, "  ","-+" ),
                Arguments.of(-1, "-", "1"),
                Arguments.of(-1, "--", "1"),
                Arguments.of(-1, "1", "a"),
                Arguments.of(-1, "-1", "a"),
                Arguments.of(-1, "100000000000000000000", "a"),
                Arguments.of(-1, "1", "2"),
                Arguments.of(-1, "1", "200000000000000000000"),
                Arguments.of(-1, "a", "b"),
                Arguments.of(-1, "a", "aa"),
                Arguments.of(-1, "300000000000000000000000000002", "30000000000000000000000000000010"),
                Arguments.of( 1, "30000000000000000000000000000010",  "300000000000000000000000000002"),
                Arguments.of(-1, "1-01 Test", "1-01-01 Test"),
                Arguments.of(-1, "x  1", "x 2"),
                Arguments.of(-1, "1-01 Test", "1-01-01 Test"),
                Arguments.of( -1, "foo-2", "foo-03-a"),
                Arguments.of(-1, "a", "b"),
                Arguments.of(-1, "a", "b"),
                Arguments.of(-1, "a", "b")


                );
    }
    @MethodSource("parameter")
    @ParameterizedTest
    void testCompare(int expected, String stringA, String stringB) {
        int actual = smartStringCollator.compare(stringA, stringB);

        assertEquals(expected, actual);
        /*assertEquals(0, (new SmartStringCollator(Locale.getDefault())).compare("foo", "foo"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare(" ", "foo"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare("9", "foo"));
        assertEquals(1, (new SmartStringCollator(Locale.getDefault())).compare("U", "foo"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare("\\s+", "foo"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare("42", "foo"));
        assertEquals(1, (new SmartStringCollator(Locale.getDefault())).compare("foo", " "));
        assertEquals(1, (new SmartStringCollator(Locale.getDefault())).compare("foo", "9"));
        assertEquals(1, (new SmartStringCollator(Locale.getDefault())).compare("foo", "\\s+"));
        assertEquals(1, (new SmartStringCollator(Locale.getDefault())).compare("foo", ""));
        assertEquals(0, (new SmartStringCollator(Locale.getDefault())).compare(" ", " "));
        assertEquals(0, (new SmartStringCollator(Locale.getDefault())).compare("9", "9"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare("9", "42"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare("\\s+", "9"));
        assertEquals(0, (new SmartStringCollator(Locale.getDefault())).compare("\\s+", "\\s+"));
        assertEquals(1, (new SmartStringCollator(Locale.getDefault())).compare("\\s+", "\\d+"));
        assertEquals(-1, (new SmartStringCollator(Locale.getDefault())).compare("\\s+", "\\w+|[a-zA-Z]"));
        assertEquals(0, (new SmartStringCollator(Locale.getDefault())).compare("\\w+|[a-zA-Z]", "\\w+|[a-zA-Z]"));*/
    }
}

