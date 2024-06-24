package franxx.code.conf.configuration.properties;

import franxx.code.conf.properties.AppProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ConfPropsTest.AppTest.class)
public class ConfPropsTest {

    @Autowired
    private AppProperties properties;

    @Test
    void create() {
        assertEquals("learn spring boot", properties.getName());
        assertEquals(1, properties.getVersion());
        assertFalse(properties.isProductionMode());
        System.out.println(properties.getName());
        System.out.println(properties.isProductionMode());
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            AppProperties.class
    })
    static class AppTest {
    }
}
