package franxx.code.conf.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application")
public class AppProperties {
    private String name;
    private Integer version;
    private boolean productionMode;
    private Database database;
    private List<Role> defaultRoles;
    private Map<String, Role> roles;
    private Duration defaultTimeOut;
    private Date expiredDate;

    @Getter
    @Setter
    public static class Database {
        private String username;
        private String password;
        private String database;
        private String url;
        private List<String> whiteListTables;
        private Map<String, Integer> maxTablesSize;
    }

    @Getter
    @Setter
    public static class Role {
        private String id;
        private String name;
    }
}
