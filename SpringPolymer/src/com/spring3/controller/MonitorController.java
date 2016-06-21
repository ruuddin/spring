
package com.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @RequestMapping("/")
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Monitoring Home!");
        return "monitor";
    }

    @RequestMapping("/version")
    public String version() {
        return "version";
    }
}
