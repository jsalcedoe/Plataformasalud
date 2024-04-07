package com.js.plataformasalud.modelos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Paciente;
import com.js.plataformasalud.modelos.servicios.PacienteServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PacienteRestController {
	
	private PacienteServiceImpl pacserv;
	
	@GetMapping("/pacientes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Paciente> index(){
		return pacserv.findAll();
	}
	
	@GetMapping("/pacientes/{idpac}")
	public ResponseEntity<?> mostrar (@PathVariable Long idpac){
		Paciente paciente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				paciente = pacserv.findById(idpac);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(paciente == null) {
		response.put("mensaje", "El paciente ID: ".concat(idpac.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	
}
	
	@GetMapping("/pacientes/buscar/{keyword}")
    public ResponseEntity<?> buscarPorNombreODocumento(@PathVariable String keyword) {
        List<Paciente> pacientes = null;
        Map<String, Object> response = new HashMap<>();

        try {
            pacientes = pacserv.buscarPacientesPorNombreODocumento(keyword);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (pacientes.isEmpty()) {
            response.put("mensaje", "No se encontraron pacientes con el nombre o documento proporcionado: " + keyword);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
    }
	
	
	
	@PostMapping("/pacientes")
	public ResponseEntity<?> crear (@Valid @RequestBody Paciente paciente, BindingResult validacion){
		Paciente pac = null;
		Map<String, Object> response = new HashMap<>();
		
		if(validacion.hasErrors()) {

			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			pac = pacserv.save(paciente);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El paciente ha sido creado con éxito!");
		response.put("paciente", pac);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/pacientes/{idpac}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Paciente paciente, @PathVariable Long idpac, BindingResult validacion) {
		
		Paciente pacActual = pacserv.findById(idpac);
		
		Paciente pacUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(pacActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el paciente ID: "
					.concat(idpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				pacActual.setAcudientepac(paciente.getAcudientepac());
				pacActual.setCiudad(paciente.getCiudad());
				pacActual.setContactoacudientepac(paciente.getContactoacudientepac());
				pacActual.setContactopac(paciente.getContactopac());
				pacActual.setDireccionpac(paciente.getDireccionpac());
				pacActual.setEmailpac(paciente.getEmailpac());
				pacActual.setEntidad(paciente.getEntidad());
				pacActual.setEstadocivilpac(paciente.getEstadocivilpac());
				pacActual.setEstpac_fk(paciente.getEstpac_fk());
				pacActual.setFechacreacionpac(paciente.getFechacreacionpac());
				pacActual.setFechaedipac(paciente.getFechaedipac());
				pacActual.setFechanacpac(paciente.getFechanacpac());
				pacActual.setPrimerapepac(paciente.getPrimerapepac());
				pacActual.setPrimernompac(paciente.getPrimernompac());
				pacActual.setSegundoapepac(paciente.getSegundoapepac());
				pacActual.setSegundonompac(paciente.getSegundonompac());
				pacActual.setSexopac(paciente.getSexopac());
				pacActual.setTipac(paciente.getTipac());
				pacActual.setTypdocpac(paciente.getTypdocpac());
				pacActual.setNumdocpac(paciente.getNumdocpac());
			
				pacUpdate = pacserv.save(pacActual);
				
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el paciente en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "el paciente ha sido actualizado con éxito!");
		response.put("paciente", pacUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	
}
