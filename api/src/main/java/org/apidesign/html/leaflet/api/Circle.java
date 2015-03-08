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
import static org.apidesign.html.leaflet.api.ILayer.registerLayerType;

/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class Circle extends Path {
    
    static {
        registerLayerType("L.Circle", (obj)->new Circle(obj));
    }
    
    protected Circle(Object jsObj) {
        super(jsObj);
    }
    
    public Circle(LatLng latlng, double radius) {
        this(latlng, radius, new PathOptions());
    }

    public Circle(LatLng latlng, double radius, PathOptions options) {
        super(create(latlng.getJSObj(), radius, options.getJSObj()));
    }

    @JavaScriptBody(args = {"latlng", "radius", "options"}, body
            = "return L.tileLayer(latlng, radius, options);")
    private static native Object create(Object latlng, double radius, Object options);
    
    
    // ------- Methods -------------------------------------------
    
    public LatLng getLatLng() {
        return new LatLng(getLatLngInternal(jsObj));
    }
    
    public double getRadius() {
        return getRadiusInternal(jsObj);
    }
    
    public Circle setLatLng(LatLng latlng) {
        setLatLngInternal(jsObj, latlng.getJSObj());
        return this;
    }
    
    public Circle setRadius(double radius) {
        setRadiusInternal(jsObj, radius);
        return this;
    }

    //TODO: GeoJSON wrapper
    /*
    public String toGeoJSON() {
        return toGeoJSONInternal(jsObj);
    }
    */
    
     
    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getLatLng();")
    private static native Object getLatLngInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getRadius();")
    private static native double getRadiusInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj", "latlng" }, body = 
        "jsObj.setLatLng(latlng);")
    private static native void setLatLngInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = { "jsObj", "radius" }, body = 
        "jsObj.setIcon(radius);")
    private static native void setRadiusInternal(Object jsObj, double radius);
    
    //TODO: GeoJSON wrapper
    /*
    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.toGeoJSON();")
    private static native Object toGeoJSONInternal(Object jsObj);
    */
    
    
    // ------- Path Methods -------------------------------------------
    
    @Override
    public Circle addTo(Map map) {
        super.addTo(map);
        return this;
    }
    
    @Override
    public Circle setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }
    
    @Override
    public Circle bringToFront() {
        super.bringToFront();
        return this;
    }
    
    @Override
    public Circle bringToBack() {
        super.bringToBack();
        return this;
    }
    
    @Override
    public Circle redraw() {
        super.redraw();
        return this;
    }
    
    
    // ------- Popup methods -------------------------------------------
    
    @Override
    public Circle bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }
    
    @Override
    public Circle bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }
    
    @Override
    public Circle bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }
    
    @Override
    public Circle unbindPopup() {
        super.unbindPopup();
        return this;
    }
    
    @Override
    public Circle openPopup() {
        super.openPopup();
        return this;
    }
    
    @Override
    public Circle closePopup() {
        super.closePopup();
        return this;
    }
    
    
}
