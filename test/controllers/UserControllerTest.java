package controllers;

import static org.junit.Assert.*;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.running;

import java.util.HashMap;
import java.util.Map;

import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;

import play.libs.F;
import play.libs.F.Callback;
import play.mvc.Result;

import play.test.*;
import static play.test.Helpers.*;
public class UserControllerTest  {

	private static final int SERVER_PORT = 3333;
	
	/**
	 * Checks if anonymous user can't see dashboard page. If anonymous user accesses a resource behind
	 *  /user path (as for example /user/dashboard), he should be redirected to connexion page (/login).
	 */
	@Test
	public void dashboardAccessDenied() {
	    running(testServer(SERVER_PORT), HTMLUNIT, new Callback<TestBrowser>() {
	        public void invoke(TestBrowser browser) {
        		// indicate to browser to access protected resource as anonymous user
	        	browser.goTo("http://localhost:3333/user/dashboard");

				// we check if connection form is present at the page. find method supports CSS selectors.
				assertTrue("Page should end with /login path", browser.url().endsWith("/login"));
				FluentList<FluentWebElement> loginField = browser.find("#login");
				FluentList<FluentWebElement> passwordField = browser.find("#login");
				assertTrue("Login field should be found on the page but it wasn't", loginField != null && loginField.size() == 1);
				assertTrue("Password field should be found on the page but it wasn't", passwordField != null && passwordField.size() == 1);
	        }
	    });
	}
	
	/**
	 * Checks if connected user can see dashboard page.
	 */
	@Test
	public void dashboardConnection() {
		running(testServer(SERVER_PORT), HTMLUNIT, new Callback<TestBrowser>() {
	        public void invoke(TestBrowser browser) {
	            browser.goTo("http://localhost:3333/user/dashboard");
	            
	            // we check if connection form is present at the page
	            assertTrue("Page should end with /login path", browser.url().endsWith("/login"));
	            FluentList<FluentWebElement> loginField = browser.find("#login");
	            FluentList<FluentWebElement> passwordField = browser.find("#login");
	            assertTrue("Login field should be found on the page but it wasn't", loginField != null && loginField.size() == 1);
	            assertTrue("Password field should be found on the page but it wasn't", passwordField != null && passwordField.size() == 1);

	            // fill connection form with user data and submit the form at the end - always using CSS selectors
	            browser.fill("#login").with("bartosz2");
	            browser.fill("#password").with("bartosz2");
	            browser.submit("#loginForm");
	            
	            // check if user was correctly connected
	            assertTrue("Page should end with /user/dashboard path", browser.url().endsWith("/user/dashboard"));
	        }
	    });
	}

}
