package com.botapeer.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.botapeer.exception.ResponseError;
import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private TypeResolver typeResolver;

	private List<Response> ErrorResponsneWithAuth = new ArrayList<>() {
		{
			add(new ResponseBuilder()
					.code("401")
					.description("Unauthorized")
					.build());
			add(new ResponseBuilder()
					.code("400")
					.description("Bad Request")
					.representation(MediaType.APPLICATION_JSON)
					.apply(r -> r.model(m -> m.referenceModel(ref -> ref
							.key(k -> k.qualifiedModelName(q -> q.namespace(ResponseError.class.getPackageName())
									.name(ResponseError.class.getSimpleName()))))))
					.build());
		}
	};

	private List<Response> ErrorResponsne = new ArrayList<>() {
		{
			add(new ResponseBuilder()
					.code("400")
					.description("Bad Request")
					.representation(MediaType.APPLICATION_JSON)
					.apply(r -> r.model(m -> m.referenceModel(ref -> ref
							.key(k -> k.qualifiedModelName(q -> q.namespace(ResponseError.class.getPackageName())
									.name(ResponseError.class.getSimpleName()))))))
					.build());
		}
	};

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("users")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com"))
				.paths(PathSelectors.regex("/api/users/*"))
				.build()
				.useDefaultResponseMessages(false)
				.additionalModels(typeResolver.resolve(ResponseError.class))
				.globalResponses(HttpMethod.POST, ErrorResponsneWithAuth)
				.globalResponses(HttpMethod.PUT, ErrorResponsneWithAuth)
				.globalResponses(HttpMethod.DELETE, ErrorResponsneWithAuth)
				.globalResponses(HttpMethod.GET, ErrorResponsne);
	}

	@Bean
	public Docket authApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("auth")
				.select()
				.paths(PathSelectors.regex("/api/auth/*"))
				.build()
				.useDefaultResponseMessages(false)
				.additionalModels(typeResolver.resolve(ResponseError.class))
				.globalResponses(HttpMethod.POST, ErrorResponsne);
	}

}
