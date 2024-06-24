package franxx.code.conf.resource;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@SpringBootTest(classes = ResourceLoaderTest.TestApp.class)
public class ResourceLoaderTest {

    @Autowired
    private TestApp.SampleResource sampleResource;

    @Test
    void createResourceLoader() throws Exception {
        Assertions.assertEquals("Meee", sampleResource.getText().trim());

    }

    @SpringBootApplication
    static class TestApp {

        @Component
        static class SampleResource implements ResourceLoaderAware {

            @Setter
            private ResourceLoader resourceLoader;

            public String getText() throws Exception {
                Resource resource = resourceLoader.getResource("classpath:/text/me.txt");
                try (InputStream inputStream = resource.getInputStream()) {
                    return new String(inputStream.readAllBytes());
                }
            }
        }
    }
}
