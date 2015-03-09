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
 *
 * @author Stefan Wurzinger
 */
public final class TileLayerWMSOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    // ------ Tile Layer options ------------------------------
    public TileLayerWMSOptions setMinZoom(int minZoom) {
        options.setValue("minZoom", minZoom);
        return this;
    }

    public TileLayerWMSOptions setMaxZoom(int maxZoom) {
        options.setValue("maxZoom", maxZoom);
        return this;
    }

    public TileLayerWMSOptions setMaxNativeZoom(int maxNativeZoom) {
        options.setValue("maxNativeZoom", maxNativeZoom);
        return this;
    }

    public TileLayerWMSOptions setTileSize(int tileSize) {
        options.setValue("tileSize", tileSize);
        return this;
    }

    public TileLayerWMSOptions setSubdomains(String subdomain) {
        options.setValue("subdomains", subdomain);
        return this;
    }

    public TileLayerWMSOptions setSubdomains(String[] subdomains) {
        options.setValue("subdomains", subdomains);
        return this;
    }

    public TileLayerWMSOptions setErrorTileUrl(String url) {
        options.setValue("errorTileUrl", url);
        return this;
    }

    public TileLayerWMSOptions setAttribution(String attribution) {
        options.setValue("attribution", attribution);
        return this;
    }

    public TileLayerWMSOptions setTms(boolean tms) {
        options.setValue("tms", tms);
        return this;
    }

    public TileLayerWMSOptions setContinuousWorld(boolean continuousWorld) {
        options.setValue("continuousWorld", continuousWorld);
        return this;
    }

    public TileLayerWMSOptions setNoWrap(boolean noWrap) {
        options.setValue("noWrap", noWrap);
        return this;
    }

    public TileLayerWMSOptions setZoomOffset(int zoomOffset) {
        options.setValue("zoomOffset", zoomOffset);
        return this;
    }

    public TileLayerWMSOptions setZoomReverse(boolean zoomReverse) {
        options.setValue("zoomReverse", zoomReverse);
        return this;
    }

    public TileLayerWMSOptions setopacity(int opacity) {
        options.setValue("opacity", opacity);
        return this;
    }

    public TileLayerWMSOptions setZIndex(int zIndex) {
        options.setValue("zIndex", zIndex);
        return this;
    }

    public TileLayerWMSOptions setUnloadInvisibleTiles(boolean unload) {
        options.setValue("unloadInvisibleTiles", unload);
        return this;
    }

    public TileLayerWMSOptions setUpdateWhenIdle(boolean updateWhenIdle) {
        options.setValue("updateWhenIdle", updateWhenIdle);
        return this;
    }

    public TileLayerWMSOptions setDetectRetina(boolean detectRetina) {
        options.setValue("detectRetina", detectRetina);
        return this;
    }

    public TileLayerWMSOptions setReuseTiles(boolean reuseTiles) {
        options.setValue("reuseTiles", reuseTiles);
        return this;
    }

    public TileLayerWMSOptions setBounds(LatLngBounds bounds) {
        options.setValue("bounds", bounds.getJSObj());
        return this;
    }

    public TileLayerWMSOptions setId(String id) {
        options.setValue("id", id);
        return this;
    }

    // ----- new for WMS ----------------------------------
    // Remark: added layers to constructor, because this field is required
    public TileLayerWMSOptions(String layers) {
        options.setValue("layers", layers);
    }

    public TileLayerWMSOptions setStyles(String styles) {
        options.setValue("styles", styles);
        return this;
    }

    public TileLayerWMSOptions setFormat(String format) {
        options.setValue("format", format);
        return this;
    }

    public TileLayerWMSOptions setTransparent(boolean transparent) {
        options.setValue("transparent", transparent);
        return this;
    }

    public TileLayerWMSOptions setVersion(String version) {
        options.setValue("version", version);
        return this;
    }

    public TileLayerWMSOptions setCRS(ICRS crs) {
        options.setValue("crs", crs);
        return this;
    }
}
