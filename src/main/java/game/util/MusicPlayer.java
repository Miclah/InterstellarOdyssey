package game.util;

import com.sun.scenario.Settings;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;

/**
 * Music player class responsible for playing music.
 */
public class MusicPlayer {

    /**
     * The Is popup shown.
     */
    private boolean isPopupShown = false;
    /**
     * The Media player.
     */
    private MediaPlayer mediaPlayer;
    /**
     * The Is music playing.
     */
    private boolean isMusicPlaying = false;
    /**
     * The Tracks.
     */
    private final HashMap<String, String> tracks;

    /**
     * Instantiates a new Music player.
     *
     * @param tracks the tracks
     */
    public MusicPlayer(HashMap<String, String> tracks) {
        this.tracks = tracks;
    }

    /**
     * Creates a new music file based on the musicPath and plays it
     *
     * @param musicPath the music path
     */
    public void playMusic(String musicPath) {
        if (!this.isMusicPlaying) {
            try {
                File musicFile = new File(musicPath);

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

                this.mediaPlayer.setOnError(this::handleMusicLoadError);

                this.isMusicPlaying = true;
                this.mediaPlayer.play();

            } catch (MediaException e) {
                this.handleMusicLoadError();
            }
        }
    }

    /**
     * Handles music error if music file is not found
     */
    private void handleMusicLoadError() {
        if (!this.isPopupShown) {
            this.isPopupShown = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error loading music");
            alert.setContentText("There was an error loading the music file. Please make sure the file exists and try again.");
            alert.getDialogPane().getStylesheets().add(Settings.class.getResource("/Styles/Menu/music_popup.css").toExternalForm());
            alert.setGraphic(null);

            alert.showAndWait();
        }
    }

    /**
     * Pauses the music
     */
    public void pause() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.pause();
            this.isMusicPlaying = false;
        }
    }

    /**
     * Completely stops the music
     */
    public void stop() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.isMusicPlaying = false;
        }
    }

    /**
     * Dispose media player.
     */
    public void disposeMediaPlayer() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.dispose();
            this.mediaPlayer = null;
        }
    }
}
