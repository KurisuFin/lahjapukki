package controllers;

import models.Band;
import models.User;
import play.data.Form;
import play.mvc.*;
import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {
	
	@Security.Authenticated(Secured.class)
    public static Result index() {
	    return ok(index.render(
			User.find.byId(getUserID()),
			Band.find.all()
        ));
    }
	
	public static Long getUserID() {
		return Long.parseLong(request().username());
	}
	
	
	public static Result login() {
		return ok(
				login.render(form(Login.class))
		);
	}
	
	public static class Login {
		public String email;
		public String password;
		
		public String validate() {
			if (User.authenticate(email, password) == null)
				return "Invalid user or password";
			else
				return null;
		}
	}
	
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			session().clear();
			session("userID", ""+User.findID(loginForm.get().email));
			return redirect(
					routes.Application.index()
			);
		}
	}
	
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(
				routes.Application.login()
		);
	}
	
	/*
	public static Result javascriptRoutes() {
		response().setContentType("text/javascript");
		return ok(
				Routes.javascriptRouter("jsRoutes",
						routes.javascript.Bands.band()
				));
	}
	*/
}
