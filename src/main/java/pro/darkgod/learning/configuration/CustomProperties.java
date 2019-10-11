package pro.darkgod.learning.configuration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "custom")
@PropertySource(value = "classpath:custom.properties")
@Validated
public class CustomProperties {

    @NotNull
    @Min(10)
    @Max(31)
    private Integer saltRounds;

    Integer getSaltRounds() {
        return saltRounds;
    }

    public void setSaltRounds(Integer saltRounds) {
        this.saltRounds = saltRounds;
    }
}
