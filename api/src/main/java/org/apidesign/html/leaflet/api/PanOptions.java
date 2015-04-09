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
 * Pan options.
 */
public final class PanOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * If <code>true</code>, panning will always be animated if possible. If
     * <code>false</code>, it will not animate panning, either resetting the map
     * view if panning more than a screen away, or just setting a new offset for
     * the map pane (except for `panBy` which always does the latter).
     *
     * @param animate The animate options
     * @return Returns a <code>PanOptions</code> object with the set options.
     */
    public PanOptions setAnimate(boolean animate) {
        options.setValue("animate", animate);
        return this;
    }

    /**
     * Duration of animated panning.
     *
     * @param duration The duration
     * @return Returns a <code>PanOptions</code> object with the set options.
     */
    public PanOptions setDuration(double duration) {
        options.setValue("duration", duration);
        return this;
    }

    /**
     * The curvature factor of panning animation easing (third parameter of the
     * Cubic Bezier curve). 1.0 means linear animation, the less the more bowed
     * the curve.
     *
     * @param easeLinearity The ease linearity
     * @return Returns a <code>PanOptions</code> object with the set options.
     */
    public PanOptions setEaseLinearity(double easeLinearity) {
        options.setValue("easeLinearity", easeLinearity);
        return this;
    }

    /**
     * If <code>true</code>, panning won't fire movestart event on start (used
     * internally for panning inertia).
     *
     * @param noMoveStart The no move start option
     * @return Returns a <code>PanOptions</code> object with the set options.
     */
    public PanOptions setNoMoveStart(boolean noMoveStart) {
        options.setValue("noMoveStart", noMoveStart);
        return this;
    }

}
