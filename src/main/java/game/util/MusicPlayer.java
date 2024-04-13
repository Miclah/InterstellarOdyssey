package game.util;

import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;

public class MusicPlayer {

    private boolean isPopupShown = false;
    private MediaPlayer mediaPlayer;
    private boolean isMusicPlaying = false;
    private HashMap<String, String> tracks;

    public MusicPlayer(HashMap<String, String> tracks) {
        this.tracks = tracks;
    }

    public void playMusic(String musicPath) {
        if (!this.isMusicPlaying) {
            try {
                File musicFile = new File(musicPath);
                System.out.println(musicFile.getAbsolutePath());

                if (!musicFile.exists()) {
                    this.handleMusicLoadError();
                    return;
                }

                Media media = new Media(musicFile.toURI().toString());
                this.mediaPlayer = new MediaPlayer(media);

                this.mediaPlayer.setOnEndOfMedia(() -> {
                    this.mediaPlayer.seek(Duration.ZERO);
                    this.mediaPlayer.play();
                });

                this.mediaPlayer.setOnError(() -> this.handleMusicLoadError());

                this.isMusicPlaying = true;
                this.mediaPlayer.play();

            } catch (MediaException e) {
                this.handleMusicLoadError();
            }
        }
    }

    private void handleMusicLoadError() {
        if (!this.isPopupShown) {
            this.isPopupShown = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading music");
            alert.setContentText("There was an error loading the music file. Please make sure the file exists and try again.");
            //alert.getDialogPane().getStylesheets().add(Settings.class.getResource("/Styles/Menu/music_popup.css").toExternalForm());
            alert.setGraphic(null);

            alert.showAndWait();
        }
    }

    public void pause() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.pause();
            this.isMusicPlaying = false;
        }
    }

    public void disposeMediaPlayer() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.dispose();
            this.mediaPlayer = null;
        }
    }
}
