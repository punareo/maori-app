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
    private String my_category;
    private int my_id;

    public MenuOption(String category, int id)
    {
        this.my_category = category;
        this.my_id = id;
    }

    public String get_category() { return my_category; }
    public int get_id() { return my_id; }
}


public class CategoryMenuActivity extends Activity
{
    private TextView header_textview;
    private TextView footer_textview;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null)
                activity = null;
            else {
                activity = extra.getString("Activity");
                setContentView( extra.getInt( "Layout" ) );
            }
        }
        else {
            activity = (String) savedInstanceState.getSerializable( "Activity" );
            setContentView( (Integer) savedInstanceState.getSerializable( "Layout" ) );
        }


        final ArrayList<MenuOption> menu_options = new ArrayList<MenuOption>()
        {{
                add(new MenuOption("Kararehe", R.drawable.buttonanimal));
                add(new MenuOption("Tau", R.drawable.buttonnumber));
                add(new MenuOption("Ahua", R.drawable.buttonshape));
                add(new MenuOption("Wakapu", R.drawable.buttonletter));
                add(new MenuOption("Ra o te Wiki", R.drawable.buttondaysoftheweek));
                add(new MenuOption("Tae", R.drawable.buttoncolour));
        }};

        GridView gridview = (GridView) findViewById(R.id.gridview_main);
        gridview.setAdapter(new ImageAdapter(this, menu_options));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent();

                if( activity.equals( "Learning" ) )
                    i = new Intent(getApplicationContext(), LearningActivity.class);
                else if( activity.equals( "Games" ) ) {
                    i = new Intent(getApplicationContext(), GameActivity.class);
                }
                i.putExtra("Category", menu_options.get(position).get_category());
                startActivity(i);
            }
        });

        footer_textview =(TextView) findViewById(R.id.imageView_main_Footer);
        header_textview =(TextView) findViewById(R.id.imageView_header_main);
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"testtext.TTF");

       // header_textview.setTypeface(typeface);
       // footer_textview.setTypeface(typeface);
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
