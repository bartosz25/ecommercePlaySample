package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.running;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;

public class CategoryControllerTest {

    @Test
    public void checkProductsList() {
    	/**
    	 *  FakeApplication is a kind of mocked application context. Without it we won't able to run controllers. By running them
    	 *  by simple execution (Result result = CategoryController.showOne(1, 1)) you'll receive RuntimeExceptions of following
    	 *  type:
    	 *  <pre>
    	 *  java.lang.RuntimeException: There is no started application
    	 *  </pre>
    	 */  
		FakeApplication fakeApp = Helpers.fakeApplication();
		
		final Map<String, String> threads = new HashMap<String, String>();
		threads.put("current", Thread.currentThread().getName());
		running(fakeApp, new Runnable() {
			@Override
			public void run() {
				/**
				 * We need to use once again a JPA.withTransaction to avoid following RuntimeException:
				 * <pre>
				 * java.lang.RuntimeException: No EntityManager bound to this thread. Try wrapping 
				 * this call in JPA.withTransaction, or ensure that the HTTP context is setup on this thread.
				 * </pre>
				 */
		    	try {
    				JPA.withTransaction(new F.Function0<Void>() {
    					@Override
    					public Void apply() throws Throwable {
    						Result result = CategoryController.showOne(1, 1);
    						String stringResult = contentAsString(result);
    						// check if bread and butter are present as products
    						String[] expectedProducts = {"<a href=\"/categories/1/products/bread_1\">bread</a>", 
    								"<a href=\"/categories/1/products/butter_2\">butter</a>"};
    						for (String expectedProduct : expectedProducts) {
    							assertTrue("Response should contain "+expectedProduct+" but it doesn't", stringResult.contains(expectedProduct));
    						}
    						threads.put("runnable", Thread.currentThread().getName());
							return null;
    					}
    				});
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		
		// Even if the test case is written as Runnable anonymous class, it will be executed in the same thread as 
		// the main thread. So they won't be the concurrency testing issues and synchronization problems between
		// the main test thread and other testing threads. Usually they need to be synchronized manually with
		// CountDownLatch, CyclicBarrier or another technique. 
		// You can learn more about synchronization in multi-threading environment through this article:
		// <a href="http://www.waitingforcode.com/java/java-concurrency/tests-inmulti-threading-environment-with-junit/read">Tests in multi-threading environment with JUnit</a>
		assertTrue("Test case within running() should be executed in the same thread as the main ("+threads.get("current")+") "+
				"but it wasn't ("+threads.get("runnable")+")", threads.get("current").equals(threads.get("runnable")));
	}

}
