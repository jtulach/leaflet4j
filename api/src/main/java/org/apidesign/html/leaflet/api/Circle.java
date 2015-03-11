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
 * A class for drawing circle overlays on a map.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class Circle extends Path {

    static {
        registerLayerType("L.Circle", (obj) -> new Circle(obj));
    }

    protected Circle(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a circle object given a geographical point and a radius in
     * meters.
     *
     * @param latlng geographical location of the circle
     * @param radius circle radius
     */
    public Circle(LatLng latlng, double radius) {
        this(latlng, radius, new PathOptions());
    }

    /**
     * Instantiates a circle object given a geographical point, a radius in
     * meters and an options object.
     *
     * @param latlng geographical location of the circle
     * @param radius circle radius
     * @param options path configuration options
     */
    public Circle(LatLng latlng, double radius, PathOptions options) {
        super(create(latlng.getJSObj(), radius, options.getJSObj()));
    }

    @JavaScriptBody(args = {"latlng", "radius", "options"}, body
            = "return L.circle(latlng, radius, options);")
    private static native Object create(Object latlng, double radius, Object options);

    // ------- Methods -------------------------------------------
    /**
     * Returns the current geographical position of the circle.
     *
     * @return geographical position of the circle
     */
    public LatLng getLatLng() {
        return new LatLng(getLatLngInternal(jsObj));
    }

    /**
     * Returns the current radius of a circle. Units are in meters.
     *
     * @return radius of a circle in meters
     */
    public double getRadius() {
        return getRadiusInternal(jsObj);
    }

    /**
     * Changes the circle position to the given point.
     *
     * @param latlng location of the circle
     * @return this
     */
    public Circle setLatLng(LatLng latlng) {
        setLatLngInternal(jsObj, latlng.getJSObj());
        return this;
    }

    /**
     * Changes the circle radius to the given value in meters.
     *
     * @param radius circle radius in meters
     * @return this
     */
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
    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getLatLng();")
    private static native Object getLatLngInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getRadius();")
    private static native double getRadiusInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "latlng"}, body
            = "jsObj.setLatLng(latlng);")
    private static native void setLatLngInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = {"jsObj", "radius"}, body
            = "jsObj.setIcon(radius);")
    private static native void setRadiusInternal(Object jsObj, double radius);

    //TODO: GeoJSON wrapper
    /*
     @JavaScriptBody(args = { "jsObj" }, body = 
     "return jsObj.toGeoJSON();")
     private static native Object toGeoJSONInternal(Object jsObj);
     */
    // ------- Path Methods -------------------------------------------
    /**
     * Adds the layer to the map.
     *
     * @param map The map
     * @return this
     */
    @Override
    public Circle addTo(Map map) {
        super.addTo(map);
        return this;
    }

    /**
     * Changes the appearance of a Path based on the options in the
     * {@link PathOptions} object.
     *
     * @param options path configuration options
     * @return this
     */
    @Override
    public Circle setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    /**
     * Brings the layer to the top of all path layers.
     *
     * @return this
     */
    @Override
    public Circle bringToFront() {
        super.bringToFront();
        return this;
    }

    /**
     * Brings the layer to the bottom of all path layers.
     *
     * @return this
     */
    @Override
    public Circle bringToBack() {
        super.bringToBack();
        return this;
    }

    /**
     * Redraws the layer. Sometimes useful after you changed the coordinates
     * that the circle uses.
     *
     * @return this
     */
    @Override
    public Circle redraw() {
        super.redraw();
        return this;
    }

    // ------- Popup methods -------------------------------------------
    /**
     * Binds a popup with a particular HTML content to a click on this circle.
     * You can also open the bound popup with the Circle <code>openPopup</code>
     * method.
     *
     * @param html HTML content of the popup
     * @return this
     */
    @Override
    public Circle bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }

    /**
     * Binds a popup to a click on this circle. You can also open the bound
     * popup with the Circle <code>openPopup</code> method.
     *
     * @param popup popup object
     * @return this
     */
    @Override
    public Circle bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }

    /**
     * Binds a popup with a particular popup configuration options to a click on
     * this circle. You can also open the bound popup with the Circle
     * <code>openPopup</code> method.
     *
     * @param popup popup object
     * @param options popup configuration options
     * @return this
     */
    @Override
    public Circle bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }

    /**
     * Unbinds the popup previously bound to the circle with
     * <code>bindPopup</code>.
     *
     * @return this
     */
    @Override
    public Circle unbindPopup() {
        super.unbindPopup();
        return this;
    }

    /**
     * Opens the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    @Override
    public Circle openPopup() {
        super.openPopup();
        return this;
    }

    /**
     * Closes the bound popup of the circle if it's opened.
     *
     * @return this
     */
    @Override
    public Circle closePopup() {
        super.closePopup();
        return this;
    }

}
