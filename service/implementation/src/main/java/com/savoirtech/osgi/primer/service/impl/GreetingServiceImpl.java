package com.savoirtech.osgi.primer.service.impl;

import com.savoirtech.osgi.primer.service.api.GreetingService;

public class GreetingServiceImpl implements GreetingService {
//----------------------------------------------------------------------------------------------------------------------
// Fields
//----------------------------------------------------------------------------------------------------------------------

    private String greetingPattern  = "Hello, %s!";

//----------------------------------------------------------------------------------------------------------------------
// GreetingService Implementation
//----------------------------------------------------------------------------------------------------------------------


    @Override
    public String greet(String name) {
        return String.format(greetingPattern, name);
    }

//----------------------------------------------------------------------------------------------------------------------
// Getter/Setter Methods
//----------------------------------------------------------------------------------------------------------------------

    public void setGreetingPattern(String greetingPattern) {
        this.greetingPattern = greetingPattern;
    }
}
