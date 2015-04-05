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
 * Options for configuring a {@link Path}.
 */
public final class PathOptions {

    private final Options options = new Options();

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * Creates a PathOptions object
     */
    public PathOptions() {

    }

    /**
     * Sets stroke option
     *
     * @param stroke Whether to draw stroke along the path. Set it to
     * <code>false</code> to disable borders on polygons or circles.
     * @return updated options
     */
    public PathOptions setStroke(boolean stroke) {
        options.setValue("stroke", stroke);
        return this;
    }

    /**
     * Sets color option
     *
     * @param color Stroke color.
     * @return updated options
     */
    public PathOptions setColor(String color) {
        options.setValue("color", color);
        return this;
    }

    /**
     * Sets weight option
     *
     * @param weight Stroke width in pixels.
     * @return updated options
     */
    public PathOptions setWeight(int weight) {
        options.setValue("weight", weight);
        return this;
    }

    /**
     * Sets opacity option
     *
     * @param opacity Stroke opacity.
     * @return updated options
     */
    public PathOptions setOpacity(double opacity) {
        options.setValue("opacity", opacity);
        return this;
    }

    /**
     * Sets fill option
     *
     * @param fill Whether to fill the path with color. Set it to
     * <code>false</code> to disable filling on polygons or circles.
     * @return updated options
     */
    public PathOptions setFill(boolean fill) {
        options.setValue("fill", fill);
        return this;
    }

    /**
     * Sets fillColor option
     *
     * @param fillColor Fill color.
     * @return updated options
     */
    public PathOptions setFillColor(String fillColor) {
        options.setValue("fillColor", fillColor);
        return this;
    }

    /**
     * Sets fillOpacity option
     *
     * @param fillOpacity Fill opacity.
     * @return updated options
     */
    public PathOptions setFillOpacity(double fillOpacity) {
        options.setValue("fillOpacity", fillOpacity);
        return this;
    }

    /**
     * Sets dashArray option
     *
     * @param dashArray A string that defines the stroke
     * <a href="https://developer.mozilla.org/en/SVG/Attribute/stroke-dasharray">dash
     * pattern</a>. Doesn't work on canvas-powered layers (e.g. Android 2).
     * @return updated options
     */
    public PathOptions setDashArray(String dashArray) {
        options.setValue("dashArray", dashArray);
        return this;
    }

    /**
     * Sets lineCap option
     *
     * @param lineCap A string that defines
     * <a href="https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/stroke-linecap">shape
     * to be used at the end</a> of the stroke.
     * @return updated options
     */
    public PathOptions setLineCap(String lineCap) {
        options.setValue("lineCap", lineCap);
        return this;
    }

    /**
     * Sets lineJoin option
     *
     * @param lineJoin A string that defines
     * <a href="https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/stroke-linejoin">shape
     * to be used at the corners</a> of the stroke.
     * @return updated options
     */
    public PathOptions setLineJoin(String lineJoin) {
        options.setValue("lineJoin", lineJoin);
        return this;
    }

    /**
     * Sets clickable option
     *
     * @param clickable If <code>false</code>, the vector will not emit mouse
     * events and will act as a part of the underlying map.
     * @return updated options
     */
    public PathOptions setClickable(boolean clickable) {
        options.setValue("clickable", clickable);
        return this;
    }

    /**
     * Sets pointerEvents option
     *
     * @param pointerEvents Sets the <code>pointer-events</code> attribute on
     * the path if SVG backend is used.
     * @return updated options
     */
    public PathOptions setPointerEvents(String pointerEvents) {
        options.setValue("pointerEvents", pointerEvents);
        return this;
    }

    /**
     * Sets className option
     *
     * @param className Custom class name set on an element.
     * @return updated options
     */
    public PathOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }

}
