package punareo.maori_app;

/**
 * Created by 2002917 on 26/08/2014.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LearningActivity extends Activity
    implements GestureDetector.OnGestureListener,
                GestureDetector.OnDoubleTapListener
    {
    private TextView text_view;
    private TextView textView_footer;
    private GestureDetectorCompat gDetector;
    private ImageView image_view;
    private int index = 0;
    private List<Content_Object> content_object_list;

        private ImageView left_button;
        private ImageView right_button;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image_layout_one);

        this.gDetector = new GestureDetectorCompat(this, this);
        gDetector.setOnDoubleTapListener(this);
        String category = "";
        if (savedInstanceState == null)
        {
            Bundle extra = getIntent().getExtras();
            if (extra == null)
                category = null;
            else
                category = extra.getString("Category");
        }
        else
            category = (String) savedInstanceState.getSerializable("Category");


        image_view = (ImageView) findViewById(R.id.full_image_view);
        text_view = (TextView) findViewById(R.id.text_view_header_main);
        textView_footer =(TextView) findViewById((R.id.text_view_footer_main));

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");

        text_view.setTypeface(typeface);
        textView_footer.setTypeface(typeface);

        try {
            InputStream in = getResources().openRawResource(R.raw.learningslides);
            content_object_list = new ArrayList<Content_Object>(XML_Parser.Get_Instance().Parse(this, in, category));
            Change_View(0);
        }
        catch (XmlPullParserException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (SAXException e) { e.printStackTrace(); }




        left_button =(ImageView)findViewById(R.id.leftbutton);
        right_button =(ImageView) findViewById(R.id.rightbutton);

        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Change_View(-1);
            }
        });

        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Change_View(1);
            }
        });

    }


    public void Change_View(int increment)
    {
        index += increment;
        if (index > content_object_list.size() - 1)
            index = 0;
        else if (index < 0)
            index = content_object_list.size() - 1;

        text_view.setText(content_object_list.get(index).getName());
        textView_footer.setText(content_object_list.get(index).getName());
        image_view.setImageResource(content_object_list.get(index).Get_Img_ID());
        try { content_object_list.get(index).Play_Sound(); }
            catch (IOException e) { e.printStackTrace(); }
    }

    //Required Gesture Detection Methods
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.gDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {

        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        if (velocityX < 0)
            Change_View(1);
        else if (velocityX > 0)
            Change_View(-1);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event)
    {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY)
    {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event)
    {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event)
    {
        try { content_object_list.get(index).Play_Sound(); }
        catch (IOException e) { e.printStackTrace(); }
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event)
    {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event)
    {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event)
    {
        return true;
    }
}
