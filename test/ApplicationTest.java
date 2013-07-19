import org.junit.*;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;


public class ApplicationTest {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}


}
