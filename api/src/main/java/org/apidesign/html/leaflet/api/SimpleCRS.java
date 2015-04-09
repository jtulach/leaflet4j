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
 * A simple CRS that maps longitude and latitude into <code>x</code> and
 * <code>y</code> directly. May be used for maps of flat surfaces (e.g. game
 * maps). Note that the <code>y</code> axis should still be inverted (going from
 * bottom to top).
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
final class SimpleCRS extends ICRS {

    private static final SimpleCRS instance = new SimpleCRS();

    static {
        ICRS.registerCRS("SimpleCRS", instance);
    }

    /**
     * Returns the Instance of SimpleCRS
     *
     * @return instance of SimpleCRS
     */
    static SimpleCRS get() {
        return instance;
    }

    private SimpleCRS() {
        super(getCRSInternal());
    }

    @JavaScriptBody(args = {}, body = "return L.CRS.Simple;")
    private static native Object getCRSInternal();
}
