package vini.com;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vini.com.InMemoryWineNoteStorage;
import vini.com.WineNote;


public class InMemoryWineNoteStorageTest {
	
	private InMemoryWineNoteStorage imwns;
	
	private WineNote createDummyWineNote(String wineType, int id)
	{
		if(wineType.toLowerCase().equals("tinto"))
		{
			return new WineNote("Vino Tinto", 
								2007,
								Calendar.getInstance(),	
								3,
								"Yuk!",
								id);
		}
		else
		{
			return new WineNote("Black Tower", 
								2009,
								Calendar.getInstance(),
								5,
								"Tasty as hell, at least when...",
								id);
		}
	}
	
	@Before
	public void setUp(){
		imwns = new InMemoryWineNoteStorage();
	}
	
	@Test
	public void getAllWineNotesNoNotes()
	{
		List<WineNote> l = imwns.getAllWineNotes();
		assertEquals("List Size", 0, l.size());
	}

	@Test
	public void getUniqueWineNoteId() {
		assertFalse("ID uniqueness",
				    imwns.getUniqueWineNoteId() == imwns.getUniqueWineNoteId());
	}

	@Test
	public void storeAndRetrieveOneWineNote() {
		WineNote wn = createDummyWineNote("tinto", 3);
		
		imwns.storeWineNote(wn);
		List<WineNote> l = imwns.getAllWineNotes();
		assertEquals("Wine Note", wn, l.get(0));
		assertEquals("List size", 1, l.size());
	}

	@Test
	public void storeAndRetrieveTwoWineNotes() {
		WineNote wn1 = createDummyWineNote("tinto", 3);		
		WineNote wn2 = createDummyWineNote("tower", 5);
		
		imwns.storeWineNote(wn1);
		imwns.storeWineNote(wn2);
		
		List<WineNote> l = imwns.getAllWineNotes();
		// TODO: Fix this test, it's a bit undeterministic
		assertEquals("Wine Note 1", wn1, l.get(0));
		assertEquals("Wine Note 2", wn2, l.get(1));
		assertEquals("List size", 2, l.size());
	}

	@Test
	public void getWineNoteWithId() {
		WineNote wnIn = createDummyWineNote("tinto", 55);

		imwns.storeWineNote(wnIn);
		WineNote wnOut = imwns.getWineNoteWithId(55);
		assertEquals("Wine Notes", wnIn, wnOut);
	}
	
	@Test
	public void deleteWineNoteWithId() {
		WineNote wnIn = createDummyWineNote("tinto", 55);

		imwns.storeWineNote(wnIn);
		WineNote wnOut = imwns.getWineNoteWithId(55);
		assertEquals("Wine Notes", wnIn, wnOut);

		imwns.deleteWineNoteWithId(55);
		wnOut = imwns.getWineNoteWithId(55);
		assertEquals("Wine Notes", null, wnOut);
	}
	

}
