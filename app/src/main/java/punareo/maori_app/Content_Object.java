package punareo.maori_app;

/**
 * Created by 21002282 on 28/08/2014.
 */
public class Content_Object
{
    private String name;
    private String img_file_path;
    private String sound_file_path;

    public Content_Object(String name, String img, String sound)
    {
        this.name = name;
        this.img_file_path = img;
        this.sound_file_path = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_file_path() {
        return img_file_path;
    }

    public void setImg_file_path(String img_file_path) {
        this.img_file_path = img_file_path;
    }

    public String getSound_file_path() {
        return sound_file_path;
    }

    public void setSound_file_path(String sound_file_path) {
        this.sound_file_path = sound_file_path;
    }
}
