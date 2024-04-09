package game.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        vbox.getChildren().addAll(buttons);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    public static Scene createScene(VBox vBox, String cssFile, boolean isMenu) {
        if (!isMenu) {
            vBox.setAlignment(Pos.CENTER);
            BorderPane root = new BorderPane();
            root.getStylesheets().add(Objects.requireNonNull(Styler.class.getResource(cssFile)).toExternalForm());
            root.setCenter(vBox);
            return new Scene(root, 800, 600);
        } else {
            vBox.getStylesheets().add(Objects.requireNonNull(Styler.class.getResource(cssFile)).toExternalForm());
            vBox.setAlignment(Pos.CENTER);
            return new Scene(vBox, 800, 600);
        }
    }

    public static ImageView createImageView(Image image, boolean preserveRatio) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(preserveRatio);
        return imageView;
    }

    public static Label createHeaderLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }

    public static void setStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interstellar Odyssey");
        primaryStage.show();
    }

    public static VBox createVBox(HBox hBox, Button button, int v, StackPane stackPane) {
        VBox vBox = new VBox(v);
        vBox.setAlignment(Pos.CENTER);
        if (isNotNull(stackPane)) {
            vBox.getChildren().addAll(stackPane, button);
        } else {
            vBox.getChildren().addAll(hBox, button);
        }
        return vBox;
    }

    public static HBox createHBox(VBox vBox1, VBox vBox2, int v) {
        HBox hBox = new HBox(v);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(vBox1,vBox2);
        return hBox;
    }

    private static boolean isNotNull(Object object) {
        return object != null;
    }
}
