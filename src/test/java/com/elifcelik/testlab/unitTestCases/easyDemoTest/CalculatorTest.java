package com.elifcelik.testlab.unitTestCases.easyDemoTest;

import com.elifcelik.testlab.unitTestCases.easyDemo.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void addTest(){
        int a = 5;
        int b = 6;
        int result = 11;

        assertEquals(result, calculator.add(a,b));
    }

    @Test
    public void divideTest(){
        int a = 8;
        int b = 5;
        int result = 8/5;

        assertEquals(result, calculator.divide(a, b));
    }
    @Test
    public void divideByZeroTest(){
        int a = 6;
        int b = 0;
        assertThrows(ArithmeticException.class, () -> calculator.divide(a,b));
    }
    @Test
    public void isEvenTest(){
        int a = 6;
        boolean isEven = true;
        assertEquals(isEven, calculator.isEven(a));
    }
}
