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

import org.apidesign.html.leaflet.api.Options;

/** 
 *
 * @author Christoph Sperl
 */
public class IconOptions extends Options {
    
    // Remark: required!
    // TODO should add to constructor??
    public void setIconUrl(String url) {
        setValue("iconUrl", url);
    }
    
    public void setIconRetinaUrl(String url) {
        setValue("iconRetinaUrl", url);
    }
    
    public void setIconSize(Point size) {
        setValue("iconSize", size);
    }
    
    public void setIconAnchor(Point anchor) {
        setValue("iconAnchor", anchor);
    }
    
    public void setShadowUrl(String url) {
        setValue("shadowUrl", url);
    }
    
    public void setShadowRetinaUrl(String url) {
        setValue("shadowRetinaUrl", url);
    }
    
    public void setShadowSize(Point size) {
        setValue("shadowSize", size);
    }
    
    public void setShadowAnchor(Point anchor) {
        setValue("shadowAnchor", anchor);
    }
    
    public void setPopupAnchor(Point anchor) {
        setValue("popupAnchor", anchor);
    }
    
    public void setClassName(String className) {
        setValue("className", className);
    }
}
