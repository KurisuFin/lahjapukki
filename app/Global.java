
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
			Ebean.save((List) Yaml.load("test-data.yml"));
		}
	}




/* VARALTA, JOS JOSSAIN VÄLISSÄ test-data.yml KUSEE
	public static void populateDatabase() {
		User tiina = new User("tiina@mail.com", "Tiina", "sala");
		User ville = new User("ville@mail.com", "Ville", "sala");
		User pekka = new User("pekka@mail.com", "Pekka", "sala");
		User jaana = new User("jaana@mail.com", "Jaana", "sala");
		User heini = new User("heini@mail.com", "Heini", "sala");
		tiina.save();
		ville.save();
		pekka.save();
		jaana.save();
		heini.save();

		Band band1 = Band.create("Porukka", tiina.id);
		Band band2 = Band.create("Porukka", pekka.id);

		band1.add(ville.id);
		band1.add(pekka.id);

		band2.add(jaana.id);
		band2.add(heini.id);
	}
*/
}

