package com.savoirtech.osgi.primer.service.consumer;

import java.util.Timer;
import java.util.TimerTask;

import com.savoirtech.osgi.primer.service.api.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceConsumer {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConsumer.class);

    private GreetingService greetingService;
    private Timer timer;
    private long period = 5000;

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void initialize() {
        timer = new Timer("ServiceConsumer");
        timer.scheduleAtFixedRate(new PrintGreetingTask(), period, period);
    }

    public void shutdown() {
        timer.cancel();
    }

//----------------------------------------------------------------------------------------------------------------------
// Inner Classes
//----------------------------------------------------------------------------------------------------------------------

    private class PrintGreetingTask extends TimerTask {
//----------------------------------------------------------------------------------------------------------------------
// Runnable Implementation
//----------------------------------------------------------------------------------------------------------------------

        @Override
        public void run() {
            try {
                LOGGER.info(greetingService.greet("OSGi"));
            } catch (RuntimeException e) {
                LOGGER.error("Unable to call GreetingService: {}", e.getMessage(), e);
            }
        }
    }
}
