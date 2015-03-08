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
public class Rectangle extends Polygon {
    
    static {
        registerLayerType("L.Rectangle", (obj)->new Rectangle(obj));
    }
    
    protected Rectangle(Object jsObj) {
        super(jsObj);
    }
    
    public Rectangle(LatLngBounds bounds) {
        this(bounds, new PolyLineOptions());
    }

    public Rectangle(LatLngBounds bounds, PolyLineOptions options) {
        super(create(bounds.getJSObj(), options));
    }
    
    @JavaScriptBody(args = {"bounds", "options"}, body
            = "return L.rectangle(bounds, options);")
    private static native Object create(Object bounds, Object options);
    
    
    // ------- Methods -------------------------------------------
    

    public Rectangle setBounds(LatLngBounds bounds) {
        setBoundsInternal(jsObj, bounds.getJSObj());
        return this;
    }
    
    
    @JavaScriptBody(args = { "jsObj", "bounds" }, body = 
        "return jsObj.setBounds(bounds);")
    private static native void setBoundsInternal(Object jsObj, Object bounds);
    
    
    
    // ------- PolyLine Methods -------------------------------------------
    
    @Override
    public Rectangle addLatLng(LatLng latlng) {
        super.addLatLng(latlng);
        return this;
    }
    
    @Override
    public Rectangle setLatLngs(LatLng[] latlngs) {
        super.setLatLngs(latlngs);
        return this;
    }
    
    
    // ------- Path Methods -------------------------------------------
    
    @Override
    public Rectangle addTo(Map map) {
        super.addTo(map);
        return this;
    }
    
    @Override
    public Rectangle setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }
    
    @Override
    public Rectangle bringToFront() {
        super.bringToFront();
        return this;
    }
    
    @Override
    public Rectangle bringToBack() {
        super.bringToBack();
        return this;
    }
    
    @Override
    public Rectangle redraw() {
        super.redraw();
        return this;
    }
    
    
    // ------- Popup methods -------------------------------------------
    
    @Override
    public Rectangle bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }
    
    @Override
    public Rectangle bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }
    
    @Override
    public Rectangle bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }
    
    @Override
    public Rectangle unbindPopup() {
        super.unbindPopup();
        return this;
    }
    
    @Override
    public Rectangle openPopup() {
        super.openPopup();
        return this;
    }
    
    @Override
    public Rectangle closePopup() {
        super.closePopup();
        return this;
    }
    
    
}
