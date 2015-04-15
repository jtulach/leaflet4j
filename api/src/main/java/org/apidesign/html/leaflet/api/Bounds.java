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
 * Represents a rectangular area in pixel coordinates.
 */
public final class Bounds {
    static {
        Options.initJS();
    }

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    Bounds(Object jsObj) {
        this.jsObj = jsObj;
    }

    /**
     * Creates a Bounds object from two coordinates.
     * @param topLeft The top left coordinate.
     * @param bottomRight The bottom right coordinate.
     */
    public Bounds(Point topLeft, Point bottomRight) {
        this.jsObj = create(topLeft.getJSObj(), bottomRight.getJSObj());
    }

    /**
     * Creates a Bounds object defined by the points it contains.
     * @param points  The points defining the bound.
     */
    public Bounds(Point[] points) {
        this.jsObj = create(getArrayOfJSObj(points));
    }

    private static Object[] getArrayOfJSObj(Point[] points) {
        Object[] pointsJS = new Object[points.length];
        for (int q = 0; q < pointsJS.length; q++) {
            pointsJS[q] = points[q].getJSObj();
        }
        return pointsJS;
    }

    @JavaScriptBody(args = {"topLeft", "bottomRight"},
            body = "return L.bounds(topLeft, bottomRight);")
    private static native Object create(Object topLeft, Object bottomRight);

    @JavaScriptBody(args = {"points"},
            body = "return L.bounds(points);")
    private static native Object create(Object[] points);

    // ------- Properties ---------------------------------------------
    /**
     * Gets the top left corner of the rectangle.
     * @return Returns the top left corner of the rectangle.
     */
    public Point getMin() {
        return new Point(getMinInternal(jsObj));
    }

    /**
     * Gets the bottom right corner of the rectangle.
     * @return Returns the bottom right corner of the rectangle.
     */
    public Point getMax() {
        return new Point(getMaxInternal(jsObj));
    }

    /**
     * Sets the top left corner of the rectangle.
     * @param point The top left corner.
     * @return Returns the bounds object.
     */
    public Bounds setMin(Point point) {
        setMinInternal(jsObj, point.getJSObj());
        return this;
    }

    /**
     * Sets the bottom right corner of the rectangle.
     * @param point The bottom right corner.
     * @return Returns the bounds object.
     */
    public Bounds setMax(Point point) {
        setMaxInternal(jsObj, point.getJSObj());
        return this;
    }

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.min;")
    private static native Object getMinInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.max;")
    private static native Object getMaxInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "point"},
            body = "jsObj.min = point;")
    private static native void setMinInternal(Object jsObj, Object point);

    @JavaScriptBody(args = {"jsObj", "point"},
            body = "jsObj.max = point;")
    private static native void setMaxInternal(Object jsObj, Object point);

    // ------- Methods ---------------------------------------------
    /**
     * Extends the bounds to contain the given point.
     * @param point The point.
     */
    public void extend(Point point) {
        extendInternal(jsObj, point.getJSObj());
    }

    /**
     * Returns the center point of the bounds.
     * @return Returns the center point of the bounds.
     */
    public Point getCenter() {
        return new Point(getCenterInternal(jsObj));
    }

    /**
     * Returns <code>true</code> if the rectangle contains the given one.
     * @param otherBounds The given bound.
     * @return Returns <code>true</code> if the rectangle contains the given one.
     */
    public boolean contains(Bounds otherBounds) {
        return containsInternal(jsObj, otherBounds.getJSObj());
    }

    /**
     * Returns <code>true</code> if the rectangle contains the given point.
     * @param point The given point.
     * @return Returns <code>true</code> if the rectangle contains the given point.
     */
    public boolean contains(Point point) {
        return containsInternal(jsObj, point.getJSObj());
    }

    /**
     * Returns <code>true</code> if the rectangle intersects the given bounds.
     * @param otherBounds The given bound.
     * @return Returns <code>true</code> if the rectangle intersects the given bounds.
     */
    public boolean intersects(Bounds otherBounds) {
        return intersectsInternal(jsObj, otherBounds.getJSObj());
    }

    /**
     * Returns <code>true</code> if the bounds are properly initialized.
     * @return Returns <code>true</code> if the bounds are properly initialized.
     */
    public boolean isValid() {
        return isValidInternal(jsObj);
    }
    
    /**
     * Returns the size of the given bounds.
     * @return Returns the size of the given bounds.
     */
    public Point getSize() {
        return new Point(getSizeInternal(jsObj));
    }

    @JavaScriptBody(args = {"jsObj", "latLng"},
            body = "jsObj.extend(latLng);")
    private static native void extendInternal(Object jsObj, Object latLng);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getCenter();")
    private static native Object getCenterInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "otherBounds"},
            body = "return jsObj.contains(otherBounds);")
    private static native boolean containsInternal(Object jsObj, Object otherBounds);

    @JavaScriptBody(args = {"jsObj", "otherBounds"},
            body = "return jsObj.intersects(otherBounds);")
    private static native boolean intersectsInternal(Object jsObj, Object otherBounds);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.isValid();")
    private static native boolean isValidInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getSize();")
    private static native Object getSizeInternal(Object jsObj);
}
