package game.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Menu {
    private Stage primaryStage;
    private Scene mainMenuScene;
    private Scene optionsScene;
    private Scene aboutScene;
    private Game gameScene;
    private final String PATH_TO_CSS = "/Styles/Menu/menu.css";

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
        this.mainMenuScene = Styler.createRoot(vBox, this.PATH_TO_CSS);

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

        buttons.get(1).setOnAction(e -> this.displayAbout());
        buttons.get(2).setOnAction(e -> this.primaryStage.setScene(this.mainMenuScene));

        VBox vBox = Styler.createVBox(buttons);
        this.optionsScene = Styler.createRoot(vBox, this.PATH_TO_CSS);
        this.primaryStage.setScene(this.optionsScene);
    }

    private void displayAbout() {
        ArrayList<String> name = new ArrayList<>();
        name.add("Back");
        ArrayList<Button> back = Styler.createButtons(name);
        back.get(0).setOnAction(e -> this.primaryStage.setScene(this.optionsScene));

        VBox vBox = Styler.createVBox(back);

        Text aboutText = new Text();
        aboutText.setText("Dynamic Duel\n\n" +
                "Game Overview:\n" +
                "Dynamic Duel is a tactical 2D card game made for two players. The goal is to lower your opponent's health using various cards, each with its own special abilities.\n\n" +
                "Game Elements:\n" +
                "- Health Cards: At the start, each player gets 100 health points.\n" +
                "- Attack Cards: These cards take away health points from your opponent.\n" +
                "- Defense Cards: Use these cards to block or reduce damage from your opponent.\n" +
                "- Special Cards: Rare and powerful, these cards can change the game. They can do things like skip a turn or let you draw extra cards.\n" +
                "- Draw Rule: Players can only draw one card per turn, so choose wisely!\n" +
                "- Hand Limit: You can have a maximum of 5 cards in your hand at the end of each turn.\n" +
                "- Special Player and AI Cards: Besides regular special cards, players and the computer have unique cards that can change the game. For example, a special player card might save you from losing by restoring 10 health and letting you draw another card.\n\n" +
                "Game Rules:\n" +
                "Players start with 5 cards and draw one card at the beginning of each turn.\n" +
                "During your turn, you can play one card—Attack, Defense, or Special.\n" +
                "If you play an Attack card, your opponent loses health equal to the card's attack value. If he does not block it by playing a defense card.\n" +
                "Defense cards help you block or reduce damage from your opponent's attack.\n" +
                "Special cards add strategic twists and excitement to the game.\n" +
                "The game goes on until one player's health drops to zero, making the other player the winner.\n\n" +
                "Author: " + "\n" +
                "Version: ");

        // Set text properties (wrap width, styles)
        aboutText.setWrappingWidth(725);
        aboutText.setStyle("-fx-fill: #fff; -fx-font-size: 18; -fx-padding: 20; -fx-text-alignment: justify;");

        ScrollPane scrollPane = new ScrollPane(aboutText);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: #2c3e50; -fx-background-color: #2c3e50;");

        vBox.getChildren().add(scrollPane);

        this.aboutScene = Styler.createRoot(vBox, this.PATH_TO_CSS);
        this.primaryStage.setScene(this.aboutScene);
    }

}

