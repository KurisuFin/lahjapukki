package controllers;

import models.Band;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.admin.*;

@Security.Authenticated(Secured.class)
public class Admin extends Controller {

	public static Result index() {
		printDatabase();

		return ok(index.render(Band.find.all()));
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
	}
}
