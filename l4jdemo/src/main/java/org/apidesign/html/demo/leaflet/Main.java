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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apidesign.html.demo.leaflet;

import net.java.html.boot.BrowserBuilder;
import org.apidesign.html.leaflet.api.ILayer;
import org.apidesign.html.leaflet.api.Icon;
import org.apidesign.html.leaflet.api.IconOptions;
import org.apidesign.html.leaflet.api.LatLng;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.listener.MouseListener;
import org.apidesign.html.leaflet.api.Map;
import org.apidesign.html.leaflet.api.MapOptions;
import org.apidesign.html.leaflet.api.TileLayer;
import org.apidesign.html.leaflet.api.TileLayerOptions;
import org.apidesign.html.leaflet.api.Marker;
import org.apidesign.html.leaflet.api.MarkerOptions;
import org.apidesign.html.leaflet.api.Polygon;
import org.apidesign.html.leaflet.api.Popup;
import org.apidesign.html.leaflet.api.PopupOptions;
import org.netbeans.api.nbrwsr.OpenHTMLRegistration;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 *
 * @author Andreas Grimmer
 * @author Christoph Sperl
 * @author Stefan Wurzinger 
 */
public final class Main {

    private Main() {
    }

    /**
     * Launches the browser
     */
    public static void main(String... args) throws Exception {
        BrowserBuilder bb = BrowserBuilder.newBrowser().
                loadPage("pages/index.html").
                loadClass(Main.class).
                invoke("onPageLoad", args);

        bb.showAndWait();
        System.exit(0);
    }

    @ActionReference(path = "Toolbars/Games")
    @ActionID(id = "org.apidesign.html.demo.leaflet4j", category = "Games")
    @OpenHTMLRegistration(
            url = "index.html",
            displayName = "Where I am?",
            iconBase = "leaflet-0.7.2/images/marker-icon.png"
    )
    /**
     * Called when page is ready
     */
    public static void onPageLoad() throws Exception {
        // Create custom layer
        ExampleCustomLayer duckLayer = new ExampleCustomLayer(new LatLng(48.337074, 14.319868), "https://cdnjs.cloudflare.com/ajax/libs/fatcow-icons/20130425/FatCow_Icons32x32/rubber_duck.png");

        // Create a map zoomed to Linz.
        MapOptions mapOptions = new MapOptions()
                .setCenter(new LatLng(48.336614, 14.319305))
                .setZoom(15)
                .setLayers(new ILayer[] { duckLayer });
        final Map map = new Map("map", mapOptions);
        
        // add a tile layer to the map
        TileLayerOptions tlo = new TileLayerOptions();
        tlo.setAttribution("Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, "
                + "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
                + "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>");
        tlo.setMaxZoom(18);
        TileLayer layer = new TileLayer("http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png", tlo);
        map.addLayer(layer);
        
        // Set a marker with a user defined icon
        Icon icon = new Icon(new IconOptions("leaflet-0.7.2/images/marker-icon.png"));
        Marker m = new Marker(new LatLng(48.336614, 14.33), new MarkerOptions().setIcon(icon));
        m.addTo(map);
        
        // Add a polygon. When you click on the polygon a popup shows up
        Polygon polygonLayer = new Polygon(new LatLng[] {
                new LatLng(48.335067, 14.320660),
                new LatLng(48.337335, 14.323642),
                new LatLng(48.335238, 14.328942),
                new LatLng(48.333883, 14.327612)
        });
        polygonLayer.addMouseListener(MouseEvent.Type.CLICK, new MouseListener() {
            @Override
            public void onEvent(MouseEvent ev) {
                PopupOptions popupOptions = new PopupOptions().setMaxWidth(400);
                Popup popup = new Popup(popupOptions);
                popup.setLatLng(ev.getLatLng());
                popup.setContent("You clicked on this polygon;");
                popup.openOn(map);
            }
        });
        map.addLayer(polygonLayer);
    }
}
