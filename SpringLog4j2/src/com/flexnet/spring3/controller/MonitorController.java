
package com.flexnet.spring3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
    private static final Logger logger = LogManager.getLogger(MonitorController.class);

    @RequestMapping("/")
    public String printWelcome(ModelMap model) {
        logger.trace("Entering application.");
        model.addAttribute("message", "Message from MonitorController.java");
        logger.error("Didn't do it.");
        logger.trace("Exiting application.");
        return "monitor";
    }

    @RequestMapping("/version")
    public String version() {
        return "version";
    }
}
