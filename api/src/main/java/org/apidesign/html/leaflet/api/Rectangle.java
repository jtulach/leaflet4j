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
 * A class for drawing rectangle overlays on a map.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Rectangle extends Polygon {

    static {
        registerLayerType("L.Rectangle", new Function<Object, ILayer>() {
            @Override
            public ILayer apply(Object obj) {
                return new Rectangle(obj);
            }
        });
    }

    protected Rectangle(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a rectangle object with the given geographical bounds.
     * @param bounds geographical bounds
     */
    public Rectangle(LatLngBounds bounds) {
        this(bounds, new PolyLineOptions());
    }

    /**
     * Instantiates a rectangle object with the given geographical bounds and an options object.
     * @param bounds geographical bounds
     * @param options configuration options
     */
    public Rectangle(LatLngBounds bounds, PolyLineOptions options) {
        super(create(bounds.getJSObj(), options));
    }

    @JavaScriptBody(args = {"bounds", "options"}, body
            = "return L.rectangle(bounds, options);")
    private static native Object create(Object bounds, Object options);

    // ------- Methods -------------------------------------------
    
    /**
     * Redraws the rectangle with the passed bounds.
     * @param bounds bounds of the rectangle
     * @return this
     */
    public Rectangle setBounds(LatLngBounds bounds) {
        setBoundsInternal(jsObj, bounds.getJSObj());
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "bounds"}, body
            = "return jsObj.setBounds(bounds);")
    private static native void setBoundsInternal(Object jsObj, Object bounds);

    // ------- PolyLine Methods -------------------------------------------
    /**
     * Adds a given point to the rectangle.
     *
     * @param latlng point to add
     * @return this
     */
    @Override
    public Rectangle addLatLng(LatLng latlng) {
        super.addLatLng(latlng);
        return this;
    }

    /**
     * Replaces all the points in the rectangle with the given array of
     * geographical points.
     *
     * @param latlngs array of points that replace the existing ones
     * @return this
     */
    @Override
    public Rectangle setLatLngs(LatLng[] latlngs) {
        super.setLatLngs(latlngs);
        return this;
    }

    // ------- Path Methods -------------------------------------------
    /**
     * Adds the layer to the map.
     *
     * @param map The map
     * @return this
     */
    @Override
    public Rectangle addTo(Map map) {
        super.addTo(map);
        return this;
    }

    /**
     * Changes the appearance of a Rectangle based on the options in the
     * {@link PathOptions} object.
     *
     * @param options path configuration options
     * @return this
     */
    @Override
    public Rectangle setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    /**
     * Brings the layer to the top of all path layers.
     *
     * @return this
     */
    @Override
    public Rectangle bringToFront() {
        super.bringToFront();
        return this;
    }

    /**
     * Brings the layer to the bottom of all path layers.
     *
     * @return this
     */
    @Override
    public Rectangle bringToBack() {
        super.bringToBack();
        return this;
    }

    /**
     * Redraws the layer. Sometimes useful after you changed the coordinates
     * that the path uses.
     *
     * @return this
     */
    @Override
    public Rectangle redraw() {
        super.redraw();
        return this;
    }

    // ------- Popup methods -------------------------------------------
    /**
     * Binds a popup with a particular HTML content to a click on this
     * rectangle.
     *
     * @param html poup HTML content
     * @return this
     */
    @Override
    public Rectangle bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }

    /**
     * Binds a given popup object to the rectangle.
     *
     * @param popup popup object
     * @return this
     */
    @Override
    public Rectangle bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }

    /**
     * Binds a given popup object with the given options to the rectangle.
     *
     * @param popup popup object
     * @param options popup configuration object
     * @return this
     */
    @Override
    public Rectangle bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }

    /**
     * Unbinds the popup previously bound to the rectangle with
     * <code>bindPopup</code>.
     *
     * @return this
     */
    @Override
    public Rectangle unbindPopup() {
        super.unbindPopup();
        return this;
    }

    /**
     * Opens the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    @Override
    public Rectangle openPopup() {
        super.openPopup();
        return this;
    }

    /**
     * Closes the rectangle's bound popup if it is opened.
     *
     * @return this
     */
    @Override
    public Rectangle closePopup() {
        super.closePopup();
        return this;
    }

}
