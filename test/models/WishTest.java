package models;

import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import java.util.ArrayList;
import java.util.List;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

public class WishTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));

		Long wendy = User.create("wendy@mail.com", "Wendy", "secret").id;
		Long jacob = User.create("jacob@mail.com", "Jacob", "secret").id;
		Long aaron = User.create("aaron@mail.com", "Aaron", "secret").id;
		Long caren = User.create("caren@mail.com", "Caren", "secret").id;

		Band band1 = Band.create("Band1", wendy);
		Band band2 = Band.create("Band2", jacob);

		band1.add(aaron);
		band2.add(wendy);
	}


	@Test
	public void addWishToNewUser() {
		Long wendy = User.find.where().eq("email", "wendy@mail.com").findUnique().id;
		Long jacob = User.find.where().eq("email", "wendy@mail.com").findUnique().id;
		Long aaron = User.find.where().eq("email", "wendy@mail.com").findUnique().id;
		Long caren = User.find.where().eq("email", "wendy@mail.com").findUnique().id;

		Band band1 = Band.find.where().eq("name", "Band1").findUnique();
		Band band2 = Band.find.where().eq("name", "Band2").findUnique();

		List<Wish> wishes = new ArrayList<>();
		wishes.add( createWish(band1.id, wendy, "B1W1-wish") );
		wishes.add( createWish(band1.id, wendy, "B1W2-wish") );
		wishes.add( createWish(band1.id, wendy, "B1W3-wish") );
		wishes.add( createWish(band2.id, wendy, "B2W1-wish") );
		wishes.add( createWish(band2.id, jacob, "B2J1-wish") );
		wishes.add( createWish(band1.id, aaron, "B1A1-wish") );
		wishes.add( createWish(band1.id, aaron, "B1A2-wish") );

		wishes.add( createWish(band1.id, jacob, "B1J1-wish") );
		wishes.add( createWish(band1.id, caren, "B1C1-wish") );
		wishes.add( createWish(band2.id, caren, "B2C1-wish") );

		// kolme viimeistä pitäisi olla null (ei toteutettu)

		// printDatabase();
	}

	private Wish createWish(Long bandID, Long userID, String name) {
		return Wish.create(
				Participation.findParticipation(bandID, userID),
				name
		);
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

		System.out.println("\n----- Wishes -----");
		for (Wish wish : Wish.find.all()) {
			System.out.println(wish);
		}

		System.out.println("\n----- Participations -----");
		for (Participation participation : Participation.find.all()) {
			System.out.println(participation);
		}

		System.out.println("\n----- LOPPU -----");
	}

}
