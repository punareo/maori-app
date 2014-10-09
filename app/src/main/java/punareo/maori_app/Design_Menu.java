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
public class Design_Menu extends Activity {

    private TextView text_header;
    private TextView text_footer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_menu);

        

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");
        text_footer = (TextView)findViewById(R.id.text_view_footer);
        text_header = (TextView) findViewById(R.id.text_view_header);
        text_header.setTypeface(typeface);
        text_footer.setTypeface(typeface);

        Button test = (Button) findViewById(R.id.button_design1);
        Button test2 = (Button) findViewById(R.id.button_design2);
        Button test3 = (Button) findViewById(R.id.button_design3);

        test.setTypeface(typeface);
        test2.setTypeface(typeface);
        test3.setTypeface(typeface);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Main_Menu_Activity.class);
                i.putExtra("Layout", R.layout.activity_main_menu_layout_one);
                startActivity(i);
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("Layout", R.layout.activity_game);
                startActivity(i);
            }
        });

        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Main_Menu_Activity.class);
                i.putExtra("Layout", R.layout.activity_main_menu_layout_three);
                startActivity(i);
            }
        });
    }
}
