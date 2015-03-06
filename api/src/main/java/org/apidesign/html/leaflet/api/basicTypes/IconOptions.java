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
package org.apidesign.html.leaflet.api.basicTypes;

import org.apidesign.html.leaflet.api.ToJS;
import org.apidesign.html.leaflet.api.implementation.Options;

/** 
 *
 * @author Christoph Sperl
 */
public final class IconOptions implements ToJS {
    
    private final Options options = new Options();
    
    // Remark: iconUrl added to constructor, because this field is required
    public IconOptions(String url) {
        options.setValue("iconUrl", url);
    }
    
    @Override
    public Object getJSObj() {
        return options.createJSObj();
    }
    
    public IconOptions setIconRetinaUrl(String url) {
        options.setValue("iconRetinaUrl", url);
        return this;
    }
    
    public IconOptions setIconSize(Point size) {
        options.setValue("iconSize", size);
        return this;
    }
    
    public IconOptions setIconAnchor(Point anchor) {
        options.setValue("iconAnchor", anchor);
        return this;
    }
    
    public IconOptions setShadowUrl(String url) {
        options.setValue("shadowUrl", url);
        return this;
    }
    
    public IconOptions setShadowRetinaUrl(String url) {
        options.setValue("shadowRetinaUrl", url);
        return this;
    }
    
    public IconOptions setShadowSize(Point size) {
        options.setValue("shadowSize", size);
        return this;
    }
    
    public IconOptions setShadowAnchor(Point anchor) {
        options.setValue("shadowAnchor", anchor);
        return this;
    }
    
    public IconOptions setPopupAnchor(Point anchor) {
        options.setValue("popupAnchor", anchor);
        return this;
    }
    
    public IconOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }
}
