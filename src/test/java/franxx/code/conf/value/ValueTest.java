package franxx.code.conf.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ValueTest.TestApp.class)
public class ValueTest {
    @Autowired
    private TestApp.AppProps props;

    @Autowired
    private TestApp.SystemEnv env;

    @Test
    void create() {
        assertEquals(1, props.getVersion());
        assertFalse(props.isProductionMode());
        assertEquals("learn spring boot", props.getName());
    }

    @Test
    void systemCreate() {
        assertEquals("firefox", env.getBrowser());
    }

    @SpringBootApplication
    static class TestApp {

        @Component
        @Getter
        static class AppProps {

            @Value("${application.name}")
            private String name;

            @Value("${application.version}")
            private Integer version;

            @Value("${application.production-mode}")
            private boolean productionMode;

        }

        @Component
        @Getter
        static class SystemEnv {
            @Value("${BROWSER}")
            private String browser;
        }
    }
}
