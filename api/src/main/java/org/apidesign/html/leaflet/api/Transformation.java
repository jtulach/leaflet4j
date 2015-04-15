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
 * Represents an affine transformation: a set of coefficients <code>a</code>,
 * <code>b</code>, <code>c</code>, <code>d</code> for transforming a point of a
 * form <code>(x, y)</code> into <code>(a*x + b, c*y + d)</code> and doing the
 * reverse.
 */
public final class Transformation {
    static {
        Options.initJS();
    }

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    /**
     * Creates a transformation object with the given coefficients.
     * 
     * @param a Coefficient a
     * @param b Coefficient b
     * @param c Coefficient c
     * @param d Coefficient d
     */
    public Transformation(double a, double b, double c, double d) {
        this.jsObj = create(a, b, c, d);
    }

    Transformation(Object jsObj) {
        this.jsObj = jsObj;
    }

    /**
     * Returns a transformed point.
     * @param point The Point to transform
     * @return The transformed point
     */
    public Point transform(Point point) {
        return new Point(transform1Internal(jsObj, point.getJSObj()));
    }

    /**
     * Returns a transformed point, multiplied by the given scale.
     * @param point The Point to transform
     * @param scale The applied scale factor
     * @return The transformed point
     */
    public Point transform(Point point, double scale) {
        return new Point(transform2Internal(jsObj, point.getJSObj(), scale));
    }

    /**
     * Returns the reverse transformation of the given point, optionally divided by the given scale.
     * @param point The Point to reverse transform
     * @return The reverse transformed point
     */
    public Point untransform(Point point) {
        return new Point(untransform1Internal(jsObj, point.getJSObj()));
    }

    /**
     * Returns the reverse transformation of the given point, optionally divided by the given scale.
     * @param point The Point to reverse transform
     * @param scale The applied scale factor
     * @return The reverse transformed point
     */
    public Point untransform(Point point, double scale) {
        return new Point(untransform2Internal(jsObj, point.getJSObj(), scale));
    }

    @JavaScriptBody(args = {"a", "b", "c", "d"},
            body = "return new L.Transformation(a, b, c, d);")
    private static native Object create(double a, double b, double c, double d);

    @JavaScriptBody(args = {"jsObj", "point"},
            body = "return jsObj.transform(point);")
    private static native Object transform1Internal(Object jsObj, Object point);

    @JavaScriptBody(args = {"jsObj", "point", "scale"},
            body = "return jsObj.transform(point, scale);")
    private static native Object transform2Internal(Object jsObj, Object point, double scale);

    @JavaScriptBody(args = {"jsObj", "point"},
            body = "return jsObj.untransform(point);")
    private static native Object untransform1Internal(Object jsObj, Object point);

    @JavaScriptBody(args = {"jsObj", "point", "scale"},
            body = "return jsObj.untransform(point, scale);")
    private static native Object untransform2Internal(Object jsObj, Object point, double scale);

}
