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
 * Class representing a marker on a map. This marker is a circle of a fixed size
 * with radius specified in pixels.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class CircleMarker extends Circle {

    static {
        registerLayerType("L.CircleMarker", new Function<Object, ILayer>() {
            @Override
            public ILayer apply(Object obj) {
                return new CircleMarker(obj);
            }
        });
    }

    protected CircleMarker(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a circle marker given a geographical point. The default
     * radius is 10.
     *
     * @param latlng location of the marker
     */
    public CircleMarker(LatLng latlng) {
        this(latlng, new PathOptions());
    }

    /**
     * Instantiates a circle marker given a geographical point and an options
     * object. The default radius is 10 and can be altered by setting the
     * "radius" option in the path options object.
     *
     * @param latlng location of the marker
     * @param options marker configuration options
     */
    public CircleMarker(LatLng latlng, PathOptions options) {
        super(create(latlng.getJSObj(), options.getJSObj()));
    }

    @JavaScriptBody(args = {"latlng", "options"}, body
            = "return L.circleMarker(latlng, options);")
    private static native Object create(Object latlng, Object options);

    // ------- Methods -------------------------------------------
    /**
     * Changes the marker position to the given point.
     *
     * @param latlng location of the marker
     * @return this
     */
    @Override
    public CircleMarker setLatLng(LatLng latlng) {
        super.setLatLng(latlng);
        return this;
    }

    /**
     * Changes the marker radius.
     *
     * @param radius marker radius
     * @return this
     */
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
    /**
     * Adds the layer to the map.
     *
     * @param map The map
     * @return this
     */
    @Override
    public CircleMarker addTo(Map map) {
        super.addTo(map);
        return this;
    }

    /**
     * Changes the appearance of a CircleMarker based on the options in the
     * {@link PathOptions} object.
     *
     * @param options path configuration options
     * @return this
     */
    @Override
    public CircleMarker setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    /**
     * Returns the LatLngBounds of the Circle Marker.
     *
     * @return LatLngBounds of the path
     */
    @Override
    public CircleMarker bringToFront() {
        super.bringToFront();
        return this;
    }

    /**
     * Brings the layer to the top of all path layers.
     *
     * @return this
     */
    @Override
    public CircleMarker bringToBack() {
        super.bringToBack();
        return this;
    }

    /**
     * Brings the layer to the bottom of all path layers.
     *
     * @return this
     */
    @Override
    public CircleMarker redraw() {
        super.redraw();
        return this;
    }

    // ------- Popup methods -------------------------------------------
    /**
     * Binds a popup with a particular HTML content to a click on this marker.
     * You can also open the bound popup with the Marker <code>openPopup</code>
     * method.
     *
     * @param html HTML content of the popup
     * @return this
     */
    @Override
    public CircleMarker bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }

    /**
     * Binds a popup to a click on this marker. You can also open the bound
     * popup with the Marker <code>openPopup</code> method.
     *
     * @param popup popup object
     * @return this
     */
    @Override
    public CircleMarker bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }

    /**
     * Binds a popup with a particular popup configuration options to a click on
     * this marker. You can also open the bound popup with the Marker
     * <code>openPopup</code> method.
     *
     * @param popup popup object
     * @param options popup configuration options
     * @return this
     */
    @Override
    public CircleMarker bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }

    /**
     * Unbinds the popup previously bound to the marker with
     * <code>bindPopup</code>.
     *
     * @return this
     */
    @Override
    public CircleMarker unbindPopup() {
        super.unbindPopup();
        return this;
    }

    /**
     * Opens the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    @Override
    public CircleMarker openPopup() {
        super.openPopup();
        return this;
    }

    /**
     * Closes the bound popup of the marker if it's opened.
     *
     * @return this
     */
    @Override
    public CircleMarker closePopup() {
        super.closePopup();
        return this;
    }

}
