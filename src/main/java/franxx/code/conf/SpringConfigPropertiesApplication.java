package franxx.code.conf;

import franxx.code.conf.converter.StringToDateConv;
import franxx.code.conf.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;

@SpringBootApplication
@EnableConfigurationProperties({
        AppProperties.class
})
public class SpringConfigPropertiesApplication {

    @Bean
    public ConversionService conversionService(StringToDateConv toDateConv) {
        ApplicationConversionService service = new ApplicationConversionService();
        service.addConverter(toDateConv);
        return service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigPropertiesApplication.class, args);
    }

}
