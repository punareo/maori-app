package punareo.maori_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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


    private List<ImageButton> button_list;
    private List<Content_Object> answer_list;

    private static int the_score;
    private static int fail_count;
    private TextView score_display;
    public Content_Object chosen_question;
    private TextView text_header;
    private List<Content_Object> content_object_list;

    TextView question_text;
    ImageButton button_1;
    ImageButton button_2;
    ImageButton button_3;
    ImageButton button_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

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

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");

        text_header = (TextView) findViewById(R.id.text_view_header);
        score_display = (TextView) findViewById(R.id.text_view_score);
        score_display.setTypeface(typeface);
        text_header.setTypeface(typeface);


        button_1 = (ImageButton) findViewById(R.id.image_button_NW);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content_Object button_obj = (Content_Object) button_1.getTag();
                try {
                    button_obj.Play_Sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.Get_Img_ID() == chosen_question.Get_Img_ID() ) {
                    right_answer();
                }
                else
                    wrong_answer();
            }
        });

        button_2 = (ImageButton) findViewById(R.id.image_button_NE);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content_Object button_obj = (Content_Object) button_2.getTag();
                try {
                    button_obj.Play_Sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.Get_Img_ID() == chosen_question.Get_Img_ID() ) {
                    right_answer();
                }
                else
                    wrong_answer();
            }
        });

        button_3 = (ImageButton) findViewById(R.id.image_button_SE);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content_Object button_obj = (Content_Object) button_3.getTag();
                try {
                    button_obj.Play_Sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.Get_Img_ID() == chosen_question.Get_Img_ID() ) {
                    right_answer();
                }
                else
                    wrong_answer();
            }
        });
        button_4 = (ImageButton) findViewById(R.id.image_button_SW);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content_Object button_obj = (Content_Object) button_4.getTag();
                try {
                    button_obj.Play_Sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.Get_Img_ID() == chosen_question.Get_Img_ID() ) {
                    right_answer();
                }
                else
                    wrong_answer();
            }
        });

        question_text = (TextView) findViewById(R.id.text_view_question);
        question_text.setTypeface(typeface);

        try {
            InputStream in = getResources().openRawResource(R.raw.learningslides);
            content_object_list = new ArrayList<Content_Object>(XML_Parser.Get_Instance().Parse(this, in, category));
        }
        catch (XmlPullParserException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        catch (SAXException e) { e.printStackTrace(); }

         button_list = new ArrayList<ImageButton>();

        button_list.add( button_1 );
        button_list.add( button_2 );
        button_list.add( button_3 );
        button_list.add( button_4 );

        initialize_game();
        generate_question();
    }

    public void initialize_game() {
        the_score = 0;
        fail_count = 0;
    }

    public void generate_question() {
        answer_list = new ArrayList<Content_Object>();

        int index = rand_num_in_range(0, content_object_list.size() - 1 );

        chosen_question = content_object_list.get( index );

        question_text.setText("Whiriwhiri te "+ chosen_question.getName());
        answer_list.add( chosen_question );

        do {
            Boolean is_chosen = false;
            index = rand_num_in_range(0, content_object_list.size() - 1 );
            for( int i = 0; i <= answer_list.size() - 1; i++ ) {
                if( content_object_list.get( index ) == answer_list.get( i ) )
                    is_chosen = true;
            }
            if( !is_chosen )
                answer_list.add( content_object_list.get( index ) );
        } while (answer_list.size() != 4 );

        Collections.shuffle( answer_list );

        for( int i = 0; i <= answer_list.size() - 1; i++ ) {
            button_list.get( i ).setImageResource(answer_list.get( i ).Get_Img_ID() );
            button_list.get( i ).setTag( answer_list.get( i ) );
        }
    }

    public void right_answer() {
        the_score += 100;
        score_display.setText("" + the_score);
        generate_question();
    }

    public void wrong_answer() {
        fail_count += 1;
        if( fail_count == 3 )
            game_over();
        else
            generate_question();
    }

    public void game_over() {
        score_display.setText("");
        new AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("Your score is " + the_score + ".\nWould you like to play again?")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    initialize_game();
                    generate_question();
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                public void onClick( DialogInterface dialog, int which) {
                    finish();
                }
            })
            .setIcon( android.R.drawable.ic_dialog_alert )
            .show();
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
