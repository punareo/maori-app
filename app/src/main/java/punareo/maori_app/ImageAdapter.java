package punareo.maori_app;

/**
 * Created by 2002917 on 26/08/2014.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<Menu_Option> menu_options;
    //Constructor
    public ImageAdapter(Context c)
    {
        mContext = c;
    }

    @Override
    public int getCount()
    {
        return menu_options.size();
    }
    @Override
    public Object getItem(int position)
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
            imageView.setLayoutParams(new GridView.LayoutParams(120,120));
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
