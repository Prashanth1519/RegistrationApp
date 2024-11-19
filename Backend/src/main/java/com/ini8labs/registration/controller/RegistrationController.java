package com.ini8labs.registration.controller;

import java.util.List;

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

import com.ini8labs.registration.dto.ErrorResponse;
import com.ini8labs.registration.dto.RegistrationReq;
import com.ini8labs.registration.entity.Registration;
import com.ini8labs.registration.exception.RegistrationNotFoundException;
import com.ini8labs.registration.service.interfaces.RegistrationService;

@RestController
@RequestMapping("/v1/registration")
public class RegistrationController {

private RegistrationService registrationService;

public RegistrationController(RegistrationService registrationService) {
	this.registrationService=registrationService;
}

@PostMapping
public ResponseEntity<Registration> createRegistration(@RequestBody RegistrationReq request) {
    return ResponseEntity.ok(registrationService.createRegistration(request));
}

@GetMapping
public ResponseEntity<List<Registration>> getAllRegistrations() {
    return ResponseEntity.ok(registrationService.getAllRegistrations());
}

@GetMapping("/{id}")
public ResponseEntity<Registration> getRegistrationById(@PathVariable Integer id) {
    return registrationService.getRegistrationById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

@PutMapping("/{id}")
public ResponseEntity<Registration> updateRegistration(@PathVariable Integer id,
                                                        @RequestBody RegistrationReq request) {
    return ResponseEntity.ok(registrationService.updateRegistration(id, request));
}

@DeleteMapping("/{id}")
public ResponseEntity<ErrorResponse> deleteRegistration(@PathVariable Integer id) {
	try {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    } catch (RegistrationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Registration not found", ex.getMessage()));
    }
}
}
