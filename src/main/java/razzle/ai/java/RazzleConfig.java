package razzle.ai.java;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * created by julian on 09/02/2023
 */
@Data
@Validated
@ConfigurationProperties(prefix = "razzle.ai.config")
public class RazzleConfig {


    @NotEmpty(message = "Razzle Api key is required. Missing razzle.ai.config.api-key")
    private String apiKey;


    @NotEmpty(message = "Razzle App ID is required. Missing razzle.ai.config.app-id")
    private String appId;


    private boolean requiresAuth;


}
