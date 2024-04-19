package game.state;

import game.gui.StartingArea;
import javafx.stage.Stage;

public class GameManager {
    private int gameState;
    private Stage stage;

    public GameManager(Stage primaryStage, int place) {
        this.stage = primaryStage;
        new KeyManager();
        if (place == 1) {
            this.loadEarth();
        }
    }

    private void loadEarth() {
        new StartingArea(this.stage);
    }
}
