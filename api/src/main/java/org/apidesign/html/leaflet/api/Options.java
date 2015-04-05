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

import java.util.HashMap;
import java.util.Map;
import net.java.html.js.JavaScriptBody;

/**
 * Class representing an configuration object
 *
 * @author Christoph Sperl
 */
final class Options {

    private final Map<String, Object> map = new HashMap<>();

    public void setValue(String name, Object value) {
        map.put(name, getObject(value));
    }

    private Object getObject(Object o) {
        if (o.getClass().isArray()) {
            Object[] arr = (Object[]) o;
            Object[] jsObjs = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                jsObjs[i] = getObject(arr[i]);
            }
            return buildArray(jsObjs);
        }

        return o;
    }

    public Object createJSObj() {
        String[] names = map.keySet().toArray(new String[map.size()]);
        Object[] values = new Object[names.length];
        for (int i = 0; i < names.length; i++) {
            values[i] = map.get(names[i]);
        }
        return createJSObj(names, values);
    }

    @JavaScriptBody(args = {"names", "values"}, body
            = "var ops = {};"
            + "for (var i = 0; i < names.length; i++) "
            + "  ops[names[i]] = values[i];"
            + " return ops;")
    private static native Object createJSObj(String[] names, Object[] values);

    @JavaScriptBody(args = {"jsObjs"}, body
            = "var arr = [];"
            + "for (var i = 0; i < jsObjs.length; i++) "
            + " arr.push(jsObjs[i]);"
            + " return arr;")
    private static native Object buildArray(Object[] jsObjs);

}
