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
package org.apidesign.html.demo.leaflet;

import net.java.html.geo.OnLocation;
import net.java.html.boot.BrowserBuilder;
import net.java.html.geo.Position;
import org.apidesign.html.leaflet.api.basicTypes.Icon;
import org.apidesign.html.leaflet.api.basicTypes.IconOptions;
import org.apidesign.html.leaflet.api.basicTypes.JSString;
import org.apidesign.html.leaflet.api.basicTypes.LatLng;
import org.apidesign.html.leaflet.api.map.Map;
import org.apidesign.html.leaflet.api.map.MapOptions;
import org.apidesign.html.leaflet.api.rasterLayers.TileLayer;
import org.apidesign.html.leaflet.api.rasterLayers.TileLayerOptions;
import org.apidesign.html.leaflet.api.uiLayers.Marker;
import org.apidesign.html.leaflet.api.uiLayers.MarkerOptions;
import org.netbeans.api.nbrwsr.OpenHTMLRegistration;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;


/** Bootstrap and initialization. */
public final class Main {
    private Main() {
    }
    
    /** Launches the browser */
    public static void main(String... args) throws Exception {
        BrowserBuilder.newBrowser().
            loadPage("pages/index.html").
            loadClass(Main.class).
            invoke("onPageLoad", args).
            showAndWait();
        System.exit(0);
    }
    /*
    @OnLocation(onError = "noPosition")
    static void whereIAm(Position p, Leaflet map) {
        final Position.Coordinates c = p.getCoords();
        final LatLng loc = new LatLng(c.getLatitude(), c.getLongitude());
        map.setView(loc, 13);
        map.addCircle(loc, c.getAccuracy(), "green", "#f03", 0.5).bindPopup("Here You Are!");
    }
    
    static void noPosition(Throwable t, Leaflet map) {
        // Location of Johannes Kepler University Linz
        final LatLng loc = new LatLng(48.336614, 14.319305);
        // could not derive current location -> set to JKU Linz
        map.setView(loc, 13);
        map.addCircle(loc, 500, "red", "#f03", 0.5).bindPopup(t.getLocalizedMessage());
    }
    */
    @ActionReference(path = "Toolbars/Games")
    @ActionID(id = "org.apidesign.html.demo.leaflet4j", category = "Games")
    @OpenHTMLRegistration(
        url = "index.html", 
        displayName = "Where I am?", 
        iconBase = "org/apidesign/html/demo/leaflet/icon.png"
    )
    /** Called when page is ready */
    public static void onPageLoad() throws Exception {
        LatLng latLng = new LatLng(48.336614, 14.319305);
        System.out.println("Latitude = " + latLng.getLatitude());
        
        MapOptions mapOptions = new MapOptions();
        mapOptions.setCenter(latLng);
        mapOptions.setZoom(13);

        /*MapOptions mo = new MapOptions();
        mo.setCenter(new LatLng(48.336614, 14.319305));
        mo.setZoom(13);
        System.out.println(mo.toString());*/
        final Map map = new Map("map", mapOptions);
        
        TileLayerOptions tlo = new TileLayerOptions();
        tlo.setAttribution("Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, " +
            "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, " +
            "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>");
        tlo.setMaxZoom(18);
        TileLayer layer = new TileLayer("http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png", tlo);
        map.addLayer(layer);
        /*
        map.addTileLayer("http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png",
//            "https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png",
            "Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, " +
            "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, " +
            "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>",
            18,
            "jtulach.iimpdmak");
        */
        IconOptions io = new IconOptions();
        io.setIconUrl("leaflet-0.7.2/images/marker-icon.png");
        MarkerOptions mo = new MarkerOptions();
        mo.setIcon(new Icon(io));
        
        Marker m = new Marker(new LatLng(48.336614, 14.33), mo);
        m.addTo(map);
        /*
        final Leaflet map = Leaflet.map("map");
        map.addTileLayer(
            "http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png",
//            "https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png",
            "Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, " +
            "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, " +
            "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>",
            18,
            "jtulach.iimpdmak"
        );*/
        
        //final LatLng loc = new LatLng(48.336614, 14.319305);
        // could not derive current location -> set to JKU Linz
        //map.setView(loc, 13);
/*
        map.on(MouseEvent.Type.CLICK, new MouseListener() {
            @Override
            public void onEvent(MouseEvent ev) {
                map.openPopup(ev.getLatLng(), "You clicked the map at " + ev.getLatLng());
            }
        });*/
        /*
        Marker m = new Marker(new LatLng(48.336614, 14.319405));
        m.addTo(map);*/
        
        // Query to mark our position if possible
        //query(map, 3000);
    }
/*
    private static void query(final Leaflet map, final long timeout) {
        Position.Handle q = WhereIAmHandle.createQuery(map);
        q.setMaximumAge(60000);
        q.setTimeout(timeout);
        q.start();
    }*/
}
