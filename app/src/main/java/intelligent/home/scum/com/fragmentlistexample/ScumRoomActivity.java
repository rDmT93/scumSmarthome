package intelligent.home.scum.com.fragmentlistexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class ScumRoomActivity extends Activity {

    List<String> mNames = Arrays.asList("Kitchen", "Bathroom", "Living Room", "Floor", "Bathroom");

    static final int ITEMS_PER_ROW = 2; // Two Columns for the Name of the Room and each Lights

    String roomTitle;

    String title;
    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scum_room);
        /*
            Set the Name of the Actionbar
         */
        Bundle bnd = getIntent().getExtras();
        if (bnd != null){
            title = bnd.getString("choice");
            getActionBar().setTitle(title);
        }
        /*
            Creating the Tables for the Rooms
         */
        TableLayout layout = new TableLayout(this);
        layout.setPadding(15, 15, 15, 15);
        // Layout for the Table
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);

        layout.setLayoutParams(lp);
        layout.setStretchAllColumns(true);

        int index = 0;

        while (index < mNames.size()) {

            TableRow tr = new TableRow(this);
            for (int k = 0; k < ITEMS_PER_ROW ; k = k+1) {
                if (k == 0){
                    final TextView btn = new TextView(this);
                    btn.setText(mNames.get(index));
                    btn.setTextSize(18);
                    setRoomTitle(mNames.get(index));
                    btn.isClickable();
                    btn.setBackgroundColor(Color.parseColor("#dddddd"));
                    btn.setPadding(10,10,10,10);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Starting an Individual Room Activity after clicking on one Room
                            // put the Names of the Rooms in a Bundle
                            Toast.makeText(ScumRoomActivity.this, "Test", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(ScumRoomActivity.this, ScumIndividualRoom.class);
                            intent.putExtra("choice", getRoomTitle());
                            startActivity(intent);

                        }
                    });
                    TableRow.LayoutParams params = new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0,0,10,0);

                    btn.setLayoutParams(params);
                    tr.addView(btn);
                }else {
                    Switch aSwitch = new Switch(this);
                    aSwitch.setText("Lights");
                    aSwitch.setPadding(0,20,10,0);
                    tr.addView(aSwitch);
                }
            }

            index++;
            layout.addView(tr);

        }

        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        super.setContentView(scroll);
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


}
