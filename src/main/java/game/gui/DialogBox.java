package game.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DialogBox {
    public static void draw(GraphicsContext gc, String message, double x, double y) {
        double width = message.length() * 10 + 20;
        double height = 40;
        double padding = 10;

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(x - padding, y - padding, width + 2 * padding, height + 2 * padding);

        gc.setFill(Color.BLACK);
        gc.fillText(message, x, y);
    }
}
