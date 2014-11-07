package intelligent.home.scum.com.fragmentlistexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class ScumIndividualRoom extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Bundle bnd = getIntent().getExtras();
        if (bnd != null){
            String title = bnd.getString("choice");
            if((title != null) && !title.equals("Lights")){
                getActionBar().setTitle(title);
                if(title.equals("Living Room")){
                    setContentView(R.layout.scum_living);
                }
                if(title.equals("Kitchen")){
                    setContentView(R.layout.scum_kitchen);
                }
                if(title.equals("Bathroom")){
                    setContentView(R.layout.scum_bathroom);
                }
            }else{
                return;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void goToMedia(View view){
        Intent intent = new Intent(this, ScumMediaController.class);
        startActivity(intent);
    }

}
