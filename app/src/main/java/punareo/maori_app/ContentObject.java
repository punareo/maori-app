package punareo.maori_app;

import android.content.Context;

import java.io.IOException;

/**
 * Created by 21002282 on 28/08/2014.
 * This class is used to store information referencing the file path of images and sound files *
 * These represent the different objects portrayed in the games
 */
public class ContentObject {
    private String my_name;
    private int my_image_id;
    private int my_sound_id;
    private Context my_context;

    public ContentObject( Context context, String name, String image, String sound ) {
        my_context = context;
        my_name = name;
        set_image_id(image);
        set_sound_id(sound);
    }

    public String get_my_name() {
        return my_name;
    }

    public void set_my_name( String name ) {
        my_name = name;
    }

    public void set_image_id( String image ) {
        //Assigns the resource id to my_image_id of the sound file whose name matches 'image'
        my_image_id = my_context.getResources().getIdentifier( image, null, my_context.getPackageName() );
    }

    public int get_image_id() {
        return my_image_id;
    }

    public void set_sound_id( String sound_id ) {
        //Assigns the resource id to my_sound_id of the sound file whose name matches 'sound_id'
        my_sound_id = my_context.getResources().getIdentifier(sound_id, null, my_context.getPackageName());
    }

    //Calls the SoundManager class' function 'play' with the object's my_sound_id resource as the argument
    public void play_sound() throws IOException
    {
        SoundManager.play(my_context, my_sound_id);
    }
}
