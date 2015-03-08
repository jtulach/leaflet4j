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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apidesign.html.leaflet.api;

import org.apidesign.html.leaflet.api.implementation.Options;

/**
 *
 * @author Stefan Wurzinger
 */
public final class TileLayerCanvasOptions {

    private final Options options = new Options();
    
    Object getJSObj() {
        return options.createJSObj();
    }
    
    // ------ Tile Layer options ------------------------------
    //TODO: check whether duplicate these or allow deriving from the TileLayerOptions class
    
    public TileLayerCanvasOptions setMinZoom(int minZoom) {
        options.setValue("minZoom", minZoom);
        return this;
    }
    
    public TileLayerCanvasOptions setMaxZoom(int maxZoom) {
        options.setValue("maxZoom", maxZoom);
        return this;
    }
    
    public TileLayerCanvasOptions setMaxNativeZoom(int maxNativeZoom) {
        options.setValue("maxNativeZoom", maxNativeZoom);
        return this;
    }
    
    public TileLayerCanvasOptions setTileSize(int tileSize) {
        options.setValue("tileSize", tileSize);
        return this;
    }
    
    public TileLayerCanvasOptions setSubdomains(String subdomain) {
        options.setValue("subdomains", subdomain);
        return this;
    }
    
    public TileLayerCanvasOptions setSubdomains(String[] subdomains) {
        options.setValue("subdomains", subdomains);
        return this;
    }
    
    public TileLayerCanvasOptions setErrorTileUrl(String url) {
        options.setValue("errorTileUrl", url);
        return this;
    }
    
    public TileLayerCanvasOptions setAttribution(String attribution) {
        options.setValue("attribution", attribution);
        return this;
    }
    
    public TileLayerCanvasOptions setTms(boolean tms) {
        options.setValue("tms", tms);
        return this;
    }
    
    public TileLayerCanvasOptions setContinuousWorld(boolean continuousWorld) {
        options.setValue("continuousWorld", continuousWorld);
        return this;
    }
    
    public TileLayerCanvasOptions setNoWrap(boolean noWrap) {
        options.setValue("noWrap", noWrap);
        return this;
    }
    
    public TileLayerCanvasOptions setZoomOffset(int zoomOffset) {
        options.setValue("zoomOffset", zoomOffset);
        return this;
    }
    
    public TileLayerCanvasOptions setZoomReverse(boolean zoomReverse) {
        options.setValue("zoomReverse", zoomReverse);
        return this;
    }
    
    public TileLayerCanvasOptions setopacity(int opacity) {
        options.setValue("opacity", opacity);
        return this;
    }
    
    public TileLayerCanvasOptions setZIndex(int zIndex) {
        options.setValue("zIndex", zIndex);
        return this;
    }
    
    public TileLayerCanvasOptions setUnloadInvisibleTiles(boolean unload) {
        options.setValue("unloadInvisibleTiles", unload);
        return this;
    }
    
    public TileLayerCanvasOptions setUpdateWhenIdle(boolean updateWhenIdle) {
        options.setValue("updateWhenIdle", updateWhenIdle);
        return this;
    }
    
    public TileLayerCanvasOptions setDetectRetina(boolean detectRetina) {
        options.setValue("detectRetina", detectRetina);
        return this;
    }
    
    public TileLayerCanvasOptions setReuseTiles(boolean reuseTiles) {
        options.setValue("reuseTiles", reuseTiles);
        return this;
    }
    
    public TileLayerCanvasOptions setBounds(LatLngBounds bounds) {
        options.setValue("bounds", bounds.getJSObj());
        return this;
    }
    
    // ----- new for Canvas ----------------------------------
    
    
    public TileLayerCanvasOptions setAsync(boolean async) {
        options.setValue("async", async);
        return this;
    }
}
