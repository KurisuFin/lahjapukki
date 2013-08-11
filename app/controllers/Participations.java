package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Participations extends Controller {


}
