package game.entity.interfazy;

import game.entity.Player;
import javafx.scene.layout.Pane;

public interface Talkable {
    void talk(Pane pane, Player player);
    void resetTalk();
    String getMessage();
}
