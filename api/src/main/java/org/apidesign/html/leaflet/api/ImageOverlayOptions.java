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
 * Options for configuring a {@link ImageOverlay} layer.
 */
public final class ImageOverlayOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * Creates ImageOverlay options.
     */
    public ImageOverlayOptions() {

    }

    /**
     * Sets opacity option
     *
     * @param opacity The opacity of the image overlay.
     * @return updated options
     */
    public ImageOverlayOptions setOpacity(double opacity) {
        options.setValue("opacity", opacity);
        return this;
    }

    /**
     * Sets attribution option
     *
     * @param attribution The attribution text of the image overlay.
     * @return updated options
     */
    public ImageOverlayOptions setAttribution(String attribution) {
        options.setValue("attribution", attribution);
        return this;
    }

}
