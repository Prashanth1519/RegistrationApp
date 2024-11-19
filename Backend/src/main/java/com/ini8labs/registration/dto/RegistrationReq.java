package com.ini8labs.registration.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationReq {
	private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String phoneNumber;
}
