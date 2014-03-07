package controllers;

import models.Participation;
import models.Wish;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import static play.data.Form.*;

@Security.Authenticated(Secured.class)
public class Wishes extends Controller {

	public static Result add(Long participationID) {
		Form<Wish> wishForm = form(Wish.class).bindFromRequest();

		return ok();
	}

	public static Result delete(Long id) {
		if (Secured.isOwnerOfWish(id)) {
			Wish.find.byId(id).delete();
			return ok();
		} else {
			return forbidden();
		}
	}
}
