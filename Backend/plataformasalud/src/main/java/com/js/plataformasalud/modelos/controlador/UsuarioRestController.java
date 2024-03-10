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

import com.js.plataformasalud.modelos.entidades.Usuario;
import com.js.plataformasalud.modelos.servicios.IUsuarioServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class UsuarioRestController {
	
	private IUsuarioServiceImpl userserv;
	
	@GetMapping("/usuarios")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Usuario> index(){
		return userserv.findAll();
	}
	
	@GetMapping("/usuarios/{iduser}")
	public ResponseEntity<?> mostrar (@PathVariable Long iduser){
		Usuario user = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				user = userserv.findById(iduser); 
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(user == null) {
		response.put("mensaje", "El usuario ID: ".concat(iduser.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	
}
	@PostMapping("/usuarios")
	public ResponseEntity<?> crear (@Valid @RequestBody Usuario user, BindingResult validacion){
		Usuario us = null;
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
			us = userserv.save(user);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("estado", us);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuarios/{iduser}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody Usuario user, @PathVariable Long iduser, BindingResult validacion) {
		
		Usuario usActual = userserv.findById(iduser);
		
		Usuario usUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(usActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el usuario ID: "
					.concat(iduser.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
				usActual.setCarguser_fk(user.getCarguser_fk());
				usActual.setCreatdateuser(user.getCreatdateuser());
				usActual.setDocuser(user.getDocuser());
				usActual.setEmailuser(user.getEmailuser());
				usActual.setEstuser_fk(user.getEstuser_fk());
				usActual.setFirma(user.getFirma());
				usActual.setFirstname(user.getFirstname());
				usActual.setLastname(user.getLastname());
				usActual.setPassword(user.getPassword());
				usActual.setTypeiduser_fk(user.getTypeiduser_fk());
				usActual.setUsername(user.getUsername());
			
				usUpdate = userserv.save(usActual);
				
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el usuario en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "el estado ha sido actualizada con éxito!");
		response.put("Estados", usUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
}
