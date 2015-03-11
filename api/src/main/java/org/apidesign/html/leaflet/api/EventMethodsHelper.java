/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apidesign.html.leaflet.api;

import java.util.IdentityHashMap;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.event.DragEndEvent;
import org.apidesign.html.leaflet.api.event.ErrorEvent;
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.LocationEvent;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.event.PopupEvent;
import org.apidesign.html.leaflet.api.event.ResizeEvent;
import org.apidesign.html.leaflet.api.event.TileEvent;
import org.apidesign.html.leaflet.api.listener.DragEndListener;
import org.apidesign.html.leaflet.api.listener.ErrorListener;
import org.apidesign.html.leaflet.api.listener.EventListener;
import org.apidesign.html.leaflet.api.listener.LayerListener;
import org.apidesign.html.leaflet.api.listener.LocationListener;
import org.apidesign.html.leaflet.api.listener.MouseListener;
import org.apidesign.html.leaflet.api.listener.PopupListener;
import org.apidesign.html.leaflet.api.listener.ResizeListener;
import org.apidesign.html.leaflet.api.listener.TileListener;

/**
 *
 * @author Andreas Grimmer
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
class EventMethodsHelper {

    private static class JSInfo {
        private final Object jsListener;
        private int refCounter;
        
        public JSInfo(Object jsListener) {
            this.jsListener = jsListener;
        }
        
        public Object getJSListener() {
            return jsListener;
        }
        
        public boolean hasReferences() {
            return refCounter > 0;
        }
        
        public void incrementCounter() {
            refCounter++;
        }
        
        public void decrementCounter() {
            refCounter--;
        }
    };
    
    /**
     * Mapping between Java-EventListeners and JS-Listeners
     */
    private static final java.util.Map<EventListener, JSInfo> funcMap = new IdentityHashMap<>();

    private static Object getJSListener(EventListener listener) {
        JSInfo info = funcMap.get(listener);
        return info == null ? null : info.getJSListener();
    }
    
    private static void addListenerReference(EventListener listener, Object jsListener) {
        JSInfo info = funcMap.get(listener);
        if (info == null) {
            info = new JSInfo(jsListener);
        }
        info.incrementCounter();
        funcMap.put(listener, info);
    }
    
    private static void removeListenerReference(EventListener listener) {
        JSInfo info = funcMap.get(listener);
        if (info != null) {
            info.decrementCounter();
            funcMap.put(listener, info);
            if (!info.hasReferences()) {
                funcMap.remove(listener);
            }
        }    
    }
    
    static void addMouseListener(Object jsObj, MouseEvent.Type type, MouseListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addMouseListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/MouseListener;)"
            + "(ev.target, ev.type, ev.latlng, ev.layerPoint, "
            + "     ev.containerPoint, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addMouseListenerImpl(
            Object o, String type, MouseListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object layerPoint,
            final Object containerPoint, final MouseListener l) {

        l.onEvent(new MouseEvent(target, type, new LatLng(latlng),
                new Point(layerPoint), new Point(containerPoint)));
    }

    static void addDragEndListener(Object jsObj, DragEndEvent.Type type, DragEndListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addDragEndListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "D"
            + "Lorg/apidesign/html/leaflet/api/listener/DragEndListener;)"
            + "(ev.target, ev.type, ev.distance, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addDragEndListenerImpl(
            Object o, String type, DragEndListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final double distance, final DragEndListener l) {

        l.onEvent(new DragEndEvent(target, type, distance));
    }

    static void addErrorListener(Object jsObj, ErrorEvent.Type type, ErrorListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addErrorListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/String;"
            + "I"
            + "Lorg/apidesign/html/leaflet/api/listener/ErrorListener;)"
            + "(ev.target, ev.type, ev.message, ev.code, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addErrorListenerImpl(
            Object o, String type, ErrorListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final String message, final int code, final ErrorListener l) {

        l.onEvent(new ErrorEvent(target, type, message, code));
    }

    static void addLayerListener(Object jsObj, LayerEvent.Type type, LayerListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addLayerListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/LayerListener;)"
            + "(ev.target, ev.type, ev.layer, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addLayerListenerImpl(
            Object o, String type, LayerListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final Object layer, final LayerListener l) {
        l.onEvent(new LayerEvent(target, type, ILayer.createLayer(layer)));
    }

    static void addLocationListener(Object jsObj, LocationEvent.Type type, LocationListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addLocationListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "DDDDDD"
            + "Lorg/apidesign/html/leaflet/api/listener/LocationListener;)"
            + "(ev.target, ev.type, ev.latlng, ev.bounds, ev.accuracy, ev.altitude, "
            + "ev.altitudeAccuracy, ev.heading, ev.speed, ev.timestamp, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addLocationListenerImpl(
            Object o, String type, LocationListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object bounds, final double accuracy,
            final double altitude, final double altitudeAccuracy, final double heading,
            final double speed, final double timestamp, final LocationListener l) {

        l.onEvent(new LocationEvent(target, type, new LatLng(latlng), new LatLngBounds(bounds),
                accuracy, altitude, altitudeAccuracy, heading, speed, timestamp));
    }

    static void addResizeListener(Object jsObj, ResizeEvent.Type type, ResizeListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addResizeListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/ResizeListener;)"
            + "(ev.target, ev.type, ev.oldSize, ev.newSize, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addResizeListenerImpl(
            Object o, String type, ResizeListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final Object oldSize, final Object newSize, final ResizeListener l) {

        l.onEvent(new ResizeEvent(target, type, new Point(oldSize), new Point(newSize)));
    }

    static void addEventListener(Object jsObj, Event.Type type, EventListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addEventListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/EventListener;)"
            + "(ev.target, ev.type, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addEventListenerImpl(
            Object o, String type, EventListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final org.apidesign.html.leaflet.api.listener.EventListener l) {

        l.onEvent(new Event(target, type));
    }

    static void addTileListener(Object jsObj, TileEvent.Type type, TileListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addTileListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/TileListener;)"
            + "(ev.target, ev.type, ev.title, ev.url, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addTileListenerImpl(
            Object o, String type, TileListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final Object tile, final String url, final TileListener l) {

        l.onEvent(new TileEvent(target, type, tile, url));
    }

    static void addPopupListener(Object jsObj, PopupEvent.Type type, PopupListener listener) {
        Object jsListener = getJSListener(listener);
        jsListener = addPopupListenerImpl(jsObj, type.toString(), listener, jsListener);
        addListenerReference(listener, jsListener);
    }
    
    @JavaScriptBody(
            args = {"o", "type", "l", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/PopupListener;)"
            + "(ev.target, ev.type, ev.popup, l);}}\n"
            + "o.addEventListener(type, fn); return fn;\n"
    )
    private static native Object addPopupListenerImpl(
            Object o, String type, PopupListener listener, Object fn);

    static void callListener(final Object target, final String type,
            final Object popup, final PopupListener l) {

        l.onEvent(new PopupEvent(target, type, new Popup(popup)));
    }

    // ------- Remove event listeners --------------------------------------
    
    static void removeEventListener(Object jsObj, String type, EventListener listener) {
        Object jsListener = getJSListener(listener);
        if (jsListener != null) {
            removeEventListenerImpl(jsObj, type, jsListener);
            removeListenerReference(listener);
        }
    }

    @JavaScriptBody(
            args = {"o", "type", "fn"}, wait4js = false, javacall = true,
            body = "o.removeEventListener(type, fn);\n"
    )
    private static native void removeEventListenerImpl(
            Object o, String type, Object fn);

    // ------- Clear all event listeners ------------------------------------
    
    static void clearAllEventListeners(Object jsObj) {
        clearAllEventListenersImpl(jsObj);
    }
    
    @JavaScriptBody(
            args = {"o"}, wait4js = false, javacall = true,
            body = "o.clearAllEventListeners();"
    )
    private static native void clearAllEventListenersImpl(Object o);

    // ------- Has event listeners -------------------------------------------
    
    static boolean hasEventListeners(Object jsObj, String type) {
        return hasEventListenersImpl(jsObj, type);
    }

    @JavaScriptBody(
            args = {"o", "type"}, wait4js = true, javacall = true,
            body = "return o.hasEventListeners(type);\n"
    )
    private static native boolean hasEventListenersImpl(Object o, String type);

    // ------- Non static part -------------------------------------------
    
    private EventMethodsHelper() {

    }
}
