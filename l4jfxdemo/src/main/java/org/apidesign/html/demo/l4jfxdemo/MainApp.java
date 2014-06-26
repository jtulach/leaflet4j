/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2014 Jaroslav Tulach <jaroslav.tulach@apidesign.org>
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
package org.apidesign.html.demo.l4jfxdemo;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.java.html.boot.fx.FXBrowsers;
import org.apidesign.html.leaflet.api.LatLng;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        final MapView map = new MapView();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(map);

        // a regular JavaFX ListView 
        ListView<Address> listView = new ListView<>();
        listView.getItems().addAll(new Address("Toni", new LatLng(48.1322840, 11.5361690)),
                new Address("Jarda", new LatLng(50.0284060, 14.4934400)),
                new Address("JUG Münster", new LatLng(51.94906770000001, 7.613701100000071)));
        // we listen for the selected item and update the map accordingly
        // as a demo of how to interact between JavaFX and DukeScript
        listView.getSelectionModel().selectedItemProperty().addListener(
            (ObservableValue<? extends Address> ov, Address old_val, Address new_val) -> {
                FXBrowsers.runInBrowser(map.getWebView(), () -> {
                    map.getLeaflet().setView(new_val.getPos(), 20);
                    map.getLeaflet().openPopup(new_val.getPos(),"Here is "+new_val);
                });
            }
        );

        borderPane.setLeft(listView);
        Scene scene = new Scene(borderPane);

        stage.setTitle("JavaFX and DukeScript");
        stage.setScene(scene);
        stage.show();
    }

    private static class Address {

        private final String name;
        private final LatLng pos;

        public Address(String name, LatLng pos) {
            this.name = name;
            this.pos = pos;
        }

        public LatLng getPos() {
            return pos;
        }

        @Override
        public String toString() {
            return name; 
        }

        
        
    }

}
