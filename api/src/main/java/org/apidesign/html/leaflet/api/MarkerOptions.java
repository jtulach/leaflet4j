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

/**
 * Options for configuring a {@link Marker}.
 */
public final class MarkerOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * Creates a MarkerOptions object
     */
    public MarkerOptions() {

    }

    /**
     * Sets icon option
     *
     * @param icon {@link Icon} class to use for rendering the marker.
     * @return updated options
     */
    public MarkerOptions setIcon(Icon icon) {
        options.setValue("icon", icon.getJSObj());
        return this;
    }

    /**
     * Sets clickable option
     *
     * @param clickable If <code>false</code>, the marker will not emit mouse
     * events and will act as a part of the underlying map.
     * @return updated options
     */
    public MarkerOptions setClickable(boolean clickable) {
        options.setValue("clickable", clickable);
        return this;
    }

    /**
     * Sets draggable option
     *
     * @param draggable Whether the marker is draggable with mouse/touch or not.
     * @return updated options
     */
    public MarkerOptions setDraggable(boolean draggable) {
        options.setValue("draggable", draggable);
        return this;
    }

    /**
     * Sets keyboard option
     *
     * @param keyboard Whether the marker can be tabbed to with a keyboard and
     * clicked by pressing enter.
     * @return updated options
     */
    public MarkerOptions setKeyboard(boolean keyboard) {
        options.setValue("keyboard", keyboard);
        return this;
    }

    /**
     * Sets title option
     *
     * @param title Text for the browser tooltip that appear on marker hover (no
     * tooltip by default).
     * @return updated options
     */
    public MarkerOptions setTitle(String title) {
        options.setValue("title", title);
        return this;
    }

    /**
     * Sets alt attribute option
     *
     * @param alt Text for the <code>alt</code> attribute of the icon image
     * (useful for accessibility).
     * @return updated options
     */
    public MarkerOptions setAlt(String alt) {
        options.setValue("alt", alt);
        return this;
    }

    /**
     * Sets zIndexOffset option
     *
     * @param zIndexOffset By default, marker images zIndex is set automatically
     * based on its latitude. Use this option if you want to put the marker on
     * top of all others (or below), specifying a high value like
     * <code>1000</code> (or high negative value, respectively).
     * @return updated options
     */
    public MarkerOptions setZIndexOffset(double zIndexOffset) {
        options.setValue("zIndexOffset", zIndexOffset);
        return this;
    }

    /**
     * Sets opacity option
     *
     * @param opacity The opacity of the marker.
     * @return updated options
     */
    public MarkerOptions setOpacity(double opacity) {
        options.setValue("opacity", opacity);
        return this;
    }

    /**
     * Sets riseOnHover option
     *
     * @param riseOnHover If <code>true</code>, the marker will get on top of
     * others when you hover the mouse over it.
     * @return updated options
     */
    public MarkerOptions setRiseOnHover(boolean riseOnHover) {
        options.setValue("riseOnHover", riseOnHover);
        return this;
    }

    /**
     * Sets riseOffset option
     *
     * @param riseOffset The z-index offset used for the
     * <code>riseOnHover</code> feature.
     * @return updated options
     */
    public MarkerOptions setRiseOffset(double riseOffset) {
        options.setValue("riseOffset", riseOffset);
        return this;
    }
}
