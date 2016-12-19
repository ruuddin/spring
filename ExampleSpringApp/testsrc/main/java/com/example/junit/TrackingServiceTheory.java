package com.example.junit;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * 
 * @author riazuddin
 *
 */
@RunWith(Theories.class)
public class TrackingServiceTheory {

    TrackingService s ;
    
    @Before
    public void before(){
        s = new TrackingService();
    }
    
    @DataPoints
    public static int[] data(){
        return new int[]{
                1, 5, 10, 15, 20, 50
        };
    }
    
    @Theory
    public void theory(int input){
        s.addProtein(input);
        
        Assume.assumeTrue(input > 0);
        
        Assert.assertTrue(s.getTotal() > 0);
    }
}
