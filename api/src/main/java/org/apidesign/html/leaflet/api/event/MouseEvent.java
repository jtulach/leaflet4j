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
package org.apidesign.html.leaflet.api.event;

import org.apidesign.html.leaflet.api.LatLng;
import org.apidesign.html.leaflet.api.Point;

/**
 *
 * @author Andreas Grimmer
 */
public final class MouseEvent extends Event {

    private final LatLng latlng;
    private final Point layerPoint;
    private final Point containerPoint;

    public MouseEvent(final Object src, final String type, final LatLng latlng,
            final Point layerPoint, final Point containerPoint) {
        super(src, type);
        this.latlng = latlng;
        this.layerPoint = layerPoint;
        this.containerPoint = containerPoint;
    }

    public LatLng getLatLng() {
        return latlng;
    }

    public Point getLayerPoint() {
        return layerPoint;
    }

    public Point getContainerPoint() {
        return containerPoint;
    }

    public static enum Type {

        CLICK, DBLCLICK, MOUSEDOWN, MOUSEUP, MOUSEOVER, MOUSEOUT, MOUSEMOVE,
        CONTEXTMENU, PRECLICK;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
