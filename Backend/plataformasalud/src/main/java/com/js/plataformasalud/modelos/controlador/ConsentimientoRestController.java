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

import com.js.plataformasalud.modelos.entidades.Consentimiento;
import com.js.plataformasalud.modelos.servicios.IConsentimientoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ConsentimientoRestController {
	
	private IConsentimientoServiceImpl coninfserv;

	@GetMapping("/consentimientos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Consentimiento> index(){
		return coninfserv.findAll();
	}
	
	@GetMapping("/consentimientos/{idconsinf}")
	public ResponseEntity<?> mostrar(@PathVariable Long idconsinf) {
		Consentimiento coninf = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			coninf = coninfserv.findById(idconsinf);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (coninf == null) {
			response.put("mensaje", "El consentimiento quirurgico ID: ".concat(idconsinf.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Consentimiento>(coninf, HttpStatus.OK);
	}
	
	@PostMapping("/consentimientos")
	public ResponseEntity<?> save (@Valid @RequestBody Consentimiento coninf, BindingResult result) {
		Consentimiento Newconinf = null;
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
			Newconinf= coninfserv.save(coninf);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el consentimiento informado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El consentimiento informado ha sido registrado con éxito!");
		response.put("Consentimiento", Newconinf);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/consentimientos/{idconsinf}")
	public ResponseEntity <?> actualizar (@PathVariable Long idconsinf,@RequestBody Consentimiento coninf, BindingResult result) {
		
		Consentimiento coninfUpdate = null;
		Consentimiento coninfAct = coninfserv.findById(idconsinf);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (coninfAct == null) {
			response.put("mensaje", "Error: no se pudo editar el consentimiento informado ID: "
					.concat(idconsinf.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			coninfAct.setDatecreatconinf(coninf.getDatecreatconinf());
			coninfAct.setDetconinf(coninf.getDetconinf());
			coninfAct.setEstconinf_fk(coninf.getEstconinf_fk());
			coninfAct.setCodconinf(coninf.getCodconinf());
			coninfAct.setEditconinf(coninf.getEditconinf());
			
			
			coninfUpdate = coninfserv.save(coninfAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el equipo quirurgico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El consentimiento informado ha sido actualizado con éxito!");
		response.put("EquipoQx", coninfUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
