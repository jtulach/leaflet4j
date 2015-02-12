/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015 Christoph Sperl <ch.sperl@gmx.at>
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
package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;

/**
 * Class representing a marker on a map
 * 
 * @author Christoph Sperl
 */
@JavaScriptResource("leaflet-src.js")
public final class Marker {
    
    private final Object instance;
    
    public Marker(LatLng latLon) {
        this(latLon, new MarkerOptions());
    }
    
    public Marker(LatLng latLon, MarkerOptions options) {
        instance = instantiate(latLon.getLatitude(), latLon.getLongitude(), options.getOptionsString());
    }
    
    public Marker addTo(Leaflet map) {
        addToMap(instance, map.map);
        return this;
    }
    
    // added icon definition because app was not able to find the default icons
    @JavaScriptBody(args = { "latitude", "longitude", "options" }, body = 
        "var defIcon = L.icon({\n" +
        "    iconUrl: 'leaflet-0.7.2/images/marker-icon.png',\n" +
        "    iconRetinaUrl: 'leaflet-0.7.2/images/marker-icon-2x.png',\n" +
        "    iconSize: [38, 95],\n" +
        "    iconAnchor: [22, 94],\n" +
        "    popupAnchor: [-3, -76],\n" +
        "    shadowUrl: 'leaflet-0.7.2/images/marker-shadow.png',\n" +
        "    shadowRetinaUrl: 'pages/leaflet-0.7.2/images/marker-shadow.png',\n" +
        "    shadowSize: [68, 95],\n" +
        "    shadowAnchor: [22, 94]\n" +
        "});" +
        "return L.marker([latitude, longitude], {icon: defIcon});")
    private static native Object instantiate(double latitude, double longitude, String options);
    
    @JavaScriptBody(args = { "marker", "map" }, body = "return marker.addTo(map);")
    private static native Object addToMap(Object marker, Object map);
}
