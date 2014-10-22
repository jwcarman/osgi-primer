package com.savoirtech.osgi.primer.service.consumer;

import com.savoirtech.osgi.primer.service.api.GreetingService;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceConsumer {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

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
        @Override
        public void run() {
            try {
                System.out.println(greetingService.greet("OSGi"));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
