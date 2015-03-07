/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015
 * Andreas Grimmer <a.grimmer@gmx.at>
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.apidesign.html.leaflet.api;

import org.apidesign.html.leaflet.api.implementation.Options;

/**
 *
 * @author Christoph Sperl
 */
public final class MapOptions {
    
    private final Options options = new Options();
    
    Object getJSObj() {
        return options.createJSObj();
    }
    
    public MapOptions setCenter(LatLng latLng) {
        options.setValue("center", latLng.getJSObj());
        return this;
    }
    
    public MapOptions setZoom(int zoom) {
        options.setValue("zoom", zoom);
        return this;
    }
    
    public MapOptions setMinZoom(int minZoom) {
        options.setValue("minZoom", minZoom);
        return this;
    }
    
    public MapOptions setMaxZoom(int maxZoom) {
        options.setValue("maxZoom", maxZoom);
        return this;
    }
}
