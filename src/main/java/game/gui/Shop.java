package game.gui;

import game.entity.Player;
import game.state.KeyManager;
import game.things.Item;
import game.things.Thing;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Shop {
    private Stage shopStage;
    private TilePane itemContainer;
    private Label currencyLabel;
    private int playerCurrency;
    private final KeyManager keyManager;
    private static final String PATH_TO_FONT = "/fonts/pixel1.ttf";
    private static final Color BACKGROUND_COLOR = Color.web ("#fad9c1");
    private static final Color INTERIOR_COLOR = Color.web ("#f9caa7");

    public Shop(int playerCurrency, KeyManager keyManager) {
        this.playerCurrency = playerCurrency;
        this.shopStage = new Stage ();
        this.itemContainer = new TilePane ();
        this.itemContainer.setPadding (new Insets (10));
        this.itemContainer.setHgap (10);
        this.itemContainer.setVgap (10);
        this.currencyLabel = new Label ("Currency: " + this.playerCurrency);
        this.currencyLabel.setTextFill (Color.web ("#5e454b"));
        Font customFont = Font.loadFont (this.getClass ().getResourceAsStream (PATH_TO_FONT), 25);
        this.currencyLabel.setFont (customFont);
        this.currencyLabel.setStyle ("-fx-background-color: transparent; -fx-padding: 5px;");
        this.keyManager = keyManager;
        this.setupShopStage ();
    }

    private void setupShopStage() {
        ScrollPane scrollPane = new ScrollPane ();
        scrollPane.setContent (this.itemContainer);
        scrollPane.setFitToWidth (true);
        scrollPane.setStyle("-fx-background: #f9caa7; -fx-background-color: #f9caa7;");
        scrollPane.getStylesheets().add(this.getClass().getResource("/Styles/Menu/scroll-pane.css").toExternalForm());

        ImageView coinGif = new ImageView (new Image ("file:src/main/resources/other/coin.gif"));
        coinGif.setFitHeight (40);
        coinGif.setFitWidth (40);
        HBox currencyBox = new HBox (0);
        currencyBox.setAlignment (Pos.CENTER);
        currencyBox.getChildren ().addAll (
                this.currencyLabel,
                coinGif
        );

        VBox layout = new VBox (10);
        layout.setAlignment (Pos.TOP_CENTER);
        layout.setBackground (new Background (new BackgroundFill (BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.getChildren ().addAll (currencyBox, scrollPane);

        Scene scene = new Scene (layout, 700, 470);
        this.shopStage.setScene (scene);
        this.shopStage.initModality (Modality.APPLICATION_MODAL);
        this.shopStage.initStyle (StageStyle.UTILITY);
        this.shopStage.setOnCloseRequest (windowEvent -> {
            this.keyManager.setShopOpen (false);
            this.shopStage.close ();
        });
    }

    public void displayShop(ArrayList<Thing> items) {
        this.itemContainer.getChildren ().clear ();
        for (Thing item : items) {
            VBox itemBox = new VBox (5);
            itemBox.setAlignment (Pos.CENTER);
            itemBox.setBackground (new Background (new BackgroundFill (INTERIOR_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
            ImageView imageView = new ImageView (new Image (item.getImagePath ()));
            imageView.setFitWidth (100);
            imageView.setFitHeight (100);

            Label nameLabel = new Label (item.getName ());
            nameLabel.setTextFill (Color.WHITE);
            nameLabel.setFont(Font.loadFont (this.getClass().getResourceAsStream (PATH_TO_FONT), 20));
            Label priceLabel = new Label ("Price: " + item.getPrice () + " credits");
            priceLabel.setTextFill (Color.WHITE);
            priceLabel.setFont(Font.loadFont (this.getClass().getResourceAsStream (PATH_TO_FONT), 20));

            itemBox.getChildren().addAll(imageView, nameLabel, priceLabel);

            Button purchaseButton = this.getButton("Purchase");
            purchaseButton.setOnAction(e -> this.displayPurchaseConfirmation (item));

            VBox vbox = new VBox(10);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(itemBox, purchaseButton);
            this.itemContainer.getChildren().add(vbox);
        }
        this.shopStage.show();
        this.shopStage.setResizable(false);
        this.keyManager.setShopOpen(true);
    }

    private void displayPurchaseConfirmation(Thing item) {
        Stage confirmationStage = new Stage ();
        confirmationStage.initModality (Modality.APPLICATION_MODAL);
        confirmationStage.initStyle (StageStyle.UTILITY);

        Label itemLabel = new Label (item.getName () + "\n" + item.getDescription () + "\nPrice: " + item.getPrice () + " credits");
        itemLabel.setWrapText (true);
        itemLabel.setFont(Font.loadFont(this.getClass().getResourceAsStream(PATH_TO_FONT), 15));
        itemLabel.setBackground(new Background(new BackgroundFill(INTERIOR_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        itemLabel.setTextFill (Color.WHITE);
        itemLabel.setPadding(new Insets(10));

        int newBalance = this.playerCurrency - item.getPrice ();
        Label balanceLabel = new Label ("Balance after purchase: " + newBalance + " credits");
        balanceLabel.setTextFill (newBalance < 0 ? Color.RED : Color.GREEN);
        balanceLabel.setFont(Font.loadFont(this.getClass().getResourceAsStream(PATH_TO_FONT), 15));
        balanceLabel.setBackground(new Background(new BackgroundFill(INTERIOR_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        itemLabel.setPadding(new Insets(10));


        Button purchaseButton = this.getButton("Purchase");
        purchaseButton.setDisable (newBalance < 0);
        purchaseButton.setOnAction(e -> {
            if (newBalance >= 0) {
                this.playerCurrency = newBalance;
                this.currencyLabel.setText("Currency: " + this.playerCurrency);
                Player.addInventoryItem((Item)item);
                confirmationStage.close();
            }
        });

        Button cancelButton = this.getButton("Cancel");
        cancelButton.setOnAction (e -> confirmationStage.close ());

        HBox buttons = new HBox (10);
        buttons.setAlignment (Pos.CENTER);
        buttons.getChildren ().addAll (purchaseButton, cancelButton);

        VBox layout = new VBox (12);
        layout.setAlignment (Pos.CENTER);
        layout.getChildren ().addAll (itemLabel, balanceLabel, buttons);
        layout.setBackground(new Background(new BackgroundFill(INTERIOR_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setPadding(new Insets(10));

        Scene scene = new Scene (layout, 350, 200);
        confirmationStage.setScene (scene);
        confirmationStage.setResizable (false);
        confirmationStage.showAndWait ();
    }

    private Button getButton(String text) {
        Button button = new Button (text);
        button.setStyle ("-fx-background-color: rgba(244, 162, 97, 1); -fx-text-fill: white;");
        button.setOnMouseEntered (e -> button.setStyle ("-fx-background-color: rgba(244, 162, 97, 0.8); -fx-text-fill: white;"));
        button.setOnMouseExited (e -> button.setStyle ("-fx-background-color: rgba(244, 162, 97, 1); -fx-text-fill: white;"));
        button.setFont (Font.loadFont (this.getClass ().getResourceAsStream (PATH_TO_FONT), 15));
        return button;
    }

    public void addItem(Thing item) {
        Button itemButton = new Button (item.getName () + " - " + item.getPrice () + " credits");
        itemButton.setOnAction (e -> this.displayPurchaseConfirmation (item));
        this.itemContainer.getChildren ().add (itemButton);
    }

    public void removeItem(Thing item) {
        this.itemContainer.getChildren ().removeIf (button -> ((Button)button).getText ().contains (item.getName ()));
    }

    public Stage getShopStage() {
        return this.shopStage;
    }
}
