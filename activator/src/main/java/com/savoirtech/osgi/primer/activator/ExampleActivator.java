package com.savoirtech.osgi.primer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleActivator implements BundleActivator {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleActivator.class);

//----------------------------------------------------------------------------------------------------------------------
// BundleActivator Implementation
//----------------------------------------------------------------------------------------------------------------------
    
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        LOGGER.info("Starting my bundle...");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        LOGGER.info("Stopping my bundle...");
    }
}
