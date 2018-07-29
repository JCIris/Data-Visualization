/*
This class is to find the media source.
 */
package sound;

import javafx.scene.media.*;
import javafx.util.Duration;
import java.io.File;

public class Soundplayer{
    public void makesound(String filename){
        String sound = filename;
        Media media = new Media(new File(sound).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
    }
}
