package co.in.hawker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private Environment env;
	
	//@formatter:off
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.groupName(env.getProperty("swagger.groupName"))
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Hawker REST API with Swagger")
			.description(env.getProperty("swagger.moduleName") + "Hawker REST API with Swagger")
			.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
			.license("Apache License Version 2.0")
			.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
			.version("1.0")
			.build();
	}
	//@formatter:on
}
