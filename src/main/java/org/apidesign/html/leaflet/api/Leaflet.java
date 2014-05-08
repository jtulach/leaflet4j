/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;

/** Class that represents one leaflet map associated with an element.
 *
 * @author Jaroslav Tulach
 */
public final class Leaflet {
    private final Object map;
    
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
        double[][] two = new double[points.length][2];
        for (int i = 0; i < points.length; i++) {
            two[i][0] = points[i].getLatitude();
            two[i][1] = points[i].getLongitude();
        }
        return new LeafPath(polygon(map, two));
    }
    
    @JavaScriptBody(args = { "map", "latitude", "longitude", "radius", "color", "fillColor", "fillOpacity" }, 
            body = 
        "return L.circle([latitude, longitude], radius, { 'color' : color,\n"
            + " 'fillColor' : fillColor, 'fillOpacity' : fillOpacity }).addTo(map);"
    )
    private static native Object circle(Object map, double longitude, double latitude, double radius, String color, String fillColor, double fillOpacity);

    @JavaScriptBody(args = { "map", "data" }, 
            body = 
        "return L.polygon(data).addTo(map);"
    )
    private static native Object polygon(Object map, double[][] data);

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
}
