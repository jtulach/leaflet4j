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

import net.java.html.js.JavaScriptBody;
import net.java.html.js.JavaScriptResource;


/**
 *
 * @author Stefan Wurzinger
 */
@JavaScriptResource("/org/apidesign/html/leaflet/api/leaflet-src.js")
public abstract class ICRS {

    protected final Object jsObj;
    
    protected ICRS(Object jsObj) {
        this.jsObj = jsObj;
    }
    
    Object getJSObj() {
        return jsObj;
    }
    
    
    // ------  Method wrappers -------------------------------------------
    
    public Point latLngToPoint(LatLng latlng, int zoom) {
        return new Point(latLngToPointInternal(jsObj, latlng.getJSObj(), zoom));
    }
    
    public LatLng pointToLatLng(Point point, int zoom) {
        return new LatLng(latLngToPointInternal(jsObj, point.getJSObj(), zoom));
    }
    
    public Point project(LatLng latlng) {
        return new Point(projectInternal(jsObj, latlng.getJSObj()));
    }
    
    public double scale(int zoom) {
        return scaleInternal(jsObj, zoom);
    }
    
    public Point getSize(int zoom) {
        return new Point(getSizeInternal(jsObj, zoom));
    }
    

    
    @JavaScriptBody(args = {"jsObj", "latlng", "zoom"},
            body = "return jsObj.latLngToPoint(latlng, zoom);")
    private static native Object latLngToPointInternal(Object jsObj, Object latlng, int zoom);
    
    @JavaScriptBody(args = {"jsObj", "point", "zoom"},
            body = "return jsObj.pointToLatLng(point, zoom);")
    private static native Object pointToLatLngInternal(Object jsObj, Object point, int zoom);
    
    @JavaScriptBody(args = {"jsObj", "latlng"},
            body = "return jsObj.project(latlng);")
    private static native Object projectInternal(Object jsObj, Object latlng);
    
    @JavaScriptBody(args = {"jsObj", "zoom"},
            body = "return jsObj.project(zoom);")
    private static native double scaleInternal(Object jsObj, int zoom);
     
    @JavaScriptBody(args = {"jsObj", "zoom"},
            body = "return jsObj.getSize(zoom);")
    private static native double getSizeInternal(Object jsObj, int zoom);
    
    
    // ------  Properties wrappers -------------------------------------------
    
    public IProjection getProjection() {
        return new IProjection(getProjectionInternal(jsObj));
    }
    
    public Transformation getTransformation() {
        return new Transformation(getTransformationInternal(jsObj));
    }
    
    public String getCode() {
        return getCodeInternal(jsObj);
    }
    
    
    
    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.projection;")
    private static native Object getProjectionInternal(Object jsObj);

    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.transformation;")
    private static native Object getTransformationInternal(Object jsObj);
    
    @JavaScriptBody(args = {"jsObj"},
            body = "return jsObj.code;")
    private static native String getCodeInternal(Object jsObj);
    
    
}
