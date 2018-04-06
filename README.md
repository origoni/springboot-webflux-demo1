# springboot-webflux-demo1 [![Build Status](https://travis-ci.org/origoni/springboot-webflux-demo1.svg?branch=master)](https://travis-ci.org/origoni/springboot-webflux-demo1)

spring-boot-webflux reactive rest demo
using spring-boot-starter-data-mongodb-reactive

## Quick Start

- JDK 1.8
- Maven 3.5
- Git

```
git clone https://github.com/origoni/springboot-webflux-demo1.git
cd springboot-webflux-demo1
mvn spring-boot:run
```

## Test

### create (& update)
```
curl -d '{"name":"olivia","age":5}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### update (& create)
```
curl -X PUT -d '{"name":"noah","age":3}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### list
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### read
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person/olivia'
```

## update, delete Test

### create
```
curl -d '{"name":"test","age":0}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### check
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### update
```
curl -d '{"name":"test","age":10}' -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### check
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```
### delete
```
curl -X DELETE -H 'Content-Type: application/json' -v 'http://localhost:8080/person/test'
```
### check
```
curl -H 'Content-Type: application/json' -v 'http://localhost:8080/person'
```


## Dependency

### Spring Boot 2.0.0.M6
- spring-boot-starter-webflux
- spring-boot-starter-data-mongodb-reactive
- de.flapdoodle.embed.de.flapdoodle.embed.mongo
- org.projectlombok.lombok
