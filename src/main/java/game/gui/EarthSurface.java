package game.gui;

import game.entity.ShopKeeper;
import game.entity.special.Cat;
import game.entity.GeneralShopKeeper;
import game.entity.NPC;
import game.entity.Player;
import game.state.GeneralManager;
import game.state.KeyManager;
import game.util.NpcCreator;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class EarthSurface {
    private final Stage primaryStage;
    private final Player player;
    private TileManager tileManager;
    private final Canvas canvas;
    private final KeyManager keyManager;
    private Label pauseText;
    private final Pane pane;
    private final Scene scene;
    private ArrayList<NPC> npcs;
    private Label interactionPrompt;
    private static final int CANVAS_WIDTH = 1216;
    private static final int CANVAS_HEIGHT = 704;
    private static final int NUMBER_OF_NPCS = 16;
    private static final int TILE_SIZE_MULTIPLIER = 50;
    private static final String FONT_PATH = "/fonts/pixel1.ttf";
    private static final int PAUSE_FONT_SIZE = 200;
    private static final int INTERACTION_FONT_SIZE = 30;

    public EarthSurface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.keyManager = new KeyManager();
        this.pane = new Pane();
        GeneralManager manager = new GeneralManager();
        this.initializeTileManager (manager);
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.pane.getChildren().add(this.canvas);
        this.npcs = new ArrayList<>();
        this.setupGraphicsContext ();
        this.scene = new Scene(this.pane, CANVAS_WIDTH, CANVAS_HEIGHT);
        this.player = new Player(this.tileManager.getTileSize() * TILE_SIZE_MULTIPLIER, this.tileManager.getTileSize() * TILE_SIZE_MULTIPLIER, 5, "Janko", this.scene, this.keyManager, manager);
        this.setupPlayer (manager);
        this.setupNPCs (manager);
        this.createInteractionLabel();
        this.pane.getChildren().add(this.interactionPrompt);
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.tileManager.draw(gc, this.player, this.npcs);
        primaryStage.setScene(this.scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
        this.startGameLoop(gc);
    }

    private void initializeTileManager(GeneralManager manager) {
        manager.createTileManger();
        this.tileManager = manager.getTileManager();
    }

    private void setupGraphicsContext() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    private void setupPlayer(GeneralManager manager) {
        this.pane.getChildren().addAll(this.player.getCurrentFrame(), this.player.getLabel());
        this.player.getCurrentFrame().setTranslateX(this.player.getScreenX());
        this.player.getCurrentFrame().setTranslateY(this.player.getScreenY());
        manager.createCollision(this.player);
    }

    private void setupNPCs(GeneralManager manager) {
        this.npcs.add(new GeneralShopKeeper(49 * this.tileManager.getTileSize(), 49 * this.tileManager.getTileSize(), manager));
        this.npcs.add(new Cat(47 * this.tileManager.getTileSize(), 47 * this.tileManager.getTileSize(), "Optimus Prime", manager));
        this.npcs.add(new Cat(52 * this.tileManager.getTileSize(), 52 * this.tileManager.getTileSize(), "Crack Sparrow", manager));
        this.npcs.add(new Cat(54 * this.tileManager.getTileSize(), 54 * this.tileManager.getTileSize(), "Godzilla", manager));

        for (int i = 0; i < NUMBER_OF_NPCS; i++) {
            NPC npc = NpcCreator.createRandomNPC(manager, this.tileManager);
            this.npcs.add(npc);
        }

        for (NPC npc : this.npcs) {
            if (npc instanceof ShopKeeper shopKeeper) {
                this.pane.getChildren().add(shopKeeper.getInteractionCircle());
            }
            this.pane.getChildren().addAll(npc.getCurrentFrame(), npc.getLabel());
            manager.addEntity(npc);
        }
    }

    private void startGameLoop(GraphicsContext gc) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), e -> {
            if (!this.keyManager.isPaused()) {
                this.hidePauseMessage();
                this.player.update();
                this.player.updateLabelPosition(this.player.getScreenX(), this.player.getScreenY());
                gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
                this.tileManager.draw(gc, this.player, this.npcs);
                for (NPC npc : this.npcs) {
                    double npcScreenX = npc.getWorldX() - this.player.getWorldX() + this.player.getScreenX();
                    double npcScreenY = npc.getWorldY() - this.player.getWorldY() + this.player.getScreenY();
                    npc.updateLabelPosition(npcScreenX, npcScreenY);
                    npc.wander();
                    if (this.npcIsInPlayerView(npc, this.player)) {
                        npc.talk(this.pane, this.player);
                        if (npc instanceof ShopKeeper shopKeeper) {
                            if (this.playerIsInInteractionZone(shopKeeper, this.player)) {
                                shopKeeper.showZone(this.canvas, this.player);
                                this.interactionPrompt.setVisible(true);
                            } else {
                                shopKeeper.getInteractionCircle().setVisible(false);
                                this.interactionPrompt.setVisible(false);
                            }
                        }
                    } else {
                        npc.resetTalk();
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
            Font customFont = Font.loadFont(this.getClass ().getResourceAsStream("/fonts/pixel1.ttf"), 200);
            this.pauseText.setFont(customFont);
            this.pauseText.setStyle("-fx-background-color: transparent; -fx-padding: 20px;");
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

    private boolean playerIsInInteractionZone(ShopKeeper shopKeeper, Player player) {
        double distance = Math.sqrt(Math.pow(player.getWorldX() - shopKeeper.getWorldX(), 2) +
                Math.pow(player.getWorldY() - shopKeeper.getWorldY(), 2));
        return distance <= shopKeeper.getInteractionCircle().getRadius();
    }

    private void createInteractionLabel() {
        this.interactionPrompt = new Label("Press SPACE to interact");
        this.interactionPrompt.setTextFill(Color.WHITE);
        Font customFont = Font.loadFont(this.getClass ().getResourceAsStream("/fonts/pixel1.ttf"), 30);
        this.interactionPrompt.setFont(customFont);
        this.interactionPrompt.setAlignment(Pos.CENTER);
        this.interactionPrompt.setPrefWidth(this.scene.getWidth());
        this.interactionPrompt.setLayoutY(this.scene.getHeight() - 50);
        this.interactionPrompt.setVisible(false);
    }
}
