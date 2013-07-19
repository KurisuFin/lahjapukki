package controllers;

import models.Band;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Bands extends Controller {

	public static Result band(Long band) {
		return ok(views.html.band.band.render(
				User.find.byId(Application.getUserID()),
				Band.find.ref(band)
		));
	}
}
