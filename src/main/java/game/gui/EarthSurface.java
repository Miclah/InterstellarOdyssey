package game.gui;

import game.entity.ShopKeeper;
import game.entity.special.Cat;
import game.entity.GeneralShopKeeper;
import game.entity.NPC;
import game.entity.Player;
import game.entity.ShipShopKeeper;
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

/**
 * Earth surface class responsible for creating starting area which holds npcs
 */
public class EarthSurface {
    /**
     * The Primary stage.
     */
    private final Stage primaryStage;
    /**
     * The Player.
     */
    private Player player;
    /**
     * The Tile manager.
     */
    private TileManager tileManager;
    /**
     * The Canvas.
     */
    private final Canvas canvas;
    /**
     * The Key manager.
     */
    private final KeyManager keyManager;
    /**
     * The Pause text.
     */
    private Label pauseText;
    /**
     * The Pane.
     */
    private final Pane pane;
    /**
     * The Scene.
     */
    private final Scene scene;
    /**
     * The Npcs.
     */
    private ArrayList<NPC> npcs;
    /**
     * The Interaction prompt.
     */
    private Label interactionPrompt;
    /**
     * The constant CANVAS_WIDTH.
     */
    private static final int CANVAS_WIDTH = 1216;
    /**
     * The constant CANVAS_HEIGHT.
     */
    private static final int CANVAS_HEIGHT = 704;
    /**
     * The constant NUMBER_OF_NPCS.
     */
    private static final int NUMBER_OF_NPCS = 17;
    /**
     * The constant TILE_SIZE_MULTIPLIER.
     */
    private static final int TILE_SIZE_MULTIPLIER = 50;
    /**
     * The constant FONT_PATH.
     */
    private static final String FONT_PATH = "/fonts/pixel1.ttf";
    /**
     * The constant PAUSE_FONT_SIZE.
     */
    private static final int PAUSE_FONT_SIZE = 200;
    /**
     * The constant INTERACTION_FONT_SIZE.
     */
    private static final int INTERACTION_FONT_SIZE = 30;
    /**
     * The In shop.
     */
    private boolean inShop;
    /**
     * The Manager.
     */
    private final GeneralManager manager;
    /**
     * The Game loop.
     */
    private Timeline gameLoop;

    /**
     * Instantiates a new Earth surface.
     *
     * @param primaryStage the primary stage
     */
    public EarthSurface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.keyManager = new KeyManager(this);
        this.pane = new Pane();
        this.manager = new GeneralManager();
        this.manager.setKeyManager(this.keyManager);
        this.initializeTileManager();
        this.canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.pane.getChildren().add(this.canvas);
        this.npcs = new ArrayList<>();
        this.setupGraphicsContext ();
        this.scene = new Scene(this.pane, CANVAS_WIDTH, CANVAS_HEIGHT);
        this.createInteractionLabel();
        this.pane.getChildren().add(this.interactionPrompt);
        this.setupPlayer(this.manager.getPlayerName(), this.manager.getSkinChoice());
        this.setupPlayer();
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        this.tileManager.draw(gc, this.player, this.npcs);
        this.setupNPCs();
        primaryStage.setScene(this.scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
        this.startGameLoop(gc);
    }

    /**
     * Creates a new player with given name and skin choice
     *
     * @param name       the name
     * @param skinChoice the skin choice
     */
    public void setupPlayer(String name, int skinChoice) {
        String pathToFrames = "";
        if (skinChoice == 1) {
            pathToFrames = "player/skin/one/player";
        } else if (skinChoice == 2) {
            pathToFrames = "player/skin/two/player";
        }
        System.out.println (pathToFrames);
        this.player = new Player(this.tileManager.getTileSize() * TILE_SIZE_MULTIPLIER, this.tileManager.getTileSize() * TILE_SIZE_MULTIPLIER, 5, name, this.scene, this.keyManager, this.manager, pathToFrames);
    }

    /**
     * Initialize tile manager.
     */
    private void initializeTileManager() {
        this.manager.createTileManger();
        this.tileManager = this.manager.getTileManager();
    }

    /**
     * Sets graphics context.
     */
    private void setupGraphicsContext() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    /**
     * Sets player.
     */
    private void setupPlayer() {
        this.pane.getChildren().addAll(this.player.getCurrentFrame(), this.player.getLabel());
        this.player.getCurrentFrame().setTranslateX(this.player.getScreenX());
        this.player.getCurrentFrame().setTranslateY(this.player.getScreenY());
        this.manager.createCollision(this.player);
    }

