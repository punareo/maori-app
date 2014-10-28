package punareo.maori_app;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by 21002282 on 14/10/2014.
 * A static MediaPlayer class that can take a sound file resource id and play it
 * Stops the current playing sound if a new is started
 */
public class SoundManager {
    private static MediaPlayer mp = null;

    public static void play(Context context, int resource) {
        stop(context);

        mp = MediaPlayer.create(context, resource);
        mp.setLooping(false);
        mp.start();
    }
    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
