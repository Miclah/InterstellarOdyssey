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
        ArrayList<Button> buttons = new ArrayList<>();
        for (String name : names) {
            Button button = new Button(name);
            buttons.add(button);
        }
        return buttons;
    }

    public static VBox createVBox(ArrayList<Button> buttons) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(buttons);
        vbox.setPadding(new Insets(20));
        return vbox;
    }

    public static Scene createRoot(VBox vBox, String cssFile) {
        BorderPane root = new BorderPane();
        root.getStylesheets().add(Styler.class.getResource(cssFile).toExternalForm());
        root.setCenter(vBox);
        return new Scene(root, 800, 600);
    }
}
