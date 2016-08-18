package com.organization.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.organization.project.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
