package models;

import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

public class BandTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void createAndRetrieveBand() {
		User user = User.create("tina@mail.com", "Tina", "secret");
		Band.create("TestGang", user.id);

		Band band = Band.find.where().eq("name", "TestGang").findUnique();
		assertNotNull(band);
		assertEquals("TestGang", band.name);
		assertEquals(user.id, band.operator.id);
	}

	@Test
	public void addUserToBand() {
		Long tina = User.create("tina@mail.com", "Tina", "secret").id;
		Long jack = User.create("jack@mail.com", "Jack", "secret").id;
		Band band = Band.create("TestGang", tina);
		band.add(jack);

		assertEquals(tina, band.participations.get(0).participant.id);
		assertEquals(jack, band.participations.get(1).participant.id);
	}

	@Test
	public void addTwoUsersToMultipleBands() {
		Long jeff = User.create("jeff@mail.com", "Jeff", "secret").id;
		Long fred = User.create("fred@mail.com", "Fred", "secret").id;

		Band band1 = Band.create("Band1", jeff);
		Band band2 = Band.create("Band2", fred);
		Band band3 = Band.create("Band3", fred);

		band1.add(fred);
		band2.add(jeff);

		assertEquals(2, band1.participations.size());
		assertEquals(2, band2.participations.size());
		assertEquals(1, band3.participations.size());
		assertEquals(2, User.find.byId(jeff).participations.size());
		assertEquals(3, User.find.byId(fred).participations.size());
	}
}
