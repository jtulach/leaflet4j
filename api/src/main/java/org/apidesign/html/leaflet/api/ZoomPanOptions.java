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
 * Combines the options of {@link PanOptions} and {@link ZoomOptions}.
 */
public final class ZoomPanOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * If <code>true</code>, the map view will be completely reset (without any
     * animations).
     *
     * @param reset The reset option.
     * @return Returns a <code>ZoomPanOptions</code> object with the set
     * options.
     */
    public ZoomPanOptions setReset(boolean reset) {
        options.setValue("reset", reset);
        return this;
    }

    /**
     * Sets the options for the panning (without the zoom change) if it occurs.
     *
     * @param pan The pan option.
     * @return Returns a <code>ZoomPanOptions</code> object with the set
     * options.
     */
    public ZoomPanOptions setPan(PanOptions pan) {
        options.setValue("pan", pan.getJSObj());
        return this;
    }

    /**
     * Sets the options for the zoom change if it occurs.
     *
     * @param zoom The zoom options.
     * @return Returns a <code>ZoomPanOptions</code> object with the set
     * options.
     */
    public ZoomPanOptions setZoom(ZoomOptions zoom) {
        options.setValue("zoom", zoom.getJSObj());
        return this;
    }

    /**
     * An equivalent of passing animate to both zoom and pan options (see
     * below).
     *
     * @param animate The animate option.
     * @return Returns a <code>ZoomPanOptions</code> object with the set
     * options.
     */
    public ZoomPanOptions setAnimate(boolean animate) {
        options.setValue("animate", animate);
        return this;
    }
}
