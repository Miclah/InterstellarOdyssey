package game.gui;

import game.entity.GeneralShopKeeper;
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
    private final Pane pane;
    private final Scene scene;
    private ArrayList<NPC> npcs;

    public EarthSurface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.keyManager = new KeyManager();
        this.pane = new Pane();
        this.tileManager = new TileManager();
        this.canvas = new Canvas(1216, 704);
        this.pane.getChildren().add(this.canvas);
        this.npcs = new ArrayList<>();

        GeneralShopKeeper shopKeeper1 = new GeneralShopKeeper(49 * tileManager.getTileSize(), 49 * tileManager.getTileSize(), "npc/general/shop/shop");
        this.npcs.add(shopKeeper1);

        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        this.scene = new Scene(this.pane, 1216, 704);
        this.player = new Player(this.tileManager.getTileSize() * 50, this.tileManager.getTileSize() * 50, 10, "Janko", this.scene, this.keyManager);
        this.pane.getChildren().addAll(this.player.getCurrentFrame(), this.player.getLabel());
        this.player.getCurrentFrame().setTranslateX(this.player.getScreenX());
        this.player.getCurrentFrame().setTranslateY(this.player.getScreenY());
        this.scene.setOnKeyPressed(this.player::update);

        for (NPC npc : this.npcs) {
            this.pane.getChildren().addAll(npc.getCurrentFrame());
        }

        this.tileManager.draw (gc, this.player, this.npcs);
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
                    this.tileManager.draw(gc, this.player, this.npcs);
                }
                for (NPC npc : this.npcs) {
                    if (this.npcIsInPlayerView(npc, this.player)) {
                        npc.interact(this.canvas);
                    }
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

    private boolean npcIsInPlayerView(NPC npc, Player player) {
        double npcX = npc.getWorldX();
        double npcY = npc.getWorldY();
        double playerX = player.getWorldX();
        double playerY = player.getWorldY();
        double playerScreenX = player.getScreenX();
        double playerScreenY = player.getScreenY();
        double tileSize = this.tileManager.getTileSize();

        return (npcX >= playerX - playerScreenX - tileSize &&
                npcX <= playerX - playerScreenX + 1216 + tileSize &&
                npcY >= playerY - playerScreenY - tileSize &&
                npcY <= playerY - playerScreenY + 704 + tileSize);
    }
}
