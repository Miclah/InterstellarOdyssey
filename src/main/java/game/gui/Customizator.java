package game.gui;

import game.util.Styler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Customizator {
    private final Stage primaryStage;
    private Scene appereanceScene;
    private Scene playerInfoScene;
    private int skinChoice;

    public Customizator(Stage primaryStage, Scene mainMenuScene) {
        this.primaryStage = primaryStage;
        this.skinSelector(mainMenuScene);
    }

    private void skinSelector(Scene mainMenuScene) {
        StackPane skin1 = this.createFrame("Player/Player1/1.png");
        StackPane skin2 = this.createFrame("Player/Player1/2.png");

        Button skinChooser1 = new Button("Skin 1");
        Button skinChooser2 = new Button("Skin 2");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> this.primaryStage.setScene(mainMenuScene));

        VBox skinBox1 = this.createSkinBox(skin1, skinChooser1, 10);
        VBox skinBox2 = this.createSkinBox(skin2, skinChooser2, 10);

        HBox hBox = this.createHBox(skinBox1, skinBox2, 40);
        VBox.setMargin(hBox, new Insets(20));

        VBox vbox1 = this.createVBox(hBox, backButton, 10);
        VBox vbox2 = this.createVBox(hBox, backButton, 10);
        HBox mainHBox = this.createHBox(vbox1, vbox2, 0);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().add(mainHBox);
        mainLayout.setAlignment(Pos.CENTER);
        this.appereanceScene = new Scene(mainLayout, 800, 600);
        this.appereanceScene.getStylesheets().add("Styles/Menu/customizer.css");
        Styler.setStage(this.primaryStage, this.appereanceScene);

        skinChooser1.setOnAction(e -> {
            this.skinChoice = 1;
        });
        skinChooser2.setOnAction(e -> {
            this.skinChoice = 2;
        });
    }

    private StackPane createFrame(String imagePath) {
        int frameSize = 350;
        int imageSize = 305;

        Image image = new Image(imagePath, imageSize, imageSize, false, false);
        ImageView imageView = Styler.createImageView(image, true);

        Image frame = new Image("other/frame.png", frameSize, frameSize, false, false);
        ImageView frameView = Styler.createImageView(frame, true);

        StackPane stackPane = new StackPane();
        StackPane.setAlignment(imageView, Pos.CENTER);
        stackPane.getChildren().addAll(imageView, frameView);
        return stackPane;
    }

    private VBox createSkinBox(StackPane stackPane, Button button, int v) {
        VBox vBox = new VBox(v);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(stackPane, button);
        return vBox;
    }

    private VBox createVBox(HBox hBox, Button button, int v) {
        VBox vBox = new VBox(v);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox, button);
        return vBox;
    }

    private HBox createHBox(VBox vBox1, VBox vBox2, int v) {
        HBox hBox = new HBox(v);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(vBox1,vBox2);
        return hBox;
    }
}
