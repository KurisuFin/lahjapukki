package models;

import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import java.util.List;

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
	public void createNewBand() {
		User user = User.create("testi@mail.com", "Testi", "salasana");
		Band.create("Testiporukka", user.id);

		Band band = Band.find.where().eq("name", "Testiporukka").findUnique();
		assertNotNull(band);
		assertEquals("Testiporukka", band.name);
		assertEquals(user, band.operator);


		System.out.println("\n----- Users -----");
		for (User user : User.find.all()) {
			System.out.println(user);
		}

		System.out.println("\n----- Bands -----");
		for (Band band : Band.find.all()) {
			System.out.println(band);
		}
	}

}
