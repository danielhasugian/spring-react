package com.organization.project.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organization.project.common.CommonController;
import com.organization.project.domain.cassandra.Owner;
import com.organization.project.model.GenericResponse;
import com.organization.project.repository.OwnerRepository;
import com.organization.project.util.ConvertUtil;

/**
 * Method to response request to /owner
 */
@RestController
@RequestMapping("/owner")
public class OwnerController extends CommonController {

	@Autowired
	private OwnerRepository ownerRepo;

	private List<Owner> owners;
	private Owner owner;
	private Boolean status;

	/**
	 * method to response get /owner/
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public GenericResponse GetAllOwner(HttpServletRequest request) {
		Iterable<Owner> iteratorOwners = ownerRepo.findAll();
		owners = ConvertUtil.IterabletoList(iteratorOwners);
		if (owners.isEmpty() || owners.equals(null)) {
			throw new NullPointerException("Owner empty");
		}
		return sendResponseSuccess(owners, request);
	}

	/**
	 * method to response get /owner/getOne using requestParam
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getOne")
	public GenericResponse GetOwnerByIdExampleRequestParam(HttpServletRequest request, @RequestParam Long id) {
		genericResponse = new GenericResponse();
		owner = ownerRepo.findOne(id);
		if (owner.equals(null)) {
			throw new NullPointerException("Owner null");
		}
		return sendResponseSuccess(owner, request);
	}

	/**
	 * method to response get /owner/getOne using PathVariable
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getOne/{id}")
	public GenericResponse GetOwnerByIdExamplePathVariable(@PathVariable Long id, HttpServletRequest request) {
		owner = ownerRepo.findOne(id);
		if (owner.equals(null)) {
			throw new NullPointerException("Owner null");
		}
		return sendResponseSuccess(owner, request);
	}

	/**
	 * method to response Post or put /owner using requestBody to update or save
	 * to database
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public GenericResponse CreateOrUpdateOwner(HttpServletRequest request, @RequestBody HashMap<?, ?> coba) {
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
		return sendResponseSuccess(owner, request);
	}

	/**
	 * method to response Deelete /owner using requestBody to delete
	 * 
	 * @return {@link GenericResponse}
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public GenericResponse DeleteOwner(HttpServletRequest request, @RequestBody HashMap<?, ?> coba) {
		owner = new Owner();
		owner.setAge(Integer.parseInt((String) coba.get("age")));
		owner.setName((String) coba.get("name"));
		owner.setId(Long.parseLong((String) coba.get("id")));
		status = ownerRepo.exists(owner.getId());
		if (status) {
			ownerRepo.delete(owner.getId());
		}
		return sendResponseSuccess(owner, request);
	}



}
