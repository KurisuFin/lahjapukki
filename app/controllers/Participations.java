package controllers;

import play.mvc.Controller;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class Participations extends Controller {


}
