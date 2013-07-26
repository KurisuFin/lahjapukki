import models.Band;
import models.User;
import play.Application;
import play.GlobalSettings;


public class Global extends GlobalSettings {
	
	@Override
	public void onStart(Application app) {
		if (User.find.findRowCount() == 0)
			populateDatabase();
	}
	
	
	public static void populateDatabase() {
		Long tiina = User.create("tiina@mail.com", "Tiina", "sala").id;
		Long ville = User.create("ville@mail.com", "Ville", "sala").id;
		Long pekka = User.create("pekka@mail.com", "Pekka", "sala").id;
		Long jaana = User.create("jaana@mail.com", "Jaana", "sala").id;
		Long heini = User.create("heini@mail.com", "Heini", "sala").id;
		
		Band band1 = Band.create("Porukka", tiina);
		band1.add(ville);
		band1.add(pekka);
		
		Band band2 = Band.create("Jengi", pekka);
		band2.add(jaana);
		
		Band band3 = Band.create("Pulkka", tiina);
		band3.add(jaana);
		
		band1.addWish(pekka, "Paljon rahaa");
		band1.addWish(pekka, "Sitäkin enemmän rakkautta");
	}
}

