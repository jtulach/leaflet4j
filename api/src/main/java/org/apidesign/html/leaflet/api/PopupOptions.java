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
 *
 * @author Andreas Grimmer
 */
public final class PopupOptions {
    
    private final Options options = new Options();
    
    public PopupOptions() {
        
    }
    
    Object getJSObj() {
        return options.createJSObj();
    }
    
    public PopupOptions setMaxWidth(int maxWidth) {
        options.setValue("maxWidth", maxWidth);
        return this;
    }
    
    public PopupOptions setMinWidth(int minWidth) {
        options.setValue("minWidth", minWidth);
        return this;
    }
    
    public PopupOptions setMaxHeight(int maxHeight) {
        options.setValue("maxHeight", maxHeight);
        return this;
    }
    
    public PopupOptions setAutoPan(boolean autoPan) {
        options.setValue("autoPan", autoPan);
        return this;
    }
    
    public PopupOptions setKeepInView(boolean keepInView) {
        options.setValue("keepInView", keepInView);
        return this;
    }
    
    public PopupOptions setCloseButton(boolean closeButton) {
        options.setValue("closeButton", closeButton);
        return this;
    }
    
    public PopupOptions setOffset(Point offset) {
        options.setValue("offset", offset.getJSObj());
        return this;
    }
    
    public PopupOptions setAutoPanPaddingTopLeft(Point autoPanPaddingTopLeft) {
        options.setValue("autoPanPaddingTopLeft", autoPanPaddingTopLeft.getJSObj());
        return this;
    }
    
    public PopupOptions setAutoPanPaddingBottomRight(Point autoPanPaddingBottomRight) {
        options.setValue("autoPanPaddingBottomRight", autoPanPaddingBottomRight.getJSObj());
        return this;
    }
    
    public PopupOptions setAutoPanPadding(Point autoPanPadding) {
        options.setValue("autoPanPadding", autoPanPadding.getJSObj());
        return this;
    }
    
    public PopupOptions setZoomAnimation(boolean zoomAnimation) {
        options.setValue("zoomAnimation", zoomAnimation);
        return this;
    }
    
    public PopupOptions setCloseOnClick(boolean closeOnClick) {
        options.setValue("closeOnClick", closeOnClick);
        return this;
    }
    
    public PopupOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }
}
