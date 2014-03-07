package controllers;

import models.Band;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Bands extends Controller {

	public static Result index(Long band) {
		return ok(views.html.bands.index.render(
				User.find.byId(Application.getUserID()),
				Band.find.ref(band),
				Band.find.all()
		));
	}

	public static Result addForm() {
		return ok(views.html.bands.add.render(
				User.find.byId(Application.getUserID()),
				Band.find.all()
		));
	}

	public static Result delete(Long id) {
		Band.find.byId(id).delete();
		return ok();
	}
}
