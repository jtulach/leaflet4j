package org.apidesign.html.leaflet.api;

import java.util.EventListener;

/**
 *
 * @author Jaroslav Tulach
 */
public interface MouseListener extends EventListener {
    public void onEvent(MouseEvent ev);
}
