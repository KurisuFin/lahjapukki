package controllers;

import models.Participation;
import models.Wish;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Wishes extends Controller {

	public static Result add(Long participationID) {
		Wish wish = Wish.create(
				Participation.find.byId(participationID),
		        "täät"
		);
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
