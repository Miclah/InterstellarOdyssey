package game.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class Styler {

    public static ArrayList<Button> createButtons(ArrayList<String> names) {
        if (isNull(names)) {
            ArrayList<Button> buttons = new ArrayList<>();
            for (String name : names) {
                Button button = new Button(name);
                buttons.add(button);
            }
            return buttons;
        }
        return null;
    }

    public static VBox createVBox(ArrayList<Button> buttons) {
        if (isNull(buttons)) {
            VBox vbox = new VBox(20);
            vbox.getChildren().addAll(buttons);
            vbox.setPadding(new Insets(20));
            return vbox;
        }
        return null;
    }

    public static Scene createRoot(VBox vBox, String cssFile) {
        if (isNull(vBox) && isNull(cssFile)) {
            BorderPane root = new BorderPane();
            root.getStylesheets().add(Styler.class.getResource(cssFile).toExternalForm());
            root.setCenter(vBox);
            return new Scene(root, 800, 600);
        }
        return null;
    }

    private static boolean isNull(Object input) {
        return input != null;
    }
}
