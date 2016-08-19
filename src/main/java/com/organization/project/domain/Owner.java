package com.organization.project.domain;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("owner")
public class Owner {

	@PrimaryKey("id")
	private Long id;

	@Column("name")
	private String name;

	@Column("age")
	private int age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public Owner() {
	}

	public Owner(Long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

}
