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

import org.apidesign.html.leaflet.api.implementation.Options;

/**
 * Options for configuring a {@link Popup}.
 */
public final class PopupOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * Creates a PopupOptions configuration object
     */
    public PopupOptions() {

    }

    /**
     * Sets maxWidth option
     *
     * @param maxWidth Max width of the popup.
     * @return updated options
     */
    public PopupOptions setMaxWidth(int maxWidth) {
        options.setValue("maxWidth", maxWidth);
        return this;
    }

    /**
     * Sets minWidth option
     *
     * @param minWidth Min width of the popup.
     * @return updated options
     */
    public PopupOptions setMinWidth(int minWidth) {
        options.setValue("minWidth", minWidth);
        return this;
    }

    /**
     * Sets maxHeight option
     *
     * @param maxHeight If set, creates a scrollable container of the given
     * height inside a popup if its content exceeds it.
     * @return updated options
     */
    public PopupOptions setMaxHeight(int maxHeight) {
        options.setValue("maxHeight", maxHeight);
        return this;
    }

    /**
     * Sets autoPan option
     *
     * @param autoPan Set it to <code>false</code> if you don't want the map to
     * do panning animation to fit the opened popup.
     * @return updated options
     */
    public PopupOptions setAutoPan(boolean autoPan) {
        options.setValue("autoPan", autoPan);
        return this;
    }

    /**
     * Sets keepInView option
     *
     * @param keepInView Set it to <code>true</code> if you want to prevent
     * users from panning the popup off of the screen while it is open.
     * @return updated options
     */
    public PopupOptions setKeepInView(boolean keepInView) {
        options.setValue("keepInView", keepInView);
        return this;
    }

    /**
     * Sets closeButton option
     *
     * @param closeButton Controls the presense of a close button in the popup.
     * @return updated options
     */
    public PopupOptions setCloseButton(boolean closeButton) {
        options.setValue("closeButton", closeButton);
        return this;
    }

    /**
     * Sets offset option
     *
     * @param offset The offset of the popup position. Useful to control the
     * anchor of the popup when opening it on some overlays.
     * @return updated options
     */
    public PopupOptions setOffset(Point offset) {
        options.setValue("offset", offset.getJSObj());
        return this;
    }

    /**
     * Sets autoPanPaddingTopLeft option
     *
     * @param autoPanPaddingTopLeft The margin between the popup and the top
     * left corner of the map view after autopanning was performed.
     * @return updated options
     */
    public PopupOptions setAutoPanPaddingTopLeft(Point autoPanPaddingTopLeft) {
        options.setValue("autoPanPaddingTopLeft", autoPanPaddingTopLeft.getJSObj());
        return this;
    }

    /**
     * Sets autoPanPaddingBottomRight option
     *
     * @param autoPanPaddingBottomRight The margin between the popup and the
     * bottom right corner of the map view after autopanning was performed.
     * @return updated options
     */
    public PopupOptions setAutoPanPaddingBottomRight(Point autoPanPaddingBottomRight) {
        options.setValue("autoPanPaddingBottomRight", autoPanPaddingBottomRight.getJSObj());
        return this;
    }

    /**
     * Sets autoPanPadding option
     *
     * @param autoPanPadding Equivalent of setting both top left and bottom
     * right autopan padding to the same value.
     * @return updated options
     */
    public PopupOptions setAutoPanPadding(Point autoPanPadding) {
        options.setValue("autoPanPadding", autoPanPadding.getJSObj());
        return this;
    }

    /**
     * Sets zoomAnimation option
     *
     * @param zoomAnimation Whether to animate the popup on zoom. Disable it if
     * you have problems with Flash content inside popups.
     * @return updated options
     */
    public PopupOptions setZoomAnimation(boolean zoomAnimation) {
        options.setValue("zoomAnimation", zoomAnimation);
        return this;
    }

    /**
     * Sets closeOnClick option
     *
     * @param closeOnClick Set it to <code>false</code> if you want to override
     * the default behavior of the popup closing when user clicks the map (set
     * globally by the <code>Map</code> <code>closePopupOnClick</code> option).
     * @return updated options
     */
    public PopupOptions setCloseOnClick(boolean closeOnClick) {
        options.setValue("closeOnClick", closeOnClick);
        return this;
    }

    /**
     * Sets className option
     *
     * @param className A custom class name to assign to the popup.
     * @return updated options
     */
    public PopupOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }
}
