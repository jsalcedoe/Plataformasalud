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

import com.js.plataformasalud.modelos.entidades.PermisoporRol;
import com.js.plataformasalud.modelos.servicios.IPermisoporRolServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class PermisoporRolRestController {
	
	private IPermisoporRolServiceImpl permrolserv;
	
	@GetMapping("/permisosporrol")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PermisoporRol> index(){
		return permrolserv.findAll();
	}
	
	@GetMapping("/permisosporrol/{idpermrol}")
	public ResponseEntity<?> mostrar (@PathVariable Long idpermrol){
		PermisoporRol permrol = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				permrol = permrolserv.findById(idpermrol);
			} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(permrol == null) {
			response.put("mensaje", "El permiso por rol ID: ".concat(idpermrol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<PermisoporRol>(permrol, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/permisosporrol")
	public ResponseEntity<?> crear (@Valid @RequestBody PermisoporRol permrol, BindingResult validacion){
		PermisoporRol permission = null;
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
			permission = permrolserv.save(permrol);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El permiso por rol ha sido creado con éxito!");
		response.put("permrol", permission);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PutMapping("/permisosporrol/{idpermrol}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody PermisoporRol permrol, @PathVariable Long idpermrol, BindingResult validacion) {
		
		PermisoporRol permrolActual = permrolserv.findById(idpermrol);
		
		PermisoporRol permrolUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(permrolActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el permiso por rol ID: "
					.concat(idpermrol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			permrolActual.setDatecreatidpermrol(permrol.getDatecreatidpermrol());
			permrolActual.setNompermrol(permrol.getNompermrol());
			permrolActual.setDetpermrol(permrol.getDetpermrol());
			permrolActual.setEstpermrol_fk(permrol.getEstpermrol_fk());
			permrolActual.setPermrol_fk(permrol.getPermrol_fk());
			permrolActual.setRolpermrol_fk(permrol.getRolpermrol_fk());
			
			permrolUpdate = permrolserv.save(permrolActual);
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el permiso por rol en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "El permiso por rol ha sido actualizada con éxito!");
		response.put("permrol", permrolUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

}
