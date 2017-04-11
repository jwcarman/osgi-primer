package com.savoirtech.osgi.primer.debug;

import java.util.Arrays;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugServiceListener implements ServiceListener {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugServiceListener.class);

//----------------------------------------------------------------------------------------------------------------------
// ServiceListener Implementation
//----------------------------------------------------------------------------------------------------------------------

    
    @Override
    public void serviceChanged(ServiceEvent event) {
        final ServiceReference<?> reference = event.getServiceReference();
        final Bundle bundle = reference.getBundle();
        final String[] objectclass = (String[]) reference.getProperty("objectclass");
        final List<String> serviceInterfaceNames = Arrays.asList(objectclass);
        switch (event.getType()) {
            case ServiceEvent.REGISTERED:
                LOGGER.info("[{}] Registered a {} service.\n", bundle.getSymbolicName(), serviceInterfaceNames);
                break;
            case ServiceEvent.UNREGISTERING:
                LOGGER.info("[{}] Unregistering a {} service.\n", bundle.getSymbolicName(), serviceInterfaceNames);
                break;
            case ServiceEvent.MODIFIED:
                LOGGER.info("[{}] Modified a {} service.\n", bundle.getSymbolicName(), serviceInterfaceNames);
                break;
        }
    }
}
