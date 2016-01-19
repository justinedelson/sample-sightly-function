package com.adobe.people.jedelson.sample_sightly_function.impl;

import javax.script.Bindings;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.scripting.api.BindingsValuesProvider;
import org.slf4j.Logger;

@Component
@Service
@Property(name = "javax.script.name", value = "sightly")
public class FindTaggedAssetsFunctionBVP implements BindingsValuesProvider {

    @Override
    public void addBindings(Bindings bindings) {
        SlingHttpServletRequest request = (SlingHttpServletRequest) bindings.get(SlingBindings.REQUEST);
        Logger log = (Logger) bindings.get(SlingBindings.LOG);
        if (request != null) {
            bindings.put("findTaggedAssets", new FindTaggedAssetsFunction(request.getResourceResolver(), log));
        }
    }

}
