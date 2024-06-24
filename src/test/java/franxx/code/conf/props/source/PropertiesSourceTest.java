package franxx.code.conf.props.source;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@SpringBootTest(classes = PropertiesSourceTest.TestApp.class)
public class PropertiesSourceTest {

    @Autowired
    private TestApp.SampleProps props;

    @Test
    void create() {
        Assertions.assertEquals("sample", props.getName());
        Assertions.assertEquals(7, props.getVersion());
    }

    @SpringBootApplication
    @PropertySources({
            @PropertySource("classpath:/sample.properties")
    })
    static class TestApp {

        @Component
        @Getter
        static class SampleProps {
            @Value("${the.name}")
            private String name;

            @Value("${the.version}")
            private Integer version;
        }
    }
}
