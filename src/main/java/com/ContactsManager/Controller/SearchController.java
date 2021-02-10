package com.ContactsManager.Controller;

import java.security.Principal;
import java.util.List;

import com.ContactsManager.dao.ContactRepository;
import com.ContactsManager.dao.UserRepository;
import com.ContactsManager.entities.Contact;
import com.ContactsManager.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;
    
    //search bar handler
    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal){
        System.out.println(query);

        //passing current user logged user with their authentic username.
        User user = this.userRepository.getUserByUserName(principal.getName()); //get current user username

        List<Contact> contacts = this.contactRepository.findByNameContainingAndUser(query, user); //get result of contacts.

        //returning the contacts.
        return ResponseEntity.ok(contacts);
    }
}
