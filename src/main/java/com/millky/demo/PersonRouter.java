package com.millky.demo;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PersonRouter {

	private PersonHandler handler;

	public PersonRouter(PersonHandler handler) {
		this.handler = handler;
	}
	
	// curl -d '{"name":"olivia","age":5}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
	// curl -X PUT -d '{"name":"noah","age":3}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
	// curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person/olivia'
	// curl -d '{"name":"test","age":0}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
	// curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
	// curl -X DELETE -H 'Content-Type: application/json' -v 'http://localhost:8080/person/test'

	@Bean
	public RouterFunction<ServerResponse> routingFunction() {

        RouterFunction<ServerResponse> personRoute =
                nest(path("/person"),
                        nest(accept(APPLICATION_JSON),
                                route(GET("/{id}"), handler::getPerson)
                                        .andRoute(method(HttpMethod.GET), handler::listPeople)
                                        .andRoute(POST("/"), handler::createPerson)
                                        .andRoute(PUT(""), handler::updatePerson)
                        ).andRoute(DELETE("/{id}").and(contentType(APPLICATION_JSON)), handler::deletePerson)
                );

// nest 안쓰고
//		route(GET("/person/{id}").and(accept(APPLICATION_JSON)), handler::getPerson)
//				.andRoute(GET("/person").and(accept(APPLICATION_JSON)), handler::listPeople)
//				.andRoute(POST("/person").and(contentType(APPLICATION_JSON)), handler::createPerson)
//				.andRoute(PUT("/person").and(contentType(APPLICATION_JSON)), handler::updatePerson)
//				.andRoute(DELETE("/person/{id}").and(contentType(APPLICATION_JSON)), handler::deletePerson);
        
		return personRoute;
	}
}
