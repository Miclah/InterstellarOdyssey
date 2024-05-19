package game.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Styler class used for creating UI elements of Menu
 */
public class Styler {

    /**
     * Create buttons array list.
     *
     * @param names the names
     * @return the array list
     */
    public static ArrayList<Button> createButtons(ArrayList<String> names) {
        ArrayList<Button> buttons = new ArrayList<>();
        for (String name : names) {
            Button button = new Button(name);
            buttons.add(button);
        }
        return buttons;
    }

    /**
     * Creates a vbox
     *
     * @param buttons the buttons
     * @return the v box
     */
    public static VBox createVBox(ArrayList<Button> buttons) {
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(buttons);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    /**
     * Create and returns a new scene
     *
     * @param vBox    the v box
     * @param cssFile the css file
     * @return the scene
     */
    public static Scene createScene(VBox vBox, String cssFile) {
        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        root.getStylesheets().add(Objects.requireNonNull(Styler.class.getResource(cssFile)).toExternalForm());

        return new Scene(root, 800, 600);
    }


    /**
     * Creates an imageview which is a class responsible for displaying an image onto the scene
     *
     * @param image         the image
     * @param preserveRatio the preserve ratio
     * @return the image view
     */
    public static ImageView createImageView(Image image, boolean preserveRatio) {
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(preserveRatio);
        return imageView;
    }

    /**
     * Create header label label.
     *
     * @param text       the text
     * @param styleClass the style class
     * @return the label
     */
    public static Label createHeaderLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }

    /**
     * Sets stage with a stage name
     *
     * @param primaryStage the primary stage
     * @param scene        the scene
     */
    public static void setStage(Stage primaryStage, Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.setTitle("Interstellar Odyssey");
        primaryStage.show();
    }

    /**
     * Overloaded method to create a vbox with more input parameters
     *
     * @param hBox      the h box
     * @param button    the button
     * @param v         the v
     * @param stackPane the stack pane
     * @return the v box
     */
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

    /**
     * Creates an Hbox
     *
     * @param vBox1 the v box 1
     * @param vBox2 the v box 2
     * @param v     the v
     * @return the h box
     */
    public static HBox createHBox(VBox vBox1, VBox vBox2, int v) {
        HBox hBox = new HBox(v);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(vBox1, vBox2);
        return hBox;
    }

    /**
     * Is not null boolean.
     *
     * @param object the object
     * @return the boolean
     */
    private static boolean isNotNull(Object object) {
        return object != null;
    }
}
