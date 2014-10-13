package punareo.maori_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

//Menu 3 with full image 1 - no exit button

class MenuOption
{
    private String category;
    private int id;

    public MenuOption(String category, int id)
    {
        this.category = category;
        this.id = id;
    }

    public String Get_Cat() { return category; }
    public int Get_ID() { return id; }
}


public class CategoryMenuActivity extends Activity
{
    private TextView textView_header;
    private TextView textView_footer;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if( extras != null ) {
            activity = extras.getString( "Activity" );
            setContentView( extras.getInt( "Layout" ) );
        }

        final ArrayList<MenuOption> menu_options = new ArrayList<MenuOption>()
        {{
                add(new MenuOption("Animals", R.drawable.buttonanimal));
                add(new MenuOption("Numbers", R.drawable.buttonnumber));
                add(new MenuOption("Shapes", R.drawable.buttonshape));
                add(new MenuOption("Letters", R.drawable.buttonletter));
                add(new MenuOption("DaysoftheWeek", R.drawable.buttondaysoftheweek));
                add(new MenuOption("Colours", R.drawable.buttoncolour));

        }};

        GridView gridview = (GridView) findViewById(R.id.gridview_main);
        gridview.setAdapter(new Image_Adapter(this, menu_options));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent();

                if( activity.equals( "Learning" ) )
                    i = new Intent(getApplicationContext(), LearningActivity.class);
                else if( activity.equals( "Games" ) )
                    i = new Intent(getApplicationContext(), GameActivity.class);

                i.putExtra("Category", menu_options.get(position).Get_Cat());
                startActivity(i);
            }
        });

        textView_footer =(TextView) findViewById(R.id.imageView_main_Footer);
        textView_header =(TextView) findViewById(R.id.imageView_header_main);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/testtext.ttf");

        textView_header.setTypeface(typeface);
        textView_footer.setTypeface(typeface);
    }















                          //Options Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

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
