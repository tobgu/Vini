package vini.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryWineNoteStorage implements WineNoteStorage {
	
	private static int wnId;
	private HashMap<Integer, WineNote> wnMap;
	
	public InMemoryWineNoteStorage() {
		wnMap = new HashMap<Integer, WineNote>();
	}
	
	public WineNote getWineNoteWithId(int id) {
		return wnMap.get(id);
	}

	public List<WineNote> getAllWineNotes()
	{
		List<WineNote> wnList = new ArrayList<WineNote>();
		wnList.addAll(wnMap.values());
		return wnList;
	}
	
	public int getUniqueWineNoteId() {
		wnId++;
		return wnId;
	}

	public void storeWineNote(WineNote wn) {
		wnMap.put(wn.getId(), wn);
	}
	
	public void deleteWineNoteWithId(int id) {
		wnMap.remove(id);
	}

}
