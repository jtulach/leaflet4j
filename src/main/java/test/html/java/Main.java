package test.html.java;

import net.java.html.boot.BrowserBuilder;


/** Bootstrap and initialization. */
public final class Main {
    private Main() {
    }
    
    /** Launches the browser */
    public static void main(String... args) throws Exception {
        BrowserBuilder.newBrowser().
            loadPage("pages/index.html").
            loadClass(Main.class).
            invoke("onPageLoad", args).
            showAndWait();
        System.exit(0);
    }
    
    /** Called when page is ready */
    public static void onPageLoad(String... args) throws Exception {
        Data d = new Data();
        d.setMessage("Hello World from HTML and Java!");
        d.applyBindings();
    }
}
