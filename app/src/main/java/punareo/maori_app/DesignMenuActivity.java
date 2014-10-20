package punareo.maori_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 2002917 on 9/09/2014.
 */
public class DesignMenuActivity extends Activity {

    private TextView text_header;
    private TextView text_footer;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_menu);

       // Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");
        text_footer = (TextView)findViewById(R.id.text_view_footer);
        text_header = (TextView) findViewById(R.id.text_view_header);
       // text_header.setTypeface(typeface);
       // text_footer.setTypeface(typeface);

       // Typeface new_typeface = Typeface.createFromAsset(getAssets(),"philly.ttf") ;
        Button learning_button = (Button) findViewById(R.id.button_design1);
        Button games_button = (Button) findViewById(R.id.button_design2);
        Button story_button = (Button) findViewById(R.id.button_design3);

       // learning_button.setTypeface(new_typeface);
      //  games_button.setTypeface(new_typeface);
      //  story_button.setTypeface(new_typeface);

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


        story_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), CategoryMenuActivity.class);
                i.putExtra("Layout", R.layout.activity_main_menu_layout_three);
                startActivity(i);
            }
        });
    }
}
