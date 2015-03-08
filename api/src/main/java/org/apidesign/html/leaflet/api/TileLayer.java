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
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.TileEvent;
import org.apidesign.html.leaflet.api.listener.TileListener;

/**
 *
 * @author Christoph Sperl
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class TileLayer extends ILayer {
    
    static {
        registerLayerType("L.TileLayer", (obj)->new TileLayer(obj));
    }
    
    protected TileLayer(Object jsObj) {
        super(jsObj);
    }
    
    public TileLayer(String urlTemplate) {
        this(urlTemplate, new TileLayerOptions());
    }

    public TileLayer(String urlTemplate, TileLayerOptions options) {
        super(create(urlTemplate, options.getJSObj()));
    }

    @JavaScriptBody(args = {"urlTemplate", "options"}, body
            = "return L.tileLayer(urlTemplate, options);")
    private static native Object create(String urlTemplate, Object options);

    public void addEventListener(String type, EventListener listener) {

        addEventListener(type, listener, null);
    }

    public void addEventListener(String type, EventListener listener, Object context) {

        if (listener instanceof TileListener) {
            addTileListenerImpl(getJSObj(), type, (TileListener) listener, context);
        } else if (listener instanceof org.apidesign.html.leaflet.api.listener.EventListener) {
            addEventListenerImpl(getJSObj(), type,
                    (org.apidesign.html.leaflet.api.listener.EventListener) listener, context);
        } else {
            throw new UnsupportedOperationException("Listener is not yet implemented!");
        }
    }

    @JavaScriptBody(
            args = {"obj", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "obj.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.TileLayer::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/TileListener;)"
            + "(ev.target, ev.type, ev.title, ev.url, l);\n"
            + "}, context);\n"
    )
    private static native void addTileListenerImpl(
            Object obj, String type, TileListener listener, Object context);

    static void callListener(final Object target, final String type,
            final Object tile, final String url, final TileListener l) {

        l.onEvent(new TileEvent(target, type, tile, url));
    }

    @JavaScriptBody(
            args = {"obj", "type", "l", "context"}, wait4js = false, javacall = true,
            body = "obj.on(type, function(ev) {\n"
            + "  @org.apidesign.html.leaflet.api.TileLayer::callListener"
            + "(Ljava/lang/Object;"
            + "Ljava/lang/String;"
            + "Lorg/apidesign/html/leaflet/api/listener/EventListener;)"
            + "(ev.target, ev.type, l);\n"
            + "}, context);\n"
    )
    private static native void addEventListenerImpl(
            Object obj, String type, EventListener listener, Object context);

    static void callListener(final Object target, final String type,
            final org.apidesign.html.leaflet.api.listener.EventListener l) {

        l.onEvent(new Event(target, type));
    }
}
