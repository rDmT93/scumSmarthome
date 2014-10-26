package intelligent.home.scum.com.fragmentlistexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by media on 20/10/14.
 */
public class ScumListAdapter extends ArrayAdapter{

    private Context context;
    private boolean useList = true;
    public ScumListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }
    /** * Holder for the list items. */
    private class ViewHolder{
        TextView titleText;
        //Switch aSwitch;
    }
    /** *
     * @param position
     * @param convertView
     * @param parent
     * @return */
     public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder = null;
         ScumListItem item = (ScumListItem) getItem(position);
         View viewToUse = null;
         // This block exists to inflate the settings list item conditionally based on whether
         // we want to support a grid or list view.
         LayoutInflater mInflater = (LayoutInflater) context .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
         if (convertView == null) {
             if(useList){
                 viewToUse = mInflater.inflate(R.layout.scum_list_item, parent, false);

             }
             else {
                 viewToUse = mInflater.inflate(R.layout.scum_grid_item, parent, false);
             }
             holder = new ViewHolder();
             holder.titleText = (TextView) viewToUse.findViewById(R.id.exampleTitle);
             viewToUse.setTag(holder);
         } else {
             viewToUse = convertView;
             holder = (ViewHolder) viewToUse.getTag();
         }
         holder.titleText.setText(item.getItemTitle());

         return viewToUse;
     }
}
