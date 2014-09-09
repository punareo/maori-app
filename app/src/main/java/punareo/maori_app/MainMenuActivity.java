package punareo.maori_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.Menu;
import android.view.MenuItem;

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

public class MainMenuActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

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
        gridview.setAdapter(new ImageAdapter(this, menu_options));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);

                i.putExtra("Category", menu_options.get(position).Get_Cat());
                startActivity(i);
            }
        });
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
