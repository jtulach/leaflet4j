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
        private Object jsListener;
        private int refCounter;
        
        public JSInfo(Object jsListener) {
            this.jsListener = jsListener;
        }
        
        public Object getJSListener() {
            return jsListener;
        }
        
        public boolean hasReferences() {
            return refCounter == 0;
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

    static void addEventListener(Object jsObj, String type, EventListener listener) {
        addEventListener(jsObj, type, listener, null);
    }

    static void addEventListener(Object jsObj, String type, EventListener listener, Object context) {

        JSInfo info = funcMap.get(listener);
        Object fn = info == null ? null : info.getJSListener();
        if (listener instanceof MouseListener) {
            fn = addMouseListenerImpl(jsObj, type, (MouseListener) listener, context, fn);
        } else if (listener instanceof DragEndListener) {
            fn = addDragEndListenerImpl(jsObj, type, (DragEndListener) listener, context, fn);
        } else if (listener instanceof ErrorListener) {
            fn = addErrorListenerImpl(jsObj, type, (ErrorListener) listener, context, fn);
        } else if (listener instanceof LayerListener) {
            fn = addLayerListenerImpl(jsObj, type, (LayerListener) listener, context, fn);
        } else if (listener instanceof LocationListener) {
            fn = addLocationListenerImpl(jsObj, type, (LocationListener) listener, context, fn);
        } else if (listener instanceof ResizeListener) {
            fn = addResizeListenerImpl(jsObj, type, (ResizeListener) listener, context, fn);
        } else if (listener instanceof TileListener) {
            fn = addTileListenerImpl(jsObj, type, (TileListener) listener, context, fn);
        } else if (listener instanceof PopupListener) {
            fn = addPopupListenerImpl(jsObj, type, (PopupListener) listener, context, fn);
        } else {
            fn = addEventListenerImpl(jsObj, type, listener, context, fn);
        }
        if (info == null) {
            info = new JSInfo(fn);
        }
        info.incrementCounter();
        funcMap.put(listener, info);
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
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
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addMouseListenerImpl(
            Object o, String type, MouseListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object layerPoint,
            final Object containerPoint, final MouseListener l) {

        l.onEvent(new MouseEvent(target, type, new LatLng(latlng),
                new Point(layerPoint), new Point(containerPoint)));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "D"
            + "Lorg/apidesign/html/leaflet/api/listener/DragEndListener;)"
            + "(ev.target, ev.type, ev.distance, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addDragEndListenerImpl(
            Object o, String type, DragEndListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final double distance, final DragEndListener l) {

        l.onEvent(new DragEndEvent(target, type, distance));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/String;"
            + "I"
            + "Lorg/apidesign/html/leaflet/api/listener/ErrorListener;)"
            + "(ev.target, ev.type, ev.message, ev.code, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addErrorListenerImpl(
            Object o, String type, ErrorListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final String message, final int code, final ErrorListener l) {

        l.onEvent(new ErrorEvent(target, type, message, code));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/LayerListener;)"
            + "(ev.target, ev.type, ev.layer, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addLayerListenerImpl(
            Object o, String type, LayerListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final Object layer, final LayerListener l) {
        l.onEvent(new LayerEvent(target, type, ILayer.createLayer(layer)));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
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
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addLocationListenerImpl(
            Object o, String type, LocationListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final Object latlng, final Object bounds, final double accuracy,
            final double altitude, final double altitudeAccuracy, final double heading,
            final double speed, final double timestamp, final LocationListener l) {

        l.onEvent(new LocationEvent(target, type, new LatLng(latlng), new LatLngBounds(bounds),
                accuracy, altitude, altitudeAccuracy, heading, speed, timestamp));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/ResizeListener;)"
            + "(ev.target, ev.type, ev.oldSize, ev.newSize, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addResizeListenerImpl(
            Object o, String type, ResizeListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final Object oldSize, final Object newSize, final ResizeListener l) {

        l.onEvent(new ResizeEvent(target, type, new Point(oldSize), new Point(newSize)));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/EventListener;)"
            + "(ev.target, ev.type, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addEventListenerImpl(
            Object o, String type, EventListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final org.apidesign.html.leaflet.api.listener.EventListener l) {

        l.onEvent(new Event(target, type));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/TileListener;)"
            + "(ev.target, ev.type, ev.title, ev.url, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addTileListenerImpl(
            Object o, String type, TileListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final Object tile, final String url, final TileListener l) {

        l.onEvent(new TileEvent(target, type, tile, url));
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context", "fn"}, wait4js = true, javacall = true,
            body = "if(fn == null) { fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/PopupListener;)"
            + "(ev.target, ev.type, ev.popup, l);}}\n"
            + "o.addEventListener(type, fn, context); return fn;\n"
    )
    private static native Object addPopupListenerImpl(
            Object o, String type, PopupListener listener, Object context, Object fn);

    static void callListener(final Object target, final String type,
            final Object popup, final PopupListener l) {

        l.onEvent(new PopupEvent(target, type, new Popup(popup)));
    }

    static void addEventListener(Object jsObj, java.util.Map<String, EventListener> eventMap) {
        addEventListener(jsObj, eventMap, null);
    }

    static void addEventListener(Object jsObj, java.util.Map<String, EventListener> eventMap, Object context) {
        eventMap.entrySet().stream().forEach((entry) -> {
            addEventListener(jsObj, entry.getKey(), entry.getValue(), context);
        });
    }

    static void addOneTimeEventListener(Object jsObj, String type, EventListener listener) {
        addOneTimeEventListener(jsObj, type, listener, null);
    }

    static void addOneTimeEventListener(Object jsObj, String type, EventListener listener, Object context) {

        // do not register any java script function at all
        
        if (listener instanceof MouseListener) {
            addOneTimeMouseListenerImpl(jsObj, type, (MouseListener) listener, context);
        } else if (listener instanceof DragEndListener) {
            addOneTimeDragEndListenerImpl(jsObj, type, (DragEndListener) listener, context);
        } else if (listener instanceof ErrorListener) {
            addOneTimeErrorListenerImpl(jsObj, type, (ErrorListener) listener, context);
        } else if (listener instanceof LayerListener) {
            addOneTimeLayerListenerImpl(jsObj, type, (LayerListener) listener, context);
        } else if (listener instanceof LocationListener) {
            addOneTimeLocationListenerImpl(jsObj, type, (LocationListener) listener, context);
        } else if (listener instanceof ResizeListener) {
            addOneTimeResizeListenerImpl(jsObj, type, (ResizeListener) listener, context);
        } else if (listener instanceof TileListener) {
            addOneTimeTileListenerImpl(jsObj, type, (TileListener) listener, context);
        } else if (listener instanceof PopupListener) {
            addOneTimePopupListenerImpl(jsObj, type, (PopupListener) listener, context);
        } else {
            addOneTimeEventListenerImpl(jsObj, type, listener, context);
        }
    }

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/MouseListener;)"
            + "(ev.target, ev.type, ev.latlng, ev.layerPoint, "
            + "     ev.containerPoint, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeMouseListenerImpl(
            Object o, String type, MouseListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "D"
            + "Lorg/apidesign/html/leaflet/api/listener/DragEndListener;)"
            + "(ev.target, ev.type, ev.distance, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeDragEndListenerImpl(
            Object o, String type, DragEndListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/String;"
            + "I"
            + "Lorg/apidesign/html/leaflet/api/listener/ErrorListener;)"
            + "(ev.target, ev.type, ev.message, ev.code, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeErrorListenerImpl(
            Object o, String type, ErrorListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/LayerListener;)"
            + "(ev.target, ev.type, ev.layer, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeLayerListenerImpl(
            Object o, String type, LayerListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "DDDDDD"
            + "Lorg/apidesign/html/leaflet/api/listener/LocationListener;)"
            + "(ev.target, ev.type, ev.latlng, ev.bounds, ev.accuracy, ev.altitude, "
            + "ev.altitudeAccuracy, ev.heading, ev.speed, ev.timestamp, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeLocationListenerImpl(
            Object o, String type, LocationListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/ResizeListener;)"
            + "(ev.target, ev.type, ev.oldSize, ev.newSize, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeResizeListenerImpl(
            Object o, String type, ResizeListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/EventListener;)"
            + "(ev.target, ev.type, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeEventListenerImpl(
            Object o, String type, EventListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/TileListener;)"
            + "(ev.target, ev.type, ev.title, ev.url, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimeTileListenerImpl(
            Object o, String type, TileListener listener, Object context);

    @JavaScriptBody(
            args = {"o", "type", "l", "context"}, wait4js = true, javacall = true,
            body = "var fn = function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.EventMethodsHelper::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Lorg/apidesign/html/leaflet/api/listener/PopupListener;)"
            + "(ev.target, ev.type, ev.popup, l);}\n"
            + "o.addOneTimeEventListener(type, fn, context);\n"
    )
    private static native void addOneTimePopupListenerImpl(
            Object o, String type, PopupListener listener, Object context);

    // TODO call impl directly? instead of deregister?
    static void removeEventListener(Object jsObj, String type) {
        removeEventListenerImpl(jsObj, type, null, null);
    }

    // TODO call impl directly? instead of deregister?
    static void removeEventListener(Object jsObj, String type, Object context) {
        removeEventListenerImpl(jsObj, type, null, context);
    }

    static void removeEventListener(Object jsObj, String type, EventListener listener) {
        removeEventListener(jsObj, type, listener, null);
    }

    static void removeEventListener(Object jsObj, java.util.Map<String, EventListener> eventMap) {
        removeEventListener(jsObj, eventMap, null);
    }

    static void removeEventListener(Object jsObj, java.util.Map<String, EventListener> eventMap, Object context) {
        eventMap.entrySet().stream().forEach((entry) -> {
            if (entry.getValue() == null) {
                removeEventListener(jsObj, entry.getKey(), context);
            } else {
                removeEventListener(jsObj, entry.getKey(), entry.getValue(), context);
            }
        });
    }

    // TODO call clearAllEventListenersImpl instead of remove!?
    static void clearAllEventListeners(Object jsObj) {
        removeEventListener(jsObj, "", null);
    }

    static void removeEventListener(Object jsObj, String type, EventListener listener, Object context) {
        JSInfo info = funcMap.get(listener);
        if (info != null) {
            removeEventListenerImpl(jsObj, type, info.getJSListener(), context);
            info.decrementCounter();
            funcMap.put(listener, info);
            if (!info.hasReferences()) {
                funcMap.remove(listener);
            }
        }
    }

    @JavaScriptBody(
            args = {"o", "type", "fn", "context"}, wait4js = false, javacall = true,
            body = "o.removeEventListener(type, fn, context);\n"
    )
    private static native void removeEventListenerImpl(
            Object o, String type, Object fn, Object context);

    static boolean hasEventListeners(Object jsObj, String type) {
        return hasEventListenersImpl(jsObj, type);
    }

    @JavaScriptBody(
            args = {"o", "type"}, wait4js = true, javacall = true,
            body = "return o.hasEventListeners(type);\n"
    )
    private static native boolean hasEventListenersImpl(Object o, String type);

    static void fireEvent(Object jsObj, String type) {
        fireEventImpl(jsObj, type, null);
    }

    static void fireEvent(Object jsObj, String type, Object data) {
        fireEventImpl(jsObj, type, data);
    }

    @JavaScriptBody(
            args = {"o", "type", "data"}, wait4js = false, javacall = true,
            body = "o.fireEvent(type, data);\n"
    )
    private static native void fireEventImpl(Object o, String type, Object data);

    private EventMethodsHelper() {

    }
}
