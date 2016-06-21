
package com.flexnet.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fnomonitor")
public class FNOMonitorController {

    @RequestMapping("/info")
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Monitoring FNO!");
        return "monitor";
    }

    @RequestMapping("/version")
    public String version() {
        return "fnoversion";
    }
}
