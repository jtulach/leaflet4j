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

import java.util.HashMap;
import net.java.html.js.JavaScriptBody;

/**
 * An object with methods for projecting geographical coordinates of the world
 * onto a flat surface (and back). See
 * <a href="http://en.wikipedia.org/wiki/Map_projection">Map projection</a>.
 */
public abstract class IProjection {
    static {
        Options.initJS();
    }
    final Object jsObj;

    IProjection(Object jsObj) {
        this.jsObj = jsObj;
    }

    Object getJSObj() {
        return jsObj;
    }

    private final static HashMap<String, IProjection> registeredProjections = new HashMap<>();

    static void registerProjection(String projectionName, IProjection projection) {
        if (!registeredProjections.containsKey(projectionName)) {
            registeredProjections.put(projectionName, projection);
        }
    }

    static void unregisterProjection(String projectionName) {
        registeredProjections.remove(projectionName);
    }

    @JavaScriptBody(args = {"jsObjA", "jsObjB"}, body
            = "return jsObj == jsObjB;")
    private static native boolean checkEqual(Object jsObjA, Object jsObjB);

    static IProjection createProjection(Object jsObj) {
        for (IProjection proj : registeredProjections.values()) {
            if (checkEqual(jsObj, proj.getJSObj())) {
                return proj;
            }
        }
        return null;
    }

    // ------  Method wrappers -------------------------------------------
    /**
     * Projects geographical coordinates into a 2D point.
     *
     * @param latlng geographical coordinates
     * @return 2D point
     */
    public Point project(LatLng latlng) {
        return new Point(projectInternal(jsObj, latlng.getJSObj()));
    }

    /**
     * The inverse of <code>project</code>. Projects a 2D point into
     * geographical location.
     *
     * @param point 2D point
     * @return geographical coordinates
     */
    public LatLng unproject(Point point) {
        return new LatLng(unprojectInternal(jsObj, point.getJSObj()));
    }

    @JavaScriptBody(args = {"jsObj", "latlng"},
            body = "return jsObj.project(latlng);")
    private static native Object projectInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = {"jsObj", "point"},
            body = "return jsObj.unproject(point);")
    private static native Object unprojectInternal(Object jsObj, Object point);

}
