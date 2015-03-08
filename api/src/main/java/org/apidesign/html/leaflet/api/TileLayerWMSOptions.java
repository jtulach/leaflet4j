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
 *
 * @author Stefan Wurzinger
 */
public final class TileLayerWMSOptions extends TileLayerOptions {

    // ------ Tile Layer options ------------------------------
    
    @Override
    public TileLayerWMSOptions setMinZoom(int minZoom) {
        super.setMinZoom(minZoom);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setMaxZoom(int maxZoom) {
        super.setMaxZoom(maxZoom);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setMaxNativeZoom(int maxNativeZoom) {
        super.setMaxNativeZoom(maxNativeZoom);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setTileSize(int tileSize) {
        super.setTileSize(tileSize);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setSubdomains(String subdomain) {
        super.setSubdomains(subdomain);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setSubdomains(String[] subdomains) {
        super.setSubdomains(subdomains);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setErrorTileUrl(String url) {
        super.setErrorTileUrl(url);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setAttribution(String attribution) {
        super.setAttribution(attribution);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setTms(boolean tms) {
        super.setTms(tms);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setContinuousWorld(boolean continuousWorld) {
        super.setContinuousWorld(continuousWorld);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setNoWrap(boolean noWrap) {
        super.setNoWrap(noWrap);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setZoomOffset(int zoomOffset) {
        super.setZoomOffset(zoomOffset);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setZoomReverse(boolean zoomReverse) {
        super.setZoomReverse(zoomReverse);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setopacity(int opacity) {
        super.setopacity(opacity);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setZIndex(int zIndex) {
        super.setZIndex(zIndex);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setUnloadInvisibleTiles(boolean unload) {
        super.setUnloadInvisibleTiles(unload);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setUpdateWhenIdle(boolean updateWhenIdle) {
        super.setUpdateWhenIdle(updateWhenIdle);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setDetectRetina(boolean detectRetina) {
        super.setDetectRetina(detectRetina);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setReuseTiles(boolean reuseTiles) {
        super.setReuseTiles(reuseTiles);
        return this;
    }
    
    @Override
    public TileLayerWMSOptions setBounds(LatLngBounds bounds) {
        super.setBounds(bounds);
        return this;
    }
    
    // ----- new for WMS ----------------------------------
    
    
    public TileLayerWMSOptions setLayers(String layers) {
        options.setValue("layers", layers);
        return this;
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
