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

import com.js.plataformasalud.modelos.entidades.Entidad;
import com.js.plataformasalud.modelos.servicios.IEntidadServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class EntidadRestController {
	
	private IEntidadServiceImpl entserv;
	
	@GetMapping("/entidades")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Entidad> index(){
		return entserv.findAll();
	}
	
	@GetMapping("/entidades/{ideapb}")
	public ResponseEntity<?> mostrar (@PathVariable Long ideapb){
		Entidad eapb = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				eapb = entserv.findById(ideapb); 
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(eapb == null) {
		response.put("mensaje", "La entidad ID: ".concat(ideapb.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<Entidad>(eapb, HttpStatus.OK);
	
}
	@PostMapping("/entidades")
	public ResponseEntity<?> crear (@Valid @RequestBody Entidad entidad, BindingResult validacion){
		Entidad eapb = null;
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
			eapb = entserv.save(entidad);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La entidad ha sido creada con éxito!");
		response.put("entidad", eapb);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/entidades/{ideapb}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Entidad entidad, @PathVariable Long ideapb, BindingResult validacion) {
		
		Entidad eapbActual = entserv.findById(ideapb);
		
		Entidad eapbUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(eapbActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la entidad prestadora de servicio ID: "
					.concat(ideapb.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				eapbActual.setContaceapb(entidad.getContaceapb());
				eapbActual.setDatecreateapb(entidad.getDatecreateapb());
				eapbActual.setDireapb(entidad.getDireapb());
				eapbActual.setEmaileapb(entidad.getEmaileapb());
				eapbActual.setEsteapb_fk(entidad.getEsteapb_fk());
				eapbActual.setGerenteapb(entidad.getGerenteapb());
				eapbActual.setNomeapb(entidad.getNomeapb());
				eapbActual.setTipent(entidad.getTipent());
				
				eapbUpdate = entserv.save(eapbActual);
				
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar la entidad en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "La entidad ha sido actualizada con éxito!");
		response.put("Entidad", eapbUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

}
