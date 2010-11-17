package vini.com;

import java.util.List;

public interface WineNoteStorage {
	public WineNote getWineNoteWithId(int id);
	public List<WineNote> getAllWineNotes();
	public int getUniqueWineNoteId();
	public void storeWineNote(WineNote wn);
	public void deleteWineNoteWithId(int id);
}
