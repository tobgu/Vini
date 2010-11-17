package vini.com;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { WineNoteHandlerTest.class, 
	                   WineNoteTest.class,
	                   InMemoryWineNoteStorageTest.class})

public class AllTests {
}

