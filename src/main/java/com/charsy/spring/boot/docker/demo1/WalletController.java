package com.charsy.spring.boot.docker.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/wallets")
@RestController
public class WalletController {
	
	@Autowired
	private WalletService service;
	
	@PostMapping
	public @ResponseBody String
	createCourse(@RequestBody Wallet entity) {
		Wallet wallet = service.create(entity);
		Resource<Wallet> res = new Resource<Wallet>(entity);
		// return new ResponseEntity<>(res, HttpStatus.OK);
		return "created...";
	}
	
	@DeleteMapping(value="/{id}")
	public @ResponseBody Boolean
	deleteEntity(@PathVariable Long id) {
		return service.delete(id);
	}
	
	@PutMapping(value="/{id}")
	public @ResponseBody ResponseEntity<Resource<Wallet>>
	updateCourse(@PathVariable Long id) {
		Wallet entity = service.update(id);
		if (entity != null) {
			Resource<Wallet> res = new Resource<Wallet>(entity);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		return null;
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Resources<Resource<Wallet>>>
	getEntities(@RequestParam Map<String,String> criterias) {
		
		Iterable<Wallet> entities = service.getEntities();
		
		List<Resource<Wallet>> list = new ArrayList<Resource<Wallet>>();
		for (Wallet entity : entities) {
			Resource<Wallet> res = new Resource<Wallet>(entity);
			list.add(res);
		}
		Link selfLink = linkTo(methodOn(WalletController.class).getEntities(criterias)).withSelfRel();
		
		return new ResponseEntity<>(new Resources<>(list, selfLink), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public @ResponseBody ResponseEntity<Resource<Wallet>>
	getEntity(@PathVariable Long id) {
		Wallet entity = service.getEntity(id);
		Resource<Wallet> res = new Resource<Wallet>(entity);
		
		return new ResponseEntity<>(res, HttpStatus.OK);		
	}
}


