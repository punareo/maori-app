package punareo.maori_app;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by 21002282 on 28/08/2014.
 */
public class Content_Object
{
    private String name;
    private int img_id;
    private int snd_id;
    private Context c;
    private MediaPlayer player;

    public Content_Object (Context c, String name, String img, String snd)
    {
        this.c = c;
        this.name = name;
        Set_Img_ID(img);
        Set_Snd_ID(snd);
        player = new MediaPlayer();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Set_Img_ID(String img)
    {
        this.img_id = c.getResources().getIdentifier(img, null, c.getPackageName());
    }

    public int Get_Img_ID()
    {
        return img_id;
    }

    public void Set_Snd_ID(String snd)
    {
        this.snd_id = c.getResources().getIdentifier(snd, null, c.getPackageName());
    }

    public int Get_Snd_ID()
    {
        return snd_id;
    }

    public void Play_Sound() throws IOException
    {
        player = MediaPlayer.create(c, snd_id);
        player.start();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer player) {
                player.release();
            }
        });
    }
}
