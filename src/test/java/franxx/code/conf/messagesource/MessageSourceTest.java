package franxx.code.conf.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@SpringBootTest(classes = MessageSourceTest.TestApplication.class)
public class MessageSourceTest {
//    private ApplicationContext applicationContext;
//
//    private MessageSource messageSource;
//
//    @BeforeEach
//    void setUp() {
//        applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
//        messageSource = applicationContext.getBean(MessageSource.class);
//    }

    @Autowired
    private MessageSource messageSource;

    @Test
    void testDefaultLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Eko"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Eko", message);
    }

    @Test
    void testIndonesianLocale() {
        String message = messageSource.getMessage("hello",
                new Object[]{"Eko"},
                new Locale("id", "ID"));

        Assertions.assertEquals("Halo Eko", message);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasenames("i18n/my");
            return messageSource;
        }

    }

}
