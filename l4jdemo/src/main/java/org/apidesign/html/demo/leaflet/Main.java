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
import org.apidesign.html.leaflet.api.event.DragEndEvent;
import org.apidesign.html.leaflet.api.event.ErrorEvent;
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.event.ResizeEvent;
import org.apidesign.html.leaflet.api.event.TileEvent;
import org.apidesign.html.leaflet.api.listener.DragEndListener;
import org.apidesign.html.leaflet.api.listener.ErrorListener;
import org.apidesign.html.leaflet.api.listener.EventListener;
import org.apidesign.html.leaflet.api.listener.LayerListener;
import org.apidesign.html.leaflet.api.listener.MouseListener;
import org.apidesign.html.leaflet.api.listener.ResizeListener;
import org.apidesign.html.leaflet.api.listener.TileListener;
import org.apidesign.html.leaflet.api.Map;
import org.apidesign.html.leaflet.api.MapOptions;
import org.apidesign.html.leaflet.api.TileLayer;
import org.apidesign.html.leaflet.api.TileLayerOptions;
import org.apidesign.html.leaflet.api.Marker;
import org.apidesign.html.leaflet.api.MarkerOptions;
import org.apidesign.html.leaflet.api.PanOptions;
import org.apidesign.html.leaflet.api.Popup;
import org.apidesign.html.leaflet.api.PopupOptions;
import org.apidesign.html.leaflet.api.ZoomOptions;
import org.apidesign.html.leaflet.api.ZoomPanOptions;
import org.apidesign.html.leaflet.api.event.PopupEvent;
import org.apidesign.html.leaflet.api.listener.PopupListener;
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
    /**
     * Called when page is ready
     */
    public static void onPageLoad() throws Exception {
        LatLng latLng = new LatLng(48.336614, 14.319305);
        System.out.println("Latitude = " + latLng.getLatitude());
        
        ExampleCustomLayer nuclearLayer = new ExampleCustomLayer(new LatLng(48.337142, 14.318822), "https://cdnjs.cloudflare.com/ajax/libs/fatcow-icons/20130425/FatCow_Icons32x32/radioactivity.png");
        ExampleCustomLayer pintLayer = new ExampleCustomLayer(new LatLng(48.338094, 14.321113), "https://cdnjs.cloudflare.com/ajax/libs/fatcow-icons/20130425/FatCow_Icons32x32/pint.png");
        ExampleCustomLayer duckLayer = new ExampleCustomLayer(new LatLng(48.337074, 14.319868), "https://cdnjs.cloudflare.com/ajax/libs/fatcow-icons/20130425/FatCow_Icons32x32/rubber_duck.png");

        MapOptions mapOptions = new MapOptions()
                .setCenter(latLng)
                .setZoom(13)
                .setLayers(new ILayer[] { pintLayer, nuclearLayer });

        /*MapOptions mo = new MapOptions();
         mo.setCenter(new LatLng(48.336614, 14.319305));
         mo.setZoom(13);
         System.out.println(mo.toString());*/
        final Map map = new Map("map", mapOptions);
        addTestEventsToMap(map);
        

        TileLayerOptions tlo = new TileLayerOptions();
        tlo.setAttribution("Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, "
                + "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
                + "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>");
        tlo.setMaxZoom(18);
        TileLayer layer = new TileLayer("http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png", tlo);
        addTestEventsToTileLayer(layer);

        map.addLayer(layer);
        
        
        Icon icon = new Icon(new IconOptions("leaflet-0.7.2/images/marker-icon.png"));
        Marker m = new Marker(new LatLng(48.336614, 14.33), new MarkerOptions().setIcon(icon));
        m.addTo(map);


        //final LatLng loc = new LatLng(48.336614, 14.319305);
        // could not derive current location -> set to JKU Linz
        //map.setView(loc, 13);
        

        /*
         Marker m = new Marker(new LatLng(48.336614, 14.319405));
         m.addTo(map);*/
        // Query to mark our position if possible
        //query(map, 3000);
        
        
        map.addLayer(duckLayer);
        
        
        map.setView(new LatLng(51.505, -0.09));
        map.setView(new LatLng(51.505, -0.09), 5);
        map.setView(new LatLng(51.505, -0.09), 5, 
                new ZoomPanOptions(false, new PanOptions(false, 0.2, 0.5, false), 
                new ZoomOptions(false), false));
        
    }
    
    private static void addTestEventsToTileLayer(TileLayer layer) {
        
        layer.addEventListener("tileload", new TileListener() {

            @Override
            public void onEvent(TileEvent ev) {
                System.out.println("TileListener url=" + ev.getUrl());
            }
        });

        layer.addEventListener("load", new EventListener() {

            @Override
            public void onEvent(Event ev) {
                System.out.println("Tile layer loaded all vidisble tiles; Type=" + ev.getType());
            }
        });
    }
    
    private static void addTestEventsToMap(Map map) {
        
        map.addEventListener("layeradd", new LayerListener() {

            @Override
            public void onEvent(LayerEvent ev) {
                System.out.println("layeradd");
            }
        });
        
        map.addEventListener("click", new MouseListener() {
            @Override
            public void onEvent(MouseEvent ev) {
                
                PopupOptions popupOptions = new PopupOptions().setMaxWidth(400);
                Popup popup = new Popup(popupOptions);
                popup.setLatLng(ev.getLatLng());
                popup.setContent("You clicked the map on " + ev.getLatLng().getLatitude() + ";" +
                        ev.getLatLng().getLongitude());
                popup.openOn(map);
            }
        });

        map.addEventListener("dragend", new DragEndListener() {

            @Override
            public void onEvent(DragEndEvent ev) {
                System.out.println("Distance=" + ev.getDistance());
            }
        });

        map.addEventListener("locationerror", new ErrorListener() {
            
            @Override
            public void onEvent(ErrorEvent ev) {
                // TODO: Untested
                System.out.println("ErrorEvent: " + ev.getMessage() + "; " + ev.getType());
            }
        });

        map.addEventListener("resize", new ResizeListener() {

            @Override
            public void onEvent(ResizeEvent ev) {
                System.out.println("Map resized " + ev.getNewSize().getX());
            }
        });
        
        map.addEventListener("zoomstart", new EventListener() {
            
            @Override
            public void onEvent(Event ev) {
                System.out.println("zoomstart" + ev.getType());
            }
        });
        
        map.addEventListener("popupopen", new PopupListener() {

            @Override
            public void onEvent(PopupEvent ev) {
                System.out.println("Popup open with content=" + ev.getPopup().getContent());
            }
        });
        
        
        
    }
    
    
    /*
     private static void query(final Leaflet map, final long timeout) {
     Position.Handle q = WhereIAmHandle.createQuery(map);
     q.setMaximumAge(60000);
     q.setTimeout(timeout);
     q.start();
     }*/
}
