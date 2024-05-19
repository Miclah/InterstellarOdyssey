package game;

import game.gui.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of the project
 */
public class Main extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the game
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Menu(stage);
    }
}
