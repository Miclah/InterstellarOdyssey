package game.gui;

import game.entity.Player;
import game.tile.TileManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartingArea {
    private Stage primaryStage;
    private Player player;
    private TileManager tileManager;

    public StartingArea(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Pane pane = new Pane();
        this.tileManager = new TileManager();
        Canvas canvas = new Canvas(1216, 704);
        pane.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Scene scene = new Scene(pane, 1216, 704);
        this.player = new Player(this.tileManager.getTileSize() * 23, this.tileManager.getTileSize() * 23, 5, "Janko", scene);
        pane.getChildren().add(this.player.getImageView());
        scene.setOnKeyPressed(event -> {
            this.player.update(event);
        });
        this.player.getImageView().setTranslateX(this.player.getScreenX());
        this.player.getImageView().setTranslateY(this.player.getScreenY());
        System.out.println(this.player.getScreenX() + "  " + this.player.getScreenY());
        primaryStage.setScene(scene);
        primaryStage.show();
        this.startGameLoop(gc);
    }

    private void startGameLoop(GraphicsContext gc) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), e -> {
            this.player.update(null);
            this.tileManager.drawTiles(gc, this.player);
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }
}
