package com.adobe.people.jedelson.sample_sightly_function.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

@SuppressWarnings("serial")
@Component
@Service(Map.class)
@Property(name = "javax.script.name", value = "sightly")
public class JavaVersionBVP extends HashMap<String, Object> {

    @Activate
    public void activate(Map<String, Object> properties) {
        put("getJavaVersion", new MyCustomFunction());
        put("javaVersion", System.getProperty("java.runtime.version"));
    }

}
