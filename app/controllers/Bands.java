package controllers;

import models.Band;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class Bands extends Controller {

	public static Result band(Long band) {
		return ok(views.html.band.band.render(
				User.find.byId((long)3),        // <- tähän oikea käyttäjä
				Band.find.ref(band)
		));
	}
}
