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

import org.apidesign.html.leaflet.api.Options;
import org.apidesign.html.leaflet.api.basicTypes.Icon;

/**
 * Class representing the options of a marker
 * 
 * @author Christoph Sperl
 */
public class MarkerOptions extends Options {
    
    public void setIcon(Icon icon) {
        setValue("icon", icon);
    }
    
    public void setClickable(boolean clickable) {
        setValue("clickable", clickable);
    }
    
    public void setDraggable(boolean draggable) {
        setValue("draggable", draggable);
    }
    
    public void setKeyboard(boolean keyboard) {
        setValue("keyboard", keyboard);
    }
    
    public void setTitle(String title) {
        setValue("title", title);
    }
    
    public void setAlt(String alt) {
        setValue("alt", alt);
    }
    
    public void setZIndexOffset(double zIndexOffset) {
        setValue("zIndexOffset", zIndexOffset);
    }
    
    public void setOpacity(double opacity) {
        setValue("opacity", opacity);
    }
    
    public void setRiseOnHover(boolean riseOnHover) {
        setValue("riseOnHover", riseOnHover);
    }
    
    public void setRiseOffset(double riseOffset) {
        setValue("riseOffset", riseOffset);
    }
}
