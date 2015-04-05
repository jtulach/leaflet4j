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
 * A class for drawing polyline overlays on a map.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class PolyLine extends Path {

    static {
        registerLayerType("L.Polyline", new Function<Object, ILayer>() {
            @Override
            public ILayer apply(Object obj) {
                return new PolyLine(obj);
            }
        });
    }

    PolyLine(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a polyline object given an array of geographical points.
     * @param latlngs array of geographical points
     */
    public PolyLine(LatLng[] latlngs) {
        this(latlngs, new PolyLineOptions());
    }

    /**
     * Instantiates a polyline object given an array of geographical points and an options object.
     * @param latlngs array of geographical points
     * @param options configuration options
     */
    public PolyLine(LatLng[] latlngs, PolyLineOptions options) {
        super(createHelper(latlngs, options));
    }

    private static Object createHelper(LatLng[] latlngs, PolyLineOptions options) {
        Object[] latlngsJS = new Object[latlngs.length];
        for (int q = 0; q < latlngsJS.length; q++) {
            latlngsJS[q] = latlngs[q].getJSObj();
        }
        return create(latlngsJS, options.getJSObj());
    }

    @JavaScriptBody(args = {"latlngs", "options"}, body
            = "return L.polyline(latlngs, options);")
    private static native Object create(Object[] latlngs, Object options);

    // ------- Methods -------------------------------------------
    /**
     * Adds a given point to the polyline.
     *
     * @param latlng point to add
     * @return this
     */
    public PolyLine addLatLng(LatLng latlng) {
        addLatLngInternal(jsObj, latlng.getJSObj());
        return this;
    }

    /**
     * Returns an array of the points in the path.
     *
     * @return array of the points in the path
     */
    public LatLng[] getLatLngs() {
        Object[] latlngsJS = getLatLngsInternal(jsObj);
        LatLng[] latlngs = new LatLng[latlngsJS.length];
        for (int q = 0; q < latlngs.length; q++) {
            latlngs[q] = new LatLng(latlngsJS[q]);
        }
        return latlngs;
    }

    /**
     * Replaces all the points in the polyline with the given array of
     * geographical points.
     *
     * @param latlngs array of points that replace the existing ones
     * @return this
     */
    public PolyLine setLatLngs(LatLng[] latlngs) {
        Object[] latlngsJS = new Object[latlngs.length];
        for (int q = 0; q < latlngsJS.length; q++) {
            latlngsJS[q] = latlngs[q].getJSObj();
        }
        setLatLngsInternal(jsObj, latlngs);
        return this;
    }

    /**
     * Allows adding, removing or replacing points in the polyline. Syntax is
     * the same as in
     * <a href="https://developer.mozilla.org/en/JavaScript/Reference/Global_Objects/Array/splice">Array#splice</a>.
     * Returns the array of removed points (if any).
     *
     * @param index index from that points should be inserted/removed
     * @param pointsToRemove number of points to remove
     * @param latlngs array of points to insert
     * @return array of removed points (if any)
     */
    public LatLng[] spliceLatLngs(int index, int pointsToRemove, LatLng... latlngs) {
        Object[] latlngsJS = new Object[latlngs.length];
        for (int q = 0; q < latlngsJS.length; q++) {
            latlngsJS[q] = latlngs[q].getJSObj();
        }
        latlngsJS = spliceLatLngsInternal(jsObj, index, pointsToRemove, latlngsJS);
        LatLng[] latlngsRet = new LatLng[latlngsJS.length];
        for (int q = 0; q < latlngsJS.length; q++) {
            latlngsRet[q] = new LatLng(latlngsJS[q]);
        }
        return latlngsRet;
    }

    //TODO: GeoJSON wrapper
    /*
     public String toGeoJSON() {
     return toGeoJSONInternal(jsObj);
     }
     */
    @JavaScriptBody(args = {"jsObj", "latlng"}, body
            = "jsObj.addLatLng(latlng);")
    private static native void addLatLngInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getLatLngs();")
    private static native Object[] getLatLngsInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "latlngs"}, body
            = "jsObj.setLatLngs(latlngs);")
    private static native void setLatLngsInternal(Object jsObj, Object[] latlngs);

    @JavaScriptBody(args = {"jsObj", "index", "pointsToRemove", "latlngs"}, body
            = "var args = [index, pointsToRemove].concat(latlngs); return apply(jsObj.spliceLatLngs, args);")
    private static native Object[] spliceLatLngsInternal(Object jsObj, int index, int pointsToRemove, Object[] latlngs);

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
    public PolyLine addTo(Map map) {
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
    public PolyLine setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    /**
     * Brings the layer to the top of all path layers.
     *
     * @return this
     */
    @Override
    public PolyLine bringToFront() {
        super.bringToFront();
        return this;
    }

    /**
     * Brings the layer to the bottom of all path layers.
     *
     * @return this
     */
    @Override
    public PolyLine bringToBack() {
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
    public PolyLine redraw() {
        super.redraw();
        return this;
    }

    // ------- Popup methods -------------------------------------------
    /**
     * Binds a popup with a particular HTML content to a click on this polyline.
     * You can also open the bound popup with the Polyline
     * <code>openPopup</code> method.
     *
     * @param html HTML content of the popup
     * @return this
     */
    @Override
    public PolyLine bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }

    /**
     * Binds a popup to a click on this polyline. You can also open the bound
     * popup with the Polyline <code>openPopup</code> method.
     *
     * @param popup popup object
     * @return this
     */
    @Override
    public PolyLine bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }

    /**
     * Binds a popup with a particular popup configuration options to a click on
     * this polyline. You can also open the bound popup with the Polyline
     * <code>openPopup</code> method.
     *
     * @param popup popup object
     * @param options popup configuration options
     * @return this
     */
    @Override
    public PolyLine bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }

    /**
     * Unbinds the popup previously bound to the polyline with
     * <code>bindPopup</code>.
     *
     * @return this
     */
    @Override
    public PolyLine unbindPopup() {
        super.unbindPopup();
        return this;
    }

    /**
     * Opens the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    @Override
    public PolyLine openPopup() {
        super.openPopup();
        return this;
    }

    /**
     * Closes the bound popup of the polyline if it's opened.
     *
     * @return this
     */
    @Override
    public PolyLine closePopup() {
        super.closePopup();
        return this;
    }

}
