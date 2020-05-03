package com.srbarrios.mybackend.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srbarrios.mybackend.entities.User;
import com.srbarrios.mybackend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<User>> getUsers() {
    	final List<User> users = userService.findByPattern("*");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

    @GetMapping(value = "/{mailAddress}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<User> getUsers(@PathVariable("mailAddress") final String mailAddress) {
    	final User user = userService.findByMailAddress(mailAddress);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> createUser(@RequestBody final User user) {
    	userService.save(user);
    	return new ResponseEntity<>(HttpStatus.CREATED);
	}

    @PutMapping(value = "/{mailAddress}", consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> updateUser(@PathVariable("mailAddress") final String mailAddress, @RequestBody final User user) {
    	user.setMailAddress(mailAddress);
    	userService.update(user);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

    @DeleteMapping(value = "/{mailAddress}")
    public ResponseEntity<Void> deleteUser(@PathVariable("mailAddress") final String mailAddress) {
    	userService.delete(mailAddress);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
