package punareo.maori_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 2002917 on 9/09/2014.
 * The Activity loaded on initialization
 * Allows users to choose between the Learning and Games sections
 */
public class MainMenuActivity extends Activity {

    //Declaring the controls in the Activity
    private TextView text_header;
    private TextView text_footer;
    Button learning_button;
    Button games_button;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_menu);

        //Initialize the declared text views with the corresponding layout text view
       // Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");
        text_footer = (TextView)findViewById(R.id.text_view_footer);
        text_header = (TextView) findViewById(R.id.text_view_header);
       // text_header.setTypeface(typeface);
       // text_footer.setTypeface(typeface);

       //Initializing the buttons to be used for selecting which section of the app to use
       // Typeface new_typeface = Typeface.createFromAsset(getAssets(),"philly.ttf") ;
        learning_button = (Button) findViewById(R.id.button_design1);
        games_button = (Button) findViewById(R.id.button_design2);


       // learning_button.setTypeface(new_typeface);
      //  games_button.setTypeface(new_typeface);
      //  story_button.setTypeface(new_typeface);

        //Use the OnClickListener event to call the Category Menu Activity
        //The intent extra "Activity" informs the Category Menu which Activity to launch when selected a category
        learning_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( getApplicationContext(), CategoryMenuActivity.class );
                i.putExtra("Layout", R.layout.activity_main_menu_layout_three);
                i.putExtra("Activity", "Learning");
                startActivity(i);
            }
        });

        games_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( getApplicationContext(), CategoryMenuActivity.class );
                i.putExtra("Layout", R.layout.activity_game_main_menu);
                i.putExtra("Activity", "Games");
                startActivity(i);
            }
        });
    }
}
