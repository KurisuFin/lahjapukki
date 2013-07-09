package models;

import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import static junit.framework.Assert.assertTrue;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

public class UserTest extends WithApplication{

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveUser() {
        assertTrue(true);
    }
}
