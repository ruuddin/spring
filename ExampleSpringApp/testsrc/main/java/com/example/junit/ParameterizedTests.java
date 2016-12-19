package com.example.junit;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

/**
 * list returned by data() is passed to each test. Each element of list is (input, expected). 
 * @author riazuddin
 *
 */
@RunWith(Parameterized.class)
public class ParameterizedTests {

    private static TrackingService s = new TrackingService();
    
    @Parameters
    public static List<Object[]> data() {
        
        return Arrays.asList(new Object[][]{
            {5, 5},
            {5, 10},
            {-12, 0},
            {50,50},
            {1, 51}
        });
    }

    private int input;
    private int expected;
    
    public ParameterizedTests(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }
    
    @Test
    public void test(){
        if(input >= 0)
            s.addProtein(input);
        else
            s.removeProtein(-input);
        
        assertEquals(expected, s.getTotal());
    }
    
    
}
