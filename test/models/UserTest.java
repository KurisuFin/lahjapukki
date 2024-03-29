package models;

import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

public class UserTest extends WithApplication{
	
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void createAndRetrieveUser() {
		new User("bob@mail.com", "Bob", "secret").save();
		User participant = User.find.where().eq("email", "bob@mail.com").findUnique();
		assertNotNull(participant);
		assertEquals("bob@mail.com", participant.email);
		assertEquals("Bob", participant.name);
	}

}
