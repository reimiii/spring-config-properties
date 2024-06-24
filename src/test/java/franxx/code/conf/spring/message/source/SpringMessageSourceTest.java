package franxx.code.conf.spring.message.source;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@SpringBootTest(classes = SpringMessageSourceTest.TestApp.class)
public class SpringMessageSourceTest {

    @Autowired
    private TestApp.SampleSource sampleSource;

    @Test
    void create() {
        Assertions.assertEquals("Default Mee", sampleSource.testLang(Locale.ENGLISH));
        Assertions.assertEquals("Indonesia Mee", sampleSource.testLang(new Locale("id")));
    }

    @SpringBootApplication
    static class TestApp {

        @Component
        static class SampleSource implements MessageSourceAware {
            @Setter
            private MessageSource messageSource;

            public String testLang(Locale locale) {
                return messageSource.getMessage("hello", new Object[]{"Mee"}, locale);
            }
        }
    }
}
