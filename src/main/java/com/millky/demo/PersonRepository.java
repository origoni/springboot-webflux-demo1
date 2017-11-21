package com.millky.demo;

//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

	// @Query
	Mono<Person> findByName(String name);

	Mono<Long> deleteByName(String name); // Mono<Person> 에러남. Flux 로 받아야 하나?
}
