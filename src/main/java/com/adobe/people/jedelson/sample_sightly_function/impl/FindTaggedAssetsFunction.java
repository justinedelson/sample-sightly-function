package com.adobe.people.jedelson.sample_sightly_function.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.RangeIterator;
import com.day.cq.dam.api.Asset;
import com.day.cq.tagging.TagManager;

@SuppressWarnings("serial")
public class FindTaggedAssetsFunction extends BaseFunction {

    private ResourceResolver resourceResolver;
    private Logger log;

    public FindTaggedAssetsFunction(final ResourceResolver resourceResolver, final Logger log) {
        this.resourceResolver = resourceResolver;
        if (log != null) {
            this.log = log;
        } else {
            this.log = LoggerFactory.getLogger(FindTaggedAssetsFunction.class);
        }

    }

    @Override
    public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
        if (args.length == 0) {
            log.warn("FindTaggedAssetFunction called with no arguments");
            return Undefined.instance;
        }
        String tagId = (String) args[0];
        List<Asset> assets = new ArrayList<Asset>();

        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
        RangeIterator<Resource> resources = tagManager.find("/content/dam", new String[] { tagId }, true);
        while (resources.hasNext()) {
            Resource r = resources.next();
            if (r.getName().equals("metadata")) {
                Asset asset = r.getParent().getParent().adaptTo(Asset.class);
                assets.add(asset);
            }
        }

        return new NativeArray(assets.toArray());
    }

}
