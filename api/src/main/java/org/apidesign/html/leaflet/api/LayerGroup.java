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

/**
 * Used to group several layers and handle them as one. If you add it to the
 * map, any layers added or removed from the group will be added/removed on the
 * map as well.
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public class LayerGroup extends ILayer {

    static {
        registerLayerType("L.LayerGroup", (obj) -> new LayerGroup(obj));
    }

    protected LayerGroup(Object jsObj) {
        super(jsObj);
    }

    /**
     * Creates a layer group from an array of layers
     *
     * @param layers array of layers
     */
    public LayerGroup(ILayer[] layers) {
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
            = "return L.layerGroup(layers);")
    private static native Object create(Object[] layers);

    // ------- Methods -------------------------------------
    /**
     * Adds the group of layers to the map.
     *
     * @param map The map
     * @return this
     */
    public LayerGroup addTo(Map map) {
        addToInternal(jsObj, map.getJSObj());
        return this;
    }

    /**
     * Adds a given layer to the group.
     *
     * @param layer The layer to add
     * @return this
     */
    public LayerGroup addLayer(ILayer layer) {
        addLayerInternal(jsObj, layer.getJSObj());
        return this;
    }

    /**
     * Removes a given layer from the group.
     *
     * @param layer The layer to remove
     * @return this
     */
    public LayerGroup removeLayer(ILayer layer) {
        removeLayerInternal(jsObj, layer.getJSObj());
        return this;
    }

    /**
     * Removes a given layer of the given id from the group.
     *
     * @param layerId The layer id of the layer to remove
     * @return this
     */
    public LayerGroup removeLayer(String layerId) {
        removeLayerByIdInternal(jsObj, layerId);
        return this;
    }

    /**
     * Returns <code>true</code> if the given layer is currently added to the
     * group.
     *
     * @param layer layer to check whether in group
     * @return <code>true</code> if the given layer is currently added to the
     * group
     */
    public boolean hasLayer(ILayer layer) {
        return hasLayerInternal(jsObj, layer.getJSObj());
    }

    /**
     * Returns all layers in the group
     *
     * @return array of all layers in the group
     */
    public ILayer[] getLayers() {
        Object[] layersJS = getLayersInternal(jsObj);
        ILayer[] layers = new ILayer[layersJS.length];
        for (int q = 0; q < layers.length; q++) {
            layers[q] = ILayer.createLayer(layersJS[q]);
        }
        return layers;
    }

    /**
     * Iterates over all layers in the group
     *
     * @param fun visitor function which is called for each layer in the group
     * @return this
     */
    public LayerGroup eachLayer(Consumer<ILayer> fun) {
        Object[] layersJS = getLayersInternal(jsObj);
        for (int q = 0; q < layersJS.length; q++) {
            fun.accept(ILayer.createLayer(layersJS[q]));
        }
        return this;
    }

    /**
     * Removes all the layers from the group.
     *
     * @return this
     */
    public LayerGroup clearLayers() {
        clearLayersInternal(jsObj);
        return this;
    }

    //TODO: toGeoJSON
    
    
    @JavaScriptBody(args = {"jsObj", "map"}, body
            = "jsObj.addTo(map);")
    private static native void addToInternal(Object jsObj, Object map);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.addLayer(layer);")
    private static native void addLayerInternal(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.removeLayer(layer);")
    private static native void removeLayerInternal(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj", "layerId"},
            body = "jsObj.removeLayer(layerId);")
    private static native void removeLayerByIdInternal(Object jsObj, String layerId);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "return jsObj.hasLayer(layer);")
    private static native boolean hasLayerInternal(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.getLayers();")
    private static native Object[] getLayersInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.clearLayers();")
    private static native void clearLayersInternal(Object jsObj);

}
