package razzle.ai.java;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import razzle.ai.java.config.RazzleConfig;


@Configuration
@ComponentScan(
	basePackages = {
		"razzle.ai.java",
	}
)
@EnableConfigurationProperties(
	value = {
		RazzleConfig.class,
	}
)
public class RazzleAutoConfiguration {


}
