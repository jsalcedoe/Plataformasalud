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
import com.js.plataformasalud.modelos.entidades.SignosVitales;
import com.js.plataformasalud.modelos.servicios.ISignosVitalesServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class SignosVitalesRestController {
	
	private ISignosVitalesServiceImpl sigvitserv;
	
	@GetMapping("/regsignosvitales")
	@ResponseStatus(code = HttpStatus.OK)
	public List<SignosVitales> index(){
		return sigvitserv.findAll();
	}
	
	
	@GetMapping("/regsignosvitales/{idsigvit}")
	public ResponseEntity<?> mostrar (@PathVariable Long idsigvit){
		SignosVitales sigvit = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				sigvit = sigvitserv.findById(idsigvit);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(sigvit == null) {
		response.put("mensaje", "El registro de signos vitales ID: ".concat(idsigvit.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<SignosVitales>(sigvit, HttpStatus.OK);
	
}
	
	@PostMapping("/regsignosvitales")
	public ResponseEntity<?> crear (@Valid @RequestBody SignosVitales sigvit, BindingResult validacion){
		SignosVitales regsigvit = null;
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
			regsigvit = sigvitserv.save(sigvit);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El registro de signos vitales ha sido creada con éxito!");
		response.put("SignosVitales", regsigvit);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/regsignosvitales/{idsigvit}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody SignosVitales sigvit, @PathVariable Long idsigvit, BindingResult validacion) {
		
		SignosVitales sigvitActual = sigvitserv.findById(idsigvit);
		
		SignosVitales sigvitUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(sigvitActual == null) {
			response.put("mensaje", "Error: no se pudo editar el registro de signos vitales con ID: "
					.concat(idsigvit.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				sigvitActual.setDateeditregsigvit(sigvit.getDateeditregsigvit());
				sigvitActual.setDateregsigvit(sigvit.getDateregsigvit());
				sigvitActual.setEstsigvit_fk(sigvit.getEstsigvit_fk());
				sigvitActual.setEventsigvit_fk(sigvit.getEventsigvit_fk());
				sigvitActual.setFc(sigvit.getFc());
				sigvitActual.setFr(sigvit.getFr());
				sigvitActual.setHoureditregsigvit(sigvit.getHoureditregsigvit());
				sigvitActual.setHouregsigvit(sigvit.getHouregsigvit());
				sigvitActual.setReguser_fk(sigvit.getReguser_fk());
				sigvitActual.setSat(sigvit.getSat());
				sigvitActual.setTa(sigvit.getTa());
				sigvitActual.setTem(sigvit.getTem());
			
				sigvitUpdate = sigvitserv.save(sigvitActual);
								
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el registro en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "El registro de signos vitales ha sido actualizada con éxito!");
		response.put("Signos Vitales", sigvitUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

}
