package com.promineotech;


import static org.junit.jupiter.params.provider.Arguments.arguments;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import tDemoSource.TestDemo;

import static org.mockito.Mockito.doReturn;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

    @BeforeEach
    public void setUp() {
        testDemo = new TestDemo();
    }

    @ParameterizedTest
    @MethodSource("argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
        TestDemo testDemo = new TestDemo(); // instantiate the class being tested
        
        // check that the sum of two positive numbers equals the expected result
        if (!expectException) {
            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
        } else { // check that an IllegalArgumentException is thrown when adding non-positive numbers
            assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
        }
    }
    
    static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
            // list of test arguments in the form (int a, int b, int expected result, boolean expect exception)
            arguments(2, 4, 6, false),
            arguments(3, -6, 0, true),
            arguments(-5, 7, 0, true),
            arguments(0, 0, 0, false),
            arguments(8, 0, 0, true)
        );
    
        
    }
    
    @Test
    void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
        assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
        assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
        // Add more assertions if needed
    }
    
    //My method - test multiplication.
    
    @Test
    public void testMultiply() {
        int a = 4;
        int b = 5;
        int expected = 20;
        int actual = testDemo.multiply(a, b);
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
   public void assertThatNumberSquaredIsCorrect(){
    	
    	TestDemo mockDemo = Mockito.spy(new TestDemo());
    	doReturn(5).when(mockDemo).getRandomInt();
    	
    	int fiveSquared = mockDemo.randomNumberSquared();
    	
    	assertThat(fiveSquared).isEqualTo(25);
    }
}

