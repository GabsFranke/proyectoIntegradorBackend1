package com.ctd.integrador.backend1;

import com.ctd.integrador.backend1.exceptions.ResourceNotFoundException;
import com.ctd.integrador.backend1.model.DomicilioDTO;
import com.ctd.integrador.backend1.model.OdontologoDTO;
import com.ctd.integrador.backend1.model.PacienteDTO;
import com.ctd.integrador.backend1.model.TurnoDTO;
import com.ctd.integrador.backend1.service.IOdontologoService;
import com.ctd.integrador.backend1.service.IPacienteService;
import com.ctd.integrador.backend1.service.ITurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@SpringBootTest
class IntegradorBackend1ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ITurnoService turnoService;
	@Autowired
	private IOdontologoService odontologoService;
	@Autowired
	private IPacienteService pacienteService;


	private PacienteDTO getPaciente() {
		DomicilioDTO domicilioFalso = new DomicilioDTO();
		domicilioFalso.setCalle("Calle Falsa");
		domicilioFalso.setNumero("123");
		domicilioFalso.setLocalidad("Springfield");
		domicilioFalso.setProvincia("Los Simpson");
		Set<DomicilioDTO> domicilios = new HashSet<>();
		domicilios.add(domicilioFalso);

		PacienteDTO paciente = new PacienteDTO();
		paciente.setNombre("Muchas");
		paciente.setApellido("Caries");
		paciente.setDni("12345678");
		paciente.setFechaIngreso(LocalDate.parse("2011-01-11"));
		paciente.setDomicilios(domicilios);

		return paciente;
	}

	private OdontologoDTO getOdontologo() {
		OdontologoDTO odontologo = new OdontologoDTO();
		odontologo.setNombre("Dr.");
		odontologo.setApellido("Muelitas");
		odontologo.setMatricula(1337);

		return odontologo;
	}

	@Test
	void testCrearPaciente() {
		PacienteDTO paciente = getPaciente();
		paciente = pacienteService.agregarPaciente(paciente);
		Assertions.assertNotNull(paciente);
		Assertions.assertEquals(paciente.getNombre(), "Muchas");
		Assertions.assertEquals(paciente.getApellido(), "Caries");
		Assertions.assertEquals(paciente.getDni(), "12345678");
		Assertions.assertEquals(paciente.getFechaIngreso(), LocalDate.parse("2011-01-11"));
		Assertions.assertEquals(paciente.getDomicilios().size(), 1);
		Assertions.assertEquals(paciente.getDomicilios().iterator().next().getCalle(), "Calle Falsa");
		Assertions.assertEquals(paciente.getDomicilios().iterator().next().getNumero(), "123");
		Assertions.assertEquals(paciente.getDomicilios().iterator().next().getLocalidad(), "Springfield");
		Assertions.assertEquals(paciente.getDomicilios().iterator().next().getProvincia(), "Los Simpson");
	}

	@Test
	void testCrearOdontologo() {
		OdontologoDTO odontologo = getOdontologo();
		odontologo = odontologoService.agregarOdontologo(odontologo);
		Assertions.assertNotNull(odontologo);
		Assertions.assertEquals(odontologo.getNombre(), "Dr.");
		Assertions.assertEquals(odontologo.getApellido(), "Muelitas");
		Assertions.assertEquals(odontologo.getMatricula(), 1337);
	}


	@Test
	void testCrearTurno() {
		OdontologoDTO odontologo = getOdontologo();
		odontologo = odontologoService.agregarOdontologo(odontologo);
		PacienteDTO paciente = getPaciente();
		paciente = pacienteService.agregarPaciente(paciente);
		TurnoDTO turno = new TurnoDTO();
		turno.setFecha(LocalDate.parse("2011-11-11"));
		turno.setOdontologo(odontologo);
		turno.setPaciente(paciente);
		turno = turnoService.agregarTurno(turno);
		Assertions.assertNotNull(turno);
		Assertions.assertEquals(turno.getFecha(), LocalDate.parse("2011-11-11"));
		Assertions.assertEquals(turno.getOdontologo().getNombre(), "Dr.");
		Assertions.assertEquals(turno.getOdontologo().getApellido(), "Muelitas");
		Assertions.assertEquals(turno.getOdontologo().getMatricula(), 1337);
		Assertions.assertEquals(turno.getPaciente().getNombre(), "Muchas");
		Assertions.assertEquals(turno.getPaciente().getApellido(), "Caries");
		// Propiedades ignoradas en el dto
		Assertions.assertNull(turno.getPaciente().getDni());
		Assertions.assertNull(turno.getPaciente().getFechaIngreso());
		Assertions.assertNull(turno.getPaciente().getDomicilios());
	}

	@Test
	void testAgregarDomicilioAPaciente() throws ResourceNotFoundException {
		PacienteDTO paciente = getPaciente();
		paciente = pacienteService.agregarPaciente(paciente);
		DomicilioDTO domicilioFalso2 = new DomicilioDTO();
		domicilioFalso2.setCalle("Avenida Siempre Viva");
		domicilioFalso2.setNumero("1234");
		domicilioFalso2.setLocalidad("Springfield");
		domicilioFalso2.setProvincia("Los Simpson");
		paciente.getDomicilios().add(domicilioFalso2);
		paciente = pacienteService.actualizarPaciente(paciente.getId(), paciente);
		Assertions.assertNotNull(paciente);
		Assertions.assertEquals(paciente.getDomicilios().size(), 2);
	}

	@Test
	public void testIntegradorAgregarEliminarBuscarYExceptions() throws ResourceNotFoundException {
		OdontologoDTO odontologo = getOdontologo();
		odontologo = odontologoService.agregarOdontologo(odontologo);
		Assertions.assertNotNull(odontologo);

		PacienteDTO paciente = getPaciente();
		paciente = pacienteService.agregarPaciente(paciente);
		Assertions.assertNotNull(paciente);

		TurnoDTO turno = new TurnoDTO();
		turno.setFecha(LocalDate.parse("2020-01-01"));
		turno.setPaciente(paciente);
		turno.setOdontologo(odontologo);
		turno = turnoService.agregarTurno(turno);
		Assertions.assertNotNull(turno.getId());
		Assertions.assertNotNull(turno.getOdontologo().getId());
		Assertions.assertNotNull(turno.getPaciente().getId());



		Long idFalso = 123L;
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			turnoService.buscarPorId(idFalso);
		});
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			odontologoService.buscarPorId(idFalso);
		});
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			pacienteService.buscarPorId(idFalso);
		});



		TurnoDTO finalTurno = turno;
		OdontologoDTO finalOdontologo = odontologo;
		PacienteDTO finalPaciente = paciente;
		Assertions.assertDoesNotThrow(() -> {
			turnoService.eliminarTurno(finalTurno.getId());
		});
		Assertions.assertDoesNotThrow(() -> {
			odontologoService.eliminarOdontologo(finalOdontologo.getId());
		});
		Assertions.assertDoesNotThrow(() -> {
			pacienteService.eliminarPaciente(finalPaciente.getId());
		});
	}

}
