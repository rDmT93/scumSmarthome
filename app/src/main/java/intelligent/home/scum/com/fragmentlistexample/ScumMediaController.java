package intelligent.home.scum.com.fragmentlistexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;


public class ScumMediaController extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scum_living_media);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scum_media_controller, menu);
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

    public void indToggle(View view){
        //Toast.makeText(this, "ID: "+view.getId(), Toast.LENGTH_SHORT).show();
        boolean on = ((ToggleButton) view).isChecked();
        ToggleButton all = (ToggleButton) findViewById(R.id.allToggleButton);
        if(on){
            all.setChecked(on);
        }
    }
    public void allToggle(View view){
        boolean on = ((ToggleButton) view).isChecked();
        ToggleButton tv = (ToggleButton) findViewById(R.id.tvToggleButton);
        ToggleButton dvd = (ToggleButton) findViewById(R.id.dvdToggleButton);
        ToggleButton hs = (ToggleButton) findViewById(R.id.hsToggleButton);
        if(on){
            tv.setChecked(on);
            dvd.setChecked(on);
            hs.setChecked(on);
        }else{
            tv.setChecked(false);
            dvd.setChecked(false);
            hs.setChecked(false);
        }
    }
}
