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

import com.js.plataformasalud.modelos.entidades.Permiso;
import com.js.plataformasalud.modelos.servicios.IPermisoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor


public class PermisoRestController {
	
	private IPermisoServiceImpl permserv;
	
	@GetMapping("/permisos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Permiso> index(){
		return permserv.findAll();
	}
	
	@GetMapping("permisos/{idperm}")
	public ResponseEntity<?> mostrar(@PathVariable Long idperm) {
		Permiso permiso = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			permiso = permserv.findById(idperm);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (permiso == null) {
			response.put("mensaje", "El permiso ID: ".concat(idperm.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Permiso>(permiso, HttpStatus.OK);
	}
	
	@PostMapping("/permisos")
	public ResponseEntity<?> save (@Valid @RequestBody Permiso permiso, BindingResult result) {
		Permiso Newperm = null;
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
				Newperm= permserv.save(permiso);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el permiso en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El permiso ha sido creado con éxito!");
		response.put("permiso", Newperm);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/permisos/{idperm}")
	public ResponseEntity <?> actualizar (@PathVariable Long idperm,@RequestBody Permiso permiso, BindingResult result) {
		
		Permiso permUpdate = null;
		Permiso permAct = permserv.findById(idperm);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (permAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el permiso ID: "
					.concat(idperm.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			permAct.setDatecreatperm(permiso.getDatecreatperm());
			permAct.setDetperm(permiso.getDetperm());
			permAct.setEstperm_fk(permiso.getEstperm_fk());
			permAct.setNamperm(permiso.getNamperm());
						
			permUpdate = permserv.save(permAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el permiso en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El permiso ha sido actualizado con éxito!");
		response.put("cargo", permUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