    /**
     * Creates randomly generated NPCS on the world map
     */
    private void setupNPCs() {
        this.npcs.add(new GeneralShopKeeper(47 * this.tileManager.getTileSize(), 47 * this.tileManager.getTileSize(), this.manager));
        this.npcs.add(new ShipShopKeeper(53 * this.tileManager.getTileSize(), 53 * this.tileManager.getTileSize(), this.manager));
        this.npcs.add(new Cat(42 * this.tileManager.getTileSize(), 42 * this.tileManager.getTileSize(), "Optimus Prime", this.manager));
        this.npcs.add(new Cat(80 * this.tileManager.getTileSize(), 80 * this.tileManager.getTileSize(), "Crack Sparrow", this.manager));
        this.npcs.add(new Cat(15 * this.tileManager.getTileSize(), 15 * this.tileManager.getTileSize(), "Godzilla", this.manager));

        for (int i = 0; i < NUMBER_OF_NPCS; i++) {
            NPC npc = NpcCreator.createRandomNPC(this.manager, this.tileManager);
            this.npcs.add(npc);
        }

        for (NPC npc : this.npcs) {
            if (npc instanceof ShopKeeper shopKeeper) {
                this.pane.getChildren().add(shopKeeper.getInteractionCircle());
            }
            this.pane.getChildren().addAll(npc.getCurrentFrame(), npc.getLabel());
            this.manager.addEntity(npc);
        }
    }

    /**
     * Starts game loop
     * Game loop makes npcs move, interact with each other and manages game
     * states such as paused or opens a shop
     *
     * @param gc the gc(Graphics content)
     */
    private void startGameLoop(GraphicsContext gc) {
        this.gameLoop = new Timeline(new KeyFrame(Duration.seconds(1.0 / 60), e -> {
            if (this.pauseText != null) {
                this.hidePauseMessage();
            }
            if (!this.keyManager.isPaused() && !this.keyManager.isShopOpen()) {
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
                                if (this.keyManager.isInteracted()) {
                                    this.interactionPrompt.setVisible(false);
                                    this.keyManager.setPaused(true);
                                    this.inShop = true;
                                    shopKeeper.interact();
                                }
                            } else {
                                shopKeeper.getInteractionCircle().setVisible(false);
                                this.interactionPrompt.setVisible(false);
                            }
                        }
                    } else {
                        npc.resetTalk();
                    }
                }
            } else if (this.inShop) {
                if (!this.keyManager.isShopOpen()) {
                    this.keyManager.setPaused(false);
                    this.keyManager.setShopOpen(false);
                    this.keyManager.setInteracted(false);
                }
            } else {
                this.displayPausedMessage();
            }
        }));
        this.gameLoop.setCycleCount(Animation.INDEFINITE);
        this.gameLoop.play();
    }


    /**
     * Displays a message that says paused once the player presses P
     */
    private void displayPausedMessage() {
        if (this.pauseText == null) {
            this.pauseText = new Label("Paused");
            this.pauseText.setAlignment(Pos.CENTER);
            this.pauseText.setTextFill(Color.WHITE);
            Font customFont = Font.loadFont(this.getClass ().getResourceAsStream(FONT_PATH), PAUSE_FONT_SIZE);
            this.pauseText.setFont(customFont);
            this.pauseText.setStyle("-fx-background-color: transparent; -fx-padding: 20px;");
            this.pauseText.setTranslateX(this.scene.getWidth() / 4);
            this.pauseText.setTranslateY(this.scene.getHeight() / 4);
            this.pane.getChildren().add(this.pauseText);
        }
    }

    /**
     * Hide pause message.
     */
    private void hidePauseMessage() {
        this.pane.getChildren().remove(this.pauseText);
        this.pauseText = null;
    }

    /**
     * Checks whether the npc is inside the player view(Game window)
     *
     * @param npc    the npc
     * @param player the player
     * @return the boolean
     */
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

    /**
     * Checks whethr the player is close enough to the
     * shopkeeper to be able to interact with him
     *
     * @param shopKeeper the shopkeeper
     * @param player     the player
     * @return the boolean
     */
    private boolean playerIsInInteractionZone(ShopKeeper shopKeeper, Player player) {
        double distance = Math.sqrt(Math.pow(player.getWorldX() - shopKeeper.getWorldX(), 2) +
                Math.pow(player.getWorldY() - shopKeeper.getWorldY(), 2));
        return distance <= shopKeeper.getInteractionCircle().getRadius();
    }

    /**
     * When the player is close enough to the shopkeeper display a label
     */
    private void createInteractionLabel() {
        this.interactionPrompt = new Label("Press SPACE to interact");
        this.interactionPrompt.setTextFill(Color.WHITE);
        Font customFont = Font.loadFont(this.getClass ().getResourceAsStream(FONT_PATH), INTERACTION_FONT_SIZE);
        this.interactionPrompt.setFont(customFont);
        this.interactionPrompt.setAlignment(Pos.CENTER);
        this.interactionPrompt.setPrefWidth(this.scene.getWidth());
        this.interactionPrompt.setLayoutY(this.scene.getHeight() - 50);
        this.interactionPrompt.setVisible(false);
    }

    /**
     * Pause game loop.
     */
    public void pauseGameLoop() {
        this.gameLoop.pause();
    }

    /**
     * Resume game loop.
     */
    public void resumeGameLoop() {
        this.gameLoop.play();
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }
}
