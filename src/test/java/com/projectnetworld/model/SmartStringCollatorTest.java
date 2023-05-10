package com.projectnetworld.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SmartStringCollatorTest {
    //Arrange
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
                Arguments.of(-1, "1-01 Test", "1-01-01 Test"),
                Arguments.of( -1, "foo-2", "foo-03-a")
                );
    }
    @MethodSource("parameter")
    @ParameterizedTest
    void testCompare(int expected, String stringA, String stringB) {
        //Act
        int actual = smartStringCollator.compare(stringA, stringB);
        //Assert
        assertEquals(expected, actual);
    }
}

