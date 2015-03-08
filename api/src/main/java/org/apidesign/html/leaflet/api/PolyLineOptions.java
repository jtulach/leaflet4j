/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apidesign.html.leaflet.api;

/**
 *
 * @author Stefan
 */
public class PolyLineOptions extends PathOptions {

    public PolyLineOptions() {
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
    
    @Override
    public PolyLineOptions setStroke(boolean stroke) {
        super.setStroke(stroke);
        return this;
    }
    
    @Override
    public PolyLineOptions setColor(String color) {
        super.setColor(color);
        return this;
    }
    
    @Override
    public PolyLineOptions setWeight(int weight) {
        super.setWeight(weight);
        return this;
    }
    
    @Override
    public PolyLineOptions setOpacity(double opacity) {
        super.setOpacity(opacity);
        return this;
    }
    
    @Override
    public PolyLineOptions setFill(boolean fill) {
        super.setFill(fill);
        return this;
    }
    
    @Override
    public PolyLineOptions setFillColor(String fillColor) {
        super.setFillColor(fillColor);
        return this;
    }
    
    @Override
    public PolyLineOptions setFillOpacity(double fillOpacity) {
        super.setFillOpacity(fillOpacity);
        return this;
    }
    
    @Override
    public PolyLineOptions setDashArray(String dashArray) {
        super.setDashArray(dashArray);
        return this;
    }
    
    @Override
    public PolyLineOptions setLineCap(String lineCap) {
        super.setLineCap(lineCap);
        return this;
    }
    
    @Override
    public PolyLineOptions setLineJoin(String lineJoin) {
        super.setLineJoin(lineJoin);
        return this;
    }
    
    @Override
    public PolyLineOptions setClickable(boolean clickable) {
        super.setClickable(clickable);
        return this;
    }
    
    @Override
    public PolyLineOptions setPointerEvents(String pointerEvents) {
        super.setPointerEvents(pointerEvents);
        return this;
    }
    
    @Override
    public PolyLineOptions setClassName(String className) {
        super.setClassName(className);
        return this;
    }
    
}
