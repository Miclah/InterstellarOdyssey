package game.gui;

import game.state.GameManager;
import game.state.GeneralManager;
import game.util.MusicPlayer;
import game.util.Styler;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Custormizor class responsible for character creation.
 * Allows player to choose a skin from two options
 * Choose his backstory, traits, name and starting bonuses
 */
public class Customizator {
    /**
     * The Primary stage.
     */
    private final Stage primaryStage;
    /**
     * The Appereance scene.
     */
    private Scene appereanceScene;
    /**
     * The Player info scene.
     */
    private Scene playerInfoScene;
    /**
     * The Ability info scene.
     */
    private Scene abilityInfoScene;
    /**
     * The Skin choice.
     */
    private int skinChoice;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Trait points.
     */
    private int traitPoints = 15;
    /**
     * The Path to css.
     */
    private final String pathToCss = "/Styles/Menu/customizer.css";
    /**
     * The Sliders.
     */
    private final List<Slider> sliders = new ArrayList<> ();
    /**
     * The Trait points left.
     */
    private Label traitPointsLeft;
    /**
     * The Trait slider listener.
     */
    private final ChangeListener<Number> traitSliderListener;
    /**
     * The Music player.
     */
    private final MusicPlayer musicPlayer;

    /**
     * Instantiates a new Customizator.
     *
     * @param primaryStage  the primary stage
     * @param mainMenuScene the main menu scene
     * @param musicPlayer   the music player
     */
    public Customizator(Stage primaryStage, Scene mainMenuScene, MusicPlayer musicPlayer) {
        this.primaryStage = primaryStage;
        this.skinSelector(mainMenuScene);
        this.musicPlayer = musicPlayer;
        this.traitSliderListener = this.createTraitSliderListener();
    }

