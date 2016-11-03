package hr.tvz.serviceplanner.configuration;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan("hr.tvz.serviceplanner")
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.build();
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ServicePlannerAPI")
				.description("API endpoints for the ServicePlanner application")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/bkomocar/ServicePlannerAPI/blob/master/LICENSE")
				.version("2.0")
				.build();
	}

}