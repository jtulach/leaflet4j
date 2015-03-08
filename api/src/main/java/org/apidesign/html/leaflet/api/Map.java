/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015 Andreas Grimmer <a.grimmer@gmx.at>
 * Christoph Sperl <ch.sperl@gmx.at>
 * Stefan Wurzinger <swurzinger@gmx.at>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.listener.EventListener;

/**
 * Class that represents one leaflet map associated with an element.
 *
 * @author Christoph Sperl
 * @author Andreas Grimmer
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Map {

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    public Map(String id) {
        this(id, new MapOptions());
    }

    public Map(String id, MapOptions options) {
        this.jsObj = create(id, options.getJSObj());
    }
    
    public Map(Object jsObj) {
        this.jsObj = jsObj;
    }



    public void setView(LatLng center) {
        setView1(jsObj, center.getJSObj());
    }

    public void setView(LatLng center, int zoom) {
        setView2(jsObj, center.getJSObj(), zoom);
    }

    public void setView(LatLng center, int zoom, ZoomPanOptions options) {
        setView3(jsObj, center.getJSObj(), zoom, options.getJSObj());
    }

    @JavaScriptBody(args = {"id", "options"},
            body = "return L.map(id, options);")
    private static native Object create(String id, Object options);

    @JavaScriptBody(args = {"jsObj", "center"}, wait4js = false, body
            = "jsObj.setView(center);")
    private static native void setView1(Object jsObj, Object center);

    @JavaScriptBody(args = {"jsObj", "center", "zoom"}, wait4js = false, body
            = "jsObj.setView(center, zoom);")
    private static native void setView2(Object jsObj, Object center, int zoom);

    @JavaScriptBody(args = {"jsObj", "center", "zoom", "options"}, wait4js = false, body
            = "jsObj.setView(center, zoom, options);")
    private static native void setView3(Object jsObj, Object center, int zoom, Object options);

	
    // ------ Methods for Layers and Controls -------------------------
    
    public void addLayer(ILayer layer) {
        addLayerInternal(jsObj, layer.getJSObj());
    }
    
    public void removeLayer(ILayer layer) {
        removeLayerInternal(jsObj, layer.getJSObj());
    }
    
    public boolean hasLayer(ILayer layer) {
        return hasLayerInternal(jsObj, layer.getJSObj());
    }
    
    public ILayer[] getLayers() {
        Object[] layersJS = eachLayerInternal(jsObj);
        ILayer[] layers = new ILayer[layersJS.length];
        for (int q = 0; q < layers.length; q++) layers[q] = ILayer.createLayer(layersJS[q]);
        return layers;
    }
    
    
    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.addLayer(layer);")
    private static native void addLayerInternal(Object jsObj, Object layer);
    
    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.removeLayer(layer);")
    private static native void removeLayerInternal(Object jsObj, Object layer);
    
    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "return jsObj.hasLayer(layer);")
    private static native boolean hasLayerInternal(Object jsObj, Object layer);
    
    @JavaScriptBody(args = {"jsObj"},
            body = "var arr = []; jsObj.eachLayer(function(layer) {arr.push(layer);}); return arr;")
    private static native Object[] eachLayerInternal(Object jsObj);

    // Event methods
    public Map addEventListener(String type, EventListener listener) {
        return addEventListener(type, listener, null);
    }

    public Map addEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener, context);
        return this;
    }
    
    public Map addOneTimeEventListener(String type, EventListener listener) {
        return addOneTimeEventListener(type, listener, null);
    }
    
    public Map addOneTimeEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addOneTimeEventListener(getJSObj(), type, listener, context);
        return this;
    }
}
