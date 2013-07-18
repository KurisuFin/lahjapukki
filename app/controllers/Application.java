package controllers;

import models.Band;
import models.User;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {
  
    public static Result index() {
	    return ok(index.render(
			User.find.byId((long)3)        // <- tähän oikea käyttäjä
        ));
    }
}
