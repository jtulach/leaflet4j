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
 * A class for drawing polygon overlays on a map.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class Polygon extends PolyLine {

    static {
        registerLayerType("L.Polygon", new Function<Object, ILayer>() {
            @Override
            public ILayer apply(Object obj) {
                return new Polygon(obj);
            }
        });
    }

    protected Polygon(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a polygon object given an array of geographical points.
     * @param latlngs Points of the polygon. Shouldn't contain an additional last point equal to the first one.
     */
    public Polygon(LatLng[] latlngs) {
        this(latlngs, new PolyLineOptions());
    }

    /**
     * Instantiates a polygon object given an array of geographical points and an options object (the same as for Polyline).
     * @param latlngs Points of the polygon. Shouldn't contain an additional last point equal to the first one.
     * @param options configuration options
     */
    public Polygon(LatLng[] latlngs, PolyLineOptions options) {
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
            = "return L.polygon(latlngs, options);")
    private static native Object create(Object[] latlngs, Object options);

    // ------- Methods -------------------------------------------
    //TODO: GeoJSON wrapper
    /*
     public String toGeoJSON() {
     return toGeoJSONInternal(jsObj);
     }
     */
    //TODO: GeoJSON wrapper
    /*
     @JavaScriptBody(args = { "jsObj" }, body = 
     "return jsObj.toGeoJSON();")
     private static native Object toGeoJSONInternal(Object jsObj);
     */
    // ------- PolyLine Methods -------------------------------------------
    /**
     * Adds a given point to the polygon.
     * @param latlng point to add
     * @return this
     */
    @Override
    public Polygon addLatLng(LatLng latlng) {
        super.addLatLng(latlng);
        return this;
    }

    /**
     * Replaces all the points in the polygon with the given array of geographical points.
     * @param latlngs array of points that replace the existing ones
     * @return this
     */
    @Override
    public Polygon setLatLngs(LatLng[] latlngs) {
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
    public Polygon addTo(Map map) {
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
    public Polygon setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    /**
     * Brings the layer to the top of all path layers.
     *
     * @return this
     */
    @Override
    public Polygon bringToFront() {
        super.bringToFront();
        return this;
    }

    /**
     * Brings the layer to the bottom of all path layers.
     *
     * @return this
     */
    @Override
    public Polygon bringToBack() {
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
    public Polygon redraw() {
        super.redraw();
        return this;
    }

    // ------- Popup methods -------------------------------------------
    /**
     * Binds a popup with a particular HTML content to a click on this polygon.
     * You can also open the bound popup with the Polygon <code>openPopup</code>
     * method.
     *
     * @param html HTML content of the popup
     * @return this
     */
    @Override
    public Polygon bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }

    /**
     * Binds a popup to a click on this polygon. You can also open the bound
     * popup with the Polygon <code>openPopup</code> method.
     *
     * @param popup popup object
     * @return this
     */
    @Override
    public Polygon bindPopup(Popup popup) {
        super.bindPopup(popup);
        return this;
    }

    /**
     * Binds a popup with a particular popup configuration options to a click on
     * this polygon. You can also open the bound popup with the Polygon
     * <code>openPopup</code> method.
     *
     * @param popup popup object
     * @param options popup configuration options
     * @return this
     */
    @Override
    public Polygon bindPopup(Popup popup, PopupOptions options) {
        super.bindPopup(popup, options);
        return this;
    }

    /**
     * Unbinds the popup previously bound to the polygon with
     * <code>bindPopup</code>.
     *
     * @return this
     */
    @Override
    public Polygon unbindPopup() {
        super.unbindPopup();
        return this;
    }

    /**
     * Opens the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    @Override
    public Polygon openPopup() {
        super.openPopup();
        return this;
    }

    /**
     * Closes the bound popup of the polygon if it's opened.
     *
     * @return this
     */
    @Override
    public Polygon closePopup() {
        super.closePopup();
        return this;
    }

}
