package game.gui;

import game.entity.Entity;
import game.entity.NPC;
import game.entity.Player;
import game.state.KeyManager;
import game.util.TileManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class EarthSurface {
    private final Stage primaryStage;
    private final Player player;
    private final TileManager tileManager;
    private final Canvas canvas;
    private final KeyManager keyManager;
    private Label pauseText;
    private Pane pane;
    private Scene scene;
    private ArrayList<Entity> entities;

    public EarthSurface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.keyManager = new KeyManager();
        this.pane = new Pane();
        this.tileManager = new TileManager();
        this.canvas = new Canvas(1216, 704);
        this.pane.getChildren().add(this.canvas);
        this.entities = new ArrayList<>();


        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        this.scene = new Scene(this.pane, 1216, 704);
        this.player = new Player(this.tileManager.getTileSize() * 50, this.tileManager.getTileSize() * 50, 10, "Janko", this.scene, this.keyManager);
        this.pane.getChildren().addAll(this.player.getImageView(), this.player.getLabel());
        this.player.getImageView().setTranslateX(this.player.getScreenX());
        this.player.getImageView().setTranslateY(this.player.getScreenY());
        this.scene.setOnKeyPressed(this.player::update);

        this.tileManager.drawTiles(gc, this.player, this.entities);
        primaryStage.setScene(this.scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
        this.startGameLoop(gc);
    }

    private void startGameLoop(GraphicsContext gc) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), e -> {
            if (!this.keyManager.isPaused()) {
                this.hidePauseMessage();
                if (this.player.isMoving()) {
                    this.player.update(null);
                    gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
                    if (this.tileManager.isInPlayerView()) {
                        for (Entity entity : this.entities) {
                            if (entity instanceof NPC npc) {
                                npc.interact(this.canvas);
                            }
                        }
                    }
                    this.tileManager.drawTiles(gc, this.player, this.entities);
                }
            } else {
                this.displayPausedMessage();
            }
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    private void displayPausedMessage() {
        if (this.pauseText == null) {
            this.pauseText = new Label("Paused");
            this.pauseText.setAlignment(Pos.CENTER);
            this.pauseText.setTextFill(Color.WHITE);
            this.pauseText.setStyle("-fx-font-size: 200px; -fx-background-color: transparent; -fx-padding: 20px;");
            this.pauseText.setTranslateX(this.scene.getWidth() / 4);
            this.pauseText.setTranslateY(this.scene.getHeight() / 4);
            this.pane.getChildren().add(this.pauseText);
        }
    }

    private void hidePauseMessage() {
        if (this.pauseText != null) {
            this.pane.getChildren().remove(this.pauseText);
            this.pauseText = null;
        }
    }
}
