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
public class PolyLine extends Path {
    
    static {
        registerLayerType("L.Polyline", (obj)->new PolyLine(obj));
    }
    
    protected PolyLine(Object jsObj) {
        super(jsObj);
    }
    
    public PolyLine(LatLng[] latlngs) {
        this(latlngs, new PolyLineOptions());
    }

    public PolyLine(LatLng[] latlngs, PolyLineOptions options) {
        super(createHelper(latlngs, options));
    }
    
    private static Object createHelper(LatLng[] latlngs, PolyLineOptions options) {
        Object[] latlngsJS = new Object[latlngs.length];
        for (int q = 0; q < latlngsJS.length; q++) latlngsJS[q] = latlngs[q].getJSObj();
        return create(latlngsJS, options.getJSObj());
    }

    @JavaScriptBody(args = {"latlngs", "options"}, body
            = "return L.polyline(latlngs, options);")
    private static native Object create(Object[] latlngs, Object options);
    
    
    // ------- Methods -------------------------------------------
    
    public PolyLine addLatLng(LatLng latlng) {
        addLatLngInternal(jsObj, latlng.getJSObj());
        return this;
    }
    
    public LatLng[] getLatLngs() {
        Object[] latlngsJS = getLatLngsInternal(jsObj);
        LatLng[] latlngs = new LatLng[latlngsJS.length];
        for (int q = 0; q < latlngs.length; q++) latlngs[q] = new LatLng(latlngsJS[q]);
        return latlngs;
    }
    
    public PolyLine setLatLngs(LatLng[] latlngs) {
        Object[] latlngsJS = new Object[latlngs.length];
        for (int q = 0; q < latlngsJS.length; q++) latlngsJS[q] = latlngs[q].getJSObj();
        setLatLngsInternal(jsObj, latlngs);
        return this;
    }
    
    //TODO: spliceLatLngs
    
    public LatLngBounds getBounds() {
        return new LatLngBounds(getBoundsInternal(jsObj));
    }

    //TODO: GeoJSON wrapper
    /*
    public String toGeoJSON() {
        return toGeoJSONInternal(jsObj);
    }
    */
    
     
    @JavaScriptBody(args = { "jsObj", "latlng" }, body = 
        "jsObj.addLatLng(latlng);")
    private static native void addLatLngInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getLatLngs();")
    private static native Object[] getLatLngsInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj", "latlngs" }, body = 
        "jsObj.setLatLngs(latlngs);")
    private static native void setLatLngsInternal(Object jsObj, Object[] latlngs);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.getBounds();")
    private static native Object getBoundsInternal(Object jsObj);
    
    
    //TODO: GeoJSON wrapper
    /*
    @JavaScriptBody(args = { "jsObj" }, body = 
        "return jsObj.toGeoJSON();")
    private static native Object toGeoJSONInternal(Object jsObj);
    */
    
    
    // ------- Path Methods -------------------------------------------
    
    @Override
    public PolyLine addTo(Map map) {
        super.addTo(map);
        return this;
    }
    
    @Override
    public PolyLine setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }
    
    @Override
    public PolyLine bringToFront() {
        super.bringToFront();
        return this;
    }
    
    @Override
    public PolyLine bringToBack() {
        super.bringToBack();
        return this;
    }
    
    @Override
    public PolyLine redraw() {
        super.redraw();
        return this;
    }
    
    
    // ------- Popup methods -------------------------------------------
    
    @Override
    public PolyLine bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }
    
    @Override
    public PolyLine bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }
    
    @Override
    public PolyLine bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }
    
    @Override
    public PolyLine unbindPopup() {
        super.unbindPopup();
        return this;
    }
    
    @Override
    public PolyLine openPopup() {
        super.openPopup();
        return this;
    }
    
    @Override
    public PolyLine closePopup() {
        super.closePopup();
        return this;
    }
    
    
}
