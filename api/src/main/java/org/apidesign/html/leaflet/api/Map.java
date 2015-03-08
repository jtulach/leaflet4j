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
package org.apidesign.html.leaflet.api;

import java.util.EventListener;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.event.DragEndEvent;
import org.apidesign.html.leaflet.api.event.ErrorEvent;
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.LocationEvent;
import org.apidesign.html.leaflet.api.event.ResizeEvent;
import org.apidesign.html.leaflet.api.listener.DragEndListener;
import org.apidesign.html.leaflet.api.listener.ErrorListener;
import org.apidesign.html.leaflet.api.listener.LayerListener;
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
public final class Map {

    private final Object jsObj;

    Object getJSObj() {
        return jsObj;
    }

    public Map(String id) {
        this(id, new MapOptions());
    }

    public Map(String id, MapOptions options) {
        this.jsObj = create(id, options.getJSObj());
    }

    public void addLayer(ILayer layer) {
        addLayer(jsObj, layer.getJSObj());
    }

    public void setView(LatLng center) {
        setView1(jsObj, center.getJSObj());
    }

    public void setView(LatLng center, int zoom) {
        setView2(jsObj, center.getJSObj(), zoom);
    }

    public void setView(LatLng center, int zoom, ZoomPanOptions options) {
        setView3(jsObj, center.getJSObj(), zoom, options.getJSObj());
    }

    @JavaScriptBody(args = {"id", "options"},
            body = "return L.map(id, options);")
    private static native Object create(String id, Object options);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.addLayer(layer);")
    private static native void addLayer(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj", "center"}, wait4js = false, body
            = "jsObj.setView(center);")
    private static native void setView1(Object jsObj, Object center);

    @JavaScriptBody(args = {"jsObj", "center", "zoom"}, wait4js = false, body
            = "jsObj.setView(center, zoom);")
    private static native void setView2(Object jsObj, Object center, int zoom);

    @JavaScriptBody(args = {"jsObj", "center", "zoom", "options"}, wait4js = false, body
            = "jsObj.setView(center, zoom, options);")
    private static native void setView3(Object jsObj, Object center, int zoom, Object options);

    //Event methods
    public void addEventListener(String type, EventListener listener) {

        addEventListener(type, listener, null);
    }

    public void addEventListener(String type, EventListener listener, Object context) {

        if (listener instanceof MouseListener) {
            EventMethodsHelper.addMouseListenerImpl(getJSObj(), type, (MouseListener) listener, context);
        } else if (listener instanceof DragEndListener) {
            EventMethodsHelper.addDragEndListenerImpl(getJSObj(), type, (DragEndListener) listener, context);
        } else if (listener instanceof ErrorListener) {
            EventMethodsHelper.addErrorListenerImpl(getJSObj(), type, (ErrorListener) listener, context);
        } else if (listener instanceof LayerListener) {
            EventMethodsHelper.addLayerListenerImpl(getJSObj(), type, (LayerListener) listener, context);
        } else if (listener instanceof LocationListener) {
            EventMethodsHelper.addLocationListenerImpl(getJSObj(), type, (LocationListener) listener, context);
        } else if (listener instanceof ResizeListener) {
            EventMethodsHelper.addResizeListenerImpl(getJSObj(), type, (ResizeListener) listener, context);
        } else if (listener instanceof org.apidesign.html.leaflet.api.listener.EventListener) {
            EventMethodsHelper.addEventListenerImpl(getJSObj(), type,
                    (org.apidesign.html.leaflet.api.listener.EventListener) listener, context);
        } else {
            //TODO Popupevent
            throw new UnsupportedOperationException("Listener is unsupported!");
        }
    }

}
