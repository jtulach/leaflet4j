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
import org.apidesign.html.leaflet.api.event.DragEndEvent;
import org.apidesign.html.leaflet.api.event.DragEndListener;
import org.apidesign.html.leaflet.api.event.ErrorEvent;
import org.apidesign.html.leaflet.api.event.ErrorListener;
import org.apidesign.html.leaflet.api.event.Event;
import org.apidesign.html.leaflet.api.event.EventListener;
import org.apidesign.html.leaflet.api.event.LayerEvent;
import org.apidesign.html.leaflet.api.event.LayerListener;
import org.apidesign.html.leaflet.api.event.LocationEvent;
import org.apidesign.html.leaflet.api.event.LocationListener;
import org.apidesign.html.leaflet.api.event.MouseEvent;
import org.apidesign.html.leaflet.api.event.MouseListener;
import org.apidesign.html.leaflet.api.event.PopupEvent;
import org.apidesign.html.leaflet.api.event.PopupListener;
import org.apidesign.html.leaflet.api.event.ResizeEvent;
import org.apidesign.html.leaflet.api.event.ResizeListener;

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

    Map(Object jsObj) {
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
    /* necessary 
    public Map eachLayer(Consumer<ILayer> fun) {
        Object[] layersJS = eachLayerInternal(jsObj);
        for (int q = 0; q < layersJS.length; q++) {
            fun.accept(ILayer.createLayer(layersJS[q]));
        }
        return this;
    }
    */

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
     * Adds a mouse listener to a particular mouse event type of the object.
     *
     * @param type The mouse event type. The types CLICK, DBLCLICK, MOUSEDOWN,
     * MOUSEUP, MOUSEOVER, MOUSEOUT, MOUSEMOVE, CONTEXTMENU and PRECLICK are
     * supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addMouseListener(MouseEvent.Type type, MouseListener listener) {
        EventMethodsHelper.addMouseListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a event listener to a particular event type of the object.
     *
     * @param type The event type. The types FOCUS, BLUR, LOAD, UNLOAD,
     * VIEWRESET, MOVESTART, MOVE, MOVEEND, DRAGSTART, DRAG, ZOOMSTART, ZOOMEND,
     * ZOOMLEVELSCHANGE and AUTOPANSTART are supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addEventListener(Event.Type type, EventListener listener) {
        EventMethodsHelper.addEventListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a drag end listener to a particular drag end event type of the
     * object.
     *
     * @param type The drag end event type. The type DRAGEND is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addDragEndListener(DragEndEvent.Type type, DragEndListener listener) {
        EventMethodsHelper.addDragEndListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a resize listener to a particular resize event type of the object.
     *
     * @param type The resize event type. The type RESIZE is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addResizeListener(ResizeEvent.Type type, ResizeListener listener) {
        EventMethodsHelper.addResizeListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a layer listener to a particular layer event type of the object.
     *
     * @param type The layer event type. The types LAYERADD, LAYERREMOVE,
     * BASSELAYERCHANGE, OVERLAYADD and OVERLAYREMOVE are supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addLayerListener(LayerEvent.Type type, LayerListener listener) {
        EventMethodsHelper.addLayerListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a location listener to a particular location event type of the
     * object.
     *
     * @param type The location event type. The type LOCATIONFOUND is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addLocationListener(LocationEvent.Type type, LocationListener listener) {
        EventMethodsHelper.addLocationListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a error listener to a particular error event type of the object.
     *
     * @param type The error event type. The type LOCATIONERROR is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addErrorListener(ErrorEvent.Type type, ErrorListener listener) {
        EventMethodsHelper.addErrorListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Adds a popup listener to a particular popup event type of the object.
     *
     * @param type The popup event type. The types POPUPOPEN and POPUPCLOSE are
     * supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map addPopupListener(PopupEvent.Type type, PopupListener listener) {
        EventMethodsHelper.addPopupListener(getJSObj(), type, listener);
        return this;
    }

    /**
     * Removes a mouse listener to a particular mouse event type of the object.
     *
     * @param type The mouse event type. The types CLICK, DBLCLICK, MOUSEDOWN,
     * MOUSEUP, MOUSEOVER, MOUSEOUT, MOUSEMOVE, CONTEXTMENU and PRECLICK are
     * supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeMouseListener(MouseEvent.Type type, MouseListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a event listener to a particular event type of the object.
     *
     * @param type The event type. The types FOCUS, BLUR, LOAD, UNLOAD,
     * VIEWRESET, MOVESTART, MOVE, MOVEEND, DRAGSTART, DRAG, ZOOMSTART, ZOOMEND,
     * ZOOMLEVELSCHANGE and AUTOPANSTART are supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeEventListener(Event.Type type, EventListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a drag end listener to a particular drag end event type of the
     * object.
     *
     * @param type The drag end event type. The type DRAGEND is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeDragEndListener(DragEndEvent.Type type, DragEndListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a resize listener to a particular resize event type of the
     * object.
     *
     * @param type The resize event type. The type RESIZE is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeResizeListener(ResizeEvent.Type type, ResizeListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a layer listener to a particular layer event type of the object.
     *
     * @param type The layer event type. The types LAYERADD, LAYERREMOVE,
     * BASSELAYERCHANGE, OVERLAYADD and OVERLAYREMOVE are supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeLayerListener(LayerEvent.Type type, LayerListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a location listener to a particular location event type of the
     * object.
     *
     * @param type The location event type. The type LOCATIONFOUND is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeLocationListener(LocationEvent.Type type, LocationListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a error listener to a particular error event type of the object.
     *
     * @param type The error event type. The type LOCATIONERROR is supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removeErrorListener(ErrorEvent.Type type, ErrorListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
        return this;
    }

    /**
     * Removes a popup listener to a particular popup event type of the object.
     *
     * @param type The popup event type. The types POPUPOPEN and POPUPCLOSE are
     * supported.
     * @param listener The registered listener.
     * @return The map object.
     */
    public Map removePopupListener(PopupEvent.Type type, PopupListener listener) {
        EventMethodsHelper.removeEventListener(getJSObj(), type.toString(), listener);
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

    // Conversion methods
    /**
     * Returns the map layer point that corresponds to the given geographical
     * coordinates (useful for placing overlays on the map).
     *
     * @param latlng The geographical coordinates.
     * @return Returns the map layer point.
     */
    public Point latLngToLayerPoint(LatLng latlng) {
        return new Point(latLngToLayerPoint(getJSObj(), latlng.getJSObj()));
    }

    /**
     * Returns the geographical coordinates of a given map layer point.
     *
     * @param point The map layer point.
     * @return Returns the geographical coordinates.
     */
    public LatLng layerPointToLatLng(Point point) {
        return new LatLng(layerPointToLatLng(getJSObj(), point.getJSObj()));
    }

    /**
     * Converts the point relative to the map container to a point relative to
     * the map layer.
     *
     * @param point A point relative to the map container.
     * @return Returns a point relative to the map layer.
     */
    public Point containerPointToLayerPoint(Point point) {
        return new Point(containerPointToLayerPoint(getJSObj(), point.getJSObj()));
    }

    /**
     * Converts the point relative to the map layer to a point relative to the
     * map container.
     *
     * @param point A point relative to the map layer.
     * @return Returns a point relative to the map container.
     */
    public Point layerPointToContainerPoint(Point point) {
        return new Point(layerPointToContainerPoint(getJSObj(), point.getJSObj()));
    }

    /**
     * Returns the map container point that corresponds to the given
     * geographical coordinates.
     *
     * @param latlng The geographical coordinates.
     * @return Returns the map container point.
     */
    public Point latLngToContainerPoint(LatLng latlng) {
        return new Point(latLngToContainerPoint(getJSObj(), latlng.getJSObj()));
    }

    /**
     * Returns the geographical coordinates of a given map container point.
     *
     * @param point The map container point.
     * @return Returns the geographical coordinates.
     */
    public LatLng containerPointToLatLng(Point point) {
        return new LatLng(containerPointToLatLng(getJSObj(), point.getJSObj()));
    }

    /**
     * Projects the given geographical coordinates to absolute pixel coordinates
     * for the given zoom level (current zoom level by default).
     *
     * @param latlng The geographical coordinates.
     * @return Returns the absolute pixel coordinates.
     */
    public Point project(LatLng latlng) {
        return new Point(project(getJSObj(), latlng.getJSObj()));
    }

    /**
     * Projects the given geographical coordinates to absolute pixel coordinates
     * for the given zoom level (current zoom level by default).
     *
     * @param latlng The geographical coordinates.
     * @param zoom The zoom level.
     * @return Returns the absolute pixel coordinates.
     */
    public Point project(LatLng latlng, int zoom) {
        return new Point(project(getJSObj(), latlng.getJSObj(), zoom));
    }

    /**
     * Projects the given absolute pixel coordinates to geographical coordinates
     * for the given zoom level (current zoom level by default).
     *
     * @param point The absolute pixel coordinates.
     * @return Returns the geographical coordinates.
     */
    public LatLng unproject(Point point) {
        return new LatLng(unproject(getJSObj(), point.getJSObj()));
    }

    /**
     * Projects the given absolute pixel coordinates to geographical coordinates
     * for the given zoom level (current zoom level by default).
     *
     * @param point The absolute pixel coordinates.
     * @param zoom The zoom level.
     * @return Returns the geographical coordinates.
     */
    public LatLng unproject(Point point, int zoom) {
        return new LatLng(unproject(getJSObj(), point.getJSObj(), zoom));
    }

    @JavaScriptBody(args = {"map", "latlng"}, body
            = "return map.latLngToLayerPoint(latlng);")
    private static native Object latLngToLayerPoint(Object map, Object latlng);

    @JavaScriptBody(args = {"map", "point"}, body
            = "return map.layerPointToLatLng(point);")
    private static native Object layerPointToLatLng(Object map, Object point);

    @JavaScriptBody(args = {"map", "point"}, body
            = "return map.containerPointToLayerPoint(point);")
    private static native Object containerPointToLayerPoint(Object map, Object point);

    @JavaScriptBody(args = {"map", "point"}, body
            = "return map.layerPointToContainerPoint(point);")
    private static native Object layerPointToContainerPoint(Object map, Object point);

    @JavaScriptBody(args = {"map", "latlng"}, body
            = "return map.latLngToContainerPoint(latlng);")
    private static native Object latLngToContainerPoint(Object map, Object latlng);

    @JavaScriptBody(args = {"map", "point"}, body
            = "return map.containerPointToLatLng(point);")
    private static native Object containerPointToLatLng(Object map, Object point);

    @JavaScriptBody(args = {"map", "latlng"}, body
            = "return map.project(latlng);")
    private static native Object project(Object map, Object latlng);

    @JavaScriptBody(args = {"map", "latlng", "zoom"}, body
            = "return map.project(latlng, zoom);")
    private static native Object project(Object map, Object latlng, int zoom);

    @JavaScriptBody(args = {"map", "point"}, body
            = "return map.unproject(point);")
    private static native Object unproject(Object map, Object point);

    @JavaScriptBody(args = {"map", "point", "zoom"}, body
            = "return map.unproject(point, zoom);")
    private static native Object unproject(Object map, Object point, int zoom);
}
