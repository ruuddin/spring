
package com.spring.webservices;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author riazuddin
 * @Service annotation makes this class a candidate for injection
 */
@Service
public class HumanResourceService {
    private static final Logger LOG = LoggerFactory.getLogger(HumanResourceService.class);

    public void bookHoliday(Date startDate, Date endDate, String name) {
        LOG.info("Received message holiday");
    }
}
