package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;

/**
 *
 * @author Jaroslav Tulach
 */
public class LeafPopup {
    Object obj;
    
    LeafPopup(Object obj) {
        this.obj = obj;
    }
    
    public void openPopup() {
        doOpen(obj);
    }
    
    @JavaScriptBody(args = { "obj" }, wait4js = false, body = "obj.openPopup();")
    private static native void doOpen(Object obj);
}
