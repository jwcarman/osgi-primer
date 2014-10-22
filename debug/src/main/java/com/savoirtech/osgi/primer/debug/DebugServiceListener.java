package com.savoirtech.osgi.primer.debug;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import java.util.Arrays;
import java.util.List;

public class DebugServiceListener implements ServiceListener {
    @Override
    public void serviceChanged(ServiceEvent event) {
        final ServiceReference<?> reference = event.getServiceReference();
        final Bundle bundle = reference.getBundle();
        final String[] objectclass = (String[]) reference.getProperty("objectclass");
        final List<String> serviceInterfaceNames = Arrays.asList(objectclass);
        switch (event.getType()) {
            case ServiceEvent.REGISTERED:
                System.out.printf("[%s] Registered a %s service.\n", bundle.getSymbolicName(), serviceInterfaceNames);
                break;
            case ServiceEvent.UNREGISTERING:
                System.out.printf("[%s] Unregistering a %s service.\n", bundle.getSymbolicName(), serviceInterfaceNames);
                break;
            case ServiceEvent.MODIFIED:
                System.out.printf("[%s] Modified a %s service.\n", bundle.getSymbolicName(), serviceInterfaceNames);
                break;
        }
    }
}
