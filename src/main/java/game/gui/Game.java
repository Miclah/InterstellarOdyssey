package game.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Game which houses the functionality of the Game
 */
public class Game {
    /**
     * The Primary stage.
     */
    private final Stage primaryStage;
    /**
     * The Scene.
     */
    private final Scene scene;

    /**
     * Instantiates a new Game.
     *
     * @param primaryStage the primary stage
     */
    public Game(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;");

        this.scene = new Scene(root, 1280, 720);
        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();
    }
}
