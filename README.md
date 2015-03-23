leaflet4j
=========

Leaflet Demo[http://leafletjs.com/examples/quick-start.html] rewritten to Java via DukeScript[http://html.java.net]. Clone and then:

    # prepare
    $ mvn clean install 
    $ cd l4jdemo

    # run or debug on your desktop:
    $ mvn exec:java
 
    # run on your **Android** device
    $ mvn -Pdlvkbrwsr package android:deploy android:run -Dandroid.sdk.path=...

    # if you are on Mac OS X, run on your iPad simulator
    $ mvn -Pibrwsr robovm:ipad-sim

to see the application. Check Main.java to see the initialization which basically consists of:

    public static void onPageLoad(String... args) throws Exception {
        // Create custom layer
        ExampleCustomLayer duckLayer = new ExampleCustomLayer(new LatLng(48.337074, 14.319868), 
			"https://cdnjs.cloudflare.com/ajax/libs/fatcow-icons/20130425/FatCow_Icons32x32/rubber_duck.png");

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

Fork and improve the Java leaflet bindings.
Or fork and design your own Java wrappers 
around your favorite JavaScript library as 
described at http://bits.netbeans.org/html+java/0.8.2/net/java/html/js/package-summary.html


Running inside of JavaFX Application
====================================

It is possible to include the browser widget inside of your existing **JavaFX** application.
To see example of such approach, just type:

    # prepare
    $ mvn clean install 
    $ cd l4jfxdemo

    $ mvn exec:exec

This mode provides incremental migration approach and should be useful for those who
already have an existing **JavaFX** application, but want to benefit from the power
of leaflet4j APIs.