    /**
     * Skin selection where player is given a choice between two skins
     *
     * @param mainMenuScene the main menu scene
     */
    private void skinSelector(Scene mainMenuScene) {
        Label appereanceChooser = Styler.createHeaderLabel("Choose appereance: ", "");
        StackPane skin1 = this.createFrame("textures/player/skin/one/player01.png");
        StackPane skin2 = this.createFrame("textures/player/skin/two/player01.png");

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
        this.appereanceScene = Styler.createScene(mainLayout, this.pathToCss);
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

    /**
     * Creates a new frame with image inside
     *
     * @param imagePath the image path
     * @return A stackpane(stack multiple images on top of each oter) which holds the frame and input image
     */
    private StackPane createFrame(String imagePath) {
        int frameSize = 305;
        int imageSize = 200;

        Image image = new Image(imagePath, imageSize, imageSize, false, false);
        ImageView imageView = Styler.createImageView(image, true);

        Image frame = new Image("other/frame.png", frameSize, frameSize, false, false);
        ImageView frameView = Styler.createImageView(frame, true);

        StackPane stackPane = new StackPane();
        StackPane.setAlignment(imageView, Pos.CENTER);
        stackPane.getChildren().addAll(imageView, frameView);
        return stackPane;
    }

    /**
     * Player info section which propmts the player for his name, backstory and traits
     */
    public void playerInfo() {
        Label playerInfoLabel = Styler.createHeaderLabel("Player Info", "");

        Label nameLabel = Styler.createHeaderLabel("Name:", "");
        TextField nameInput = new TextField();
        nameInput.setPrefWidth(215);
        nameInput.setPromptText("Your name here");
        nameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!nameInput.getText().matches("[a-zA-Z]*")) {
                    nameInput.setText("Input only letters!");
                    this.name = "";
                } else {
                    this.name = nameInput.getText();
                }
            } else {
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
        HBox warriorBox = new HBox(36, new Text("Trained as a warrior (+3 Strength, +1 Agility):"), warriorCheckBox);
        VBox backgroundBox = new VBox(15, backgroundLabel, streetBox, richBox, workshopBox, luckBox, warriorBox);
        backgroundBox.setAlignment(Pos.CENTER_LEFT);

        List<CheckBox> checkBoxes = Arrays.asList(streetCheckBox, richCheckBox, workshopCheckBox, luckCheckBox, warriorCheckBox);

        HashMap<CheckBox, Consumer<List<Slider>>> checkBoxActions = new HashMap<>();
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
                checkBoxes.stream().filter(cb -> !cb.equals(checkBox)).forEach(cb -> cb.setSelected(false));
                this.sliders.forEach(slider -> slider.valueProperty().removeListener(this.traitSliderListener));
                if (originalSliderValues.isEmpty()) {
                    this.sliders.forEach(slider -> originalSliderValues.add(slider.getValue()));
                }
                checkBoxActions.get(checkBox).accept(this.sliders);
                this.sliders.forEach(slider -> slider.valueProperty().addListener(this.traitSliderListener));
            } else {
                for (int i = 0; i < this.sliders.size(); i++) {
                    Slider slider = this.sliders.get(i);
                    slider.valueProperty().removeListener(this.traitSliderListener);
                    slider.setValue(originalSliderValues.get(i));
                    slider.valueProperty().addListener(this.traitSliderListener);
                }
                originalSliderValues.clear();
            }
        }));


        Label traitsLabel = Styler.createHeaderLabel("Character Traits:", "");
        Label pointsLeftLabel = Styler.createHeaderLabel("Trait points left: ", "");
        this.traitPointsLeft = new Label(String.valueOf(this.traitPoints));
        HBox traitPointsBox = new HBox(pointsLeftLabel, this.traitPointsLeft);
        traitPointsBox.setAlignment(Pos.CENTER);

        VBox traitsBox = this.createTraitsBox(Arrays.asList("Intelligence", "Charisma", "Agility", "Luck", "Strength"), Arrays.asList(10, 10, 10, 10, 10));

        VBox leftVBox = new VBox(30);
        leftVBox.getChildren().addAll(nameBox, backgroundBox);
        leftVBox.setAlignment(Pos.CENTER);

        VBox rightVBox = new VBox(30, traitsLabel, traitPointsBox, traitsBox);
        rightVBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            this.primaryStage.setScene(this.appereanceScene);
            this.skinChoice = 0;
        });

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            this.abilityInfo();
        });
        HBox buttonsBox = new HBox(20, backButton, continueButton);
        buttonsBox.setAlignment(Pos.CENTER);

        HBox centerBox = new HBox(40, leftVBox, rightVBox);
        centerBox.setAlignment(Pos.CENTER);

        VBox finalVBox = new VBox(30, playerInfoLabel, centerBox, buttonsBox);
        finalVBox.setAlignment(Pos.CENTER);

        this.playerInfoScene = Styler.createScene(finalVBox, this.pathToCss);
        Styler.setStage(this.primaryStage, this.playerInfoScene);
    }

    /**
     * Ability section which allows the user the choose one of six starting bonuses
     */
    public void abilityInfo() {
        Label abilityInfoLabel = Styler.createHeaderLabel("Choose Your Ability", "");

        GridPane abilitiesGrid = new GridPane();
        abilitiesGrid.setAlignment(Pos.CENTER);
        abilitiesGrid.setHgap(15);
        abilitiesGrid.setVgap(15);

        List<String> abilityNames = Arrays.asList("Ability 1", "Ability 2", "Ability 3", "Ability 4", "Ability 5", "Ability 6");
        List<String> abilityImagePaths = Arrays.asList("other/icon.png", "textures/player/skin/one/player01.png", "other/frame.png", "other/frame.png", "other/icon.png", "textures/player/skin/two/player01.png");
        List<String> abilityBonuses = Arrays.asList("+1 Strength", "+2 Agility", "+3 Intelligence", "+1 Luck", "+2 Charisma", "+1 Strength");

        for (int i = 0; i < abilityNames.size(); i++) {
            String abilityName = abilityNames.get(i);
            String imagePath = abilityImagePaths.get(i);
            String bonus = abilityBonuses.get(i);

            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(140);
            imageView.setFitHeight(140);

            Button abilityButton = new Button();
            abilityButton.setGraphic(imageView);
            abilityButton.setOnAction(e -> {
                GeneralManager.setUpPlayer(this.name, this.skinChoice);
                new GameManager(this.primaryStage, 1);
                this.musicPlayer.stop();
            });

            Label bonusLabel = new Label(bonus);
            bonusLabel.setAlignment(Pos.CENTER);

            VBox abilityBox = new VBox(10, abilityButton, bonusLabel);
            abilityBox.setAlignment(Pos.CENTER);

            abilitiesGrid.add(abilityBox, i % 3, i / 3);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            this.primaryStage.setScene(this.playerInfoScene);
            this.skinChoice = 0;
        });

        VBox abilitiesLayout = new VBox(20, abilityInfoLabel, abilitiesGrid, backButton);
        abilitiesLayout.setAlignment(Pos.CENTER);

        this.abilityInfoScene = Styler.createScene(abilitiesLayout, this.pathToCss);
        Styler.setStage(this.primaryStage, this.abilityInfoScene);
    }


    /**
     * Creates a vbox which holds the traits
     *
     * @param traits       the traits
     * @param spaceBetween the space between
     * @return Vbox of traits
     */
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
            traitSlider.setMinorTickCount(0);
            traitSlider.setBlockIncrement(1);

            traitSlider.setSnapToTicks(true);
            Label sliderValueLabel = new Label("1");

            traitSlider.valueProperty().addListener(this.traitSliderListener);
            sliderValueLabel.textProperty().bind(traitSlider.valueProperty().asString("%.0f"));
            traitRow.getChildren().addAll(traitLabel, traitSlider, sliderValueLabel);
            traitsBox.getChildren().add(traitRow);
            this.sliders.add(traitSlider);
        }
        return traitsBox;
    }

    /**
     * Create trait slider listener change listener.
     *
     * @return the change listener
     */
    private ChangeListener<Number> createTraitSliderListener() {
        return (observable, oldValue, newValue) -> {
            int totalSliderValue = this.sliders.stream().mapToInt(slider -> (int)slider.getValue()).sum();
            int usedTraitPoints = totalSliderValue - this.sliders.size();
            int remainingTraitPoints = this.traitPoints - usedTraitPoints;
            this.traitPointsLeft.setText(String.valueOf(remainingTraitPoints));
            if (observable instanceof Slider) {
                Slider sourceSlider = (Slider)observable;
                int difference = newValue.intValue() - oldValue.intValue();
                if (difference > 0) {
                    if (this.traitPoints - difference >= 0) {
                        this.traitPoints -= difference;
                    } else {
                        sourceSlider.setValue(oldValue.doubleValue());
                    }
                } else if (difference < 0) {
                    this.traitPoints += Math.abs(difference);
                }
                this.traitPoints = Math.min(Math.max(this.traitPoints, 0), 15);
                this.traitPointsLeft.setText(String.valueOf(this.traitPoints));

                if (this.traitPoints == 0) {
                    sourceSlider.setValue(Math.min(oldValue.doubleValue(), newValue.doubleValue()));
                }
            }
        };
    }
}
