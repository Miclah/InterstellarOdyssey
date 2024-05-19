package game.state;

import game.gui.EarthSurface;
import game.gui.Game;
import javafx.stage.Stage;

/**
 * GameManager class responsible for switching between games
 */
public class GameManager {
    /**
     * The Stage.
     */
    private final Stage stage;
    /**
     * The Earth surface.
     */
    private EarthSurface earthSurface;

    /**
     * Instantiates a new Game manager.
     *
     * @param primaryStage the primary stage
     * @param place        the place
     */
    public GameManager(Stage primaryStage, int place) {
        this.stage = primaryStage;
        if (place == 1) {
            this.loadEarth();
        } else if (place == 2) {
            this.loadGame();
        }
    }

    /**
     * Load earth.
     */
    private void loadEarth() {
        this.earthSurface = new EarthSurface(this.stage);
    }

    /**
     * Load game.
     */
    private void loadGame() {
        new Game(this.stage);
    }
}
