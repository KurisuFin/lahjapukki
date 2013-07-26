package controllers;

import models.User;
import org.junit.*;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.*;
import java.util.*;

import play.mvc.*;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;
import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;

public class LoginTest extends WithApplication {
	
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
		Ebean.save((List) Yaml.load("test-data.yml"));
	}
	
	@Test
	public void tryAuthenticateUser() {
		User.create("bob@mail.com", "Bob", "secret");
		
		assertNotNull(User.authenticate("bob@mail.com", "secret"));
		assertNull(User.authenticate("bob@mail.com", "badpassword"));
		assertNull(User.authenticate("tom@mail.com", "secret"));
	}
	
	@Test
	public void authenticateSuccess() {
		Result result = callAction(
				controllers.routes.ref.Application.authenticate(),
				fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
						"email", "jack@mail.com",
						"password", "secret"))
		);
		
		assertEquals(303, status(result));
		Long userID = Long.parseLong(session(result).get("userID"));
		assertEquals("jack@mail.com", User.find.byId(userID).email);
	}
	
	@Test
	public void authenticateFailure() {
		Result result = callAction(
				controllers.routes.ref.Application.authenticate(),
				fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
						"email", "jack@mail.com",
						"password", "badpassword"))
		);
		assertEquals(400, status(result));
		assertNull(session(result).get("userID"));
	}
	
	@Test
	public void authenticated() {
		Long bellaID = User.create("bella@mail.com", "Bella", "secret").id;
		
		Result result = callAction(
				controllers.routes.ref.Application.index(),
				fakeRequest().withSession("userID", ""+bellaID)
		);
		assertEquals(200, status(result));
	}
	
	@Test
	public void notAuthenticated() {
		Result result = callAction(
				controllers.routes.ref.Application.index(),
				fakeRequest()
		);
		assertEquals(303, status(result));
		assertEquals("/login", header("Location", result));
	}
}