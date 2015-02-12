/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2014 Jaroslav Tulach <jaroslav.tulach@apidesign.org>
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

/** Class that represents one leaflet map associated with an element.
 *
 * @author Jaroslav Tulach
 */
@JavaScriptResource("leaflet-src.js")
public final class Leaflet {
    final Object map;
    
    private Leaflet(Object map) {
        this.map = map;
    }
    
    public static Leaflet map(String id) {
        return new Leaflet(init(id));
    }
    
    public Leaflet setView(LatLng ll, int zoom) {
        setViewImpl(map, ll.getLatitude(), ll.getLongitude(), zoom);
        return this;
    }
    
    public Leaflet addTileLayer(String url, String attribution, int maxZoom, String id) {
        addTileLayerImpl(map, url, attribution, maxZoom, id);
        return this;
    }
    
    public LeafPath addCircle(LatLng ll, double radius, String color, String fillColor, double fillOpacity) {
        return new LeafPath(circle(map, ll.getLatitude(), ll.getLongitude(), radius, color, fillColor, fillOpacity));
    }

    public LeafPath addPolygon(LatLng... points) {
        double[] two = new double[points.length * 2];
        for (int i = 0; i < two.length; i += 2) {
            two[i + 0] = points[i / 2].getLatitude();
            two[i + 1] = points[i / 2].getLongitude();
        }
        return new LeafPath(polygon(map, two));
    }
    
    public LeafPopup openPopup(LatLng ll, String content) {
        return new LeafPopup(popup(map, ll.getLatitude(), ll.getLongitude(), content));
    }
    
    public Leaflet on(MouseEvent.Type type, MouseListener what) {
        addListener(map, this, type.toString().toLowerCase(), what);
        return this;
    }
    
    public Leaflet once(MouseEvent.Type type, MouseListener what) {
        addListenerOnce(map, this, type.toString().toLowerCase(), what);
        return this;
    }
    
    @JavaScriptBody(args = { "map", "latitude", "longitude", "radius", "color", "fillColor", "fillOpacity" }, 
            body = 
        "return L.circle([latitude, longitude], radius, { 'color' : color,\n"
            + " 'fillColor' : fillColor, 'fillOpacity' : fillOpacity }).addTo(map);"
    )
    private static native Object circle(Object map, double latitude, double longitude, double radius, String color, String fillColor, double fillOpacity);

    @JavaScriptBody(args = { "map", "data" }, 
            body = 
        "var arr = [];" +
        "for (var i = 0; i < data.length; i += 2) " +
        "  arr.push(L.latLng(data[i], data[i + 1]));" +
        "var p = L.polygon(arr);" +
        "return p.addTo(map);"
    )
    private static native Object polygon(Object map, double[] data);

    @JavaScriptBody(args = { "id" }, body = "return L.map(id);")
    private static native Object init(String id);
    
    @JavaScriptBody(args = { "map", "latitude", "longitude", "zoom" }, wait4js = false, body = 
        "map.setView([latitude, longitude], zoom);"
    )
    private static native void setViewImpl(Object map, double longitude, double latitude, int zoom);

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
    
    @JavaScriptBody(
        args = { "map", "self", "type", "l" }, wait4js = false, javacall = true,
        body = "map.on(type, function(ev) {\n"
                + "  @org.apidesign.html.leaflet.api.Leaflet::callListener"
                + "(Lorg/apidesign/html/leaflet/api/Leaflet;DD"
                + "Lorg/apidesign/html/leaflet/api/MouseListener;)"
                + "(self, ev.latlng.lat, ev.latlng.lng, l);\n"
                + "});\n"
    )
    private static native void addListener(
        Object map, Leaflet self, String on, MouseListener listener
    );
    
    @JavaScriptBody(
        args = { "map", "self", "type", "l" }, wait4js = false, javacall = true,
        body = "map.once(type, function(ev) {\n"
                + "  @org.apidesign.html.leaflet.api.Leaflet::callListener"
                + "(Lorg/apidesign/html/leaflet/api/Leaflet;DD"
                + "Lorg/apidesign/html/leaflet/api/MouseListener;)"
                + "(self, ev.latlng.lat, ev.latlng.lng, l);\n"
                + "});\n"
    )
    private static native void addListenerOnce(
        Object map, Leaflet self, String on, MouseListener listener
    );
            
    static void callListener(Leaflet self, double l1, double l2, MouseListener l) {
        l.onEvent(new MouseEvent(self, new LatLng(l1, l2)));
    }

    @JavaScriptBody(args = { "map", "latitude", "longitude", "content" }, body = 
        "return L.popup().setLatLng([ latitude, longitude ]).setContent(content).openOn(map);"
    )
    private static native Object popup(Object map, double latitude, double longitude, String content);
}
