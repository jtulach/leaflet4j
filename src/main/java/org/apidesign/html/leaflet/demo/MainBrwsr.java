package org.apidesign.html.leaflet.demo;

public class MainBrwsr {
    static {
        try {
            Main.onPageLoad();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
