/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015
 * Andreas Grimmer <a.grimmer@gmx.at>
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.apidesign.html.leaflet.api.map;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.JSWrapper;
import org.apidesign.html.leaflet.api.basicTypes.LatLng;

/** Class that represents one leaflet map associated with an element.
 *
 * @author Christoph Sperl
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Map implements JSWrapper {
    
    private final Object jsObj;
    
    public Map(String id) {
        this(id, new MapOptions());
    }

    public Map(String id, MapOptions options) {
        jsObj = Map.create(id, options.getJSObj());
    }
    
    @Override
    public Object getJSObj() {
        return jsObj;
    }
    
    // TODO zoom is optional, and there is also a third optional argument
    public void setView(LatLng center, int zoom) {
        setView(jsObj, center, zoom);
    }
    
    
    // TODO remove this and add a method addLayer(ILayer)
    public void addTileLayer(String url, String attribution, int maxZoom, String id) {
        addTileLayerImpl(jsObj, url, attribution, maxZoom, id);
    }
    
    @JavaScriptBody(args = { "map", "url", "attribution", "maxZoom", "id" }, wait4js = false, body =
        "L.tileLayer(url, {\n" +
        "  'maxZoom': 18,\n" +
        "  'attribution': attribution,\n" +
        "  'id': id\n" +
        "}).addTo(map);"
    )
    private static native void addTileLayerImpl(
        Object map, String url, String attribution, int maxZoom, String id
    );
    
    @JavaScriptBody(args = { "id", "options" }, 
            body = "return L.map(id, options);")
    private static native Object create(String id, Object options);
        
    @JavaScriptBody(args = { "jsObj", "center", "zoom" }, wait4js = false, body = 
        "jsObj.setView(center, zoom);")
    private static native void setView(Object jsObj, Object center, int zoom);
}
