package punareo.maori_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
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

/*A class that controls the game section of the application
*Calls the XMLParser to populate a list of ContentObjects
*Randomly generates question from that list
*Tracks correct answers and after 10 question displays the accuracy of the user
*/
public class GameActivity extends Activity {

    private List<ContentObject> content_object_list;
    private List<ImageButton> button_list;
    private List<ContentObject> answer_list;

    private static int questions_asked;
    private static int correct_answers;

    //Declaring controls
    private TextView score_display;
    public ContentObject chosen_question;
    private TextView text_header;

    TextView question_text;
    ImageButton button_1;
    ImageButton button_2;
    ImageButton button_3;
    ImageButton button_4;

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        //Retrieving the String value of 'Category' from the previous Activity
        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null)
                category = null;
            else
                category = extra.getString("Category");
        }
        else
            category = (String) savedInstanceState.getSerializable("Category");

      //  Typeface typeface =Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");

        text_header = (TextView) findViewById(R.id.text_view_header);
        score_display = (TextView) findViewById(R.id.text_view_score);
       // score_display.setTypeface(typeface);
       // text_header.setTypeface(typeface);


        button_1 = (ImageButton) findViewById(R.id.image_button_NW);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentObject button_obj = (ContentObject) button_1.getTag();
                try {
                    button_obj.play_sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.get_image_id() == chosen_question.get_image_id() ) {
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
                ContentObject button_obj = (ContentObject) button_2.getTag();
                try {
                    button_obj.play_sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.get_image_id() == chosen_question.get_image_id() ) {
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
                ContentObject button_obj = (ContentObject) button_3.getTag();
                try {
                    button_obj.play_sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.get_image_id() == chosen_question.get_image_id() ) {
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
                ContentObject button_obj = (ContentObject) button_4.getTag();
                try {
                    button_obj.play_sound();
                } catch ( IOException e ) { e.printStackTrace(); }
                if( button_obj.get_image_id() == chosen_question.get_image_id() ) {
                    right_answer();
                }
                else
                    wrong_answer();
            }
        });

        question_text = (TextView) findViewById(R.id.text_view_question);
       // question_text.setTypeface(typeface);

        try {
            InputStream in = getResources().openRawResource(R.raw.learningslides);
            content_object_list = new ArrayList<ContentObject>(XMLParser.get_instance().parse_xml(this, in, category));
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
        questions_asked = 1;
        correct_answers = 0;
    }

    public void generate_question() {
        answer_list = new ArrayList<ContentObject>();

        int index = rand_num_in_range(0, content_object_list.size() - 1 );

        chosen_question = content_object_list.get( index );

        if( category.equals("Arapū") )
            question_text.setText( "Whiria te mea i tīmata ki te pū "  + chosen_question.get_my_name());
        else if( category.equals("Tau") || category.equals("Tae") )
            question_text.setText( "Whiria te " + category.toLowerCase() + " " + chosen_question.get_my_name().toLowerCase() + "?");
        else if( category.equals("Kararehe")|| category.equals("Ahua") )
            question_text.setText( "Whiria te " + chosen_question.get_my_name().toLowerCase() + "?" );
        else if( category.equals("Ra o te Wiki") ) {
            String ordinal_number = "";
            switch( content_object_list.indexOf( chosen_question ) ) {
                case 0 :
                    ordinal_number = "tuatahi";
                    break;
                case 1 :
                    ordinal_number = "tuarua";
                    break;
                case 2 :
                    ordinal_number = "tuatoru";
                    break;
                case 3 :
                    ordinal_number = "tuawhā";
                    break;
                case 4 :
                    ordinal_number = "tuarima";
                    break;
                case 5 :
                    ordinal_number = "tuaono";
                    break;
                case 6 :
                    ordinal_number = "tuawhitu";
                    break;
                default :
                    break;
            }
            question_text.setText( " Whiria te rā " + ordinal_number + " o te Wiki?" );
        }


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
            button_list.get( i ).setImageResource(answer_list.get( i ).get_image_id() );
            button_list.get( i ).setTag( answer_list.get( i ) );
        }
        questions_asked++;
        score_display.setText( String.valueOf(correct_answers) );
    }

    public void right_answer() {
        correct_answers++;
        if( questions_asked > 10 )
            game_over();
        else
            generate_question();
    }

    public void wrong_answer() {
        if( questions_asked > 10 )
            game_over();
        else
            generate_question();
    }

    public void game_over() {
        score_display.setText("");
        new AlertDialog.Builder(this)
            .setTitle( "Kua mutu" )
            .setMessage( "Ko āu māka " + correct_answers + " I te " + questions_asked + ".\nMe mai anō koe?" )
            .setPositiveButton("Āe", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    initialize_game();
                    generate_question();
                }
            })
            .setNegativeButton("Kāore", new DialogInterface.OnClickListener() {
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


}
