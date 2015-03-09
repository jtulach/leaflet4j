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
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public abstract class Path extends ILayer {

    protected Path(Object jsObj) {
        super(jsObj);
    }

    // ------- Methods -------------------------------------------
    public Path addTo(Map map) {
        addToInternal(jsObj, map.getJSObj());
        return this;
    }

    public Path setStyle(PathOptions options) {
        setStyleInternal(jsObj, options.getJSObj());
        return this;
    }

    public LatLngBounds getBounds() {
        return new LatLngBounds(getBoundsInternal(jsObj));
    }

    public Path bringToFront() {
        bringToFrontInternal(jsObj);
        return this;
    }

    public Path bringToBack() {
        bringToBackInternal(jsObj);
        return this;
    }

    public Path redraw() {
        redrawInternal(jsObj);
        return this;
    }

    @JavaScriptBody(args = {"jsObj", "map"}, body
            = "jsObj.addTo(map);")
    private static native void addToInternal(Object jsObj, Object map);

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

    @JavaScriptBody(args = {"jsObj"}, body
            = "jsObj.redraw();")
    private static native void redrawInternal(Object jsObj);

    // ------- Popup methods -------------------------------------------
    public Path bindPopup(String html) {
        bindPopup1sInternal(jsObj, html);
        return this;
    }

    public Path bindPopup(Popup popup) {
        bindPopup1pInternal(jsObj, popup.getJSObj());
        return this;
    }

    public Path bindPopup(Popup popup, PopupOptions options) {
        bindPopup2Internal(jsObj, popup.getJSObj(), options.getJSObj());
        return this;
    }

    public Path unbindPopup() {
        unbindPopupInternal(jsObj);
        return this;
    }

    public Path openPopup() {
        openPopupInternal(jsObj);
        return this;
    }

    public Path closePopup() {
        closePopupInternal(jsObj);
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
            = "jsObj.closePopup();")
    private static native void closePopupInternal(Object jsObj);

}
