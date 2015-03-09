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
package org.apidesign.html.demo.l4jfxdemo;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import org.apidesign.html.leaflet.api.Circle;
import org.apidesign.html.leaflet.api.LatLng;
import org.apidesign.html.leaflet.api.Map;
import org.apidesign.html.leaflet.api.PathOptions;
import org.apidesign.html.leaflet.api.Polygon;
import org.apidesign.html.leaflet.api.TileLayer;
import org.apidesign.html.leaflet.api.TileLayerOptions;

/**
 * A simple example View of how to embed DukeScript in a JavaFX Application. In
 * this example we use leaflet4j https://github.com/jtulach/leaflet4j.
 *
 * @author antonepple
 */
public class MapView extends StackPane {

    private final WebView webView;
    private Map map;

    public MapView() {
        // we define a regular JavaFX WebView that DukeScript can use for rendering
        webView = new WebView();
        getChildren().add(webView);
        
        // FXBrowsers loads the associated page into the WebView and runs our 
        // code. 
        FXBrowsers.load(webView, MapView.class.getResource("/pages/index.html"), new Runnable() {

            @Override
            public void run() {
                // Here we define that the map is rendered to a div with id="map" 
                // in our index.html.
                // This can only be done after the page is loaded and the context is initialized.
                map = new Map("map");
                
                // from here we just use the Leaflet API to show some stuff on the map
                map.setView(new LatLng(51.505, -0.09), 13);
                map.addLayer(new TileLayer("https://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png",
                        new TileLayerOptions()
                                .setAttribution("Map data &copy; <a href='http://openstreetmap.org'>OpenStreetMap</a> contributors, "
                                                + "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
                                                + "Imagery © <a href='http://mapbox.com'>Mapbox</a>")
                                .setMaxZoom(18)
                                .setId("eppleton.ia9c2p12")
                ));
                
                // sample code showing how to use the Java API
                map.addLayer(new Circle(new LatLng(51.508, -0.11), 500,
                        new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5)
                ).bindPopup("I am a Circle"));
                map.addLayer(new Polygon(new LatLng[] {
                        new LatLng(51.509, -0.08),
                        new LatLng(51.503, -0.06),
                        new LatLng(51.51, -0.047)
                }).bindPopup("I am a Polygon"));

            }
        });
    }

    public Map getMap() {
        return map;
    }

    public WebView getWebView() {
        return webView;
    }
}
