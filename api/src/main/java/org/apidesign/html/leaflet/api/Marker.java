/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015
 * Andreas Grimmer <a.grimmer@gmx.at>
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

/**
 * Class representing a marker on a map
 * 
 * @author Christoph Sperl
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Marker extends ILayer {
    
    static {
        registerLayerType("L.Marker", (obj)->new Marker(obj));
    }
    
    private Marker(Object jsObj) {
        super(jsObj);
    }    
    
    public Marker(LatLng latLng) {
        this(latLng, new MarkerOptions());
    }
    
    public Marker(LatLng latLng, MarkerOptions options) {
        super(create(latLng.getJSObj(), options.getJSObj()));
    }
    
    @JavaScriptBody(args = { "latLng", "options" }, body = 
        "return L.marker(latLng, options);")
    private static native Object create(Object latLng, Object options);
    
    
    // ------- Methods -------------------------------------------
    
    public void addTo(Map map) {
        addToInternal(jsObj, map.getJSObj());
    }
    
    public LatLng getLatLng() {
        return new LatLng(getLatLngInternal(jsObj));
    }
    
    public void setLatLng(LatLng latlng) {
        setLatLngInternal(jsObj, latlng.getJSObj());
    }
    
    public void setIcon(Icon icon) {
        setIconInternal(jsObj, icon.getJSObj());
    }
    
    public void setZIndexOffset(double offset) {
        setZIndexOffsetInternal(jsObj, offset);
    }
    
    public void setOpacity(double opacity) {
        setOpacityInternal(jsObj, opacity);
    }
    
    public void update() {
        updateInternal(jsObj);
    }
    
    public String toGeoJSON() {
        return toGeoJSONInternal(jsObj);
    }
    
     
    @JavaScriptBody(args = { "jsObj", "map" }, body = 
        "jsObj.addTo(map);")
    private static native void addToInternal(Object jsObj, Object map);
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getLatLng();")
    private static native Object getLatLngInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj", "latlng" }, body = 
        "jsObj.setLatLng(latlng);")
    private static native void setLatLngInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = { "jsObj", "icon" }, body = 
        "jsObj.setIcon(icon);")
    private static native void setIconInternal(Object jsObj, Object icon);

    @JavaScriptBody(args = { "jsObj", "zIndexOffset" }, body = 
        "jsObj.setZIndexOffset(zIndexOffset);")
    private static native void setZIndexOffsetInternal(Object jsObj, double zIndexOffset);
    
    @JavaScriptBody(args = { "jsObj", "opacity" }, body = 
        "jsObj.setOpacity(opacity);")
    private static native void setOpacityInternal(Object jsObj, double opacity);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.update();")
    private static native void updateInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.toGeoJSON().toString();")
    private static native String toGeoJSONInternal(Object jsObj);

    
    // ------- Popup methods -------------------------------------------
    
    public void bindPopup(String html) {
        bindPopup1sInternal(jsObj, html);
    }
    
    public void bindPopup(Popup popup) {
        bindPopup1pInternal(jsObj, popup.getJSObj());
    }
    
    public void bindPopup(Popup popup, PopupOptions options) {
        bindPopup2Internal(jsObj, popup.getJSObj(), options.getJSObj());
    }
    
    public void unbindPopup() {
        unbindPopupInternal(jsObj);
    }
    
    public void openPopup() {
        openPopupInternal(jsObj);
    }
    
    public Popup getPopup() {
        return new Popup(getPopupInternal(jsObj));
    }
    
    public void closePopup() {
        closePopupInternal(jsObj);
    }
    
    public void togglePopup() {
        togglePopupInternal(jsObj);
    }
    
    public void setPopupContent(String html) {
        setPopupContentInternal(jsObj, html);
    }
    
    

    @JavaScriptBody(args = { "jsObj", "html" }, body = 
        "jsObj.bindPopup(html);")
    private static native void bindPopup1sInternal(Object jsObj, String html);    
    
    @JavaScriptBody(args = { "jsObj", "popup" }, body = 
        "jsObj.bindPopup(popup);")
    private static native void bindPopup1pInternal(Object jsObj, Object popup);    
    
    @JavaScriptBody(args = { "jsObj", "popup", "options" }, body = 
        "jsObj.bindPopup(popup, options);")
    private static native void bindPopup2Internal(Object jsObj, Object popup, Object options);    
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.unbindPopup();")
    private static native void unbindPopupInternal(Object jsObj);    
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.openPopup();")
    private static native void openPopupInternal(Object jsObj);    
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getPopup();")
    private static native Object getPopupInternal(Object jsObj);    
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.closePopup();")
    private static native void closePopupInternal(Object jsObj);    
    
    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.togglePopup();")
    private static native void togglePopupInternal(Object jsObj);    
    
    @JavaScriptBody(args = { "jsObj", "html" }, body = 
        "jsObj.setPopupContent(html);")
    private static native void setPopupContentInternal(Object jsObj, String html);    
    
}
