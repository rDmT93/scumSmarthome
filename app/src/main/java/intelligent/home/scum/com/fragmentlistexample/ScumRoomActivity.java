package intelligent.home.scum.com.fragmentlistexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import android.os.Handler;

public class ScumRoomActivity extends Activity {

    List<String> mNames = Arrays.asList("Kitchen", "Bathroom", "Living Room", "Floor");

    static final int ITEMS_PER_ROW = 2; // Two Columns for the Name of the Room and each Lights

    String roomTitle;

    // Saving Time
    private TextView counter;
    private long startTime = 0L;
    private Handler handler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    // Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";
    SharedPreferences sharedPreferences;

    String title = "All Rooms";
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

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        getActionBar().setTitle(title);
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

        TextView text = new TextView(this);
        text.setText(title);
        text.setPadding(0,10,0,0);
        text.setTextSize(30);
        text.setTypeface(null, Typeface.BOLD);
        layout.addView(text);
        int index = 0;

        while (index < mNames.size()) {
            roomTitle = mNames.get(index);
            TableRow tr = new TableRow(this);
            for (int k = 0; k < ITEMS_PER_ROW ; k = k+1) {
                if (k == 0){
                    final TextView btn = new TextView(this);
                    btn.setText(mNames.get(index));
                    btn.setTextSize(18);
                    btn.setId(index);
                    btn.isClickable();
                    btn.setBackgroundColor(Color.parseColor("#dddddd"));
                    btn.setPadding(10,10,10,10);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Starting an Individual Room Activity after clicking on one Room
                            // put the Names of the Rooms in a Bundle
                            Toast.makeText(ScumRoomActivity.this, "Roomname: "+btn.getText(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ScumRoomActivity.this, ScumIndividualRoom.class);
                            intent.putExtra("choice", btn.getText());
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

                    final Switch aSwitch = new Switch(this);
                    aSwitch.setText("Lights");
                    aSwitch.setId(index);
                    aSwitch.setPadding(0, 20, 10, 0);
                    /**
                     * When switching the Button
                     */
                    aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                editor.putBoolean("switch_true", true);
                                editor.commit();
                                //counter.setText("test");
                                startTime = SystemClock.uptimeMillis();
                                handler.postDelayed(updatedTimeThread, 0);
                                Toast.makeText(ScumRoomActivity.this, aSwitch.getId()+": ON", Toast.LENGTH_SHORT).show();
                            }else{
                                editor.putBoolean("switch_false", false);
                                editor.commit();
                                timeSwapBuff += timeInMilliseconds;
                                handler.removeCallbacks(updatedTimeThread);
                                Toast.makeText(ScumRoomActivity.this, aSwitch.getId()+": OFF", Toast.LENGTH_SHORT).show();
                            }
                        }

                        private Runnable updatedTimeThread = new Runnable() {
                            @Override
                            public void run() {
                                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
                                updatedTime = timeSwapBuff + timeInMilliseconds;
                                int secs = (int) (updatedTime / 1000);
                                int mins = secs/60;
                                secs = secs % 60;
                                counting(mins, secs);
                                //Toast.makeText(ScumRoomActivity.this, ""+mins+": "+String.format("%02d", secs), Toast.LENGTH_SHORT).show();
                                handler.postDelayed(this, 0);
                            }


                        };

                        public void counting(int mins, int secs){
                            TextView view = (TextView) counter.findViewById(counter.getId());
                            view.setText("" + mins + ": " + String.format("%02d", secs));
                        }
                    });
                    tr.addView(aSwitch);
                }
            }

            index++;
            layout.addView(tr);

        }

        counter = new TextView(this);
        counter.setId(index);
        layout.addView(counter);


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
