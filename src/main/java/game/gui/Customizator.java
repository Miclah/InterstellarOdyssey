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

import java.util.*;
import java.util.function.Consumer;

public class Customizator {
    private final Stage primaryStage;
    private Scene appereanceScene;
    private Scene playerInfoScene;
    private int skinChoice;
    private String name;
    private int traitPoints = 15;
    private final String PATH_TO_CSS = "/Styles/Menu/customizer.css";
    private List<Slider> sliders = new ArrayList<>();

    public Customizator(Stage primaryStage, Scene mainMenuScene) {
        this.primaryStage = primaryStage;
        this.skinSelector(mainMenuScene);
    }

    private void skinSelector(Scene mainMenuScene) {
        Label appereanceChooser = Styler.createHeaderLabel("Choose appereance: ", "");
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

        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(appereanceChooser, mainHBox);
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
        nameInput.setPrefWidth(245);
        nameInput.setPromptText("Input your name here");
        nameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!nameInput.getText().matches("[a-zA-Z]*")) {
                    nameInput.setText("Can only input letters!");
                    this.name = "";
                } else {
                    this.name = nameInput.getText();
                }
            } else if (newValue) {
                nameInput.setText("");
            }
        });
        HBox nameBox = new HBox(10, nameLabel, nameInput);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        Label backgroundLabel = Styler.createHeaderLabel("Character Background:", "");
        CheckBox streetCheckBox = new CheckBox();
        CheckBox richCheckBox = new CheckBox();
        CheckBox workshopCheckBox = new CheckBox();
        CheckBox luckCheckBox = new CheckBox();
        CheckBox warriorCheckBox = new CheckBox();

        HBox streetBox = new HBox(10, new Text("Grown up on the streets (+1 Strength, +3 Agility):"), streetCheckBox);
        HBox richBox = new HBox(12, new Text("Grown up in a rich family (+3 Charisma, +1 Luck):"), richCheckBox);
        HBox workshopBox = new HBox(51, new Text("Grown up in a workshop (+4 Intelligence):"), workshopCheckBox);
        HBox luckBox = new HBox(24, new Text("Born under a lucky star (+3 Luck, +1 Charisma):"), luckCheckBox);
        HBox warriorBox = new HBox(35, new Text("Trained as a warrior (+3 Strength, +1 Agility):"), warriorCheckBox);
        VBox backgroundBox = new VBox(10, backgroundLabel, streetBox, richBox, workshopBox, luckBox, warriorBox);
        backgroundBox.setAlignment(Pos.CENTER_LEFT);

        List<CheckBox> checkBoxes = Arrays.asList(streetCheckBox, richCheckBox, workshopCheckBox, luckCheckBox, warriorCheckBox);

        Map<CheckBox, Consumer<List<Slider>>> checkBoxActions = new HashMap<>();
        checkBoxActions.put(streetCheckBox, sliders -> {
            sliders.get(4).setValue(sliders.get(4).getValue() + 1);
            sliders.get(2).setValue(sliders.get(2).getValue() + 3);
        });
        checkBoxActions.put(richCheckBox, sliders -> {
            sliders.get(1).setValue(sliders.get(1).getValue() + 3);
            sliders.get(3).setValue(sliders.get(3).getValue() + 1);
        });
        checkBoxActions.put(workshopCheckBox, sliders -> {
            sliders.get(0).setValue(sliders.get(0).getValue() + 4);
        });
        checkBoxActions.put(luckCheckBox, sliders -> {
            sliders.get(3).setValue(sliders.get(3).getValue() + 3);
            sliders.get(1).setValue(sliders.get(1).getValue() + 1);
        });
        checkBoxActions.put(warriorCheckBox, sliders -> {
            sliders.get(4).setValue(sliders.get(4).getValue() + 3);
            sliders.get(2).setValue(sliders.get(2).getValue() + 1);
        });

        ArrayList<Double> originalSliderValues = new ArrayList<>();

        checkBoxes.forEach(checkBox -> checkBox.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                if (originalSliderValues.isEmpty()) {
                    for (Slider slider : this.sliders) {
                        originalSliderValues.add(slider.getValue());
                    }
                }
                checkBoxActions.get(checkBox).accept(this.sliders);

                checkBoxes.forEach(otherCheckBox -> {
                    if (otherCheckBox != checkBox) {
                        otherCheckBox.setSelected(false);
                    }
                });
            } else {
                if (!originalSliderValues.isEmpty()) {
                    for (int i = 0; i < this.sliders.size(); i++) {
                        this.sliders.get(i).setValue(originalSliderValues.get(i));
                    }
                    originalSliderValues.clear(); 
                }
            }
        }));

        Label traitsLabel = Styler.createHeaderLabel("Character Traits:", "");
        Label pointsLeftLabel = Styler.createHeaderLabel("Trait points left: ", "");
        Label traitPointsLeft = new Label(String.valueOf(this.traitPoints));
        HBox traitPointsBox = new HBox(pointsLeftLabel, traitPointsLeft);
        traitPointsBox.setAlignment(Pos.CENTER);

        VBox traitsBox = this.createTraitsBox(Arrays.asList("Intelligence", "Charisma", "Agility", "Luck", "Strength"), Arrays.asList(10, 10, 10, 10, 10));

        VBox leftVBox = new VBox(20);
        leftVBox.getChildren().addAll(nameBox, backgroundBox);
        leftVBox.setAlignment(Pos.CENTER);

        VBox rightVBox = new VBox(20, traitsLabel, traitPointsBox, traitsBox);
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

    private VBox createTraitsBox(List<String> traits, List<Integer> spaceBetween) {
        VBox traitsBox = new VBox(10);

        for (int i = 0; i < traits.size(); i++) {
            HBox traitRow = new HBox(spaceBetween.get(i));
            traitRow.setAlignment(Pos.CENTER_LEFT);
            Label traitLabel = new Label(traits.get(i) + ":");
            Slider traitSlider = new Slider(1, 10, 0);
            HBox.setHgrow(traitLabel, Priority.ALWAYS);
            HBox.setHgrow(traitSlider, Priority.ALWAYS);
            traitSlider.setMajorTickUnit(1);
            traitSlider.setBlockIncrement(1);
            traitSlider.setSnapToTicks(true);
            Label sliderValueLabel = new Label("1");
            traitSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double snappedValue = Math.round(newValue.doubleValue());
                traitSlider.setValue(snappedValue);
                sliderValueLabel.setText(String.valueOf((int) snappedValue));
            });
            traitRow.getChildren().addAll(traitLabel, traitSlider, sliderValueLabel);

            traitsBox.getChildren().add(traitRow);
            this.sliders.add(traitSlider);
        }
        return traitsBox;
    }
}
