package com.millky.demo;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonHandler {

	private final PersonRepository repository;

	public PersonHandler(PersonRepository repository) {
		this.repository = repository;
	}

	
	public Mono<ServerResponse> createPerson(ServerRequest request) {
		Mono<Person> person = request.bodyToMono(Person.class);
		return ServerResponse.ok().body(addPerson(person), Person.class);
	}

	private Mono<Person> addPerson(Mono<Person> mono) {
		return mono.flatMap(repository::save);
	}

	public Mono<ServerResponse> updatePerson(ServerRequest request) {
		Mono<Person> person = request.bodyToMono(Person.class);
		return ServerResponse.ok().body(person.flatMap(repository::save), Person.class);
	}

	public Mono<ServerResponse> getPerson(ServerRequest request) {
		String personId = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Person> personMono = this.repository.findByName(personId);
		return personMono.flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person))).switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> listPeople(ServerRequest request) {
		Flux<Person> people = this.repository.findAll();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
	}
	
	public Mono<ServerResponse> deletePerson(ServerRequest request) {
		String personId = request.pathVariable("id");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Long> personMono = this.repository.deleteByName(personId);
		return personMono.flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person))).switchIfEmpty(notFound);
	}
}
