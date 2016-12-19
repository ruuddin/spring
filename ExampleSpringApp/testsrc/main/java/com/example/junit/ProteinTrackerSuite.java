package com.example.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TrackingServiceTest.class
}) //classes this suite is made of.
public class ProteinTrackerSuite {

}
