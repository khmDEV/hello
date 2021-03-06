package es.unizar.webeng.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.HashMap;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

// JUnit4 invoke SpringJUnit4ClassRunner class
@RunWith(SpringJUnit4ClassRunner.class)
/*
 * Class used to determinate how to load and configure an ApplicationContext
 * for integration tests
 */
@SpringApplicationConfiguration(classes = Application.class)
public class UnitTest {

	// Set the default value from the config file: application.properties
	@Value("${app.message:Hello World}")
	private String message;

	// Place an instance of HelloController into field
	@Autowired
	private HelloController controller;

	/**
		* The function run the unity tests, if a test fail, the function
		* will throw a Exception
		*
		* @param
		* @return
		* @throws Exception
		*/
    @Test
    public void testMessage() throws Exception {
    	HashMap<String,Object> map = new HashMap<>();
    	String view = controller.welcome(map);
			Date now = new Date();

			// Check that the messages are correct
    	assertThat(view, is("wellcome"));
    	assertThat(map.containsKey("message"), is(true));

			// Check that the time field contain a Date object
    	assertThat(map.get("time"), instanceOf(Date.class));

			// Check that the Date is correct
			Date date=((Date)map.get("time"));
			assertThat(date.after(now), is(false));

    }
}
