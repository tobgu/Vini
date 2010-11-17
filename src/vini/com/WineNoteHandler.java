package vini.com;


import java.util.GregorianCalendar;
import java.util.List;

public class WineNoteHandler {
	private WineNoteStorage wns;

	public WineNoteHandler(WineNoteStorage wns) {
		this.wns = wns;
	}
	
	public WineNote getEmptyWineNote(){
		WineNote wn = new WineNote("",
								   0,
								   new GregorianCalendar(),
								   0,
								   "", 
								   this.wns.getUniqueWineNoteId());		
		return wn;
	}

	public void updateWineNote(WineNote wn)
	{
		wns.storeWineNote(wn);
	}
	
	public List<WineNote> getAllWineNotes()
	{
		return wns.getAllWineNotes();
	}
	
	public WineNote getWineNoteById(int id){
		return wns.getWineNoteWithId(id);
	}
	
	public void deleteWineNote(WineNote wn){
		wns.deleteWineNoteWithId(wn.getId());
	}
	
}
