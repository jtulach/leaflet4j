/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015 Andreas Grimmer <a.grimmer@gmx.at>
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apidesign.html.leaflet.api.map;

import java.util.EventListener;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.JSWrapper;
import org.apidesign.html.leaflet.api.basicTypes.LatLng;
import org.apidesign.html.leaflet.api.basicTypes.LatLngBounds;
import org.apidesign.html.leaflet.api.basicTypes.Point;
import org.apidesign.html.leaflet.api.event.DragEndEvent;
import org.apidesign.html.leaflet.api.event.ErrorEvent;
import org.apidesign.html.leaflet.api.event.GeoJSONEvent;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.LayersControlEvent;
import org.apidesign.html.leaflet.api.event.LocationEvent;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.event.ResizeEvent;
import org.apidesign.html.leaflet.api.interfaces.ILayer;
import org.apidesign.html.leaflet.api.listener.DragEndListener;
import org.apidesign.html.leaflet.api.listener.ErrorListener;
import org.apidesign.html.leaflet.api.listener.GeoJSONListener;
import org.apidesign.html.leaflet.api.listener.LayerListener;
import org.apidesign.html.leaflet.api.listener.LayersControlListener;
import org.apidesign.html.leaflet.api.listener.LocationListener;
import org.apidesign.html.leaflet.api.listener.MouseListener;
import org.apidesign.html.leaflet.api.listener.ResizeListener;

