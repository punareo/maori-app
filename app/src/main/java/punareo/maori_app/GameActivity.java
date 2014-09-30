package punareo.maori_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class GameActivity extends Activity {

    public List<Content_Object> content_object_list;
    TextView question_text;
    ImageButton button_1;
    ImageButton button_2;
    ImageButton button_3;
    ImageButton button_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button_1 = (ImageButton) findViewById(R.id.imageButton);
        button_2 = (ImageButton) findViewById(R.id.imageButton2);
        button_3 = (ImageButton) findViewById(R.id.imageButton3);
        button_4 = (ImageButton) findViewById(R.id.imageButton4);
        question_text = (TextView) findViewById(R.id.textView2);

        try {
            InputStream in = getResources().openRawResource(R.raw.learningslides);
            content_object_list = new ArrayList<Content_Object>(XML_Parser.Get_Instance().Parse(this, in, "Numbers"));
        }
        catch (XmlPullParserException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (SAXException e) { e.printStackTrace(); }

        List<ImageButton> button_list = new ArrayList<ImageButton>();

        button_list.add( button_1 );
        button_list.add( button_2 );
        button_list.add( button_3 );
        button_list.add( button_4 );

        List<Integer> answer_list = new ArrayList<Integer>();

        int index = rand_num_in_range(0, content_object_list.size() - 1 );

        Content_Object chosen_object = content_object_list.get( index );

        question_text.setText("Whiriwhiri te kau " + chosen_object.getName() + "?");
        answer_list.add( chosen_object.Get_Img_ID() );

        do {
            Boolean is_chosen = false;
            index = rand_num_in_range(0, content_object_list.size() - 1 );
            for( int i = 0; i <= answer_list.size() - 1; i++ ) {
                if( content_object_list.get( index ).Get_Img_ID() == answer_list.get( i ) )
                    is_chosen = true;
            }
            if( !is_chosen )
                answer_list.add( content_object_list.get( index ).Get_Img_ID() );
        } while (answer_list.size() != 4 );

        Collections.shuffle( answer_list );

        for( int i = 0; i <= answer_list.size() - 1; i++ ) {
            button_list.get( i ).setImageResource( answer_list.get( i ) );
        }
    }

    public static int rand_num_in_range( int min, int max ) {
        Random rand = new Random();
        return rand.nextInt( ( max - min) + 1) + min;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
