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
public class CircleMarker extends Circle {

    static {
        registerLayerType("L.CircleMarker", (obj) -> new CircleMarker(obj));
    }

    protected CircleMarker(Object jsObj) {
        super(jsObj);
    }

    public CircleMarker(LatLng latlng) {
        this(latlng, new PathOptions());
    }

    public CircleMarker(LatLng latlng, PathOptions options) {
        super(create(latlng.getJSObj(), options.getJSObj()));
    }

    @JavaScriptBody(args = {"latlng", "options"}, body
            = "return L.circleMarker(latlng, options);")
    private static native Object create(Object latlng, Object options);

    // ------- Methods -------------------------------------------
    @Override
    public CircleMarker setLatLng(LatLng latlng) {
        super.setLatLng(latlng);
        return this;
    }

    @Override
    public CircleMarker setRadius(double radius) {
        super.setRadius(radius);
        return this;
    }

    //TODO: GeoJSON wrapper
    /*
     public String toGeoJSON() {
     return toGeoJSONInternal(jsObj);
     }
     */
    // ------- Path Methods -------------------------------------------
    @Override
    public CircleMarker addTo(Map map) {
        super.addTo(map);
        return this;
    }

    @Override
    public CircleMarker setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    @Override
    public CircleMarker bringToFront() {
        super.bringToFront();
        return this;
    }

    @Override
    public CircleMarker bringToBack() {
        super.bringToBack();
        return this;
    }

    @Override
    public CircleMarker redraw() {
        super.redraw();
        return this;
    }

    // ------- Popup methods -------------------------------------------
    @Override
    public CircleMarker bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }

    @Override
    public CircleMarker bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }

    @Override
    public CircleMarker bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }

    @Override
    public CircleMarker unbindPopup() {
        super.unbindPopup();
        return this;
    }

    @Override
    public CircleMarker openPopup() {
        super.openPopup();
        return this;
    }

    @Override
    public CircleMarker closePopup() {
        super.closePopup();
        return this;
    }

}
