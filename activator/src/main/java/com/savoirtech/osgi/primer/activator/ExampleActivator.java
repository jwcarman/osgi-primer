package com.savoirtech.osgi.primer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ExampleActivator implements BundleActivator {
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting my bundle...");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopping my bundle...");
    }
}
