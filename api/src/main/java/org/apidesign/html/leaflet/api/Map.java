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
import org.apidesign.html.leaflet.api.listener.EventListener;

/**
 * The central class of the API — it is used to create a map on a page and
 * manipulate it.
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

    public Map(Object jsObj) {
        this.jsObj = jsObj;
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     *
     * @param center The center value
     */
    public void setView(LatLng center) {
        setView1(jsObj, center.getJSObj());
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     *
     * @param center The center value
     * @param zoom The zoom value
     */
    public void setView(LatLng center, int zoom) {
        setView2(jsObj, center.getJSObj(), zoom);
    }

    /**
     * Sets the view of the map (geographical center and zoom) with the given
     * animation options.
     *
     * @param center The center value
     * @param zoom The zoom value
     * @param options The zoom/pan options
     */
    public void setView(LatLng center, int zoom, ZoomPanOptions options) {
        setView3(jsObj, center.getJSObj(), zoom, options.getJSObj());
    }

    @JavaScriptBody(args = {"id", "options"},
            body = "return L.map(id, options);")
    private static native Object create(String id, Object options);

    @JavaScriptBody(args = {"jsObj", "center"}, wait4js = false, body
            = "jsObj.setView(center);")
    private static native void setView1(Object jsObj, Object center);

    @JavaScriptBody(args = {"jsObj", "center", "zoom"}, wait4js = false, body
            = "jsObj.setView(center, zoom);")
    private static native void setView2(Object jsObj, Object center, int zoom);

    @JavaScriptBody(args = {"jsObj", "center", "zoom", "options"}, wait4js = false, body
            = "jsObj.setView(center, zoom, options);")
    private static native void setView3(Object jsObj, Object center, int zoom, Object options);

    // ------ Methods for Layers and Controls -------------------------
    /**
     * Adds the given layer to the map.
     *
     * @param layer The added layer.
     * @return The map object.
     */
    public Map addLayer(ILayer layer) {
        addLayerInternal(jsObj, layer.getJSObj());
        return this;
    }

    /**
     * Removes the given layer from the map.
     *
     * @param layer The removed layer.
     * @return The map object.
     */
    public Map removeLayer(ILayer layer) {
        removeLayerInternal(jsObj, layer.getJSObj());
        return this;
    }

    /**
     * Returns <code>true</code> if the given layer is currently added to the
     * map.
     *
     * @param layer The layer.
     * @return Returns <code>true</code> if the given layer is currently added
     * to the map, <code>false</code> otherwise.
     */
    public boolean hasLayer(ILayer layer) {
        return hasLayerInternal(jsObj, layer.getJSObj());
    }

    /**
     * Returns all layers.
     *
     * @return The layers.
     */
    public ILayer[] getLayers() {
        Object[] layersJS = eachLayerInternal(jsObj);
        ILayer[] layers = new ILayer[layersJS.length];
        for (int q = 0; q < layers.length; q++) {
            layers[q] = ILayer.createLayer(layersJS[q]);
        }
        return layers;
    }

    /**
     * Iterates over the layers of the group and performs the function.
     *
     * @param fun The performed function.
     * @return The map object.
     */
    public Map eachLayer(Consumer<ILayer> fun) {
        Object[] layersJS = eachLayerInternal(jsObj);
        for (int q = 0; q < layersJS.length; q++) {
            fun.accept(ILayer.createLayer(layersJS[q]));
        }
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.addLayer(layer);")
    private static native void addLayerInternal(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "jsObj.removeLayer(layer);")
    private static native void removeLayerInternal(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj", "layer"},
            body = "return jsObj.hasLayer(layer);")
    private static native boolean hasLayerInternal(Object jsObj, Object layer);

    @JavaScriptBody(args = {"jsObj"},
            body = "var arr = []; jsObj.eachLayer(function(layer) {arr.push(layer);}); return arr;")
    private static native Object[] eachLayerInternal(Object jsObj);

    // ------- Event methods --------------------------------------
    /**
     * Adds a listener function (<code>listener</code>) to a particular event
     * type of the object.
     *
     * @param type The types. You can also pass several space-separated types
     * (e.g. 'click dblclick').
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addEventListener(String type, EventListener listener) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a listener function (<code>listener</code>) to a particular event
     * type of the object.
     *
     * @param type The types. You can also pass several space-separated types
     * (e.g. 'click dblclick').
     * @param listener The registered listener.
     * @param context The context of the listener.
     * @return The map object.
     */
    public Map addEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener, context);
        return this;
    }

    /**
     * Same as method {@link #addEventListener} except the listener will only
     * get fired once and then removed.
     *
     * @param type The types. You can also pass several space-separated types
     * (e.g. 'click dblclick').
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addOneTimeEventListener(String type, EventListener listener) {
        EventMethodsHelper.addOneTimeEventListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Same as method {@link #addEventListener} except the listener will only
     * get fired once and then removed.
     *
     * @param type The types. You can also pass several space-separated types
     * (e.g. 'click dblclick').
     * @param listener The registered listener.
     * @param context The context of the listener.
     * @return The map object.
     */
    public Map addOneTimeEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.addOneTimeEventListener(getJSObj(), type, listener, context);
        return this;
    }

    /**
     * Adds a set of type/listener pairs
     *
     * @param eventMap The map containing the type/listener pairs
     * @return The map object.
     */
    public Map addEventListener(java.util.Map<String, EventListener> eventMap) {
        EventMethodsHelper.addEventListener(getJSObj(), eventMap);
        return this;
    }

    /**
     * Adds a set of type/listener pairs
     *
     * @param eventMap The map containing the type/listener pairs
     * @param context The context of the listener.
     * @return The map object.
     */
    public Map addEventListener(java.util.Map<String, EventListener> eventMap, Object context) {
        EventMethodsHelper.addEventListener(getJSObj(), eventMap, context);
        return this;
    }

    /**
     * Removes a previously added listener function.
     *
     * @param type The types.
     * @return The map object.
     */
    public Map removeEventListener(String type) {
        EventMethodsHelper.removeEventListener(getJSObj(), type);
        return this;
    }

    /**
     * Removes a previously added listener function.
     *
     * @param type The types.
     * @param context The context. Note that if you passed a custom context to
     * addEventListener, you must pass the same context to removeEventListener
     * in order to remove the listener.
     * @return The map object.
     */
    public Map removeEventListener(String type, Object context) {
        EventMethodsHelper.removeEventListener(getJSObj(), type, context);
        return this;
    }

    /**
     * Removes a previously added listener function.
     *
     * @param type The types.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeEventListener(String type, EventListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Removes a previously added listener function.
     *
     * @param type The types.
     * @param listener The registered listener.
     * @param context The context. Note that if you passed a custom context to
     * addEventListener, you must pass the same context to removeEventListener
     * in order to remove the listener.
     * @return The map object.
     */
    public Map removeEventListener(String type, EventListener listener, Object context) {
        EventMethodsHelper.removeEventListener(getJSObj(), type, listener, context);
        return this;
    }

    /**
     * Removes a set of type/listener pairs.
     *
     * @param eventMap The map containing the type/listener pairs
     * @return The map object.
     */
    public Map removeEventListener(java.util.Map<String, EventListener> eventMap) {
        EventMethodsHelper.removeEventListener(getJSObj(), eventMap);
        return this;
    }

    /**
     * Removes a set of type/listener pairs.
     *
     * @param eventMap The map containing the type/listener pairs
     * @param context The context.
     * @return The map object.
     */
    public Map removeEventListener(java.util.Map<String, EventListener> eventMap, Object context) {
        EventMethodsHelper.removeEventListener(getJSObj(), eventMap, context);
        return this;
    }

    /**
     * Removes all listeners to all events on the object.
     *
     * @return The map object.
     */
    public Map clearAllEventListeners() {
        EventMethodsHelper.clearAllEventListeners(getJSObj());
        return this;
    }

    /**
     * Checks if a particular event type has some listeners attached to it.
     *
     * @param type The type.
     * @return Returns <code>true</code> if a particular event type has some
     * listeners attached to it.
     */
    public boolean hasEventListeners(String type) {
        return EventMethodsHelper.hasEventListeners(getJSObj(), type);
    }

    /**
     * Fires an event of the specified type.
     *
     * @param type The type.
     * @return The map object.
     */
    public Map fireEvent(String type) {
        EventMethodsHelper.fireEvent(getJSObj(), type);
        return this;
    }

    /**
     * Fires an event of the specified type.
     *
     * @param type The type.
     * @param data The data object.
     * @return The map object.
     */
    public Map fireEvent(String type, Object data) {
        EventMethodsHelper.fireEvent(getJSObj(), type, data);
        return this;
    }

    // ------- Popup methods -------------------------------------------
    /**
     * Creates a popup and opens it in the given point on a map.
     *
     * @param html The html content.
     * @param latlng The point.
     * @return The map object.
     */
    public Map openPopup(String html, LatLng latlng) {
        openPopup1sInternal(jsObj, html, latlng.getJSObj());
        return this;
    }

    /**
     * Opens the specified popup while closing the previously opened (to make
     * sure only one is opened at one time for usability).
     *
     * @param popup The popup.
     * @param latlng The point.
     * @return The map object.
     */
    public Map openPopup(Popup popup, LatLng latlng) {
        openPopup1pInternal(jsObj, popup.getJSObj(), latlng.getJSObj());
        return this;
    }

    /**
     * Opens the specified popup while closing the previously opened (to make
     * sure only one is opened at one time for usability).
     *
     * @param popup The popup.
     * @param latlng The point.
     * @param options The {@link PopupOptions}.
     * @return The map object.
     */
    public Map openPopup(Popup popup, LatLng latlng, PopupOptions options) {
        openPopup2Internal(jsObj, popup.getJSObj(), latlng.getJSObj(), options.getJSObj());
        return this;
    }

    /**
     * Closes the popup previously opened with {@link #openPopup}.
     *
     * @return The map object.
     */
    public Map closePopup() {
        closePopup0Internal(jsObj);
        return this;
    }

    /**
     * Closes the given popup.
     *
     * @param popup The popup to close.
     * @return The map object.
     */
    public Map closePopup(Popup popup) {
        closePopup1Internal(jsObj, popup.getJSObj());
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "html", "latlng"}, body
            = "jsObj.openPopup(html, latlng);")
    private static native void openPopup1sInternal(Object jsObj, String html, Object latlng);

    @JavaScriptBody(args = {"jsObj", "popup", "latlng"}, body
            = "jsObj.openPopup(popup, latlng);")
    private static native void openPopup1pInternal(Object jsObj, Object popup, Object latlng);

    @JavaScriptBody(args = {"jsObj", "popup", "latlng", "options"}, body
            = "jsObj.openPopup(popup, latlng, options);")
    private static native void openPopup2Internal(Object jsObj, Object popup, Object latlng, Object options);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.closePopup();")
    private static native void closePopup0Internal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "popup"}, body
            = "jsObj.closePopup(popup);")
    private static native void closePopup1Internal(Object jsObj, Object popup);

    // ------ Methods for Getting Map State -------------------------
    /**
     * Returns the geographical center of the map view.
     *
     * @return Returns the geographical center of the map view.
     */
    public LatLng getCenter() {
        return new LatLng(getCenterInternal(jsObj));
    }

    /**
     * Returns the current zoom of the map view.
     *
     * @return Returns the current zoom of the map view.
     */
    public int getZoom() {
        return getZoomInternal(jsObj);
    }

    /**
     * Returns the minimum zoom level of the map.
     *
     * @return Returns the minimum zoom level of the map.
     */
    public int getMinZoom() {
        return getMinZoomInternal(jsObj);
    }

    /**
     * Returns the maximum zoom level of the map.
     *
     * @return Returns the maximum zoom level of the map.
     */
    public int getMaxZoom() {
        return getMaxZoomInternal(jsObj);
    }

    /**
     * Returns the LatLngBounds of the current map view.
     *
     * @return Returns the {@link LatLngBounds} of the current map view.
     */
    public LatLngBounds getBounds() {
        return new LatLngBounds(getBoundsInternal(jsObj));
    }

    /**
     * Returns the maximum zoom level on which the given bounds fit to the map
     * view in its entirety.
     *
     * @param bounds The bounds.
     * @return The maximum zoom level.
     */
    public int getBoundsZoom(LatLngBounds bounds) {
        return getBoundsZoom1Internal(jsObj, bounds.getJSObj());
    }

    /**
     * Returns the maximum zoom level on which the given bounds fit to the map
     * view in its entirety.
     *
     * @param bounds The bounds.
     * @param inside If inside is set <code>true</code>, the method instead
     * returns the minimum zoom level on which the map view fits into the given
     * bounds in its entirety.
     * @return The maximum/minimum zoom level.
     */
    public int getBoundsZoom(LatLngBounds bounds, boolean inside) {
        return getBoundsZoom2Internal(jsObj, bounds.getJSObj(), inside);
    }

    /**
     * Returns the current size of the map container.
     *
     * @return Returns the current size of the map container.
     */
    public Point getSize() {
        return new Point(getSizeInternal(jsObj));
    }

    /**
     * Returns the bounds of the current map view in projected pixel coordinates
     * (sometimes useful in layer and overlay implementations).
     *
     * @return Returns the bounds of the current map view in projected pixel
     * coordinates (sometimes useful in layer and overlay implementations).
     */
    public Bounds getPixelBounds() {
        return new Bounds(getPixelBoundsInternal(jsObj));
    }

    /**
     * Returns the projected pixel coordinates of the top left point of the map
     * layer (useful in custom layer and overlay implementations).
     *
     * @return Returns the projected pixel coordinates of the top left point of
     * the map layer (useful in custom layer and overlay implementations).
     */
    public Point getPixelOrigin() {
        return new Point(getPixelOriginInternal(jsObj));
    }

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getCenter();")
    private static native Object getCenterInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getZoom();")
    private static native int getZoomInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getMinZoom();")
    private static native int getMinZoomInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getMaxZoom();")
    private static native int getMaxZoomInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getBounds();")
    private static native Object getBoundsInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "bounds"},
            body = "jsObj.getBoundsZoom(bounds);")
    private static native int getBoundsZoom1Internal(Object jsObj, Object bounds);

    @JavaScriptBody(args = {"jsObj", "bounds", "inside"},
            body = "jsObj.getBoundsZoom(bounds, inside);")
    private static native int getBoundsZoom2Internal(Object jsObj, Object bounds, boolean inside);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getSize();")
    private static native Object getSizeInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getPixelBounds();")
    private static native Object getPixelBoundsInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "jsObj.getPixelOrigin();")
    private static native Object getPixelOriginInternal(Object jsObj);

}
