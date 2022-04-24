package com.ctd.integrador.backend1;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.AddressDTO;
import com.ctd.integrador.backend1.model.AppointmentDTO;
import com.ctd.integrador.backend1.model.DentistDTO;
import com.ctd.integrador.backend1.model.PatientDTO;
import com.ctd.integrador.backend1.service.IDentistService;
import com.ctd.integrador.backend1.service.IPatientService;
import com.ctd.integrador.backend1.service.IAppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class IntegradorBackend1ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private IAppointmentService appointmentService;
	@Autowired
	private IDentistService dentistService;
	@Autowired
	private IPatientService patientService;


	private PatientDTO getPatient() {
		AddressDTO fakeAddress = new AddressDTO();
		fakeAddress.setStreet("Calle Falsa");
		fakeAddress.setNumber("123");
		fakeAddress.setCity("Springfield");
		fakeAddress.setState("Los Simpson");
		Set<AddressDTO> addresses = new HashSet<>();
		addresses.add(fakeAddress);

		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setName("Muchas");
		patientDTO.setLastname("Caries");
		patientDTO.setDni("12345678");
		patientDTO.setRegistrationDate(LocalDate.parse("2011-01-11"));
		patientDTO.setAddresses(addresses);

		return patientDTO;
	}

	private DentistDTO getDentist() {
		DentistDTO dentistDTO = new DentistDTO();
		dentistDTO.setName("Dr.");
		dentistDTO.setLastname("Muelitas");
		dentistDTO.setRegistrationNumber(1337);

		return dentistDTO;
	}

	@Test
	void testCreatePatient() {
		PatientDTO patient = patientService.addPatient(getPatient());
		Assertions.assertNotNull(patient);
		Assertions.assertNotNull(patient.getId());
		Assertions.assertEquals(patient.getName(), "Muchas");
		Assertions.assertEquals(patient.getLastname(), "Caries");
		Assertions.assertEquals(patient.getDni(), "12345678");
		Assertions.assertEquals(patient.getRegistrationDate(), LocalDate.parse("2011-01-11"));
		Assertions.assertEquals(patient.getAddresses().size(), 1);
		Assertions.assertEquals(patient.getAddresses().iterator().next().getStreet(), "Calle Falsa");
		Assertions.assertEquals(patient.getAddresses().iterator().next().getNumber(), "123");
		Assertions.assertEquals(patient.getAddresses().iterator().next().getCity(), "Springfield");
		Assertions.assertEquals(patient.getAddresses().iterator().next().getState(), "Los Simpson");
	}

	@Test
	void testCrearOdontologo() {
		DentistDTO dentistDTO = dentistService.addDentist(getDentist());
		Assertions.assertNotNull(dentistDTO);
		Assertions.assertNotNull(dentistDTO.getId());
		Assertions.assertEquals(dentistDTO.getName(), "Dr.");
		Assertions.assertEquals(dentistDTO.getLastname(), "Muelitas");
		Assertions.assertEquals(dentistDTO.getRegistrationNumber(), 1337);
	}


	@Test
	void testCrearTurno() {
		DentistDTO dentistDTO = dentistService.addDentist(getDentist());
		PatientDTO patientDTO = patientService.addPatient(getPatient());
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDate(LocalDate.parse("2011-11-11"));
		appointment.setDentist(dentistDTO);
		appointment.setPatient(patientDTO);
		appointment = appointmentService.addAppointment(appointment);
		Assertions.assertNotNull(appointment);
		Assertions.assertNotNull(appointment.getId());
		Assertions.assertEquals(appointment.getDate(), LocalDate.parse("2011-11-11"));
		Assertions.assertNotNull(appointment.getDentist().getId());
		Assertions.assertEquals(appointment.getDentist().getName(), "Dr.");
		Assertions.assertEquals(appointment.getDentist().getLastname(), "Muelitas");
		Assertions.assertEquals(appointment.getDentist().getRegistrationNumber(), 1337);
		Assertions.assertNotNull(appointment.getPatient().getId());
		Assertions.assertEquals(appointment.getPatient().getName(), "Muchas");
		Assertions.assertEquals(appointment.getPatient().getLastname(), "Caries");
		// Propiedades ignoradas en el dto
		Assertions.assertNull(appointment.getPatient().getDni());
		Assertions.assertNull(appointment.getPatient().getRegistrationDate());
		Assertions.assertNull(appointment.getPatient().getAddresses());
	}

	@Test
	void testAddAddressToPatient() throws ResourceNotFoundException {
		PatientDTO patient = patientService.addPatient(getPatient());
		AddressDTO fakeAddress = new AddressDTO();
		fakeAddress.setStreet("Avenida Siempre Viva");
		fakeAddress.setNumber("1234");
		fakeAddress.setCity("Springfield");
		fakeAddress.setState("Los Simpson");
		patient.getAddresses().add(fakeAddress);
		patient = patientService.updatePatient(patient.getId(), patient);
		Assertions.assertNotNull(patient);
		Assertions.assertEquals(patient.getAddresses().size(), 2);
	}

	@Test
	public void testAdd_Delete_Find_Exceptions() throws ResourceNotFoundException {
		DentistDTO dentistDTO = dentistService.addDentist(getDentist());
		Assertions.assertNotNull(dentistDTO);

		PatientDTO patientDTO = patientService.addPatient(getPatient());
		Assertions.assertNotNull(patientDTO);

		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDate(LocalDate.parse("2020-01-01"));
		appointment.setPatient(patientDTO);
		appointment.setDentist(dentistDTO);
		appointment = appointmentService.addAppointment(appointment);
		Assertions.assertNotNull(appointment.getId());
		Assertions.assertNotNull(appointment.getDentist().getId());
		Assertions.assertNotNull(appointment.getPatient().getId());



		Long fakeId = 123L;
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			appointmentService.findById(fakeId);
		});
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			dentistService.findById(fakeId);
		});
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			patientService.findById(fakeId);
		});



		AppointmentDTO finalAppointment = appointment;
		Assertions.assertDoesNotThrow(() -> {
			appointmentService.deleteAppointment(finalAppointment.getId());
		});
		Assertions.assertDoesNotThrow(() -> {
			dentistService.deleteDentist(dentistDTO.getId());
		});
		Assertions.assertDoesNotThrow(() -> {
			patientService.deletePatient(patientDTO.getId());
		});

	}

}
