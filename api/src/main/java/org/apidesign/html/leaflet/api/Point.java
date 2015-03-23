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
 * Represents a point with x and y coordinates in pixels.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Point {

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    /**
     * Creates a Point object with the given x and y coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Point(double x, double y) {
        this(x, y, false);
    }

    /**
     * Creates a Point object with the given x and y coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param round Rounds the x and y value if <code>true</code>.
     */
    public Point(double x, double y, boolean round) {
        this.jsObj = create(x, y, round);
    }

    Point(Object jsObj) {
        this.jsObj = jsObj;
    }

    /**
     * Gets the x coordinate.
     *
     * @return Returns the x coordinate.
     */
    public double getX() {
        return getX(jsObj);
    }

    /**
     * Sets the x coordinate.
     *
     * @param x The x coordinate.
     */
    public void setX(double x) {
        setX(jsObj, x);
    }

    /**
     * Gets the y coordinate.
     *
     * @return Returns the y coordinate.
     */
    public double getY() {
        return getY(jsObj);
    }

    /**
     * Sets the y coordinate
     *
     * @param y The y coordinate.
     */
    public void setY(double y) {
        setY(jsObj, y);
    }

    /**
     * Returns the result of addition of the current and the given points.
     *
     * @param other The given point.
     * @return Returns the result of addition of the current and the given
     * points.
     */
    public Point add(Point other) {
        return new Point(add(jsObj, other.getJSObj()));
    }

    /**
     * Returns the result of subtraction of the given point from the current.
     *
     * @param other The given point.
     * @return Returns the result of subtraction of the given point from the
     * current.
     */
    public Point subtract(Point other) {
        return new Point(subtract(jsObj, other.getJSObj()));
    }

    /**
     * Returns the result of multiplication of the current point by the given
     * number.
     *
     * @param number The given number.
     * @return Returns the result of multiplication of the current point by the
     * given number.
     */
    public Point multiplyBy(double number) {
        return new Point(multiplyBy(jsObj, number));
    }

    /**
     * Returns the result of division of the current point by the given number.
     *
     * @param number The given number.
     * @return Returns the result of division of the current point by the given
     * number.
     */
    public Point divideBy(double number) {
        return divideBy(number, false);
    }

    /**
     * Returns the result of division of the current point by the given number.
     *
     * @param number The given number.
     * @param round Rounds the result if true.
     * @return Returns the result of division of the current point by the given
     * number.
     */
    public Point divideBy(double number, boolean round) {
        return new Point(divideBy(jsObj, number, round));
    }

    /**
     * Returns the distance between the current and the given points.
     *
     * @param other The given point.
     * @return Returns the distance between the current and the given points.
     */
    public double distanceTo(Point other) {
        return distanceTo(jsObj, other.getJSObj());
    }

    /**
     * Returns a copy of the current point.
     *
     * @return Returns a copy of the current point.
     */
    @Override
    public Point clone() {
        return new Point(clone(jsObj));
    }

    /**
     * Returns a copy of the current point with rounded coordinates.
     *
     * @return Returns a copy of the current point with rounded coordinates.
     */
    public Point round() {
        return new Point(round(jsObj));
    }

    /**
     * Returns a copy of the current point with floored coordinates (rounded
     * down).
     *
     * @return Returns a copy of the current point with floored coordinates
     * (rounded down).
     */
    public Point floor() {
        return new Point(floor(jsObj));
    }

    /**
     * Returns <code>true</code> if the given point has the same coordinates.
     *
     * @param other The other point.
     * @return Returns <code>true</code> if the given point has the same
     * coordinates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point)) {
            return false;
        }
        Point otherPoint = (Point) other;
        return equals(jsObj, otherPoint.getJSObj());
    }

    /**
     * Returns <code>true</code> if the both coordinates of the given point are
     * less than the corresponding current point coordinates (in absolute
     * values).
     *
     * @param other The other point.
     * @return Returns <code>true</code> if the both coordinates of the given
     * point are less than the corresponding current point coordinates (in
     * absolute values).
     */
    public boolean contains(Point other) {
        return contains(jsObj, other.getJSObj());
    }

    /**
     * Returns a string representation of the point for debugging purposes.
     *
     * @return Returns a string representation of the point for debugging
     * purposes.
     */
    @Override
    public String toString() {
        return toString(jsObj);
    }

    @JavaScriptBody(args = {"x", "y", "round"},
            body = "return L.point(x, y, round);")
    private static native Object create(double x, double y, boolean round);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.x;")
    private static native double getX(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "xVal"},
            body = "jsObj.x = xVal;")
    private static native void setX(Object jsObj, double xVal);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.y;")
    private static native double getY(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "yVal"},
            body = "jsObj.y = yVal;")
    private static native void setY(Object jsObj, double yVal);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.add(other);")
    private static native Object add(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.subtract(other);")
    private static native Object subtract(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj", "number"},
            body = "return jsObj.multiplyBy(number);")
    private static native Object multiplyBy(Object jsObj, double number);

    @JavaScriptBody(args = {"jsObj", "number", "round"},
            body = "return jsObj.divideBy(number, round);")
    private static native Object divideBy(Object jsObj, double number, boolean round);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.distanceTo(other);")
    private static native double distanceTo(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.clone();")
    private static native Object clone(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.round();")
    private static native Object round(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.floor();")
    private static native Object floor(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.equals(other);")
    private static native boolean equals(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj", "other"},
            body = "return jsObj.contains(other);")
    private static native boolean contains(Object jsObj, Object other);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.toString();")
    private static native String toString(Object jsObj);
}
