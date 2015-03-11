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

/**
 * Class representing a marker on a map
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public final class Marker extends ILayer {

    static {
        registerLayerType("L.Marker", (obj) -> new Marker(obj));
    }

    private Marker(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a Marker object given a geographical point.
     *
     * @param latLng geographical location
     */
    public Marker(LatLng latLng) {
        this(latLng, new MarkerOptions());
    }

    /**
     * Instantiates a Marker object given a geographical point and an options
     * object.
     *
     * @param latLng geographical location
     * @param options marker configuration options
     */
    public Marker(LatLng latLng, MarkerOptions options) {
        super(create(latLng.getJSObj(), options.getJSObj()));
    }

    @JavaScriptBody(args = {"latLng", "options"}, body
            = "return L.marker(latLng, options);")
    private static native Object create(Object latLng, Object options);

    // ------- Methods -------------------------------------------
    /**
     * Adds the marker to the map.
     *
     * @param map The map
     */
    public void addTo(Map map) {
        addToInternal(jsObj, map.getJSObj());
    }

    /**
     * Returns the current geographical position of the marker.
     *
     * @return location of the marker
     */
    public LatLng getLatLng() {
        return new LatLng(getLatLngInternal(jsObj));
    }

    /**
     * Changes the marker position to the given point.
     *
     * @param latlng location of the marker
     */
    public void setLatLng(LatLng latlng) {
        setLatLngInternal(jsObj, latlng.getJSObj());
    }

    /**
     * Changes the marker icon.
     *
     * @param icon marker icon
     */
    public void setIcon(Icon icon) {
        setIconInternal(jsObj, icon.getJSObj());
    }

    /**
     * Changes the zIndex offset of the marker.
     *
     * @param offset zIndex offset of the marker
     */
    public void setZIndexOffset(double offset) {
        setZIndexOffsetInternal(jsObj, offset);
    }

    /**
     * Changes the opacity of the marker.
     *
     * @param opacity opacity of the marker
     */
    public void setOpacity(double opacity) {
        setOpacityInternal(jsObj, opacity);
    }

    /**
     * Updates the marker position.
     */
    public void update() {
        updateInternal(jsObj);
    }

    //TODO: GeoJSON wrapper
    /*
     public String toGeoJSON() {
     return toGeoJSONInternal(jsObj);
     }
     */
    @JavaScriptBody(args = {"jsObj", "map"}, body
            = "jsObj.addTo(map);")
    private static native void addToInternal(Object jsObj, Object map);

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getLatLng();")
    private static native Object getLatLngInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "latlng"}, body
            = "jsObj.setLatLng(latlng);")
    private static native void setLatLngInternal(Object jsObj, Object latlng);

    @JavaScriptBody(args = {"jsObj", "icon"}, body
            = "jsObj.setIcon(icon);")
    private static native void setIconInternal(Object jsObj, Object icon);

    @JavaScriptBody(args = {"jsObj", "zIndexOffset"}, body
            = "jsObj.setZIndexOffset(zIndexOffset);")
    private static native void setZIndexOffsetInternal(Object jsObj, double zIndexOffset);

    @JavaScriptBody(args = {"jsObj", "opacity"}, body
            = "jsObj.setOpacity(opacity);")
    private static native void setOpacityInternal(Object jsObj, double opacity);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.update();")
    private static native void updateInternal(Object jsObj);

    //TODO: GeoJSON wrapper
    /*
     @JavaScriptBody(args = { "jsObj" }, body = 
     "return jsObj.toGeoJSON().toString();")
     private static native String toGeoJSONInternal(Object jsObj);
     */
    // ------- Popup methods -------------------------------------------
    /**
     * Binds a popup with a particular HTML content to a click on this marker.
     * You can also open the bound popup with the Marker <code>openPopup</code>
     * method.
     *
     * @param html HTML content of the popup
     * @return this
     */
    public Marker bindPopup(String html) {
        bindPopup1sInternal(jsObj, html);
        return this;
    }

    /**
     * Binds a popup to a click on this marker. You can also open the bound
     * popup with the Marker <code>openPopup</code> method.
     *
     * @param popup popup object
     * @return this
     */
    public Marker bindPopup(Popup popup) {
        bindPopup1pInternal(jsObj, popup.getJSObj());
        return this;
    }

    /**
     * Binds a popup with a particular popup configuration options to a click on
     * this marker. You can also open the bound popup with the Marker
     * <code>openPopup</code> method.
     *
     * @param popup popup object
     * @param options popup configuration options
     * @return this
     */
    public Marker bindPopup(Popup popup, PopupOptions options) {
        bindPopup2Internal(jsObj, popup.getJSObj(), options.getJSObj());
        return this;
    }

    /**
     * Unbinds the popup previously bound to the marker with
     * <code>bindPopup</code>.
     *
     * @return this
     */
    public Marker unbindPopup() {
        unbindPopupInternal(jsObj);
        return this;
    }

    /**
     * Opens the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    public Marker openPopup() {
        openPopupInternal(jsObj);
        return this;
    }

    /**
     * Returns the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    public Popup getPopup() {
        return new Popup(getPopupInternal(jsObj));
    }

    /**
     * Closes the bound popup of the marker if it's opened.
     *
     * @return this
     */
    public Marker closePopup() {
        closePopupInternal(jsObj);
        return this;
    }

    /**
     * Toggles the popup previously bound by the <code>bindPopup</code> method.
     *
     * @return this
     */
    public Marker togglePopup() {
        togglePopupInternal(jsObj);
        return this;
    }

    /**
     * Sets an HTML content of the popup of this marker.
     *
     * @param html HTML content of the popup
     * @return this
     */
    public Marker setPopupContent(String html) {
        setPopupContentInternal(jsObj, html);
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "html"}, body
            = "jsObj.bindPopup(html);")
    private static native void bindPopup1sInternal(Object jsObj, String html);

    @JavaScriptBody(args = {"jsObj", "popup"}, body
            = "jsObj.bindPopup(popup);")
    private static native void bindPopup1pInternal(Object jsObj, Object popup);

    @JavaScriptBody(args = {"jsObj", "popup", "options"}, body
            = "jsObj.bindPopup(popup, options);")
    private static native void bindPopup2Internal(Object jsObj, Object popup, Object options);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.unbindPopup();")
    private static native void unbindPopupInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.openPopup();")
    private static native void openPopupInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getPopup();")
    private static native Object getPopupInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.closePopup();")
    private static native void closePopupInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.togglePopup();")
    private static native void togglePopupInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj", "html"}, body
            = "jsObj.setPopupContent(html);")
    private static native void setPopupContentInternal(Object jsObj, String html);

}
