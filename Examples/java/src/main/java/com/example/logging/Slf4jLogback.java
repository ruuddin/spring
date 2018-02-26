package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogback {

	private static Logger LOG = LoggerFactory.getLogger(Slf4jLogback.class);
	public static void main(String[] args) {
		
		//logs based on the level set for the package in logback.xml
		LOG.debug("Inside main method at {}", System.currentTimeMillis());
	}
}
