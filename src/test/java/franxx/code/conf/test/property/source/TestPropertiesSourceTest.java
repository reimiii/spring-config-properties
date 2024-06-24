package franxx.code.conf.test.property.source;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@TestPropertySources({
        @TestPropertySource("classpath:/test.properties")
})
@SpringBootTest(classes = TestPropertiesSourceTest.TestApp.class)
public class TestPropertiesSourceTest {

    @Autowired
    private TestApp.SampleProps props;

    @Test
    void create() {
        Assertions.assertEquals("sample test", props.getName());
        Assertions.assertEquals(7, props.getVersion());
    }

    @SpringBootApplication
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
