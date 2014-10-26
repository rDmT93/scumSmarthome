package intelligent.home.scum.com.fragmentlistexample;

/**
 * Created by media on 20/10/14.
 */
public class ScumListItem {

    private boolean useList = false;
    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public ScumListItem(String title){
        this.itemTitle = title;
    }

    @Override
    public String toString(){
        return getItemTitle();
    }

}
