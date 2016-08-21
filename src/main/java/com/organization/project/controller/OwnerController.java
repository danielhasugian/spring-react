package com.organization.project.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.domain.cassandra.Owner;
import com.organization.project.model.GenericResponse;
import com.organization.project.repository.OwnerRepository;
import com.organization.project.util.ConvertUtil;

/**
 * Method to response request to /owner
 */
@RestController
@RequestMapping("/owner")
public class OwnerController extends RootController {

	@Autowired
	private OwnerRepository ownerRepo;

	private GenericResponse genericResponse;

	private List<Owner> owners;
	private Owner owner;
	private Boolean status;

	/**
	 * method to response get /owner/
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public GenericResponse GetAllOwner() {
		genericResponse = new GenericResponse();
		Iterable<Owner> iteratorOwners = ownerRepo.findAll();
		owners = ConvertUtil.IterabletoList(iteratorOwners);
		genericResponse.setMessage("success");
		if (owners.isEmpty() || owners.equals(null)) {
			genericResponse.setMessage("data is empty");
			genericResponse.setFailed(true);
		}
		genericResponse.setFailed(false);
		genericResponse.setDate(new Date());
		genericResponse.setPath("/owner/");
		genericResponse.setResult(owners);
		return genericResponse;
	}

	/**
	 * method to response get /owner/getOne using requestParam
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getOne")
	public GenericResponse GetOwnerByIdExampleRequestParam(@RequestParam Long id) {
		genericResponse = new GenericResponse();
		owner = ownerRepo.findOne(id);
		genericResponse.setMessage("success");
		if (owner.equals(null)) {
			genericResponse.setMessage("data is empty");
			genericResponse.setFailed(true);
		}
		genericResponse.setFailed(false);
		genericResponse.setDate(new Date());
		genericResponse.setPath("/owner/getOne?id=" + id);
		genericResponse.setResult(owner);
		return genericResponse;
	}

	/**
	 * method to response get /owner/getOne using PathVariable
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getOne/{id}")
	public GenericResponse GetOwnerByIdExamplePathVariable(@PathVariable Long id) {
		genericResponse = new GenericResponse();
		owner = ownerRepo.findOne(id);
		genericResponse.setMessage("success");
		if (owner.equals(null)) {
			genericResponse.setMessage("data is empty");
			genericResponse.setFailed(true);
		}
		genericResponse.setFailed(false);
		genericResponse.setDate(new Date());
		genericResponse.setPath("/owner/getOne/" + id);
		genericResponse.setResult(owner);
		return genericResponse;
	}

	/**
	 * method to response Post or put /owner using requestBody to update or save
	 * to database
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public GenericResponse CreateOrUpdateOwner(@RequestBody HashMap<?, ?> coba) {
		genericResponse = new GenericResponse();
		owner = new Owner();
		owner.setAge(Integer.parseInt((String) coba.get("age")));
		owner.setName((String) coba.get("name"));
		owner.setId(Long.parseLong((String) coba.get("id")));
		status = ownerRepo.exists(owner.getId());
		genericResponse.setFailed(false);
		genericResponse.setMessage("data created");

		if (status) {
			genericResponse.setMessage("data updated");
		}

		ownerRepo.save(owner);
		genericResponse.setDate(new Date());
		genericResponse.setPath("/owner/");
		genericResponse.setResult(owner);
		return genericResponse;
	}

	/**
	 * method to response Deelete /owner using requestBody to delete
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public GenericResponse DeleteOwner(@RequestBody HashMap<?, ?> coba) {
		genericResponse = new GenericResponse();
		owner = new Owner();
		owner.setAge(Integer.parseInt((String) coba.get("age")));
		owner.setName((String) coba.get("name"));
		owner.setId(Long.parseLong((String) coba.get("id")));
		status = ownerRepo.exists(owner.getId());
		genericResponse.setMessage("success");
		if (!status) {
			genericResponse.setFailed(true);
			genericResponse.setMessage("id not found");
		} else {
			ownerRepo.delete(owner.getId());
			genericResponse.setFailed(false);
		}
		genericResponse.setDate(new Date());
		genericResponse.setPath("/owner/");
		genericResponse.setResult(owner);
		return genericResponse;
	}



}
