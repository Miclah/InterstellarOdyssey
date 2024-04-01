package game;

import game.gui.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Menu menu = new Menu(stage);
        // TODO: Pixel art font
    }
}
