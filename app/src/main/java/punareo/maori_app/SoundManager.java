package punareo.maori_app;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by 21002282 on 14/10/2014.
 */
public class SoundManager {
    private static MediaPlayer mp = null;

    /** Stop old sound and start new one */
    public static void play(Context context, int resource)
    {
        stop(context);

        mp = MediaPlayer.create(context, resource);
        mp.setLooping(false);
        mp.start();
    }

    /** Stop the music */
    public static void stop(Context context)
    {
        if (mp != null)
        {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
