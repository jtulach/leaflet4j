/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015
 * Andreas Grimmer <a.grimmer@gmx.at>
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

import java.util.function.Consumer;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import static org.apidesign.html.leaflet.api.ILayer.registerLayerType;

/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class MultiPolyline extends FeatureGroup {

    static {
        registerLayerType("L.MultiPolyline", (obj) -> new MultiPolyline(obj));
    }

    protected MultiPolyline(Object jsObj) {
        super(jsObj);
    }

    public MultiPolyline(LatLng[][] latlngs) {
        super(create(createHelper(latlngs), new PolyLineOptions().getJSObj()));
    }
    
    public MultiPolyline(LatLng[][] latlngs, PolyLineOptions options) {
        super(create(createHelper(latlngs), options.getJSObj()));
    }

    private static Object[] createHelper(LatLng[][] latlngs) {
        Object[] latlngsJS = new Object[latlngs.length];
        for (int q = 0; q < latlngsJS.length; q++) {
            Object[] tmpJS = new Object[latlngs[q].length];
            for (int w = 0; w < tmpJS.length; w++) tmpJS[q] = latlngs[q][w].getJSObj();
            latlngsJS[q] = createJSArray(tmpJS);
        }
        return latlngsJS;
    }

    private static LatLng[][] parseHelper(Object[] latlngsJS) {
        LatLng[][] latlngs = new LatLng[latlngsJS.length][];
        for (int q = 0; q < latlngsJS.length; q++) {
            Object[] tmpJS = parseJSArray(latlngsJS[q]);
            LatLng[] tmp = new LatLng[tmpJS.length];
            for (int w = 0; w < tmp.length; w++) tmp[q] = new LatLng(tmpJS[w]);
            latlngs[q] = tmp;
        }
        return latlngs;
    }

    
    @JavaScriptBody(args = {"elems"}, body = "return elems;")
    private static native Object createJSArray(Object[] elems);
    @JavaScriptBody(args = {"elems"}, body = "return elems;")
    private static native Object[] parseJSArray(Object elems);

    
    @JavaScriptBody(args = {"latlngs", "options"}, body
            = "return L.multiPolyline(latlngs, options);")
    private static native Object create(Object[] latlngs, Object options);

    
    
    // ------- Methods -------------------------------------
    
    
    public MultiPolyline setLatLngs(LatLng[][] latlngs) {
        setLatLngsInternal(jsObj, createHelper(latlngs));
        return this;
    }
        
    public LatLng[][] getLatLngs() {
        return parseHelper(getLatLngsInternal(jsObj));
    }
    
    public MultiPolyline openPopup() {
        openPopupInternal(jsObj);
        return this;
    }
    
    
    //TODO: toGeoJSON
    
    
    @JavaScriptBody(args = {"jsObj", "latlngs"}, body
            = "jsObj.setLatLngs(latlngs);")
    private static native void setLatLngsInternal(Object jsObj, Object[] latlngs);
    
    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.getLatLngs();")
    private static native Object[] getLatLngsInternal(Object jsObj);

    @JavaScriptBody(args = { "jsObj" }, body = 
        "jsObj.openPopup();")
    private static native void openPopupInternal(Object jsObj);    

   
    
    
    // ------- FeatureGroup Methods -------------------------------------
    
    @Override
    public MultiPolyline bindPopup(String html) {
        super.bindPopup(html);
        return this;
    }
    
    @Override
    public MultiPolyline bindPopup(String html, PopupOptions options) {
        super.bindPopup(html, options);
        return this;
    }

    @Override
    public MultiPolyline setStyle(PathOptions options) {
        super.setStyle(options);
        return this;
    }

    @Override
    public MultiPolyline bringToFront() {
        super.bringToFront();
        return this;
    }

    @Override
    public MultiPolyline bringToBack() {
        super.bringToBack();
        return this;
    }
    
    
    
    // ------- LayerGroup Methods -------------------------------------
    
    @Override
    public MultiPolyline addTo(Map map) {
        super.addTo(map);
        return this;
    }

    @Override
    public MultiPolyline addLayer(ILayer layer) {
        super.addLayer(layer);
        return this;
    }

    @Override
    public MultiPolyline removeLayer(ILayer layer) {
        super.removeLayer(layer);
        return this;
    }

    @Override
    public MultiPolyline removeLayer(String layerId) {
        super.removeLayer(layerId);
        return this;
    }

    @Override
    public MultiPolyline eachLayer(Consumer<ILayer> fun) {
        super.eachLayer(fun);
        return this;
    }

    @Override
    public MultiPolyline clearLayers() {
        super.clearLayers();
        return this;
    }
    
}
