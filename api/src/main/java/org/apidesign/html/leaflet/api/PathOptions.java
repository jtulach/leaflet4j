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
 * Class representing the options of a path
 * 
 * @author Stefan Wurzinger
 */
public abstract class PathOptions {
    
    private final Options options = new Options();
    
    Object getJSObj() {
        return options.createJSObj();
    }
    
    public PathOptions setStroke(boolean stroke) {
        options.setValue("stroke", stroke);
        return this;
    }
    
    public PathOptions setColor(String color) {
        options.setValue("color", color);
        return this;
    }
    
    public PathOptions setWeight(int weight) {
        options.setValue("weight", weight);
        return this;
    }
    
    public PathOptions setOpacity(double opacity) {
        options.setValue("opacity", opacity);
        return this;
    }
    
    public PathOptions setFill(boolean fill) {
        options.setValue("fill", fill);
        return this;
    }
    
    public PathOptions setFillColor(String fillColor) {
        options.setValue("fillColor", fillColor);
        return this;
    }
    
    public PathOptions setFillOpacity(double fillOpacity) {
        options.setValue("fillOpacity", fillOpacity);
        return this;
    }
    
    public PathOptions setDashArray(String dashArray) {
        options.setValue("dashArray", dashArray);
        return this;
    }
    
    public PathOptions setLineCap(String lineCap) {
        options.setValue("lineCap", lineCap);
        return this;
    }
    
    public PathOptions setLineJoin(String lineJoin) {
        options.setValue("lineJoin", lineJoin);
        return this;
    }
    
    public PathOptions setClickable(boolean clickable) {
        options.setValue("clickable", clickable);
        return this;
    }
    
    public PathOptions setPointerEvents(String pointerEvents) {
        options.setValue("pointerEvents", pointerEvents);
        return this;
    }
    
    public PathOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }

}
