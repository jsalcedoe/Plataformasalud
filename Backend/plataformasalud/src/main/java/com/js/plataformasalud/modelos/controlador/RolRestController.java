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
import com.js.plataformasalud.modelos.entidades.Rol;
import com.js.plataformasalud.modelos.servicios.IRolServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class RolRestController {
	
	private IRolServiceImpl rolserv;
	
	
	@GetMapping("/roles")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Rol> index(){
		return rolserv.findAll();
	}
	
	@GetMapping("/roles/{idrol}")
	public ResponseEntity<?> mostrar (@PathVariable Long idrol){
		Rol rol = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				rol = rolserv.findById(idrol);
			} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(rol == null) {
			response.put("mensaje", "El Rol ID: ".concat(idrol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/roles")
	public ResponseEntity<?> crear (@Valid @RequestBody Rol rol, BindingResult validacion){
		Rol roles = null;
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
			roles = rolserv.save(rol);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El rol ha sido creado con éxito!");
		response.put("Rol", roles);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PutMapping("/roles/{idrol}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Rol rol, @PathVariable Long idrol, BindingResult validacion) {
		
		Rol rolActual = rolserv.findById(idrol);
		
		Rol rolUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(rolActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el rol ID: "
					.concat(idrol.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			rolActual.setCreatrol(rol.getCreatrol());
			rolActual.setDesrol(rol.getDesrol());
			rolActual.setEstrol_fk(rol.getEstrol_fk());
			rolActual.setNomrol(rol.getNomrol());
			
			rolUpdate = rolserv.save(rolActual);
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el rol en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "El rol ha sido actualizado con éxito!");
		response.put("Rol", rolUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

}
