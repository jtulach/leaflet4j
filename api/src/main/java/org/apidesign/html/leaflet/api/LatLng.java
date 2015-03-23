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
 * Represents a geographical point with a certain latitude and longitude.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class LatLng {

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    /**
     * Creates an object representing a geographical point with the given
     * latitude and longitude.
     *
     * @param latitude The latitude.
     * @param longitude The longitude.
     */
    public LatLng(double latitude, double longitude) {
        this.jsObj = create(latitude, longitude);
    }

    LatLng(Object jsObj) {
        this.jsObj = jsObj;
    }

    /**
     * Getter for the latitude in degrees.
     *
     * @return Returns the latitude in degrees.
     */
    public double getLatitude() {
        return getLat(jsObj);
    }

    /**
     * Setter for the latitude in degrees.
     *
     * @param latitude The latitude in degrees.
     */
    public void setLatitude(double latitude) {
        setLat(jsObj, latitude);
    }

    /**
     * Getter for the longitude in degrees.
     *
     * @return Returns the longitude in degrees.
     */
    public double getLongitude() {
        return getLng(jsObj);
    }

    /**
     * Setter for the longitude in degrees.
     *
     * @param longitude The longitude in degrees.
     */
    public void setLongitude(double longitude) {
        setLng(jsObj, longitude);
    }

    /**
     * Returns the distance (in meters) to the given LatLng calculated using the
     * Haversine formula.
     *
     * @param other The other latlng.
     * @return Returns the distance to the giben LatLng.
     */
    public double distanceTo(LatLng other) {
        return distanceTo(jsObj, other.getJSObj());
    }

    /**
     * Returns <code>true</code> if the given LatLng point is at the same
     * position (within a small margin of error).
     *
     * @param other The other LatLng point.
     * @return Returns <code>true</code> if the given LatLng point is at the
     * same position (within a small margin of error), <code>false</code>
     * otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LatLng)) {
            return false;
        }
        LatLng otherLatLng = (LatLng) other;
        return equals(jsObj, otherLatLng.getJSObj());
    }

    /**
     * Returns a string representation of the point (for debugging purposes).
     * @return Returns a string representation of the point (for debugging purposes).
     */
    @Override
    public String toString() {
        return toString(jsObj);
    }

    /**
     * Returns a new LatLng object with the longitude wrapped around left and right boundaries (-180 to 180 by default).
     * @param left The left boundary.
     * @param right The right boundary.
     * @return The new LatLng object.
     */
    public LatLng wrap(double left, double right) {
        return new LatLng(wrap(jsObj, left, right));
    }

    @JavaScriptBody(args = {"latitude", "longitude"},
            body = "return L.latLng(latitude, longitude);")
    private static native Object create(double latitude, double longitude);

    @JavaScriptBody(args = {"jsObj"}, body = "return jsObj.lat;")
    private static native double getLat(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "latitudeVal"}, body = "jsObj.lat = latitudeVal;")
    private static native void setLat(Object jsObj, double latitudeVal);

    @JavaScriptBody(args = {"jsObj"}, body = "return jsObj.lng;")
    private static native double getLng(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "longitudeVal"}, body = "jsObj.lng = longitudeVal;")
    private static native void setLng(Object jsObj, double longitudeVal);

    @JavaScriptBody(args = {"jsObj", "other"}, body = "return jsObj.distanceTo(other);")
    private static native double distanceTo(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj", "other"}, body = "return jsObj.equals(other);")
    private static native boolean equals(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj"}, body = "return jsObj.toString();")
    private static native String toString(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "left", "right"}, body = "return jsObj.wrap(left, right);")
    private static native Object wrap(Object jsObj, double left, double right);
}
