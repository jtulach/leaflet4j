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

import java.util.function.Consumer;
import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;
import static org.apidesign.html.leaflet.api.ILayer.registerLayerType;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.listener.LayerListener;
import org.apidesign.html.leaflet.api.listener.MouseListener;

/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class FeatureGroup extends LayerGroup {

    static {
        registerLayerType("L.FeatureGroup", (obj) -> new FeatureGroup(obj));
    }

    protected FeatureGroup(Object jsObj) {
        super(jsObj);
    }

    public FeatureGroup(ILayer[] layers) {
        super(createHelper(layers));
    }

    private static Object createHelper(ILayer[] layers) {
        Object[] layersJS = new Object[layers.length];
        for (int q = 0; q < layersJS.length; q++) {
            layersJS[q] = layers[q].getJSObj();
        }
        return create(layers);
    }

    @JavaScriptBody(args = {"layers"}, body
            = "return L.featureGroup(layers);")
    private static native Object create(Object[] layers);

    // ------- Event methods --------------------------------------
    /**
     * Adds a mouse listener to a particular mouse event type of the object.
     *
     * @param type The mouse event type. The types CLICK, DBLCLICK, MOUSEOVER,
     * MOUSEOUT and MOUSEMOVE are supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public FeatureGroup addMouseListener(MouseEvent.Type type, MouseListener listener) {
        EventMethodsHelper.addMouseListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a layer listener to a particular layer event type of the object.
     *
     * @param type The layer event type. The types LAYERADD and LAYERREMOVE are
     * supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public FeatureGroup addLayerListener(LayerEvent.Type type, LayerListener listener) {
        EventMethodsHelper.addLayerListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Removes a mouse listener to a particular mouse event type of the object.
     *
     * @param type The mouse event type. The types CLICK, DBLCLICK, MOUSEOVER,
     * MOUSEOUT and MOUSEMOVE are supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public FeatureGroup removeMouseListener(MouseEvent.Type type, MouseListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a layer listener to a particular layer event type of the object.
     *
     * @param type The layer event type. The types LAYERADD and LAYERREMOVE are
     * supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public FeatureGroup removeLayerListener(LayerEvent.Type type, LayerListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes all listeners to all events on the object.
     *
     * @return The map object.
     */
    public FeatureGroup clearAllEventListeners() {
        EventMethodsHelper.clearAllEventListeners(getJSObj());
        return this;
    }

    // ------- Methods -------------------------------------
    public FeatureGroup bindPopup(String html) {
        bindPopupInternal(jsObj, html, new PopupOptions().getJSObj());
        return this;
    }

    public FeatureGroup bindPopup(String html, PopupOptions options) {
        bindPopupInternal(jsObj, html, options.getJSObj());
        return this;
    }

    public FeatureGroup setStyle(PathOptions options) {
        setStyleInternal(jsObj, options.getJSObj());
        return this;
    }

    public LatLngBounds getBounds() {
        return new LatLngBounds(getBoundsInternal(jsObj));
    }

    public FeatureGroup bringToFront() {
        bringToFrontInternal(jsObj);
        return this;
    }

    public FeatureGroup bringToBack() {
        bringToBackInternal(jsObj);
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "html", "options"}, body
            = "jsObj.bindPopup(html, options);")
    private static native void bindPopupInternal(Object jsObj, String html, Object options);

    @JavaScriptBody(args = {"jsObj", "options"}, body
            = "jsObj.setStyle(options);")
    private static native void setStyleInternal(Object jsObj, Object options);

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getBounds();")
    private static native Object getBoundsInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.bringToFront();")
    private static native void bringToFrontInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.bringToBack();")
    private static native void bringToBackInternal(Object jsObj);

    // ------- LayerGroup Methods -------------------------------------
    @Override
    public FeatureGroup addTo(Map map) {
        super.addTo(map);
        return this;
    }

    @Override
    public FeatureGroup addLayer(ILayer layer) {
        super.addLayer(layer);
        return this;
    }

    @Override
    public FeatureGroup removeLayer(ILayer layer) {
        super.removeLayer(layer);
        return this;
    }

    @Override
    public FeatureGroup removeLayer(String layerId) {
        super.removeLayer(layerId);
        return this;
    }

    @Override
    public FeatureGroup eachLayer(Consumer<ILayer> fun) {
        super.eachLayer(fun);
        return this;
    }

    @Override
    public FeatureGroup clearLayers() {
        super.clearLayers();
        return this;
    }

}
