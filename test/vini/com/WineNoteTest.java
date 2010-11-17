package vini.com;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import vini.com.WineNote;


public class WineNoteTest {

@Test
public void testConstructor(){
	Calendar c = Calendar.getInstance();
	WineNote wn = new WineNote("Gato Negro",
							   2009,
							   c,
							   4,
							   "Cheap but alright taste", 
							   55);
	
	assertTrue("Name", "Gato Negro".equals(wn.getName()));
	assertEquals("Year", 2009, wn.getYear());
	assertEquals("Date tasted", c, wn.getTasteDate());
	assertEquals("Grade", 4, wn.getGrade());
	assertTrue("Comment", "Cheap but alright taste".equals(wn.getComment()));
	assertEquals("ID", 55, wn.getId());
	}

}
