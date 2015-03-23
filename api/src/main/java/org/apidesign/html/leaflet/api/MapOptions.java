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
 * Options for configuring a {@link Map}.
 */
public final class MapOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    // Map State Options
    /**
     * Initial geographical center of the map.
     *
     * @param latLng The initial geographical center of the map.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setCenter(LatLng latLng) {
        options.setValue("center", latLng.getJSObj());
        return this;
    }

    /**
     * Initial map zoom.
     *
     * @param zoom The initial map zoom.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setZoom(int zoom) {
        options.setValue("zoom", zoom);
        return this;
    }

    /**
     * Layers that will be added to the map initially.
     *
     * @param layers The layers that will be added to the map initially.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setLayers(ILayer[] layers) {
        Object[] layersJS = new Object[layers.length];
        for (int q = 0; q < layers.length; q++) {
            layersJS[q] = layers[q].getJSObj();
        }
        options.setValue("layers", layersJS);
        return this;
    }

    /**
     * Minimum zoom level of the map. Overrides any minZoom set on map layers.
     *
     * @param minZoom The minimum zoom level of the map.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setMinZoom(int minZoom) {
        options.setValue("minZoom", minZoom);
        return this;
    }

    /**
     * Maximum zoom level of the map. This overrides any maxZoom set on map
     * layers.
     *
     * @param maxZoom The maximum zoom level of the map.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setMaxZoom(int maxZoom) {
        options.setValue("maxZoom", maxZoom);
        return this;
    }

    /**
     * When this option is set, the map restricts the view to the given
     * geographical bounds, bouncing the user back when he tries to pan outside
     * the view.
     *
     * @param bounds The geographical bounds.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setMaxBounds(LatLngBounds bounds) {
        options.setValue("maxBounds", bounds.getJSObj());
        return this;
    }

    /**
     * Coordinate Reference System to use. Don't change this if you're not sure
     * what it means.
     *
     * @param crs The coordinate Reference System.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setCRS(ICRS crs) {
        options.setValue("crs", crs.getJSObj());
        return this;
    }

    // Interaction Options
    /**
     * Whether the map is draggable with mouse/touch or not.
     *
     * @param dragging The draggable option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setDragging(boolean dragging) {
        options.setValue("dragging", dragging);
        return this;
    }

    /**
     * Whether the map can be zoomed by touch-dragging with two fingers.
     *
     * @param touchZoom The touch zoom option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setTouchZoom(boolean touchZoom) {
        options.setValue("touchZoom", touchZoom);
        return this;
    }

    /**
     * Whether the map can be zoomed by using the mouse wheel. If passed
     * 'center', it will zoom to the center of the view regardless of where the
     * mouse was.
     *
     * @param scrollWheelZoom The mouse wheel zoom option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setScrollWheelZoom(boolean scrollWheelZoom) {
        options.setValue("scrollWheelZoom", scrollWheelZoom);
        return this;
    }

    /**
     * Whether the map can be zoomed in by double clicking on it and zoomed out
     * by double clicking while holding shift. If passed 'center', double-click
     * zoom will zoom to the center of the view regardless of where the mouse
     * was.
     *
     * @param doubleClickZoom The double click zoom option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setDoubleClickZoom(boolean doubleClickZoom) {
        options.setValue("doubleClickZoom", doubleClickZoom);
        return this;
    }

    /**
     * Whether the map can be zoomed to a rectangular area specified by dragging
     * the mouse while pressing shift.
     *
     * @param boxZoom The box zoom option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setBoxZoom(boolean boxZoom) {
        options.setValue("boxZoom", boxZoom);
        return this;
    }

    /**
     * Enables mobile hacks for supporting instant taps (fixing 200ms click
     * delay on iOS/Android) and touch holds (fired as contextmenu events).
     *
     * @param tap The tap option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setTap(boolean tap) {
        options.setValue("tap", tap);
        return this;
    }

    /**
     * The max number of pixels a user can shift his finger during touch for it
     * to be considered a valid tap.
     *
     * @param tapTolerance The tap tolerance.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setTapTolerance(int tapTolerance) {
        options.setValue("tapTolerance", tapTolerance);
        return this;
    }

    /**
     * Whether the map automatically handles browser window resize to update
     * itself.
     *
     * @param trackResize The track resize option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setTrackResize(boolean trackResize) {
        options.setValue("trackResize", trackResize);
        return this;
    }

    /**
     * With this option enabled, the map tracks when you pan to another "copy"
     * of the world and seamlessly jumps to the original one so that all
     * overlays like markers and vector layers are still visible.
     *
     * @param worldCopyJump The world copy jump option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setWorldCopyJump(boolean worldCopyJump) {
        options.setValue("worldCopyJump", worldCopyJump);
        return this;
    }

    /**
     * Set it to <code>false</code> if you don't want popups to close when user
     * clicks the map.
     *
     * @param closePopupOnClick The close popup on click option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setClosePopupOnClick(boolean closePopupOnClick) {
        options.setValue("closePopupOnClick", closePopupOnClick);
        return this;
    }

    /**
     * Set it to <code>false</code> if you don't want the map to zoom beyond
     * min/max zoom and then bounce back when pinch-zooming.
     *
     * @param bounceAtZoomLimits The bounce at zoom limits option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setBounceAtZoomLimits(boolean bounceAtZoomLimits) {
        options.setValue("bounceAtZoomLimits", bounceAtZoomLimits);
        return this;
    }

    // Keyboard Navigation Options
    /**
     * Makes the map focusable and allows users to navigate the map with
     * keyboard arrows and +/- keys.
     *
     * @param keyboard The keyboard option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setKeyboard(boolean keyboard) {
        options.setValue("keyboard", keyboard);
        return this;
    }

    /**
     * Amount of pixels to pan when pressing an arrow key.
     *
     * @param keyboardPanOffset the keyboard pan offset.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setKeyboardPanOffset(int keyboardPanOffset) {
        options.setValue("keyboardPanOffset", keyboardPanOffset);
        return this;
    }

    /**
     * Number of zoom levels to change when pressing + or - key.
     *
     * @param keyboardZoomOffset The keyboard zoom offset.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setKeyboardZoomOffset(int keyboardZoomOffset) {
        options.setValue("keyboardZoomOffset", keyboardZoomOffset);
        return this;
    }

    // Panning Inertia Options
    /**
     * If enabled, panning of the map will have an inertia effect where the map
     * builds momentum while dragging and continues moving in the same direction
     * for some time. Feels especially nice on touch devices.
     *
     * @param inertia The inertia option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setInertia(boolean inertia) {
        options.setValue("inertia", inertia);
        return this;
    }

    /**
     * The rate with which the inertial movement slows down, in pixels/second^2.
     *
     * @param inertiaDeceleration The inertia deceleration.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setInertiaDeceleration(int inertiaDeceleration) {
        options.setValue("inertiaDeceleration", inertiaDeceleration);
        return this;
    }

    /**
     * Max speed of the inertial movement, in pixels/second.
     *
     * @param inertiaMaxSpeed The inertia max speed.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setInertiaMaxSpeed(int inertiaMaxSpeed) {
        options.setValue("inertiaMaxSpeed", inertiaMaxSpeed);
        return this;
    }

    /**
     * Number of milliseconds that should pass between stopping the movement and
     * releasing the mouse or touch to prevent inertial movement. 32 for touch
     * devices and 14 for the rest by default.
     *
     * @param inertiaThreshold The inertia threshold.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setInertiaThreshold(int inertiaThreshold) {
        options.setValue("inertiaThreshold", inertiaThreshold);
        return this;
    }

    // Control options
    /**
     * Whether the zoom control is added to the map by default.
     *
     * @param zoomControl The zoom control option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setZoomControl(boolean zoomControl) {
        options.setValue("zoomControl", zoomControl);
        return this;
    }

    /**
     * Whether the attribution control is added to the map by default.
     *
     * @param attributionControl The attribution control option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setAttributionControl(boolean attributionControl) {
        options.setValue("attributionControl", attributionControl);
        return this;
    }

    // Animation options
    /**
     * Whether the tile fade animation is enabled. By default it's enabled in
     * all browsers that support CSS3 Transitions except Android.
     *
     * @param fadeAnimation The fade animation option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setFadeAnimation(boolean fadeAnimation) {
        options.setValue("fadeAnimation", fadeAnimation);
        return this;
    }

    /**
     * Whether the tile zoom animation is enabled. By default it's enabled in
     * all browsers that support CSS3 Transitions except Android.
     *
     * @param zoomAnimation The zoom animation option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setZoomAnimation(boolean zoomAnimation) {
        options.setValue("zoomAnimation", zoomAnimation);
        return this;
    }

    /**
     * Won't animate zoom if the zoom difference exceeds this value.
     *
     * @param zoomAnimationThreshold The zoom animation threshold.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setZoomAnimationThreshold(int zoomAnimationThreshold) {
        options.setValue("zoomAnimationThreshold", zoomAnimationThreshold);
        return this;
    }

    /**
     * Whether markers animate their zoom with the zoom animation, if disabled
     * they will disappear for the length of the animation. By default it's
     * enabled in all browsers that support CSS3 Transitions except Android.
     *
     * @param markerZoomAnimation The marker zoom animation option.
     * @return Returns a <code>MapOptions</code> object with the set options.
     */
    public MapOptions setMarkerZoomAnimation(boolean markerZoomAnimation) {
        options.setValue("markerZoomAnimation", markerZoomAnimation);
        return this;
    }

}
