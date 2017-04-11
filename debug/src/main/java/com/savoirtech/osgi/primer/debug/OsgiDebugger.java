package com.savoirtech.osgi.primer.debug;

import org.osgi.framework.BundleContext;

public class OsgiDebugger {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private final DebugBundleListener bundleListener = new DebugBundleListener();
    private final DebugServiceListener serviceListener = new DebugServiceListener();
    private BundleContext bundleContext;

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }

//----------------------------------------------------------------------------------------------------------------------
// Other Methods
//----------------------------------------------------------------------------------------------------------------------

    public void initialize() {
        bundleContext.addBundleListener(bundleListener);
        bundleContext.addServiceListener(serviceListener);
    }

    public void shutdown() {
        bundleContext.removeBundleListener(bundleListener);
        bundleContext.removeServiceListener(serviceListener);
    }
}
