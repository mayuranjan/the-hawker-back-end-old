package co.in.hawker;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import co.in.hawker.web.config.HawkerApplicationContextInitializer;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class CustomerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CustomerApplication.class).initializers(new HawkerApplicationContextInitializer())
				.run(args);
	}
}
