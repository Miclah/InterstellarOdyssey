package game.gui;

import game.entity.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartingArea {
    private Stage primaryStage;
    private Player player;

    public StartingArea(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.player = new Player(100, 100, 40);
        Pane pane = new Pane();
        pane.getChildren().add(this.player.getImageView());
        Scene scene = new Scene(pane, 800, 600);
        scene.setOnKeyPressed(event -> this.player.update(event));
        primaryStage.setScene(scene);
        primaryStage.show();
        this.startGameLoop();
    }

    private void startGameLoop() {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), e -> {
            this.player.update(null);
            this.player.getImageView().setTranslateX(this.player.getX());
            this.player.getImageView().setTranslateY(this.player.getY());
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }
}
