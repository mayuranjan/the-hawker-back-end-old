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
public class InventoryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(InventoryApplication.class).initializers(new HawkerApplicationContextInitializer())
				.run(args);
	}
}
