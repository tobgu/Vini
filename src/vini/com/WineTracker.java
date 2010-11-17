package vini.com;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListAdapter;
import android.widget.Toast;

public class WineTracker extends ListActivity {
	
	private static final int WINE_NOTE_DIALOG = 0;
	private static final int WINE_NOTE_DELETE = 1;
	private WineNote currentWn;
	private WineNoteHandler wnh;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        wnh = new WineNoteHandler(new InMemoryWineNoteStorage());
        
        registerForContextMenu(getListView());
        
        generateDummyWineNotes();
        updateWineNoteList();
        
    }
    
    @Override
    protected Dialog onCreateDialog(int id){
    	switch(id)
    	{
    	case WINE_NOTE_DIALOG:
    		WineNoteDialog wnDialog = new WineNoteDialog(this,
    				new OnReadyListener());
        	return wnDialog;
        
    	case WINE_NOTE_DELETE:
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Are you sure?")
    		       .setCancelable(false)
    		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		                wnh.deleteWineNote(currentWn);
    		                currentWn = null;
    		                updateWineNoteList();
    		           }
    		       })
    		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		                dialog.cancel();
    		           }
    		       });
    		return builder.create();
        default:
        	return null;
    	}
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {    	
        
    	switch(id)
    	{
    	case WINE_NOTE_DIALOG:
    		// TODO: This is not a very pretty way
    		// of doing it. Instead of using currentWn
    		// the Bundle parameter that can be passed
    		// to this function should probably be utilized
    		// in some way instead
            ((WineNoteDialog)dialog).setWineNote(currentWn);
    		break;
        default:
        	break;	
    	}
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
      super.onCreateContextMenu(menu, v, menuInfo);
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.wine_note_item_context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
      switch (item.getItemId()) {
      case R.id.edit_wine_note:
    	currentWn = wnh.getWineNoteById((int)info.id);
    	showDialog(WINE_NOTE_DIALOG);
        return true;
      case R.id.delete_wine_note:
      	currentWn = wnh.getWineNoteById((int)info.id);
      	showDialog(WINE_NOTE_DELETE);
        return true;
      default:
        return super.onContextItemSelected(item);
      }
    }
    
    private void updateWineNoteList(){
        List<WineNote> wnList = wnh.getAllWineNotes();
        ListAdapter adapter = new WineNoteListAdapter(wnList, this);
        getListView().setAdapter(adapter);        	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.add_wine_note:
        	currentWn = wnh.getEmptyWineNote();
        	showDialog(WINE_NOTE_DIALOG);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private class OnReadyListener implements WineNoteDialog.ReadyListener {
        public void ready(WineNote wn) {
        	wnh.updateWineNote(wn);
        	updateWineNoteList();
        }
    }
    
    private void generateDummyWineNotes() {
    	// Create two dummy wine notes
    	WineNote wn = wnh.getEmptyWineNote();
    	wn.setName("Vino Tinto"); 
    	wn.setYear(2007);
    	wn.setGrade(3);
    	wn.setComment("Yuk!");
    	wnh.updateWineNote(wn);
    	
    	wn = wnh.getEmptyWineNote();
    	wn.setName("Black Tower"); 
    	wn.setYear(2009);
    	wn.setGrade(5);
    	wn.setComment("Tasty as hell, at least when...");
    	wnh.updateWineNote(wn);
    }	

}