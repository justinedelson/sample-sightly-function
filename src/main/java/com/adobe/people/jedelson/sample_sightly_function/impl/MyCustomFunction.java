package com.adobe.people.jedelson.sample_sightly_function.impl;

import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

@SuppressWarnings("serial")
public class MyCustomFunction extends BaseFunction {
    
    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        return System.getProperty("java.runtime.version");
    }

}
