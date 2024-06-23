package franxx.code.conf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTest {

    @Test
    void create() throws IOException {
        var resource = new ClassPathResource("/text/me.txt");

        assertNotNull(resource);
        assertTrue(resource.exists());
        assertNotNull(resource.getFile());
        System.out.println(resource.getPath());
    }
}
