
import com.avaje.ebean.Ebean;
import models.Band;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;


public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		if (User.find.findRowCount() == 0) {
//			Ebean.save((List) Yaml.load("test-data.yml"));
			populateDatabase();
		}
	}


	public static void populateDatabase() {
		User tiina = User.create("tiina@mail.com", "Tiina", "sala");
		User ville = User.create("ville@mail.com", "Ville", "sala");
		User pekka = User.create("pekka@mail.com", "Pekka", "sala");
		User jaana = User.create("jaana@mail.com", "Jaana", "sala");
		User heini = User.create("heini@mail.com", "Heini", "sala");

		Band band1 = Band.create("Porukka", tiina.id);
		band1.add(ville.id);
		band1.add(pekka.id);

		Band band2 = Band.create("Jengi", pekka.id);
		band2.add(jaana.id);
	}

}

