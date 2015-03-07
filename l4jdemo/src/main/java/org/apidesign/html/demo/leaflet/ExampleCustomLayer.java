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
package org.apidesign.html.demo.leaflet;

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import org.apidesign.html.leaflet.api.ILayer;
import org.apidesign.html.leaflet.api.LatLng;

/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/demo/leaflet/customLayer.js")
public class ExampleCustomLayer extends ILayer {
    
    static {
        registerLayerType("ExampleCustomLayer", (obj)->new ExampleCustomLayer(obj));
    }
    
    private ExampleCustomLayer(Object jsObj) {
        super(jsObj);
    }
    
    public ExampleCustomLayer(LatLng latlng) {
        super(create(latlng.getJSObj(), "https://cdnjs.cloudflare.com/ajax/libs/fatcow-icons/20130425/FatCow_Icons32x32/radioactivity.png"));
    }
    
    public ExampleCustomLayer(LatLng latlng, String imgURL) {
        super(create(latlng.getJSObj(), imgURL));
    }
    
    
    
    @JavaScriptBody(args = {"latlng", "imgURL"}, body
            = "return new ExampleCustomLayer(latlng, imgURL);")
    private static native Object create(Object latlng, String imgURL);
}
