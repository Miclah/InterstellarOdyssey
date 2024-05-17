package game.entity;

import javafx.scene.layout.Pane;

public interface Talkable {
    void talk(Pane pane, Player player);
    void resetTalk();
}
