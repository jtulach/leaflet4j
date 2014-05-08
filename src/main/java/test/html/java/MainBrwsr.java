package test.html.java;

public class MainBrwsr {
    static {
        try {
            Main.onPageLoad();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
