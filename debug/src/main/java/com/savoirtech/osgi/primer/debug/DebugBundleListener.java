package com.savoirtech.osgi.primer.debug;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class DebugBundleListener implements BundleListener {
//----------------------------------------------------------------------------------------------------------------------
// BundleListener Implementation
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void bundleChanged(BundleEvent event) {
        switch (event.getType()) {
            case BundleEvent.INSTALLED:
                System.out.println(String.format("[%s] Installed", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.RESOLVED:
                System.out.println(String.format("[%s] Resolved", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.STARTED:
                System.out.println(String.format("[%s] Started", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.STARTING:
                System.out.println(String.format("[%s] Starting", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.STOPPED:
                System.out.println(String.format("[%s] Stopped", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.STOPPING:
                System.out.println(String.format("[%s] Stopping", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.UNINSTALLED:
                System.out.println(String.format("[%s] Uninstalled", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.UNRESOLVED:
                System.out.println(String.format("[%s] Unresolved", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.UPDATED:
                System.out.println(String.format("[%s] Updated", event.getBundle().getSymbolicName()));
                break;
            case BundleEvent.LAZY_ACTIVATION:
                System.out.println(String.format("[%s] Lazy Activation", event.getBundle().getSymbolicName()));
                break;
        }
    }
}
