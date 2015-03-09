/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apidesign.html.leaflet.api;

import org.apidesign.html.leaflet.api.implementation.Options;

/**
 *
 * @author Stefan
 */
public final class PolyLineOptions {

    private final Options options = new Options();
    
    Object getJSObj() {
        return options.createJSObj();
    }
      
    public PolyLineOptions setSmoothFactor(double smoothFactor) {
        options.setValue("smoothFactor", smoothFactor);
        return this;
    }
        
    public PolyLineOptions setNoClip(boolean noClip) {
        options.setValue("noClip", noClip);
        return this;
    }
    
    
    // ------- Path Options -----------------------------------
    
    public PolyLineOptions setStroke(boolean stroke) {
        options.setValue("stroke", stroke);
        return this;
    }
    
    public PolyLineOptions setColor(String color) {
        options.setValue("color", color);
        return this;
    }
    
    public PolyLineOptions setWeight(int weight) {
        options.setValue("weight", weight);
        return this;
    }
    
    public PolyLineOptions setOpacity(double opacity) {
        options.setValue("opacity", opacity);
        return this;
    }
    
    public PolyLineOptions setFill(boolean fill) {
        options.setValue("fill", fill);
        return this;
    }
    
    public PolyLineOptions setFillColor(String fillColor) {
        options.setValue("fillColor", fillColor);
        return this;
    }
    
    public PolyLineOptions setFillOpacity(double fillOpacity) {
        options.setValue("fillOpacity", fillOpacity);
        return this;
    }
    
    public PolyLineOptions setDashArray(String dashArray) {
        options.setValue("dashArray", dashArray);
        return this;
    }
    
    public PolyLineOptions setLineCap(String lineCap) {
        options.setValue("lineCap", lineCap);
        return this;
    }
    
    public PolyLineOptions setLineJoin(String lineJoin) {
        options.setValue("lineJoin", lineJoin);
        return this;
    }
    
    public PolyLineOptions setClickable(boolean clickable) {
        options.setValue("clickable", clickable);
        return this;
    }
    
    public PolyLineOptions setPointerEvents(String pointerEvents) {
        options.setValue("pointerEvents", pointerEvents);
        return this;
    }
    
    public PolyLineOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }
    
}
