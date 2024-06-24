package franxx.code.conf.profile.properties;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ProfilePropsTest.AppTest.class)
@ActiveProfiles({"production", "test"})
public class ProfilePropsTest {

    @Autowired
    private AppTest.SampleProfile profile;

    @Test
    void create() {
        Assertions.assertEquals("default", profile.getDefaultFile());
        Assertions.assertEquals("test", profile.getTestFile());
        Assertions.assertEquals("production", profile.getProductionFile());
    }

    @SpringBootApplication
    static class AppTest {

        @Component
        @Getter
        static class SampleProfile {

            @Value("${profile.default}")
            private String defaultFile;

            @Value("${profile.production}")
            private String productionFile;

            @Value("${profile.test}")
            private String testFile;

        }
    }
}
