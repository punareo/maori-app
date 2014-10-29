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

    //A function that plays a sound one per call
    //@param Context context represents the context of the Activity calling the function
    //@param int resource represents an Android resource ID which points to a file in the Resources directory
    public static void play(Context context, int resource) {
        stop(context);

        mp = MediaPlayer.create(context, resource);
        mp.setLooping(false);
        mp.start();
    }

    //A function that stops sounds that are currently playing
    //@param Context context represents the context of the Activity calling the function
    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
