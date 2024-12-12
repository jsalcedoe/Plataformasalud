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

import com.js.plataformasalud.modelos.entidades.AsignaCita;
import com.js.plataformasalud.modelos.servicios.IAsignaCitaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class AsignaCitaRestController {
	
	
	private IAsignaCitaServiceImpl ascitserv;
	
	@GetMapping("/asignacita")
	@ResponseStatus(code = HttpStatus.OK)
	public List<AsignaCita> index(){
		return ascitserv.findAll();
	}
	
	@GetMapping("/asignacita/{idascit}")
	public ResponseEntity<?> mostrar(@PathVariable Long idascit) {
		AsignaCita ascit = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			ascit = ascitserv.findById(idascit);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ascit == null) {
			response.put("mensaje", "La cita ID: ".concat(idascit.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AsignaCita>(ascit, HttpStatus.OK);
	}
	
	@PostMapping("/asignacita")
	public ResponseEntity<?> save (@Valid @RequestBody AsignaCita ascit, BindingResult result) {
		AsignaCita Newascit = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
				Newascit= ascitserv.save(ascit);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la cita en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La cita ha sido creada con éxito!");
		response.put("AsignaCita", Newascit);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/asignacita/{idascit}")
	public ResponseEntity <?> actualizar (@PathVariable Long idascit,@RequestBody AsignaCita ascit, BindingResult result) {
		
		AsignaCita ascitUpdate = null;
		AsignaCita ascitAct = ascitserv.findById(idascit);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (ascitAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la cita ID: "
					.concat(idascit.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			ascitAct.setCitagcit_fk(ascit.getCitagcit_fk());
			ascitAct.setCitpac_fk(ascit.getCitpac_fk());
			ascitAct.setCitprcexam_fk(ascit.getCitprcexam_fk());
			ascitAct.setEstcit_fk(ascit.getEstcit_fk());
			ascitAct.setFechacreatascit(ascit.getFechacreatascit());
			ascitAct.setHoraCita(ascit.getHoraCita());
			
			ascitUpdate = ascitserv.save(ascitAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la cita en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La cita ha sido actualizada con éxito!");
		response.put("Agenda", ascitUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
