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
		User tina = User.create("tina@mail.com", "Tina", "secret");
		User jack = User.create("jack@mail.com", "Jack", "secret");
		Band band = Band.create("TestGang", tina.id);

		band.add(jack.id);
		assertEquals(tina.id, band.participations.get(0).id);
		assertEquals(jack.id, band.participations.get(1).id);
	}

	@Test
	public void addTwoUsersToMultipleBands() {
		User jeff = User.create("jeff@mail.com", "Jeff", "secret");
		User fred = User.create("fred@mail.com", "Fred", "secret");

		jeff = User.find.byId(jeff.id);
		fred = User.find.byId(fred.id);

		Band band1 = Band.create("Band1", jeff.id);
		Band band2 = Band.create("Band2", fred.id);
		Band band3 = Band.create("Band3", fred.id);

		band1.add(fred.id);
		band2.add(jeff.id);

		assertEquals(2, band1.participations.size());
		assertEquals(2, band2.participations.size());
		assertEquals(1, band3.participations.size());
		assertEquals(2, jeff.participations.size());
		assertEquals(3, fred.participations.size());

//		printDatabase();
	}

	public static void printDatabase() {
		System.out.println("\n----- Users -----");
		for (User user : User.find.all()) {
			System.out.println(user);
		}

		System.out.println("\n----- Bands -----");
		for (Band band : Band.find.all()) {
			System.out.println(band);
		}
		System.out.println("----- LOPPU -----");
	}

}
