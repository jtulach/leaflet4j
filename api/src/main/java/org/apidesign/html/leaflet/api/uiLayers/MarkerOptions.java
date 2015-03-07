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
package org.apidesign.html.leaflet.api.uiLayers;

import org.apidesign.html.leaflet.api.implementation.Options;
import org.apidesign.html.leaflet.api.basicTypes.Icon;

/**
 * Class representing the options of a marker
 * 
 * @author Christoph Sperl
 */
public final class MarkerOptions {
    
    private final Options options = new Options();
    
    public Object getJSObj() {
        return options.createJSObj();
    }
    
    public MarkerOptions setIcon(Icon icon) {
        options.setValue("icon", icon.getJSObj());
        return this;
    }
    
    public MarkerOptions setClickable(boolean clickable) {
        options.setValue("clickable", clickable);
        return this;
    }
    
    public MarkerOptions setDraggable(boolean draggable) {
        options.setValue("draggable", draggable);
        return this;
    }
    
    public MarkerOptions setKeyboard(boolean keyboard) {
        options.setValue("keyboard", keyboard);
        return this;
    }
    
    public MarkerOptions setTitle(String title) {
        options.setValue("title", title);
        return this;
    }
    
    public MarkerOptions setAlt(String alt) {
        options.setValue("alt", alt);
        return this;
    }
    
    public MarkerOptions setZIndexOffset(double zIndexOffset) {
        options.setValue("zIndexOffset", zIndexOffset);
        return this;
    }
    
    public MarkerOptions setOpacity(double opacity) {
        options.setValue("opacity", opacity);
        return this;
    }
    
    public MarkerOptions setRiseOnHover(boolean riseOnHover) {
        options.setValue("riseOnHover", riseOnHover);
        return this;
    }
    
    public MarkerOptions setRiseOffset(double riseOffset) {
        options.setValue("riseOffset", riseOffset);
        return this;
    }
}
