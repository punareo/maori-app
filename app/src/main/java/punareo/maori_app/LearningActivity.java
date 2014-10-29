package punareo.maori_app;

/**
 * Created by 2002917 on 26/08/2014.
 * The Activity for the Learning section of the app
 * Allows users to view the objects of a category
 * Users can cycle between the objects, or play the sound clip pronouncing the name of the object
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

//The LearningActivity implements GestureDetectors for double tap and swiping functionality
public class LearningActivity extends Activity
    implements GestureDetector.OnGestureListener,
                GestureDetector.OnDoubleTapListener
    {
    //Declaring controls used by the Activity
    private TextView header_textview;
    private TextView main_textview;
    private TextView footer_textview;
    private GestureDetectorCompat gesture_detector;
    private ImageView image_view;
    private ImageView left_button;
    private ImageView right_button;

    //Declaring important attributes of the Activity
    // index keeps track of the current object to display/interact with from the content_object_list
    private int index = 0;
    private List<ContentObject> content_object_list;


    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.full_image_layout_one );

        //Initializing gesture detectors
        gesture_detector = new GestureDetectorCompat( this, this );
        gesture_detector.setOnDoubleTapListener( this );

        //This is used to find the string value passed by the previous Activity to represent which category the user has chosen
        String category = "";
        if( savedInstanceState == null ) {
            Bundle extra = getIntent().getExtras();
            if( extra != null )
                category = extra.getString("Category");
        }
        else
            category = (String) savedInstanceState.getSerializable("Category");

        //Initialize the controls
        main_textview = (TextView) findViewById(R.id.text_view_main);
        image_view = (ImageView) findViewById (R.id.full_image_view );
        header_textview = (TextView) findViewById( R.id.text_view_header_main );
        footer_textview =(TextView) findViewById( R.id.text_view_footer_main );

       // Typeface typeface =Typeface.createFromAsset(getAssets(),"font/testtext.ttf");

      //  header_textview.setTypeface( typeface );
     //   footer_textview.setTypeface( typeface );

        //Call the XMLParser to load the content_object_list with objects referencing the information about objects in the application
        //Uses the string 'category' to determine which objects are parsed by the XMLParser
        //Calls change_view with an argument of 0 to not change the index
        try {
            InputStream i_stream = getResources().openRawResource( R.raw.learningslides );
            content_object_list = new ArrayList<ContentObject>( XMLParser.get_instance().parse_xml( this, i_stream, category ) );
            change_view(0);
        }
        catch ( XmlPullParserException e ) { e.printStackTrace(); }
        catch ( IOException e ) { e.printStackTrace(); }
        catch ( SAXException e ) { e.printStackTrace(); }

        //Initialize the buttons
        //Set the OnClickListener to call change_view with a -1 argument, to move down the content_object_list
        //Set the OnClickListener to call change_view with a 1 argument, to move up the content_object_list
        left_button =(ImageView)findViewById( R.id.leftbutton );
        right_button =(ImageView) findViewById( R.id.rightbutton );
        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_view(-1);
            }
        });
        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_view(1);
            }
        });
    }

    //A function that changes the index that tracks the active object in the content_object_list
    //@param int increment represents the change in value of the index
    //When the index reaches 0 or the max size, it sets the index to the other extreme: 0 - 1 = max. max + 1 = 0
    //Displays the name, the picture and plays the sound of the object at the index value of 'index' of the content_object_list
    public void change_view( int increment ) {
        index += increment;
        if (index > content_object_list.size() - 1)
            index = 0;
        else if (index < 0)
            index = content_object_list.size() - 1;

        header_textview.setText( content_object_list.get(index).get_my_name() );
        footer_textview.setText( content_object_list.get(index).get_my_name() );
        image_view.setImageResource( content_object_list.get(index).get_image_id() );
        try { content_object_list.get( index ).play_sound(); }
            catch (IOException e) { e.printStackTrace(); }
    }

    //Required Gesture Detection Methods
    //Removing any of them will cause issues due to how Java handles classes implementing other classes
    @Override
    public boolean onTouchEvent( MotionEvent event ) {
        this.gesture_detector.onTouchEvent( event );
        return super.onTouchEvent( event );
    }

    @Override
    public boolean onDown( MotionEvent event ) {
        return true;
    }

    //Using the parameters velocityX a quick check is done to see which way the user is 'flinging'
    //Flinging left is a negative velocity and flinging right is a positive velocity
    //Call change_view with appropriate values -1 for left, 1 for right
    @Override
    public boolean onFling( MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY ) {
        if( velocityX < 0 )
            change_view(1);
        else if( velocityX > 0 )
            change_view(-1);
        return true;
    }

    @Override
    public void onLongPress( MotionEvent event ) { }

    @Override
    public boolean onScroll( MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY )
    {
        return true;
    }

    @Override
    public void onShowPress( MotionEvent event ) { }

    @Override
    public boolean onSingleTapUp( MotionEvent event ) {
        try { content_object_list.get(index).play_sound(); }
        catch (IOException e) { e.printStackTrace(); }
        return true;
    }

    @Override
    public boolean onDoubleTap( MotionEvent event )
    {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent( MotionEvent event)
    {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed( MotionEvent event )
    {
        return true;
    }
}
