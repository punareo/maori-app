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


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.animal, R.drawable.bodypart,
            R.drawable.colour, R.drawable.letter,
            R.drawable.number, R.drawable.shape,

    };
    //Constructor
    public ImageAdapter(Context c)
    {
        mContext = c;
    }

    @Override
    public int getCount()
    {
        return mThumbIds.length;
    }
    @Override
    public Object getItem(int position)
    {
        return mThumbIds[position];
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

        imageView.setImageResource(mThumbIds[position]);
        return imageView;

    }



}
