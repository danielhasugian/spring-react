package com.organization.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.organization.project.domain.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
