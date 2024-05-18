package game.gui;

import game.things.Thing;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Shop {
    private Stage shopStage;
    private VBox itemContainer;
    private Label currencyLabel;
    private int playerCurrency;

    public Shop(int playerCurrency) {
        this.playerCurrency = playerCurrency;
        this.shopStage = new Stage();
        this.itemContainer = new VBox(10);
        this.itemContainer.setAlignment(Pos.TOP_CENTER);
        this.currencyLabel = new Label("Currency: " + this.playerCurrency);
        this.currencyLabel.setTextFill(Color.WHITE);
        this.currencyLabel.setStyle("-fx-font-size: 20px; -fx-background-color: black; -fx-padding: 10px;");
    }

    public void displayShop(ArrayList<Thing> items) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(this.itemContainer);
        scrollPane.setFitToWidth(true);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(this.currencyLabel, scrollPane);

        for (Thing item : items) {
            Button itemButton = new Button(item.getName() + " - " + item.getPrice() + " credits");
            itemButton.setOnAction(e -> this.displayPurchaseConfirmation(item));
            this.itemContainer.getChildren().add(itemButton);
        }

        Scene scene = new Scene(layout, 400, 600);
        this.shopStage.setScene(scene);
        this.shopStage.initModality(Modality.APPLICATION_MODAL);
        this.shopStage.showAndWait();
    }

    private void displayPurchaseConfirmation(Thing item) {
        Stage confirmationStage = new Stage();
        confirmationStage.initModality(Modality.APPLICATION_MODAL);
        confirmationStage.initStyle(StageStyle.UTILITY);

        Label itemLabel = new Label(item.getName() + "\n" + item.getDescription() + "\nPrice: " + item.getPrice() + " credits");
        itemLabel.setWrapText(true);

        int newBalance = this.playerCurrency - item.getPrice();
        Label balanceLabel = new Label("Balance after purchase: " + newBalance + " credits");
        balanceLabel.setTextFill(newBalance < 0 ? Color.RED : Color.GREEN);

        Button purchaseButton = new Button("Purchase");
        purchaseButton.setDisable(newBalance < 0);
        purchaseButton.setOnAction(e -> {
            if (newBalance >= 0) {
                this.playerCurrency = newBalance;
                this.currencyLabel.setText("Currency: " + this.playerCurrency);
                this.itemContainer.getChildren().removeIf(button -> ((Button)button).getText().contains(item.getName()));
                confirmationStage.close();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> confirmationStage.close());

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(purchaseButton, cancelButton);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(itemLabel, balanceLabel, buttons);

        Scene scene = new Scene(layout, 300, 200);
        confirmationStage.setScene(scene);
        confirmationStage.showAndWait();
    }

    public void addItem(Thing item) {
        Button itemButton = new Button(item.getName() + " - " + item.getPrice() + " credits");
        itemButton.setOnAction(e -> this.displayPurchaseConfirmation(item));
        this.itemContainer.getChildren().add(itemButton);
    }

    public void removeItem(Thing item) {
        this.itemContainer.getChildren().removeIf(button -> ((Button)button).getText().contains(item.getName()));
    }

    public void setPlayerCurrency(int playerCurrency) {
        this.playerCurrency = playerCurrency;
        this.currencyLabel.setText("Currency: " + this.playerCurrency);
    }

    public void open() {
        this.shopStage.showAndWait();
    }

    public void close() {
        this.shopStage.close();
    }
}
