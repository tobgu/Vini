package vini.com;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vini.com.WineNote;
import vini.com.WineNoteHandler;
import vini.com.WineNoteStorage;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

public class WineNoteHandlerTest {
	private WineNoteHandler wnh;
	private WineNoteStorage wnsm;
	
	@Before
	public void setUp(){
		wnsm = mock(WineNoteStorage.class);
		wnh = new WineNoteHandler(wnsm);
	}
	
	@Test
	public void getEmptyWineNote() {
		when(wnsm.getUniqueWineNoteId()).thenReturn(12);
		
		WineNote wn = wnh.getEmptyWineNote();
		assertTrue("Name", "".equals(wn.getName()));
		assertEquals("Year", 0, wn.getYear());
		assertEquals("Grade", 0, wn.getGrade());
		assertTrue("Comment", "".equals(wn.getComment()));
		assertEquals("ID", 12, wn.getId());
		
		verify(wnsm).getUniqueWineNoteId();
	}
	
	@Test
	public void getWineNoteById()
	{
		WineNote testWn = new WineNote("Gato Negro",
				   2009,
				   Calendar.getInstance(),
				   4,
				   "Cheap but alright taste", 
				   55);
		
		when(wnsm.getWineNoteWithId(34)).thenReturn(testWn);
		WineNote wn = wnh.getWineNoteById(34);

		verify(wnsm).getWineNoteWithId(34);
		assertEquals("Wine Note", testWn, wn);
	}
	
	@Test
	public void updateWineNote() {
		WineNote wn = wnh.getEmptyWineNote();
		wnh.updateWineNote(wn);

		verify(wnsm).getUniqueWineNoteId();
		verify(wnsm).storeWineNote(wn);
	}

	@Test
	public void getAllWineNotes() {
		when(wnsm.getUniqueWineNoteId()).thenReturn(12);

		WineNote wn = wnh.getEmptyWineNote();

		List<WineNote> lIn = new ArrayList<WineNote>();
		lIn.add(wn);
		
		when(wnsm.getAllWineNotes()).thenReturn(lIn);
		
		wnh.updateWineNote(wn);
		List<WineNote> lOut = wnh.getAllWineNotes();
		assertTrue("List content", lOut.contains(wn));
		assertEquals("List length", 1, lOut.size());
		
		verify(wnsm, times(1)).getUniqueWineNoteId();
		verify(wnsm, times(1)).storeWineNote(wn);
		verify(wnsm, times(1)).getAllWineNotes();
	}
	
	@Test
	public void deleteWineNote(){
		when(wnsm.getUniqueWineNoteId()).thenReturn(12);

		WineNote wn = wnh.getEmptyWineNote();
		wnh.updateWineNote(wn);
		wnh.deleteWineNote(wn);
		
		verify(wnsm).deleteWineNoteWithId(12);
	}
}
