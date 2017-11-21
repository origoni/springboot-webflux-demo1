package com.millky.demo;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	@Id
	private String name;

	private int age;
}
