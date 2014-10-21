package com.savoirtech.osgi.primer.lifecycle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class LifecycleActivator implements BundleActivator {

    private final LifecycleListener listener = new LifecycleListener();

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.printf("[%s] Adding lifecycle listener...\n",
                context.getBundle().getSymbolicName());
        context.addBundleListener(listener);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        //System.out.printf("[%s] Removing lifecycle listener...", context.getBundle().getSymbolicName());
        //context.removeBundleListener(listener);
    }

    private static class LifecycleListener implements BundleListener {
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
}
