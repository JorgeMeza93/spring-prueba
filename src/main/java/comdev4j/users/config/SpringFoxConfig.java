package comdev4j.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SpringFoxConfig {
	
	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {
	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.apiInfo(getApiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.mayware.gforks.controllers"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(new ApiInfoBuilder().title("Delivery").description("Doc").build());
	    }
	 
	}
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Dev4J prueba Pipo")
				.version("1.0")
				.license("Apache 2.0")
				.contact(new Contact("@pipo2007", "https://www.pipo.com", "pipo2007@gmail.com"))
				.build();
	}
}
