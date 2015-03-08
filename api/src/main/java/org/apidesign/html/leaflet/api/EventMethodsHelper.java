/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apidesign.html.leaflet.api;

import java.util.EventListener;
import net.java.html.js.JavaScriptBody;
import org.apidesign.html.leaflet.api.event.DragEndEvent;
import org.apidesign.html.leaflet.api.event.ErrorEvent;
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.LocationEvent;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.event.ResizeEvent;
import org.apidesign.html.leaflet.api.event.TileEvent;
import org.apidesign.html.leaflet.api.listener.DragEndListener;
import org.apidesign.html.leaflet.api.listener.ErrorListener;
import org.apidesign.html.leaflet.api.listener.LayerListener;
import org.apidesign.html.leaflet.api.listener.LocationListener;
import org.apidesign.html.leaflet.api.listener.MouseListener;
import org.apidesign.html.leaflet.api.listener.ResizeListener;
import org.apidesign.html.leaflet.api.listener.TileListener;

/**
 *
 * @author Andreas Grimmer
 */
class EventMethodsHelper {
    
    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
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
    static native void addMouseListenerImpl(
            Object o, String type, MouseListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object layerPoint,
            final Object containerPoint, final MouseListener l) {

        l.onEvent(new MouseEvent(target, type, new LatLng(latlng),
                new Point(layerPoint), new Point(containerPoint)));
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "D"
            + "Lorg/apidesign/html/leaflet/api/listener/DragEndListener;)"
            + "(ev.target, ev.type, ev.distance, l);\n"
            + "}, context);\n"
    )
    static native void addDragEndListenerImpl(
            Object o, String type, DragEndListener listener, Object context);

    static void callListener(final Object target, final String type,
            final double distance, final DragEndListener l) {

        l.onEvent(new DragEndEvent(target, type, distance));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/String;"
            + "I"
            + "Lorg/apidesign/html/leaflet/api/listener/ErrorListener;)"
            + "(ev.target, ev.type, ev.message, ev.code, l);\n"
            + "}, context);\n"
    )
    static native void addErrorListenerImpl(
            Object o, String type, ErrorListener listener, Object context);

    static void callListener(final Object target, final String type,
            final String message, final int code, final ErrorListener l) {

        l.onEvent(new ErrorEvent(target, type, message, code));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/LayerListener;)"
            + "(ev.target, ev.type, ev.layer, l);\n"
            + "}, context);\n"
    )
    static native void addLayerListenerImpl(
            Object o, String type, LayerListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object layer, final LayerListener l) {
        l.onEvent(new LayerEvent(target, type, ILayer.createLayer(layer)));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
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
    static native void addLocationListenerImpl(
            Object o, String type, LocationListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object bounds, final double accuracy,
            final double altitude, final double altitudeAccuracy, final double heading,
            final double speed, final double timestamp, final LocationListener l) {

        // TODO: Test after method locate is implemented
        l.onEvent(new LocationEvent(target, type, new LatLng(latlng), new LatLngBounds(bounds),
                accuracy, altitude, altitudeAccuracy, heading, speed, timestamp));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/ResizeListener;)"
            + "(ev.target, ev.type, ev.oldSize, ev.newSize, l);\n"
            + "}, context);\n"
    )
    static native void addResizeListenerImpl(
            Object o, String type, ResizeListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object oldSize, final Object newSize, final ResizeListener l) {

        l.onEvent(new ResizeEvent(target, type, new Point(oldSize), new Point(newSize)));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/EventListener;)"
            + "(ev.target, ev.type, l);\n"
            + "}, context);\n"
    )
    static native void addEventListenerImpl(
            Object o, String type,
            org.apidesign.html.leaflet.api.listener.EventListener listener, Object context);

    static void callListener(final Object target, final String type,
            final org.apidesign.html.leaflet.api.listener.EventListener l) {

        l.onEvent(new Event(target, type));
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "o.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/TileListener;)"
            + "(ev.target, ev.type, ev.title, ev.url, l);\n"
            + "}, context);\n"
    )
    static native void addTileListenerImpl(
            Object o, String type, TileListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object tile, final String url, final TileListener l) {

        l.onEvent(new TileEvent(target, type, tile, url));
    }
    
    private EventMethodsHelper() {
        
    }
}
