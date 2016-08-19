package com.organization.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.organization.project.domain.cassandra.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
