package com.example.junit;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.example.junit.TrackingService.InvalidInputException;

public class TrackingServiceTest {

    TrackingService s = null;
    
    
    @Test
    @Category(GoodTestsCategory.class)
    public void NewTrackingServiceTotalIsZero() {
        assertEquals("Unexpected total", 0, s.getTotal());
    }
    
    @Test
    @Category(GoodTestsCategory.class)
    public void WhenAddingProteinTotalIncreaesThatAmount(){
        s.addProtein(10);
//        assertEquals("unexpected total", 10, s.getTotal());
        assertThat(s.getTotal(), is(10));
        assertThat(s.getTotal(), allOf(is(10), instanceOf(Integer.class)));
    }
    
    @Test
    @Category(GoodTestsCategory.class)
    public void WhenRemovingProteinTotalIncreaesThatAmount(){
        s.addProtein(10);
        s.removeProtein(5);
        s.addProtein(3);
        s.removeProtein(0);
        
        assertEquals("unexpected total", 8, s.getTotal());
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
//    @Test(expected = InvalidInputException.class)
    @Test
    public void InvalidProteinAmount(){
        thrown.expect(InvalidInputException.class);
//        thrown.expectMessage("Input protein amount is invalid");
        thrown.expectMessage(JUnitMatchers.containsString("invalid"));
        s.addProtein(-1);
    }
    
    @Rule
    public Timeout timeout = new Timeout(50); //timeout applies to the whole class
    
//    @Test(timeout = 200)
    @Test
    public void BadTest(){
        for(int i = 0; i < 10000000; i++)
            s.addProtein(i);
    }
    
    @Ignore
    public void failingTestIgnored(){
        
    }
    
    @BeforeClass
    public static void beforeClass(){
        
    }
    
    @AfterClass
    public static void afterClass(){
        
    }
    
    @Before
    public void before(){
        s = new TrackingService();
    }
    
    @After
    public void after(){
        s = null;
    }

}
