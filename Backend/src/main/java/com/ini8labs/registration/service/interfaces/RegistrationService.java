package com.ini8labs.registration.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.ini8labs.registration.dto.RegistrationReq;
import com.ini8labs.registration.entity.Registration;

public interface RegistrationService {
    Registration createRegistration(RegistrationReq request);

    List<Registration> getAllRegistrations();

    Optional <Registration> getRegistrationById(Integer id);

    Registration updateRegistration(Integer id, RegistrationReq request);

    void deleteRegistration(Integer id);
}
