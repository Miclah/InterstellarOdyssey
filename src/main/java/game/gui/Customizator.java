package game.gui;

import game.util.Styler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;

public class Customizator {
    private final Stage primaryStage;
    private Scene appereanceScene;
    private Scene playerInfoScene;
    private int skinChoice;
    private String name;
    private final String PATH_TO_CSS = "/Styles/Menu/customizer.css";

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

        VBox skinBox1 = Styler.createVBox(null, skinChooser1, 10, skin1);
        VBox skinBox2 = Styler.createVBox(null, skinChooser2, 10, skin2);

        HBox hBox = Styler.createHBox(skinBox1, skinBox2, 40);
        VBox.setMargin(hBox, new Insets(20));

        VBox vbox1 = Styler.createVBox(hBox, backButton, 10, null);
        VBox vbox2 = Styler.createVBox(hBox, backButton, 10, null);
        HBox mainHBox = Styler.createHBox(vbox1, vbox2, 0);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().add(mainHBox);
        this.appereanceScene = Styler.createScene(mainLayout, this.PATH_TO_CSS, false);
        Styler.setStage(this.primaryStage, this.appereanceScene);

        skinChooser1.setOnAction(e -> {
            this.skinChoice = 1;
            this.playerInfo();
        });
        skinChooser2.setOnAction(e -> {
            this.skinChoice = 2;
            this.playerInfo();
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

    public void playerInfo() {
        Label playerInfoLabel = Styler.createHeaderLabel("Player Info", "");

        Label nameLabel = Styler.createHeaderLabel("Name:", "");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Input your name here");
        nameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                nameInput.setText("");
                this.name = String.valueOf(nameInput.getCharacters());
            }
        });
        HBox nameBox = new HBox(10, nameLabel, nameInput);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        Label backgroundLabel = Styler.createHeaderLabel("Character Background:", "");
        HBox streetBox = new HBox(10, new Text("Grown up on the streets (+1 Strength, +3 Agility):"), new CheckBox());
        HBox richBox = new HBox(10, new Text("Grown up in a rich family (+3 Charisma, +1 Luck):"), new CheckBox());
        HBox workshopBox = new HBox(10, new Text("Grown up in a workshop (+4 Intelligence):"), new CheckBox());
        HBox luckBox = new HBox(10, new Text("Born under a lucky star (+3 Luck, +1 Charisma):"), new CheckBox());
        HBox warriorBox = new HBox(10, new Text("Trained as a warrior (+3 Strength, +1 Agility):"), new CheckBox());
        VBox backgroundBox = new VBox(10, backgroundLabel, streetBox, richBox, workshopBox, luckBox, warriorBox);
        backgroundBox.setAlignment(Pos.CENTER_LEFT);

        Label traitsLabel = Styler.createHeaderLabel("Character Traits:", "");
        VBox traitsBox = new VBox(10);
        for (String trait : Arrays.asList("Intelligence", "Charisma", "Agility", "Luck", "Strength")) {
            HBox traitRow = new HBox(10);
            traitRow.setAlignment(Pos.CENTER_LEFT);
            Label traitLabel = new Label(trait + ":");
            Slider traitSlider = new Slider(1, 10, 5);
            traitSlider.setShowTickLabels(true);
            traitSlider.setMajorTickUnit(1);
            traitSlider.setBlockIncrement(1);
            traitSlider.setSnapToTicks(true);
            Label sliderValueLabel = new Label("5");
            traitSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double snappedValue = Math.round(newValue.doubleValue());
                traitSlider.setValue(snappedValue);
                sliderValueLabel.setText(String.valueOf((int) snappedValue));
            });
            traitRow.getChildren().addAll(traitLabel, traitSlider, sliderValueLabel);
            traitsBox.getChildren().add(traitRow);
        }

        VBox leftVBox = new VBox(20);
        leftVBox.getChildren().addAll(nameBox, backgroundBox);
        leftVBox.setAlignment(Pos.CENTER);

        VBox rightVBox = new VBox(20, traitsLabel, traitsBox);
        rightVBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> this.primaryStage.setScene(this.appereanceScene));

        Button continueButton = new Button("Continue");
        HBox buttonsBox = new HBox(20, backButton, continueButton);
        buttonsBox.setAlignment(Pos.CENTER);

        HBox centerBox = new HBox(40, leftVBox, rightVBox);
        centerBox.setAlignment(Pos.CENTER);

        VBox finalVBox = new VBox(20, playerInfoLabel, centerBox, buttonsBox);
        finalVBox.setAlignment(Pos.CENTER);

        this.playerInfoScene = Styler.createScene(finalVBox, this.PATH_TO_CSS, false);
        Styler.setStage(this.primaryStage, this.playerInfoScene);
    }
}
