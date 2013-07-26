package controllers;

import models.Band;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import static play.data.Form.form;

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
/*
	public static Result add() {
		Form<Band> bandForm = form(Band.class).bindFromRequest();

		if (bandForm.hasErrors()) {
			return badRequest();
		} else {
			return ok(views.html.bands.index.render(
					User.find.byId(Application.getUserID()),
					bandForm.get(),
					Band.find.all()
			));
		}
	}
*/
}
