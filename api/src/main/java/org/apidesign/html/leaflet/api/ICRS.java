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

/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public abstract class ICRS {

    protected final Object jsObj;

    protected ICRS(Object jsObj) {
        this.jsObj = jsObj;
    }

    Object getJSObj() {
        return jsObj;
    }

    // ------  Method wrappers -------------------------------------------
    /**
     * Projects geographical coordinates on a given zoom into pixel coordinates.
     *
     * @param latlng geographical coordinates
     * @param zoom zoom level
     * @return pixel coordinates of geographical coordinates on the given zoom
     */
    public Point latLngToPoint(LatLng latlng, int zoom) {
        return new Point(latLngToPointInternal(jsObj, latlng.getJSObj(), zoom));
    }

    /**
     * The inverse of <code>latLngToPoint</code>. Projects pixel coordinates on
     * a given zoom into geographical coordinates.
     *
     * @param point pixel coordinates
     * @param zoom zoom level
     * @return geographical coordinates of pixel coordinates on the given zoom
     */
    public LatLng pointToLatLng(Point point, int zoom) {
        return new LatLng(pointToLatLngInternal(jsObj, point.getJSObj(), zoom));
    }

    /**
     * Projects geographical coordinates into coordinates in units accepted for
     * this CRS (e.g. meters for <code>EPSG:3857</code>, for passing it to WMS
     * services).
     *
     * @param latlng geographical coordinates
     * @return projected coordinates for this CRS
     */
    public Point project(LatLng latlng) {
        return new Point(projectInternal(jsObj, latlng.getJSObj()));
    }

    /**
     * Returns the scale used when transforming projected coordinates into pixel coordinates for a particular zoom. For example, it returns <code>256 * 2^zoom</code> for Mercator-based CRS.
     * @param zoom zoom level
     * @return the scale used when transforming projected coordinates into pixel coordinates
     */
    public double scale(int zoom) {
        return scaleInternal(jsObj, zoom);
    }

    /**
     * Returns the size of the world in pixels for a particular zoom.
     * @param zoom zoom level
     * @return the size of the world in pixels
     */
    public Point getSize(int zoom) {
        return new Point(getSizeInternal(jsObj, zoom));
    }

    @JavaScriptBody(args = {"jsObj", "latlng", "zoom"},
            body = "return jsObj.latLngToPoint(latlng, zoom);")
    private static native Object latLngToPointInternal(Object jsObj, Object latlng, int zoom);

    @JavaScriptBody(args = {"jsObj", "point", "zoom"},
            body = "return jsObj.pointToLatLng(point, zoom);")
    private static native Object pointToLatLngInternal(Object jsObj, Object point, int zoom);

    @JavaScriptBody(args = {"jsObj", "latlng"},
            body = "return jsObj.project(latlng);")
    private static native Object projectInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = {"jsObj", "zoom"},
            body = "return jsObj.project(zoom);")
    private static native double scaleInternal(Object jsObj, int zoom);

    @JavaScriptBody(args = {"jsObj", "zoom"},
            body = "return jsObj.getSize(zoom);")
    private static native double getSizeInternal(Object jsObj, int zoom);

    // ------  Properties wrappers -------------------------------------------
    /**
     * Retrieves the projection that this CRS uses
     * @return Projection that this CRS uses
     */
    public IProjection getProjection() {
        return new IProjection(getProjectionInternal(jsObj));
    }

    /**
     * Retrieves the transformation that this CRS uses to turn projected coordinates into screen coordinates for a particular tile service.
     * @return transformation that this CRS uses
     */
    public Transformation getTransformation() {
        return new Transformation(getTransformationInternal(jsObj));
    }

    /**
     * Retrieves the standard code name of the CRS passed into WMS services (e.g. <code>'EPSG:3857'</code>)
     * @return standard code name of the CRS
     */
    public String getCode() {
        return getCodeInternal(jsObj);
    }

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.projection;")
    private static native Object getProjectionInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.transformation;")
    private static native Object getTransformationInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.code;")
    private static native String getCodeInternal(Object jsObj);

}
