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

import java.util.HashMap;
import java.util.Map;
import net.java.html.js.JavaScriptBody;

/**
 * Base class representing an options object
 * @author Christoph Sperl
 */
public class Options implements JSWrapper {
    
    private final Map<String, String> primitivePairs = new HashMap<>();
    private final Map<String, Object> referencePairs = new HashMap<>();
    
    public final void setValue(String name, Object value) {
        // put or override value in the map
        
        if (value instanceof JSWrapper) {
            // add JS reference
            Object jsObj = ((JSWrapper)value).getJSObj();
            referencePairs.put(name, jsObj);
        }
        else {
            // assume to be a primitive
            primitivePairs.put(name, value.toString());
        }
    }
    
    @Override
    public final Object getJSObj() {
        String[] referenceNames = referencePairs.keySet().toArray(new String[referencePairs.size()]);
        Object[] referenceVals = new Object[referenceNames.length];
        for (int i = 0; i < referenceNames.length; i++) {
            referenceVals[i] = referencePairs.get(referenceNames[i]);
        }
        String[] primitiveNames = primitivePairs.keySet().toArray(new String[primitivePairs.size()]);
        String[] primitiveVals = new String[primitiveNames.length];
        for (int i = 0; i < primitiveNames.length; i++) {
            primitiveVals[i] = primitivePairs.get(primitiveNames[i]);
        }
        return createJSObj(referenceNames, referenceVals, primitiveNames, primitiveVals);
    }
    
    @JavaScriptBody(args = { "referenceNames", "referenceVals", "primitiveNames", "primitiveVals" }, body =
        "var ops = {};" +
        "for (var i = 0; i < referenceNames.length; i++) " +
        "  ops[referenceNames[i]] = referenceVals[i];" +
        "for (var i = 0; i < primitiveNames.length; i++) " +
        "  ops[primitiveNames[i]] = eval(primitiveVals[i]);" +
        " return ops;")
    private static native Object createJSObj(String[] referenceNames, Object[] referenceVals, String[] primitiveNames, String[] primitiveVals);
    
}
