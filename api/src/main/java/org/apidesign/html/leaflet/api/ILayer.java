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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

/**
 * Abstract class which implements basic Layer functionality. Derive from this
 * class to implement custom layers. Do not forget to register the new layer
 * type using registerLayerType.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public abstract class ILayer {

    protected final Object jsObj;

    private final static HashMap<String, Function<Object, ILayer>> registeredLayerTypes = new HashMap<>();

    /**
     * Registers a layer to allow correct type mapping
     *
     * @param layerTypeName The global accessible JS layer type name
     * @param creator A function returning a new Layer instance from a JS object
     */
    protected static void registerLayerType(String layerTypeName, Function<Object, ILayer> creator) {
        registeredLayerTypes.putIfAbsent(layerTypeName, creator);
    }

    protected static void unregisterLayerType(String layerTypeName) {
        registeredLayerTypes.remove(layerTypeName);
    }

    @JavaScriptBody(args = {"jsObj", "layerTypeName"}, body
            = "return jsObj instanceof eval(layerTypeName);")
    private static native boolean checkLayerType(Object jsObj, String layerTypeName);

    @JavaScriptBody(args = {"classAName", "classBName"}, body
            = "return eval(classAName).prototype instanceof eval(classBName);")
    private static native boolean isSubclassOf(String classAName, String classBName);

    protected static ILayer createLayer(Object jsObj) {
        List<String> compatibleTypes = new ArrayList<>();
        for (String layerName : registeredLayerTypes.keySet()) {
            if (checkLayerType(jsObj, layerName)) {
                compatibleTypes.add(layerName);
            }
        }
        if (compatibleTypes.isEmpty()) {
            return new UnknownLayer(jsObj);
        }
        compatibleTypes.sort((a, b) -> isSubclassOf(b, a) ? 1 : -1);
        return registeredLayerTypes.get(compatibleTypes.get(0)).apply(jsObj);
    }

    protected ILayer(Object jsObj) {
        this.jsObj = jsObj;
    }

    Object getJSObj() {
        return jsObj;
    }

    // Accessor methods for internal Javascript object wrappers
    protected static Object getJSObj(ILayer obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(ICRS obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(IProjection obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(Icon obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(LatLng obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(LatLngBounds obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(Bounds obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(Map obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(Point obj) {
        return obj.getJSObj();
    }

    protected static Object getJSObj(Transformation obj) {
        return obj.getJSObj();
    }

}
