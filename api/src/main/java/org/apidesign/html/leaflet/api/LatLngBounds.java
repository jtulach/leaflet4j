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

/**
 * Represents a rectangular geographical area on a map.
 */
public final class LatLngBounds {
    static {
        Options.initJS();
    }

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    /**
     * Creates a latLngBounds object by defining south-west and north-east
     * corners of the rectangle.
     *
     * @param southWest The south-west corner.
     * @param northEast The north-east corner.
     */
    public LatLngBounds(LatLng southWest, LatLng northEast) {
        this.jsObj = create(southWest.getJSObj(), northEast.getJSObj());
    }

    /**
     * Creates a LatLngBounds object defined by the geographical points it
     * contains.
     *
     * @param latLngs The geographical points.
     */
    public LatLngBounds(LatLng[] latLngs) {
        this.jsObj = create(getArrayOfJSObj(latLngs));
    }

    private static Object[] getArrayOfJSObj(LatLng[] latLngs) {
        Object[] objs = new Object[latLngs.length];
        for (int i = 0; i < latLngs.length; i++) {
            objs[i] = latLngs[i].getJSObj();
        }
        return objs;
    }

    LatLngBounds(Object jsObj) {
        this.jsObj = jsObj;
    }

    /**
     * Extends the bounds to contain the given point.
     *
     * @param latLng The point.
     */
    public void extend(LatLng latLng) {
        extend(jsObj, latLng.getJSObj());
    }

    /**
     * Extends the bounds to contain the given bound.
     *
     * @param latLngBounds The bounds.
     */
    public void extend(LatLngBounds latLngBounds) {
        extend(jsObj, latLngBounds.getJSObj());
    }

    /**
     * Returns the south-west point of the bounds.
     *
     * @return Returns the south-west point of the bounds.
     */
    public LatLng getSouthWest() {
        return new LatLng(getSouthWest(jsObj));
    }

    /**
     * Returns the north-east point of the bounds.
     *
     * @return Returns the north-east point of the bounds.
     */
    public LatLng getNorthEast() {
        return new LatLng(getNorthEast(jsObj));
    }

    /**
     * Returns the north-west point of the bounds.
     *
     * @return Returns the north-west point of the bounds.
     */
    public LatLng getNorthWest() {
        return new LatLng(getNorthWest(jsObj));
    }

    /**
     * Returns the south-east point of the bounds.
     *
     * @return Returns the south-east point of the bounds.
     */
    public LatLng getSouthEast() {
        return new LatLng(getSouthEast(jsObj));
    }

    /**
     * Returns the west longitude of the bounds.
     *
     * @return Returns the west longitude of the bounds.
     */
    public double getWest() {
        return getWest(jsObj);
    }

    /**
     * Returns the south latitude of the bounds.
     *
     * @return Returns the south latitude of the bounds.
     */
    public double getSouth() {
        return getSouth(jsObj);
    }

    /**
     * Returns the east longitude of the bounds.
     *
     * @return Returns the east longitude of the bounds.
     */
    public double getEast() {
        return getEast(jsObj);
    }

    /**
     * Returns the north latitude of the bounds.
     *
     * @return Returns the north latitude of the bounds.
     */
    public double getNorth() {
        return getNorth(jsObj);
    }

    /**
     * Returns the center point of the bounds.
     *
     * @return Returns the center point of the bounds.
     */
    public LatLng getCenter() {
        return new LatLng(getCenter(jsObj));
    }

    /**
     * Returns <code>true</code> if the rectangle contains the given one.
     *
     * @param other The rectangle.
     * @return Returns <code>true</code> if the rectangle contains the given
     * one.
     */
    public boolean contains(LatLngBounds other) {
        return contains(jsObj, other.getJSObj());
    }

    /**
     * Returns <code>true</code> if the rectangle contains the given point.
     *
     * @param latLng The point.
     * @return Returns <code>true</code> if the rectangle contains the given
     * point.
     */
    public boolean contains(LatLng latLng) {
        return contains(jsObj, latLng.getJSObj());
    }

    /**
     * Returns <code>true</code> if the rectangle intersects the given bounds.
     *
     * @param other The bounds.
     * @return Returns <code>true</code> if the rectangle intersects the given
     * bounds.
     */
    public boolean intersects(LatLngBounds other) {
        return intersects(jsObj, other.getJSObj());
    }

    /**
     * Returns <code>true</code> if the rectangle is equivalent (within a small
     * margin of error) to the given bounds.
     *
     * @param other The other rectangle.
     * @return Returns <code>true</code> if the rectangle is equivalent (within
     * a small margin of error) to the given bounds.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds otherBounds = (LatLngBounds) other;
        return equals(jsObj, otherBounds.getJSObj());
    }

    /**
     * Returns a string with bounding box coordinates in a
     * 'southwest_lng,southwest_lat,northeast_lng,northeast_lat' format.
     *
     * @return Returns the bounding box coordinates as string.
     */
    public String toBBoxString() {
        return toBBoxString(jsObj);
    }

    /**
     * Returns bigger bounds created by extending the current bounds by a given
     * percentage in each direction.
     *
     * @param bufferRatio The buffer ration.
     * @return Returns bigger bounds created by extending the current bounds by
     * a given percentage in each direction.
     */
    public LatLngBounds pad(double bufferRatio) {
        return new LatLngBounds(pad(jsObj, bufferRatio));
    }

    /**
     * Returns <code>true</code> if the bounds are properly initialized.
     *
     * @return Returns <code>true</code> if the bounds are properly initialized.
     */
    public boolean isValid() {
        return isValid(jsObj);
    }

    @JavaScriptBody(args = {"southWest", "northEast"},
            body = "return L.latLngBounds(southWest, northEast);")
    private static native Object create(Object southWest, Object northEast);

    @JavaScriptBody(args = {"latLngs"},
            body = "return L.latLngBounds(latLngs);")
    private static native Object create(Object[] latLngs);

    @JavaScriptBody(args = {"jsObj", "latLng"},
            body = "jsObj.extend(latLng);")
    private static native void extend(Object jsObj, Object latLng);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getSouthWest();")
    private static native Object getSouthWest(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getNorthEast();")
    private static native Object getNorthEast(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getNorthWest();")
    private static native Object getNorthWest(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getSouthEast();")
    private static native Object getSouthEast(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getWest();")
    private static native double getWest(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getSouth();")
    private static native double getSouth(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getEast();")
    private static native double getEast(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getNorth();")
    private static native double getNorth(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getCenter();")
    private static native Object getCenter(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.contains(other);")
    private static native boolean contains(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.intersects(other);")
    private static native boolean intersects(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.equals(other);")
    private static native boolean equals(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.toBBoxString();")
    private static native String toBBoxString(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "bufferRatio"},
            body = "return jsObj.pad(bufferRatio);")
    private static native LatLngBounds pad(Object jsObj, double bufferRatio);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.isValid();")
    private static native boolean isValid(Object jsObj);
}
