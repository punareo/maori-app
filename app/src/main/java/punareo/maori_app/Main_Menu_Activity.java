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



class Menu_Option
{
    private String category;
    private int id;

    public Menu_Option(String category, int id)
    {
        this.category = category;
        this.id = id;
    }

    public String Get_Cat() { return category; }
    public int Get_ID() { return id; }
}


public class Main_Menu_Activity extends Activity
{
    private TextView textView_header;
    private TextView textView_footer;
    private int img_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = R.layout.activity_main_menu_layout_one;



        if (savedInstanceState == null)
        {
            Bundle extra = getIntent().getExtras();
            if (extra != null)
                layout = extra.getInt("Layout");
        }
        else
            layout = savedInstanceState.getInt("Layout");
        setContentView(layout);

        final ArrayList<Menu_Option> menu_options = new ArrayList<Menu_Option>()
        {{
                add(new Menu_Option("Animals", R.drawable.animal));
                add(new Menu_Option("Numbers", R.drawable.number));
                add(new Menu_Option("Shapes", R.drawable.shape));
                add(new Menu_Option("Letters", R.drawable.letter));
                add(new Menu_Option("Colours", R.drawable.colour));
                add(new Menu_Option("Body parts", R.drawable.bodypart));
        }};

        GridView gridview = (GridView) findViewById(R.id.gridview_main);
        gridview.setAdapter(new Image_Adapter(this, menu_options));

        switch (layout)
        {
            case R.layout.activity_main_menu_layout_one :
                img_layout = R.layout.full_image_layout_one;
                break;
            case R.layout.activity_main_menu_layout_two :
                img_layout = R.layout.full_image_layout_two;
                break;
            case R.layout.activity_main_menu_layout_three :
                img_layout = R.layout.full_image_layout_three;
                break;
            default:
                img_layout = R.layout.full_image_layout_one;
        }



        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getApplicationContext(), Full_Image_Activity.class);
                i.putExtra("Layout", img_layout);
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
