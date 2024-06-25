package franxx.code.conf.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("application")
public class AppProperties {
    private String name;
    private Integer version;
    private boolean productionMode;
    private Database database;

    @Getter
    @Setter
    public static class Database {
        private String username;
        private String password;
        private String database;
        private String url;
    }
}
