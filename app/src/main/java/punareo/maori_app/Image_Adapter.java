package punareo.maori_app;

/**
 * Created by 2002917 on 26/08/2014.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


public class Image_Adapter extends BaseAdapter {
    private Context mContext;
    ArrayList<MenuOption> menu_options;
    //Constructor
    public Image_Adapter(Context c, ArrayList<MenuOption> menu_options)
    {
        this.menu_options = menu_options;
        mContext = c;
    }

    @Override
    public int getCount()
    {
        return menu_options.size();
    }
    @Override
    public MenuOption getItem(int position)
    {
        return menu_options.get(position);
    }

    public long getItemId(int position)
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150,150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(getItem(position).Get_ID());
        return imageView;

    }



}
