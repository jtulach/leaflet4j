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
 * Options for configuring a {@link TileLayer}.
 */
public final class TileLayerOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * Creates a <code>TileLayerOptions</code> object
     */
    public TileLayerOptions() {

    }
    /**
     * Sets the minZoom option.
     *
     * @param minZoom Minimum zoom number
     * @return updated options
     */
    public TileLayerOptions setMinZoom(int minZoom) {
        options.setValue("minZoom", minZoom);
        return this;
    }

    /**
     * Sets the maxZoom option
     * @param maxZoom Maximum zoom number
     * @return updated options
     */
    public TileLayerOptions setMaxZoom(int maxZoom) {
        options.setValue("maxZoom", maxZoom);
        return this;
    }

    /**
     * Sets the maxNativeZoom option
     * @param maxNativeZoom Maximum zoom number the tiles source has available. If it is specified, the tiles on all zoom levels higher than <code>maxNativeZoom</code> will be loaded from <code>maxZoom</code> level and auto-scaled.
     * @return updated options
     */
    public TileLayerOptions setMaxNativeZoom(int maxNativeZoom) {
        options.setValue("maxNativeZoom", maxNativeZoom);
        return this;
    }

    /**
     * Sets the tileSize option
     * @param tileSize Tile size (width and height in pixels, assuming tiles are square).
     * @return updated options
     */
    public TileLayerOptions setTileSize(int tileSize) {
        options.setValue("tileSize", tileSize);
        return this;
    }

    /**
     * Sets the subdomains option
     * @param subdomain Subdomains of the tile service. Each letter of the String is a subdomain name.
     * @return updated options
     */
    public TileLayerOptions setSubdomains(String subdomain) {
        options.setValue("subdomains", subdomain);
        return this;
    }

    /**
     * Sets the subdomains option
     * @param subdomains Subdomains of the tile service.
     * @return updated options
     */
    public TileLayerOptions setSubdomains(String[] subdomains) {
        options.setValue("subdomains", subdomains);
        return this;
    }

    /**
     * Sets the error tile url option
     * @param url URL to the tile image to show in place of the tile that failed to load.
     * @return updated options
     */
    public TileLayerOptions setErrorTileUrl(String url) {
        options.setValue("errorTileUrl", url);
        return this;
    }

    /**
     * Sets the attribution option
     * @param attribution The string used by the attribution control, describes the layer data.
     * @return updated options
     */
    public TileLayerOptions setAttribution(String attribution) {
        options.setValue("attribution", attribution);
        return this;
    }

    /**
     * Sets the tms option
     * @param tms If <code>true</code>, inverses Y axis numbering for tiles (turn this on for TMS services)
     * @return updated options
     */
    public TileLayerOptions setTms(boolean tms) {
        options.setValue("tms", tms);
        return this;
    }

    /**
     * Sets the continuousWorld option
     * @param continuousWorld If set to <code>true</code>, the tile coordinates won't be wrapped by world width (-180 to 180 longitude) or clamped to lie within world height (-90 to 90). Use this if you use Leaflet for maps that don't reflect the real world (e.g. game, indoor or photo maps).
     * @return updated options
     */
    public TileLayerOptions setContinuousWorld(boolean continuousWorld) {
        options.setValue("continuousWorld", continuousWorld);
        return this;
    }

    /**
     * Sets the noWrap option
     * @param noWrap If set to <code>true</code>, the tiles just won't load outside the world width (-180 to 180 longitude) instead of repeating.
     * @return updated options
     */
    public TileLayerOptions setNoWrap(boolean noWrap) {
        options.setValue("noWrap", noWrap);
        return this;
    }

    /**
     * Sets the zoomOffset option
     * @param zoomOffset The zoom number used in tile URLs will be offset with this value.
     * @return updated options
     */
    public TileLayerOptions setZoomOffset(int zoomOffset) {
        options.setValue("zoomOffset", zoomOffset);
        return this;
    }

    /**
     * Sets the zoomReverse option
     * @param zoomReverse If set to <code>true</code>, the zoom number used in tile URLs will be reversed (<code>maxZoom - zoom</code> instead of <code>zoom</code>)
     * @return updated options
     */
    public TileLayerOptions setZoomReverse(boolean zoomReverse) {
        options.setValue("zoomReverse", zoomReverse);
        return this;
    }

    /**
     * Sets the opacity option
     * @param opacity The opacity of the tile layer.
     * @return updated options
     */
    public TileLayerOptions setopacity(int opacity) {
        options.setValue("opacity", opacity);
        return this;
    }

    /**
     * Sets the zIndex option
     * @param zIndex The explicit zIndex of the tile layer. Not set by default.
     * @return updated options
     */
    public TileLayerOptions setZIndex(int zIndex) {
        options.setValue("zIndex", zIndex);
        return this;
    }

    /**
     * Sets the unload invisible tiles option
     * @param unload If <code>true</code>, all the tiles that are not visible after panning are removed (for better performance). <code>true</code> by default on mobile WebKit, otherwise <code>false</code>.
     * @return updated options
     */
    public TileLayerOptions setUnloadInvisibleTiles(boolean unload) {
        options.setValue("unloadInvisibleTiles", unload);
        return this;
    }

    /**
     * Sets the updateWhenIdle option
     * @param updateWhenIdle If <code>false</code>, new tiles are loaded during panning, otherwise only after it (for better performance). <code>true</code> by default on mobile WebKit, otherwise <code>false</code>.
     * @return updated options
     */
    public TileLayerOptions setUpdateWhenIdle(boolean updateWhenIdle) {
        options.setValue("updateWhenIdle", updateWhenIdle);
        return this;
    }

    /**
     * Sets the detectRetina option
     * @param detectRetina If <code>true</code> and user is on a retina display, it will request four tiles of half the specified size and a bigger zoom level in place of one to utilize the high resolution.
     * @return updated options
     */
    public TileLayerOptions setDetectRetina(boolean detectRetina) {
        options.setValue("detectRetina", detectRetina);
        return this;
    }

    /**
     * Sets the reuseTiles option
     * @param reuseTiles If <code>true</code>, all the tiles that are not visible after panning are placed in a reuse queue from which they will be fetched when new tiles become visible (as opposed to dynamically creating new ones). This will in theory keep memory usage low and eliminate the need for reserving new memory whenever a new tile is needed.
     * @return updated options
     */
    public TileLayerOptions setReuseTiles(boolean reuseTiles) {
        options.setValue("reuseTiles", reuseTiles);
        return this;
    }

    /**
     * Sets the bounds option
     * @param bounds When this option is set, the TileLayer only loads tiles that are in the given geographical bounds.
     * @return updated options
     */
    public TileLayerOptions setBounds(LatLngBounds bounds) {
        options.setValue("bounds", bounds.getJSObj());
        return this;
    }

    /**
     * Sets the id option
     * @param id (Unique) identifier for a layer.
     * @return updated options
     */
    public TileLayerOptions setId(String id) {
        options.setValue("id", id);
        return this;
    }
}
