package com.savoirtech.osgi.primer.debug;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugBundleListener implements BundleListener {
//----------------------------------------------------------------------------------------------------------------------
// BundleListener Implementation
//----------------------------------------------------------------------------------------------------------------------
    private static final Logger LOGGER = LoggerFactory.getLogger(DebugBundleListener.class);
    
    @Override
    public void bundleChanged(BundleEvent event) {
        switch (event.getType()) {
            case BundleEvent.INSTALLED:
                LOGGER.info("[{}] Installed", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.RESOLVED:
                LOGGER.info("[{}] Resolved", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.STARTED:
                LOGGER.info("[{}] Started", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.STARTING:
                LOGGER.info("[{}] Starting", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.STOPPED:
                LOGGER.info("[{}] Stopped", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.STOPPING:
                LOGGER.info("[{}] Stopping", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.UNINSTALLED:
                LOGGER.info("[{}] Uninstalled", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.UNRESOLVED:
                LOGGER.info("[{}] Unresolved", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.UPDATED:
                LOGGER.info("[{}] Updated", event.getBundle().getSymbolicName());;
                break;
            case BundleEvent.LAZY_ACTIVATION:
                LOGGER.info("[{}] Lazy Activation", event.getBundle().getSymbolicName());;
                break;
        }
    }
}
