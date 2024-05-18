package game.gui;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class DialogBox {

    private static boolean isVisible = false;
    private double worldX, worldY;
    private ImageView imageView;
    private Text text;

    public DialogBox(Pane pane, String message, String pathToBubble, double worldX, double worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.drawDialog(pane, message, pathToBubble);
    }

    private void drawDialog(Pane pane, String message, String pathToBubble) {
        if (!isVisible) {
            Image image = new Image(pathToBubble);
            this.imageView = new ImageView(image);
            this.imageView.setX(this.worldX - 20);
            this.imageView.setY(this.worldY - 130);

            this.text = new Text();
            this.text.setFill(Color.BLACK);
            this.text.setX(this.worldX + 10);
            this.text.setY(this.worldY - 80);
            this.setText(message);

            pane.getChildren().addAll(this.imageView, this.text);

            isVisible = true;

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                pane.getChildren().remove(this.imageView);
                pane.getChildren().remove(this.text);
                isVisible = false;
            });
            pause.play();
        }
    }

    private void setText(String message) {
        Font customFont = Font.loadFont(this.getClass ().getResourceAsStream("/fonts/pixel1.ttf"), 20);
        this.text.setFont(customFont);
        this.text.setSmooth(false);

        int charCount = 0;
        boolean firstCharacter = false;
        StringBuilder newText = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (c == ' ') {
                if (firstCharacter) {
                    continue;
                }
            } else {
                firstCharacter = false;
            }

            charCount++;
            newText.append(c);

            if (charCount >= 19) {
                newText.append("\n");
                charCount = 0;
                firstCharacter = true;
            }
        }
        System.out.println (newText);
        this.text.setText(newText.toString());
        this.text.setTextAlignment(TextAlignment.LEFT);
    }

    public void setCoordinatesHumans(double worldX, double worldY) {
        this.setCoordinates (worldX, worldY, true);
    }

    public void setCoordinatesAnimals(double worldX, double worldY) {
        this.setCoordinates (worldX, worldY, false);
    }

    private void setCoordinates(double worldX, double worldY, boolean human) {
        if (this.imageView != null && this.text != null) {
            if (human) {
                this.imageView.setX(worldX - 20);
                this.imageView.setY(worldY - 130);
                this.text.setX(worldX + 10);
                this.text.setY(worldY - 80);
            } else {
                this.imageView.setX(worldX - 20);
                this.imageView.setY(worldY - 130);
                this.text.setX(worldX + 67);
                this.text.setY(worldY - 55);
            }
        }
    }
}

