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
package org.apidesign.html.leaflet.api.rasterLayers;

import org.apidesign.html.leaflet.api.Options;
import org.apidesign.html.leaflet.api.basicTypes.LatLngBounds;

/**
 *
 * @author Christoph Sperl
 */
public class TileLayerOptions extends Options {
    
    public void setMinZoom(int minZoom) {
        setValue("minZoom", minZoom);
    }
    
    public void setMaxZoom(int maxZoom) {
        setValue("maxZoom", maxZoom);
    }
    
    public void setMaxNativeZoom(int maxNativeZoom) {
        setValue("maxNativeZoom", maxNativeZoom);
    }
    
    public void setTileSize(int tileSize) {
        setValue("tileSize", tileSize);
    }
    
    public void setSubdomains(String subdomain) {
        setValue("subdomains", subdomain);
    }
    
    public void setSubdomains(String[] subdomains) {
        setValue("subdomains", subdomains);
    }
    
    public void setErrorTileUrl(String url) {
        setValue("errorTileUrl", url);
    }
    
    public void setAttribution(String attribution) {
        setValue("attribution", attribution);
    }
    
    public void setTms(boolean tms) {
        setValue("tms", tms);
    }
    
    public void setContinuousWorld(boolean continuousWorld) {
        setValue("continuousWorld", continuousWorld);
    }
    
    public void setNoWrap(boolean noWrap) {
        setValue("noWrap", noWrap);
    }
    
    public void setZoomOffset(int zoomOffset) {
        setValue("zoomOffset", zoomOffset);
    }
    
    public void setZoomReverse(boolean zoomReverse) {
        setValue("zoomReverse", zoomReverse);
    }
    
    public void setopacity(int opacity) {
        setValue("opacity", opacity);
    }
    
    public void setZIndex(int zIndex) {
        setValue("zIndex", zIndex);
    }
    
    public void setUnloadInvisibleTiles(boolean unload) {
        setValue("unloadInvisibleTiles", unload);
    }
    
    public void setUpdateWhenIdle(boolean updateWhenIdle) {
        setValue("updateWhenIdle", updateWhenIdle);
    }
    
    public void setDetectRetina(boolean detectRetina) {
        setValue("detectRetina", detectRetina);
    }
    
    public void setReuseTiles(boolean reuseTiles) {
        setValue("reuseTiles", reuseTiles);
    }
    
    public void setBounds(LatLngBounds bounds) {
        setValue("bounds", bounds);
    }
    
    
}
