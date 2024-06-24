package franxx.code.conf.application.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = AppPropertiesTest.TestApp.class)
public class AppPropertiesTest {

    @Autowired
    private Environment environment;

    @Test
    void testCreate() {
        String property = environment.getProperty("application.name");
        Assertions.assertEquals("learn spring boot", property);
    }

    @SpringBootApplication
    static class TestApp {
    }
}
