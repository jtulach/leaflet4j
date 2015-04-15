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

import net.java.html.js.JavaScriptBody;
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.EventListener;
import org.apidesign.html.leaflet.api.event.TileEvent;
import org.apidesign.html.leaflet.api.event.TileListener;

/** Layer of tiles.
 *
 * @author Christoph Sperl
 * @author Andreas Grimmer
 *
 */
public class TileLayer extends ILayer {

    static {
        Options.initJS();
        registerLayerType("L.TileLayer", new Function<Object, ILayer>() {
            @Override
            public ILayer apply(Object obj) {
                return new TileLayer(obj);
            }
        });
    }

    TileLayer(Object jsObj) {
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

    // ------- Event methods --------------------------------------
    /**
     * Adds a tile listener to a particular tile event type of the object.
     *
     * @param type The tile event type. The types TILELOADSTART, TILELOAD and
     * TILEUNLOAD are supported.
     * @param listener The registered listener.
     * @return The tile layer object.
     */
    public TileLayer addTileListener(TileEvent.Type type, TileListener listener) {
        EventMethodsHelper.addTileListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a event listener to a particular event type of the object.
     *
     * @param type The event type. The types LOADING and LOAD are supported.
     * @param listener The registered listener.
     * @return The tile layer object.
     */
    public TileLayer addEventListener(Event.Type type, EventListener listener) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Removes a tile listener to a particular tile event type of the object.
     *
     * @param type The tile event type. The types TILELOADSTART, TILELOAD and
     * TILEUNLOAD are supported.
     * @param listener The registered listener.
     * @return The tile layer object.
     */
    public TileLayer removeTileListener(TileEvent.Type type, TileListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a event listener to a particular event type of the object.
     *
     * @param type The event type. The types LOADING and LOAD are supported.
     * @param listener The registered listener.
     * @return The tile layer object.
     */
    public TileLayer removeEventListener(Event.Type type, EventListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes all listeners to all events on the object.
     *
     * @return The tile layer object.
     */
    public TileLayer clearAllEventListeners() {
        EventMethodsHelper.clearAllEventListeners(getJSObj());
        return this;
    }
}
