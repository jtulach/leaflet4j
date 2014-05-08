package org.apidesign.html.leaflet.api;

import net.java.html.js.JavaScriptBody;

/**
 *
 * @author Jaroslav Tulach
 */
public final class LeafPath {
    private final Object path;
    
    LeafPath(Object path) {
        this.path = path;
    }
    
    public LeafPopup bindPopup(String html) {
        return new LeafPopup(bind(path, html));
    }
    
    @JavaScriptBody(args = { "path", "content" }, body = "return path.bindPopup(content);")
    private static native Object bind(Object path, String content);
}
 