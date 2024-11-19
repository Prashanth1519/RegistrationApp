package com.ini8labs.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ini8labs.registration.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

}
