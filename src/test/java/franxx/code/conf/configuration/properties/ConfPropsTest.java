package franxx.code.conf.configuration.properties;

import franxx.code.conf.converter.StringToDateConv;
import franxx.code.conf.properties.AppProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ConfPropsTest.AppTest.class)
public class ConfPropsTest {

    @Autowired
    private AppProperties properties;

    @Autowired
    private ConversionService conversionService;

    @Test
    void conversionManually() {
        assertTrue(conversionService.canConvert(String.class, Duration.class));
        assertTrue(conversionService.canConvert(String.class, Date.class));

        Duration duration = conversionService.convert("10s", Duration.class);
        assertEquals(Duration.ofSeconds(10), duration);
    }

    @Test
    void create() {
        assertEquals("learn spring boot", properties.getName());
        assertEquals(1, properties.getVersion());
        assertFalse(properties.isProductionMode());
        System.out.println(properties.getName());
        System.out.println(properties.isProductionMode());
    }

    @Test
    void embeddedObject() {
        assertEquals("me", properties.getDatabase().getUsername());
        assertEquals("franxx", properties.getDatabase().getPassword());
        assertEquals("code", properties.getDatabase().getDatabase());
        assertEquals("jdbc:/pp", properties.getDatabase().getUrl());
    }

    @Test
    void collectionTest() {
        assertEquals(List.of("user", "products", "categories"), properties.getDatabase().getWhiteListTables());
        assertEquals(100, properties.getDatabase().getMaxTablesSize().get("user"));
        assertEquals(100, properties.getDatabase().getMaxTablesSize().get("products"));
        assertEquals(100, properties.getDatabase().getMaxTablesSize().get("categories"));
    }

    @Test
    void EmbeddedCollection() {
        System.out.println(properties.getDefaultRoles().get(0).getId());
        assertEquals("default", properties.getDefaultRoles().get(0).getId());
        assertEquals("default role", properties.getDefaultRoles().get(0).getName());
        assertEquals("guess", properties.getDefaultRoles().get(1).getId());
        assertEquals("guess role", properties.getDefaultRoles().get(1).getName());
        assertEquals(2, properties.getDefaultRoles().size());

        assertEquals("admin", properties.getRoles().get("admin").getId());
        assertEquals("admin role", properties.getRoles().get("admin").getName());
        assertEquals("moderator", properties.getRoles().get("moderator").getId());
        assertEquals("moderator role", properties.getRoles().get("moderator").getName());
    }

    @Test
    void testDuration() {
        assertEquals(Duration.ofSeconds(10), properties.getDefaultTimeOut());
    }

    @Test
    void testCustomConverter() {
        Date expiredDate = properties.getExpiredDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2024-08-08", dateFormat.format(expiredDate));
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            AppProperties.class
    })
    @Import(StringToDateConv.class)
    static class AppTest {

        @Bean
        public ConversionService conversionService(StringToDateConv toDateConv) {
            ApplicationConversionService service = new ApplicationConversionService();
            service.addConverter(toDateConv);
            return service;
        }
    }
}
