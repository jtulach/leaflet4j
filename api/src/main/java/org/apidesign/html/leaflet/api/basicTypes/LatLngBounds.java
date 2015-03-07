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
package org.apidesign.html.leaflet.api.basicTypes;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

/**
 *
 * @author Christoph Sperl
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class LatLngBounds  {
    
    private final Object jsObj;
    
    public Object getJSObj() {
        return jsObj;
    }
    
    public LatLngBounds(LatLng southWest, LatLng northEast) {
        this.jsObj = create(southWest.getJSObj(), northEast.getJSObj());
    }
    
    public LatLngBounds(LatLng[] latLngs) {
        this.jsObj = create(optionsHelper(latLngs));
    }
    
    private static Object[] optionsHelper(LatLng[] latLngs) {
        Object[] objs = new Object[latLngs.length];
        for (int i = 0; i < latLngs.length; i++)
            objs[i] = latLngs[i].getJSObj();
        return objs;
    }
    
    public LatLngBounds(Object jsObj) {
        this.jsObj = jsObj;
    }
    
    public void extend(LatLng latLng) {
        extend(jsObj, latLng.getJSObj());
    }
    
    public void extend(LatLngBounds latLngBounds) {
        extend(jsObj, latLngBounds.getJSObj());
    }
    
    public LatLng getSouthWest() {
        return new LatLng(getSouthWest(jsObj));
    }
    
    public LatLng getNorthEast() {
        return new LatLng(getNorthEast(jsObj));
    }
    
    public LatLng getNorthWest() {
        return new LatLng(getNorthWest(jsObj));
    }
    
    public LatLng getSouthEast() {
        return new LatLng(getSouthEast(jsObj));
    }
    
    public double getWest() {
        return getWest(jsObj);
    }
    
    public double getSouth() {
        return getSouth(jsObj);
    }
    
    public double getEast() {
        return getEast(jsObj);
    }
    
    public double getNorth() {
        return getNorth(jsObj);
    }
    
    public LatLng getCenter() {
        return new LatLng(getCenter(jsObj));
    }
    
    public boolean contains(LatLngBounds other) {
        return contains(jsObj, other.getJSObj());
    }
    
    public boolean contains(LatLng latLng) {
        return contains(jsObj, latLng.getJSObj());
    }
    
    public boolean intersects(LatLngBounds other) {
        return intersects(jsObj, other.getJSObj());
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds otherBounds = (LatLngBounds)other;
        return equals(jsObj, otherBounds.getJSObj());
    }
    
    public String toBBoxString() {
        return toBBoxString(jsObj);
    }
    
    public LatLngBounds pad(double bufferRatio) {
        return new LatLngBounds(pad(jsObj, bufferRatio));
    }
    
    public boolean isValid() {
        return isValid(jsObj);
    }
    
    @JavaScriptBody(args = { "southWest", "northEast" }, 
            body = "return L.latLngBounds(southWest, northEast);")
    private static native Object create(Object southWest, Object northEast);
    
    @JavaScriptBody(args = { "latLngs" }, 
            body = "return L.latLngBounds(latLngs);")
    private static native Object create(Object[] latLngs);
    
    @JavaScriptBody(args = { "jsObj", "latLng" }, 
            body = "jsObj.extend(latLng);")
    private static native void extend(Object jsObj, Object latLng);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getSouthWest();")
    private static native Object getSouthWest(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getNorthEast();")
    private static native Object getNorthEast(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getNorthWest();")
    private static native Object getNorthWest(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getSouthEast();")
    private static native Object getSouthEast(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getWest();")
    private static native double getWest(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getSouth();")
    private static native double getSouth(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getEast();")
    private static native double getEast(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getNorth();")
    private static native double getNorth(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.getCenter();")
    private static native Object getCenter(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj", "other"}, 
            body = "return jsObj.contains(other);")
    private static native boolean contains(Object jsObj, Object other);
    
    @JavaScriptBody(args = { "jsObj", "other"}, 
            body = "return jsObj.intersects(other);")
    private static native boolean intersects(Object jsObj, Object other);
    
    @JavaScriptBody(args = { "jsObj", "other"}, 
            body = "return jsObj.equals(other);")
    private static native boolean equals(Object jsObj, Object other);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.toBBoxString();")
    private static native String toBBoxString(Object jsObj);
    
    @JavaScriptBody(args = { "jsObj", "bufferRatio"}, 
            body = "return jsObj.pad(bufferRatio);")
    private static native LatLngBounds pad(Object jsObj, double bufferRatio);
    
    @JavaScriptBody(args = { "jsObj"}, 
            body = "return jsObj.isValid();")
    private static native boolean isValid(Object jsObj);
}
