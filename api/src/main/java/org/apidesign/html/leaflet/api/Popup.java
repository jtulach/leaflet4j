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

/**
 * Used to open popups in certain places of the map. Use
 * <code>Map.openPopup</code> to open popups while making sure that only one
 * popup is open at one time (recommended for usability), or use
 * <code>Map.addLayer</code> to open as many as you want.
 */

public final class Popup extends ILayer {
    static {
        Options.initJS();
        registerLayerType("L.Popup", new Function<Object, ILayer>() {
            @Override
            public ILayer apply(Object obj) {
                return new Popup(obj);
            }
        });
    }

    Popup(Object jsObj) {
        super(jsObj);
    }

    /**
     * Instantiates a Popup object given an optional options object that
     * describes its appearance and location and an optional source object that
     * is used to tag the popup with a reference to the ILayer to which it
     * refers.
     */
    public Popup() {
        super(create(null, null));
    }

    /**
     * Instantiates a Popup object given an options object that describes its
     * appearance and location.
     *
     * @param options options object that describes popup's appearance and
     * location
     */
    public Popup(PopupOptions options) {
        super(create(options.getJSObj(), null));
    }

    /**
     * Instantiates a Popup object given an optional options object that
     * describes its appearance and location and an optional source object that
     * is used to tag the popup with a reference to the ILayer to which it
     * refers.
     *
     * @param options options object that describes popup's appearance and
     * location
     * @param source Source object that is used to tag the popup with a
     * reference to the ILayer to which it refers
     */
    public Popup(PopupOptions options, ILayer source) {
        super(create(options.getJSObj(), source.getJSObj()));
    }

    /**
     * Instantiates a Popup object given an source object that is used to tag
     * the popup with a reference to the ILayer to which it refers.
     *
     * @param source Source object that is used to tag the popup with a
     * reference to the ILayer to which it refers
     */
    public Popup(ILayer source) {
        super(create(null, source.getJSObj()));
    }

    @JavaScriptBody(args = {"options", "source"}, body
            = "return L.popup(options, source);")
    private static native Object create(Object options, Object source);

    /**
     * Adds the popup to the map.
     *
     * @param map The map
     * @return this
     */
    public Popup addTo(Map map) {
        addTo(jsObj, map.getJSObj());
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "map"}, wait4js = false, body
            = "jsObj.addTo(map);")
    private static native void addTo(Object jsObj, Object map);

    /**
     * Adds the popup to the map and closes the previous one. The same as
     * <code>map.openPopup</code>.
     *
     * @param map The map
     * @return this
     */
    public Popup openOn(Map map) {
        openOn(jsObj, map.getJSObj());
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "map"}, wait4js = false, body
            = "jsObj.openOn(map);")
    private static native void openOn(Object jsObj, Object map);

    /**
     * Sets the geographical point where the popup will open.
     *
     * @param latLng The LatLng location
     * @return this
     */
    public Popup setLatLng(LatLng latLng) {
        setLatLng(jsObj, latLng.getJSObj());
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "latlng"}, wait4js = false, body
            = "jsObj.setLatLng(latlng);")
    private static native void setLatLng(Object jsObj, Object latlng);

    /**
     * Returns the geographical point of popup.
     *
     * @return The LatLng location where the popup will open
     */
    public LatLng getLatLng() {
        return new LatLng(getLatLng(jsObj));
    }

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getLatLng();")
    private static native Object getLatLng(Object jsObj);

    /**
     * Sets the HTML content of the popup.
     *
     * @param htmlContent HTML content of the popup
     * @return this
     */
    public Popup setContent(String htmlContent) {
        setContent(jsObj, htmlContent);
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "htmlContent"}, wait4js = false, body
            = "jsObj.setContent(htmlContent);")
    private static native void setContent(Object jsObj, String htmlContent);

    /**
     * Returns the content of the popup.
     *
     * @return Popup content as HTML string
     */
    public String getContent() {
        return getContent(jsObj);
    }

    @JavaScriptBody(args = {"jsObj"}, body
            = "return jsObj.getContent();")
    private static native String getContent(Object jsObj);

    /**
     * Updates the popup content, layout and position. Useful for updating the
     * popup after something inside changed, e.g. image loaded.
     *
     * @return this
     */
    public Popup update() {
        update(jsObj);
        return this;
    }

    @JavaScriptBody(args = {"jsObj"}, wait4js = false, body
            = "jsObj.update();")
    private static native void update(Object jsObj);

}
