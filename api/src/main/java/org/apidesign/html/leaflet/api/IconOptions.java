/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2015 Andreas Grimmer <a.grimmer@gmx.at>
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apidesign.html.leaflet.api;

/**
 * Options for configuring a {@link Icon}.
 */
public final class IconOptions {

    private final Options options = new Options();

    // Remark: iconUrl added to constructor, because this field is required
    /**
     * Creates an Icon options instance
     *
     * @param url The URL to the icon image (absolute or relative to your script
     * path).
     */
    public IconOptions(String url) {
        options.setValue("iconUrl", url);
    }

    Object getJSObj() {
        return options.createJSObj();
    }

    /**
     * Sets icon retina url option
     *
     * @param url The URL to a retina sized version of the icon image (absolute
     * or relative to your script path). Used for Retina screen devices.
     * @return updated options
     */
    public IconOptions setIconRetinaUrl(String url) {
        options.setValue("iconRetinaUrl", url);
        return this;
    }

    /**
     * Sets icon size option
     *
     * @param size Size of the icon image in pixels.
     * @return updated options
     */
    public IconOptions setIconSize(Point size) {
        options.setValue("iconSize", size.getJSObj());
        return this;
    }

    /**
     * Sets icon anchor option
     *
     * @param anchor The coordinates of the "tip" of the icon (relative to its
     * top left corner). The icon will be aligned so that this point is at the
     * marker's geographical location. Centered by default if size is specified,
     * also can be set in CSS with negative margins.
     * @return updated options
     */
    public IconOptions setIconAnchor(Point anchor) {
        options.setValue("iconAnchor", anchor.getJSObj());
        return this;
    }

    /**
     * Sets shadow url option
     *
     * @param url The URL to the icon shadow image. If not specified, no shadow
     * image will be created.
     * @return updated options
     */
    public IconOptions setShadowUrl(String url) {
        options.setValue("shadowUrl", url);
        return this;
    }

    /**
     * Sets shadow retina url option
     *
     * @param url The URL to the retina sized version of the icon shadow image.
     * If not specified, no shadow image will be created. Used for Retina screen
     * devices.
     * @return updated options
     */
    public IconOptions setShadowRetinaUrl(String url) {
        options.setValue("shadowRetinaUrl", url);
        return this;
    }

    /**
     * Sets shadow size option
     *
     * @param size Size of the shadow image in pixels.
     * @return updated options
     */
    public IconOptions setShadowSize(Point size) {
        options.setValue("shadowSize", size.getJSObj());
        return this;
    }

    /**
     * Sets shadow anchor option
     *
     * @param anchor The coordinates of the "tip" of the shadow (relative to its
     * top left corner) (the same as <code>iconAnchor</code> if not specified).
     * @return updated options
     */
    public IconOptions setShadowAnchor(Point anchor) {
        options.setValue("shadowAnchor", anchor.getJSObj());
        return this;
    }

    /**
     * Sets popup anchor option
     *
     * @param anchor The coordinates of the point from which popups will "open",
     * relative to the icon anchor.
     * @return updated options
     */
    public IconOptions setPopupAnchor(Point anchor) {
        options.setValue("popupAnchor", anchor.getJSObj());
        return this;
    }

    /**
     * Sets className option
     *
     * @param className A custom class name to assign to both icon and shadow
     * images. Empty by default.
     * @return updated options
     */
    public IconOptions setClassName(String className) {
        options.setValue("className", className);
        return this;
    }
}