/**
 * Class that represents one leaflet map associated with an element.
 *
 * @author Christoph Sperl
 * @author Andreas Grimmer
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Map extends JSWrapper {

    public Map(String id) {
        this(id, new MapOptions());
    }

    public Map(String id, MapOptions options) {
        super(create(id, options.getJSObj()));
    }

    public void addLayer(ILayer layer) {
        addLayer(jsObj, layer.getJSObj());
    }

    // TODO zoom is optional, and there is also a third optional argument
    public void setView(LatLng center, int zoom) {
        setView(jsObj, center, zoom);
    }

    // TODO remove this and add a method addLayer(ILayer)
    public void addTileLayer(String url, String attribution, int maxZoom, String id) {
        addTileLayerImpl(jsObj, url, attribution, maxZoom, id);
    }

    @JavaScriptBody(args = {"map", "url", "attribution", "maxZoom", "id"}, wait4js = false, body
            = "L.tileLayer(url, {\n"
            + "  'maxZoom': 18,\n"
            + "  'attribution': attribution\n"
            + "}).addTo(map);"
    )
    private static native void addTileLayerImpl(
            Object map, String url, String attribution, int maxZoom, String id
    );

    @JavaScriptBody(args = {"id", "options"},
            body = "return L.map(id, options);")
    private static native Object create(String id, Object options);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.addLayer(layer);")
    private static native void addLayer(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj", "center", "zoom"}, wait4js = false, body
            = "jsObj.setView(center, zoom);")
    private static native void setView(Object jsObj, Object center, int zoom);

    //Event methods
    public void addEventListener(String type, EventListener listener) {

        addEventListener(type, listener, null);
    }

    public void addEventListener(String type, EventListener listener, Object context) {

        if (listener instanceof MouseListener) {
            addMouseListenerImpl(getJSObj(), type, (MouseListener) listener, context);
        } else if (listener instanceof DragEndListener) {
            addDragEndListenerImpl(getJSObj(), type, (DragEndListener) listener, context);
        } else if (listener instanceof ErrorListener) {
            addErrorListenerImpl(getJSObj(), type, (ErrorListener) listener, context);
        } else if (listener instanceof GeoJSONListener) {
            addGeoJSONListenerImpl(getJSObj(), type, (GeoJSONListener) listener, context);
        } else if (listener instanceof LayerListener) {
            addLayerListenerImpl(getJSObj(), type, (LayerListener) listener, context);
        } else if (listener instanceof LayersControlListener) {
            addLayersControlListenerImpl(getJSObj(), type, (LayersControlListener) listener, context);
        } else if (listener instanceof LocationListener) {
            addLocationListenerImpl(getJSObj(), type, (LocationListener) listener, context);
        } else if (listener instanceof ResizeListener) {
            addResizeListenerImpl(getJSObj(), type, (ResizeListener) listener, context);
        } else {
            throw new UnsupportedOperationException("Listener is not yet implemented!");
        }
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/MouseListener;)"
            + "(ev.target, ev.type, ev.latlng, ev.layerPoint, "
            + "     ev.containerPoint, l);\n"
            + "}, context);\n"
    )
    private static native void addMouseListenerImpl(
            Object map, String type, MouseListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object layerPoint,
            final Object containerPoint, final MouseListener l) {

        l.onEvent(new MouseEvent(target, type, new LatLng(latlng),
                new Point(layerPoint), new Point(containerPoint)));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "D"
            + "Lorg/apidesign/html/leaflet/api/listener/DragEndListener;)"
            + "(ev.target, ev.type, ev.distance, l);\n"
            + "}, context);\n"
    )
    private static native void addDragEndListenerImpl(
            Object map, String type, DragEndListener listener, Object context);

    static void callListener(final Object target, final String type,
            final double distance, final DragEndListener l) {

        l.onEvent(new DragEndEvent(target, type, distance));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/String;"
            + "I"
            + "Lorg/apidesign/html/leaflet/api/listener/ErrorListener;)"
            + "(ev.target, ev.type, ev.message, ev.code, l);\n"
            + "}, context);\n"
    )
    private static native void addErrorListenerImpl(
            Object map, String type, ErrorListener listener, Object context);

    static void callListener(final Object target, final String type,
            final String message, final int code, final ErrorListener l) {

        l.onEvent(new ErrorEvent(target, type, message, code));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/GeoJSONListener;)"
            + "(ev.target, ev.type, ev.layer, ev.properties, ev.geometryType, ev.id, l);\n"
            + "}, context);\n"
    )
    private static native void addGeoJSONListenerImpl(
            Object map, String type, GeoJSONListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object layer, final Object proprties, final String geometryType,
            final String id, final GeoJSONListener l) {
        // TODO: Generate ILayer Java-object
        l.onEvent(new GeoJSONEvent(target, type, null, proprties, geometryType, id));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/LayerListener;)"
            + "(ev.target, ev.type, ev.layer, l);\n"
            + "}, context);\n"
    )
    private static native void addLayerListenerImpl(
            Object map, String type, LayerListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object layer, final LayerListener l) {
        // TODO: Generate ILayer Java-object
        l.onEvent(new LayerEvent(target, type, null));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/LayersControlListener;)"
            + "(ev.target, ev.type, ev.layer, ev.name, l);\n"
            + "}, context);\n"
    )
    private static native void addLayersControlListenerImpl(
            Object map, String type, LayersControlListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object layer, final String name, final LayersControlListener l) {
        // TODO: Generate ILayer Java-object
        l.onEvent(new LayersControlEvent(target, type, null, name));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "DDDDDD"
            + "Lorg/apidesign/html/leaflet/api/listener/LocationListener;)"
            + "(ev.target, ev.type, ev.latlng, ev.bounds, ev.accuracy, ev.altitude, "
            + "ev.altitudeAccuracy, ev.heading, ev.speed, ev.timestamp, l);\n"
            + "}, context);\n"
    )
    private static native void addLocationListenerImpl(
            Object map, String type, LocationListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object bounds, final double accuracy,
            final double altitude, final double altitudeAccuracy, final double heading,
            final double speed, final double timestamp, final LocationListener l) {

        // TODO: Test after method locate is implemented
        l.onEvent(new LocationEvent(target, type, new LatLng(latlng), new LatLngBounds(bounds),
                accuracy, altitude, altitudeAccuracy, heading, speed, timestamp));
    }

    @JavaScriptBody(
            args = {"map", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "map.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.map.Map::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/ResizeListener;)"
            + "(ev.target, ev.type, ev.oldSize, ev.newSize, l);\n"
            + "}, context);\n"
    )
    private static native void addResizeListenerImpl(
            Object map, String type, ResizeListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object oldSize, final Object newSize, final ResizeListener l) {

        l.onEvent(new ResizeEvent(target, type, new Point(oldSize), new Point(newSize)));
    }

}
