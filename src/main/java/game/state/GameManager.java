package game.state;

import game.gui.EarthSurface;
import javafx.stage.Stage;

public class GameManager {
    private final Stage stage;

    public GameManager(Stage primaryStage, int place) {
        this.stage = primaryStage;
        if (place == 1) {
            this.loadEarth();
        }
    }

    private void loadEarth() {
        new EarthSurface(this.stage);
    }
}
