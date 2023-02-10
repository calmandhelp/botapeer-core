package com.botapeer.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {

	//	@Autowired
	//	private TypeResolver typeResolver;

	//	private List<Response> ErrorResponsneWithAuth = new ArrayList<>() {
	//		{
	//			add(new ResponseBuilder()
	//					.code("401")
	//					.description("Unauthorized")
	//					.build());
	//			add(new ResponseBuilder()
	//					.code("400")
	//					.description("Bad Request")
	//					.representation(MediaType.APPLICATION_JSON)
	//					.apply(r -> r.model(m -> m.referenceModel(ref -> ref
	//							.key(k -> k.qualifiedModelName(q -> q.namespace(ResponseError.class.getPackageName())
	//									.name(ResponseError.class.getSimpleName()))))))
	//					.build());
	//		}
	//	};
	//
	//	private List<Response> ErrorResponsne = new ArrayList<>() {
	//		{
	//			add(new ResponseBuilder()
	//					.code("400")
	//					.description("Bad Request")
	//					.representation(MediaType.APPLICATION_JSON)
	//					.apply(r -> r.model(m -> m.referenceModel(ref -> ref
	//							.key(k -> k.qualifiedModelName(q -> q.namespace(ResponseError.class.getPackageName())
	//									.name(ResponseError.class.getSimpleName()))))))
	//					.build());
	//		}
	//	};
	//
	//	@Bean
	//	public Docket api() {
	//		return new Docket(DocumentationType.SWAGGER_2)
	//				.groupName("users")
	//				.select()
	//				.apis(RequestHandlerSelectors.basePackage("com"))
	//				.paths(PathSelectors.regex("/api/users/*"))
	//				.build()
	//				.useDefaultResponseMessages(false)
	//				.additionalModels(typeResolver.resolve(ResponseError.class))
	//				.globalResponses(HttpMethod.POST, ErrorResponsneWithAuth)
	//				.globalResponses(HttpMethod.PUT, ErrorResponsneWithAuth)
	//				.globalResponses(HttpMethod.DELETE, ErrorResponsneWithAuth)
	//				.globalResponses(HttpMethod.GET, ErrorResponsne);
	//	}
	//
	//	@Bean
	//	public Docket authApi() {
	//		return new Docket(DocumentationType.SWAGGER_2)
	//				.groupName("auth")
	//				.select()
	//				.paths(PathSelectors.regex("/api/auth/*"))
	//				.build()
	//				.useDefaultResponseMessages(false)
	//				.additionalModels(typeResolver.resolve(ResponseError.class))
	//				.globalResponses(HttpMethod.POST, ErrorResponsne);
	//	}

}
