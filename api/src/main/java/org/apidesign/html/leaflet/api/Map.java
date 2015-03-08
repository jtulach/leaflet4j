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

import java.util.function.Consumer;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.listener.EventListener;

/**
 * Class that represents one leaflet map associated with an element.
 *
 * @author Christoph Sperl
 * @author Andreas Grimmer
 * @author Stefan Wurzinger
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
    
    public Map addLayer(ILayer layer) {
        addLayerInternal(jsObj, layer.getJSObj());
        return this;
    }
    
    public Map removeLayer(ILayer layer) {
        removeLayerInternal(jsObj, layer.getJSObj());
        return this;
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
    
    public Map eachLayer(Consumer<ILayer> fun) {
        Object[] layersJS = eachLayerInternal(jsObj);
        for (int q = 0; q < layersJS.length; q++) fun.accept(ILayer.createLayer(layersJS[q]));
        return this;
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

    
    
    // ------- Event methods --------------------------------------
	
    public Map addEventListener(String type, EventListener listener) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener);
        return this;
    }

    public Map addEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener, context);
        return this;
    }
    
    public Map addOneTimeEventListener(String type, EventListener listener) {
        EventMethodsHelper.addOneTimeEventListener(getJSObj(), type, listener);
        return this;
    }
    
    public Map addOneTimeEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addOneTimeEventListener(getJSObj(), type, listener, context);
        return this;
    }
    
    public Map addEventListener(java.util.Map<String, EventListener> eventMap) {
        EventMethodsHelper.addEventListener(getJSObj(), eventMap);
        return this;
    }
    
    public Map addEventListener(java.util.Map<String, EventListener> eventMap, Object context) {
        EventMethodsHelper.addEventListener(getJSObj(), eventMap, context);
        return this;
    }
    
    public Map removeEventListener(String type) {
        EventMethodsHelper.removeEventListener(getJSObj(), type);
        return this;
    }
    
    public Map removeEventListener(String type, Object context) {
        EventMethodsHelper.removeEventListener(getJSObj(), type, context);
        return this;
    }
    
    public Map removeEventListener(String type, EventListener listener) {    
        EventMethodsHelper.removeEventListener(getJSObj(), type, listener);
        return this;
    }
    
    public Map removeEventListener(String type, EventListener listener, Object context) {    
        EventMethodsHelper.removeEventListener(getJSObj(), type, listener, context);
        return this;
    }
    
    public Map removeEventListener(java.util.Map<String, EventListener> eventMap) {
        EventMethodsHelper.removeEventListener(getJSObj(), eventMap);
        return this;
    }
    
    public Map removeEventListener(java.util.Map<String, EventListener> eventMap, Object context) {
        EventMethodsHelper.removeEventListener(getJSObj(), eventMap, context);
        return this;
    }
    
    public Map clearAllEventListeners() {
        EventMethodsHelper.clearAllEventListeners(getJSObj());
        return this;
    }
    
    public boolean hasEventListeners(String type) {
        return EventMethodsHelper.hasEventListeners(getJSObj(), type);
    }
    
    public Map fireEvent(String type) {
        EventMethodsHelper.fireEvent(getJSObj(), type);
        return this;
    }
    
    public Map fireEvent(String type, Object data) {
        EventMethodsHelper.fireEvent(getJSObj(), type, data);
        return this;
    }
	
	
    // ------- Popup methods -------------------------------------------
    
    public Map openPopup(String html, LatLng latlng) {
        openPopup1sInternal(jsObj, html, latlng.getJSObj());
        return this;
    }
    
    public Map openPopup(Popup popup, LatLng latlng) {
        openPopup1pInternal(jsObj, popup.getJSObj(), latlng.getJSObj());
        return this;
    }
    
    public Map openPopup(Popup popup, LatLng latlng, PopupOptions options) {
        openPopup2Internal(jsObj, popup.getJSObj(), latlng.getJSObj(), options.getJSObj());
        return this;
    }
    
    public Map closePopup() {
        closePopup0Internal(jsObj);
        return this;
    }

    public Map closePopup(Popup popup) {
        closePopup1Internal(jsObj, popup.getJSObj());
        return this;
    }
    

    @JavaScriptBody(args = { "jsObj", "html", "latlng" }, body = 
        "jsObj.openPopup(html, latlng);")
    private static native void openPopup1sInternal(Object jsObj, String html, Object latlng);    
    
    @JavaScriptBody(args = { "jsObj", "popup", "latlng" }, body = 
        "jsObj.openPopup(popup, latlng);")
    private static native void openPopup1pInternal(Object jsObj, Object popup, Object latlng);    
    
    @JavaScriptBody(args = { "jsObj", "popup", "latlng", "options" }, body = 
        "jsObj.openPopup(popup, latlng, options);")
    private static native void openPopup2Internal(Object jsObj, Object popup, Object latlng, Object options);    

    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.closePopup();")
    private static native void closePopup0Internal(Object jsObj);    
    
    @JavaScriptBody(args = { "jsObj", "popup" }, body = 
        "jsObj.closePopup(popup);")
    private static native void closePopup1Internal(Object jsObj, Object popup);    
    
    
    
    
    // ------ Methods for Getting Map State -------------------------
    
    public LatLng getCenter() {
        return new LatLng(getCenterInternal(jsObj));
    }
    
    public int getZoom() {
        return getZoomInternal(jsObj);
    }

    public int getMinZoom() {
        return getMinZoomInternal(jsObj);
    }

    public int getMaxZoom() {
        return getMaxZoomInternal(jsObj);
    }

    public LatLngBounds getBounds() {
        return new LatLngBounds(getBoundsInternal(jsObj));
    }

    public int getBoundsZoom(LatLngBounds bounds) {
        return getBoundsZoom1Internal(jsObj, bounds.getJSObj());
    }
    
    public int getBoundsZoom(LatLngBounds bounds, boolean inside) {
        return getBoundsZoom2Internal(jsObj, bounds.getJSObj(), inside);
    }

    public Point getSize() {
        return new Point(getSizeInternal(jsObj));
    }

    public Bounds getPixelBounds() {
        return new Bounds(getPixelBoundsInternal(jsObj));
    }

    public Point getPixelOrigin() {
        return new Point(getPixelOriginInternal(jsObj));
    }

    
    
    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getCenter();")
    private static native Object getCenterInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getZoom();")
    private static native int getZoomInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getMinZoom();")
    private static native int getMinZoomInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getMaxZoom();")
    private static native int getMaxZoomInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getBounds();")
    private static native Object getBoundsInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "bounds"},
            body = "jsObj.getBoundsZoom(bounds);")
    private static native int getBoundsZoom1Internal(Object jsObj, Object bounds);

    @JavaScriptBody(args = {"jsObj", "bounds", "inside"},
            body = "jsObj.getBoundsZoom(bounds, inside);")
    private static native int getBoundsZoom2Internal(Object jsObj, Object bounds, boolean inside);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getSize();")
    private static native Object getSizeInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getPixelBounds();")
    private static native Object getPixelBoundsInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getPixelOrigin();")
    private static native Object getPixelOriginInternal(Object jsObj);


    
}
