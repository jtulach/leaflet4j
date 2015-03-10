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
import static org.apidesign.html.leaflet.api.ILayer.registerLayerType;

/**
 * TileLayer used to display WMS services as tile layers on the map.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class TileLayerWMS extends TileLayer {

    static {
        registerLayerType("L.TileLayer.WMS", (obj) -> new TileLayerWMS(obj));
    }

    protected TileLayerWMS(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a WMS tile layer object given a base URL of the WMS service
     * and a WMS parameters/options object.
     *
     * @param urlTemplate The URL to the WMS service
     * @param options The {@link TileLayerWMSOptions}
     */
    public TileLayerWMS(String urlTemplate, TileLayerWMSOptions options) {
        super(create(urlTemplate, options.getJSObj()));
    }

    @JavaScriptBody(args = {"urlTemplate", "options"}, body
            = "return L.tileLayer.wms(urlTemplate, options);")
    private static native Object create(String urlTemplate, Object options);

    // ------ Methods ------------------------------------
    /**
     * Merges an object with the new parameters and re-requests tiles on the
     * current screen.
     *
     * @param options The new {@link TileLayerWMSOptions}
     */
    public void setParams(TileLayerWMSOptions options) {
        setParamsInteral(jsObj, options.getJSObj(), false);
    }

    /**
     * Merges an object with the new parameters,but does not re-requests tiles
     * on the current screen.
     *
     * @param options The new {@link TileLayerWMSOptions}
     */
    public void setParams(TileLayerWMSOptions options, boolean noRedraw) {
        setParamsInteral(jsObj, options.getJSObj(), noRedraw);
    }

    @JavaScriptBody(args = {"jsObj", "options", "noRedraw"}, body
            = "return jsObj.setParams(options, noRedraw);")
    private static native void setParamsInteral(Object jsObj, Object options, boolean noRedraw);

}
