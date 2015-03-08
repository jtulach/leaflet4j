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
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.listener.EventListener;

/**
 *
 * @author Christoph Sperl
 * @author Andreas Grimmer
 * 
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

    public TileLayer addEventListener(String type, EventListener listener) {
        return new TileLayer(addEventListener(type, listener, null));
    }

    public TileLayer addEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener, context);
        return this;
    }
}
