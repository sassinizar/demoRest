package org.ns.services;

import java.util.List;
import org.ns.dao.ContactRepository;
import org.ns.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestContactService {

	@Autowired
	private ContactRepository contactRepo;
	
	@GetMapping(value="contacts")
	public List<Contact> getAllContact(){
		return contactRepo.findAll(); 
	}
	
	@RequestMapping(value="contacts/{id}")
	public Contact getContact(@PathVariable Long id) {
		return contactRepo.getOne(id);
	}
	
	@RequestMapping(value="chercherContact")
	public Page<Contact> chercherContact( @RequestParam(name="mc", defaultValue="") String mc,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="4") int size) {
		return contactRepo.chercher("%"+mc+"%", PageRequest.of(page, size));
	}
	
	@PostMapping(value="contacts")
	public Contact saveContact(@RequestBody Contact c) {
		return contactRepo.save(c);
	}
	
	@PutMapping(value="contacts/{id}")
	public Contact editContact(@PathVariable Long id, @RequestBody Contact c) {
		c.setId(id);
		return contactRepo.save(c);
	}
	
	@DeleteMapping(value="contacts/{id}")
	public String deleteContact(@PathVariable Long id) {
		Contact c=contactRepo.getOne(id);
		if(c!= null) {
			contactRepo.delete(c);
			return "it's removed from the contact list";
		}else {
			return "contact does not exist in the list";
		}
	}
	
}
