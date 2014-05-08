package org.apidesign.html.leaflet.demo;

import net.java.html.boot.BrowserBuilder;
import org.apidesign.html.leaflet.api.LatLng;
import org.apidesign.html.leaflet.api.Leaflet;


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
    
    /** Called when page is ready */
    public static void onPageLoad(String... args) throws Exception {
        Leaflet map = Leaflet.map("map");
        map.setView(new LatLng(51.505, -0.09), 13);
        map.addTileLayer(
            "https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png",
            "Map data &copy; <a href='http://openstreetmap.org'>OpenStreetMap</a> contributors, " +
            "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, " +
            "Imagery © <a href='http://mapbox.com'>Mapbox</a>",
            18,
            "examples.map-9ijuk24y"
        );
        map.addCircle(
            new LatLng(51.508, -0.11), 500, "red", "#f03", 0.5
        ).bindPopup("I am a circle");
        map.addPolygon(
            new LatLng(51.509, -0.08),
            new LatLng(51.503, -0.06),
            new LatLng(51.51, -0.047) 
        ).bindPopup("I am a polygon");

    }
}
