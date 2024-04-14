package game.gui;

import game.util.MusicPlayer;
import game.util.Styler;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private Stage primaryStage;
    private boolean transitionPlayed;
    private Scene mainMenuScene;
    private Scene optionsScene;
    private Scene aboutScene;
    private Scene settingsScene;
    private final String PATH_TO_CSS = "/Styles/Menu/menu.css";

    public Menu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.displayMain();
        this.transitionPlayed = true;
    }

    private void displayMain() {
        Image icon = new Image("file:src/main/resources/other/icon.png");
        this.primaryStage.getIcons().add(icon);
        HashMap<String, String> tracks = new HashMap<String, String>();
        tracks.put("menu", "music/menu.mp3");
        MusicPlayer musicPlayer = new MusicPlayer(tracks);
        musicPlayer.playMusic("src/main/resources/music/menu.mp3");

        ArrayList<String> names = new ArrayList<>();
        names.add("Start");
        names.add("Options");
        names.add("Exit");
        ArrayList<Button> buttons = Styler.createButtons(names);

        buttons.get(0).setOnAction(e -> new Customizator(this.primaryStage, this.mainMenuScene, musicPlayer));
        buttons.get(1).setOnAction(e -> this.displayOptions());
        buttons.get(2).setOnAction(e -> this.primaryStage.close());

        VBox vBox = Styler.createVBox(buttons);
        vBox.setAlignment(Pos.CENTER);
        this.mainMenuScene = Styler.createScene(vBox, this.PATH_TO_CSS);

        this.primaryStage.setResizable(false);
        this.primaryStage.setOnCloseRequest(e -> this.primaryStage.close());
        Styler.setStage(this.primaryStage, this.mainMenuScene);

        if (!this.transitionPlayed) {
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), primaryStage.getScene().getRoot());
            fadeIn.setFromValue(0.25);
            fadeIn.setToValue(1);
            fadeIn.play();
            transitionPlayed = true;
        }
    }

    private void displayOptions() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Settings");
        names.add("About");
        names.add("Back");
        ArrayList<Button> buttons = Styler.createButtons(names);

        buttons.get(0).setOnAction(e -> this.displaySettings());
        buttons.get(1).setOnAction(e -> this.displayAbout());
        buttons.get(2).setOnAction(e -> this.primaryStage.setScene(this.mainMenuScene));

        VBox vBox = Styler.createVBox(buttons);
        vBox.setAlignment(Pos.CENTER);
        this.optionsScene = Styler.createScene(vBox, this.PATH_TO_CSS);
        this.primaryStage.setScene(this.optionsScene);
    }

    private void displayAbout() {
        ArrayList<String> name = new ArrayList<>();
        name.add("Back");
        ArrayList<Button> back = Styler.createButtons(name);
        back.get(0).setOnAction(e -> this.primaryStage.setScene(this.optionsScene));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20));

        TextFlow textFlow = new TextFlow();
        textFlow.setTextAlignment(TextAlignment.LEFT);
        textFlow.setPrefWidth(725);

        Text gameTitle = new Text("Interstellar Odyssey\n");
        gameTitle.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-fill: #fff; -fx-font-family: 'Arial Black';");
        gameTitle.setTextAlignment(TextAlignment.CENTER);
        textFlow.getChildren().add(gameTitle);

        Text gameOverviewTitle = new Text("Game Overview:\n");
        gameOverviewTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #fff;");

        Text gameOverviewText = new Text("Embark on an epic journey through the cosmos in Interstellar Odyssey, a thrilling space exploration game where the universe is your playground. Begin your adventure on Earth, customize your character, and dive into a rich backstory that sets the stage for your intergalactic voyage.\n\n");
        gameOverviewText.setStyle("-fx-font-size: 20; -fx-fill: #fff;");

        Text gameElementsTitle = new Text("Game Elements:\n");
        gameElementsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #fff;");

        Text gameElementsText = new Text("Character Customization: Personalize your avatar's appearance and backstory, making your mark on the vast expanse of space.\n" +
                "Upgrades and Items: Utilize coins to purchase upgrades and essential items, including your very own spaceship, to enhance your exploration capabilities.\n" +
                "Exploration: Navigate an automatically generated universe, filled with diverse solar systems and uncharted worlds waiting to be discovered.\n" +
                "Resource Mining: Venture to planets and mine valuable resources, establishing outposts to expand your reach and influence.\n" +
                "Ship Upgrades: Enhance your spaceship with various upgrades, from improved speed and fuel efficiency to advanced weaponry and defensive systems.\n" +
                "Inventory Management: Manage your inventory, storing essential items like teleporters and outposts to aid you on your journey.\n\n");
        gameElementsText.setStyle("-fx-font-size: 20; -fx-fill: #fff;");

        Text gameRulesTitle = new Text("Game Rules:\n");
        gameRulesTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #fff;");

        Text gameRulesText = new Text("Return to Earth: Return to Earth to purchase upgrades and replenish your supplies, ensuring you're prepared for the challenges ahead.\n" +
                "Sandbox Experience: Immerse yourself in a sandbox environment, where the possibilities are limitless and your destiny is yours to shape.\n" +
                "Future Additions: Stay tuned for future updates, where we may introduce new challenges and adversaries to test your skills in the depths of space.\n\n");
        gameRulesText.setStyle("-fx-font-size: 20; -fx-fill: #fff;");

        Text gameAuthorTitle = new Text("Author:\n");
        gameAuthorTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #fff;");
        Text gameAuthorText = new Text("New Frontriers\n\n");
        gameAuthorText.setStyle("-fx-font-size: 20; -fx-fill: #fff;");

        Text gameVersionTitle = new Text("Version:\n");
        gameVersionTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-fill: #fff;");
        Text gameVersionText = new Text("0.1");
        gameVersionText.setStyle("-fx-font-size: 20; -fx-fill: #fff;");

        textFlow.getChildren().addAll(gameOverviewTitle, gameOverviewText, gameElementsTitle, gameElementsText, gameRulesTitle, gameRulesText, gameAuthorTitle, gameAuthorText, gameVersionTitle, gameVersionText);

        ScrollPane scrollPane = new ScrollPane(textFlow);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        vBox.getChildren().addAll(scrollPane, back.get(0));

        this.aboutScene = Styler.createScene(vBox, this.PATH_TO_CSS);
        this.primaryStage.setScene(this.aboutScene);
    }

    private void displaySettings() {
        ArrayList<Button> back = new ArrayList<>();
        back.add(new Button("Back"));

        back.getLast().setOnAction(e -> this.primaryStage.setScene(this.optionsScene));
        VBox vBox = Styler.createVBox(back);
        vBox.setAlignment(Pos.CENTER);
        this.settingsScene = Styler.createScene(vBox, this.PATH_TO_CSS);
        this.primaryStage.setScene(this.settingsScene);
    }
}

