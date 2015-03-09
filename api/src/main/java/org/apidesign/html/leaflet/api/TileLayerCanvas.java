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
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class TileLayerCanvas extends TileLayer {

    static {
        registerLayerType("L.TileLayer.Canvas", (obj) -> new TileLayerCanvas(obj));
    }

    protected TileLayerCanvas(Object jsObj) {
        super(jsObj);
    }

    public TileLayerCanvas() {
        this(new TileLayerCanvasOptions());
    }

    public TileLayerCanvas(TileLayerCanvasOptions options) {
        super(create(options.getJSObj()));
    }

    @JavaScriptBody(args = {"options"}, body
            = "return L.tileLayer.canvas(options);")
    private static native Object create(Object options);

    // ------ Methods ------------------------------------
    //TODO: drawTile CALLBACK METHOD !!!
    // see also https://github.com/eppleton/canvas for a html5 canvas wrapper
    // however, it seems that it doesn't allow initialization from an existing canvas
    public void tileDrawn(Object htmlCanvasElem) {
        tileDrawnInteral(jsObj, htmlCanvasElem);
    }

    @JavaScriptBody(args = {"jsObj", "htmlCanvasElem"}, body
            = "return jsObj.setParams(htmlCanvasElem);")
    private static native void tileDrawnInteral(Object jsObj, Object htmlCanvasElem);

}
