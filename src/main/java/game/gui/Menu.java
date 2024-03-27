package game.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Menu {
    private Stage primaryStage;
    private Scene mainMenuScene;
    private Scene optionsScene;
    private Game gameScene;

    public Menu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.displayMain();
    }

    private void displayMain() {
        Image icon = new Image("file:src/main/resources/icon.png");
        this.primaryStage.getIcons().add(icon);

        ArrayList<String> names = new ArrayList<>();
        names.add("Start");
        names.add("Options");
        names.add("Exit");
        ArrayList<Button> buttons = Styler.createButtons(names);

        buttons.get(1).setOnAction(e -> this.displayOptions());
        buttons.get(2).setOnAction(e -> this.primaryStage.close());

        VBox vBox = Styler.createVBox(buttons);
        this.mainMenuScene = Styler.createRoot(vBox, "/Styles/Menu/menu.css");

        this.primaryStage.setTitle("Interstellar Odyssey");
        this.primaryStage.setScene(this.mainMenuScene);
        this.primaryStage.setResizable(false);
        this.primaryStage.setOnCloseRequest(e -> this.primaryStage.close());
        this.primaryStage.show();
    }

    private void displayOptions() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Settings");
        names.add("About");
        names.add("Back");
        ArrayList<Button> buttons = Styler.createButtons(names);

        buttons.get(2).setOnAction(e -> this.primaryStage.setScene(this.mainMenuScene));

        VBox vBox = Styler.createVBox(buttons);
        this.optionsScene = Styler.createRoot(vBox, "/Styles/Menu/menu.css");
        this.primaryStage.setScene(this.optionsScene);
    }

}

