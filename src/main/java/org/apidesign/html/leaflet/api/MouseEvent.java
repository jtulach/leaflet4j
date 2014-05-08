package org.apidesign.html.leaflet.api;

/**
 *
 * @author Jaroslav Tulach
 */
public final class MouseEvent extends java.util.EventObject {
    private final LatLng where;
    
    MouseEvent(Leaflet src, LatLng where) {
        super(src);
        this.where = where;
    }
    
    public Leaflet getLeaflet() {
        return (Leaflet) getSource();
    }
    
    public LatLng getLatLng() {
        return where;
    }
    
    public static enum Type {
        CLICK, DBLCLICK, MOUSEDOWN, MOUSEUP, MOUSEOUT, MOUSEMOVE, CONTEXTMENU,
        PRECLICK
    }
}
