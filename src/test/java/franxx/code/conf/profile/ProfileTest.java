package franxx.code.conf.profile;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ProfileTest.TestApp.class)
@ActiveProfiles({"production"})
public class ProfileTest {

    @Autowired
    private TestApp.Greeting greeting;

    @Autowired
    private TestApp.EnvProfile profile;

    @Test
    void create() {
        Assertions.assertEquals("hai from production", greeting.greeting());
    }

    @Test
    void getProfiles() {
        Assertions.assertArrayEquals(new String[]{"production"}, profile.getProfiles());
    }

    @SpringBootApplication
    static class TestApp {

        @Component
        static class EnvProfile implements EnvironmentAware {

            @Setter
            private Environment environment;

            public String[] getProfiles() {
                return environment.getActiveProfiles();
            }
        }

        @Component
        @Profile({"local"})
        static class TheLocal implements Greeting {

            @Override
            public String greeting() {
                return "hai from local";
            }
        }

        @Component
        @Profile({"production"})
        static class TheProduction implements Greeting {

            @Override
            public String greeting() {
                return "hai from production";
            }
        }

        interface Greeting {
            String greeting();
        }
    }
}
