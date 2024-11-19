package com.ini8labs.registration.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ini8labs.registration.dto.RegistrationReq;
import com.ini8labs.registration.entity.Registration;
import com.ini8labs.registration.exception.RegistrationNotFoundException;
import com.ini8labs.registration.repository.RegistrationRepository;
import com.ini8labs.registration.service.interfaces.RegistrationService;
@Service
public class RegistrationImpl implements RegistrationService {
	@Autowired
	private RegistrationRepository registrationRepository;
	@Override
	public Registration createRegistration(RegistrationReq request) {
		 Registration registration = new Registration();
	        registration.setName(request.getName());
	        registration.setEmail(request.getEmail());
	        registration.setDateOfBirth(request.getDateOfBirth());
	        registration.setPhoneNumber(request.getPhoneNumber());
	        return registrationRepository.save(registration);
	}

	@Override
	public List<Registration> getAllRegistrations() {
	return registrationRepository.findAll();
	}

	@Override
	public Optional<Registration> getRegistrationById(Integer id) {
		 return registrationRepository.findById(id);
	}

	@Override
	public Registration updateRegistration(Integer id, RegistrationReq request) {
		 Registration registration = registrationRepository.findById(id)
	                .orElseThrow(() -> new RegistrationNotFoundException("Registration not found with id: " + id));
	        registration.setName(request.getName());
	        registration.setEmail(request.getEmail());
	        registration.setDateOfBirth(request.getDateOfBirth());
	        registration.setPhoneNumber(request.getPhoneNumber());
	        return registrationRepository.save(registration);
	}

	@Override
	public void deleteRegistration(Integer id) {
		Optional<Registration> registration = registrationRepository.findById(id);

	    if (registration.isEmpty()) {
	        throw new RegistrationNotFoundException("Registration not found with id: " + id);
	    }
	    registrationRepository.deleteById(id);
	}

}
