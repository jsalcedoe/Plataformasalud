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

import com.js.plataformasalud.modelos.entidades.OrigenDestino;
import com.js.plataformasalud.modelos.servicios.IOrigenDestinoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class OrigenDestinoRestController {
	
	private IOrigenDestinoServiceImpl orgdeserv;
	
	@GetMapping("/origendestino")
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrigenDestino> index(){
		return orgdeserv.findAll();
	}
	
	@GetMapping("origendestino/{idorgdes}")
	public ResponseEntity<?> mostrar(@PathVariable Long idorgdes) {
		OrigenDestino orgdes = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			orgdes = orgdeserv.findById(idorgdes);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (orgdes == null) {
			response.put("mensaje", "El origen o destino ID: ".concat(idorgdes.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrigenDestino>(orgdes, HttpStatus.OK);
	}
	
	@PostMapping("/origendestino")
	public ResponseEntity<?> save (@Valid @RequestBody OrigenDestino origendestino, BindingResult result) {
		OrigenDestino Neworgdes = null;
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
				Neworgdes = orgdeserv.save(origendestino);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el origeno o destino en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El origen o destino ha sido creado con éxito!");
		response.put("cargo", Neworgdes);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/origendestino/{idorgdes}")
	public ResponseEntity <?> actualizar (@PathVariable Long idorgdes,@RequestBody OrigenDestino origendestino, BindingResult result) {
		
		OrigenDestino orgdesUpdate = null;
		OrigenDestino orgdesAct = orgdeserv.findById(idorgdes);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (orgdesAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el origen o destino ID: "
					.concat(idorgdes.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			orgdesAct.setDatecreatorgdes(origendestino.getDatecreatorgdes());
			orgdesAct.setDetorgdes(origendestino.getDetorgdes());
			orgdesAct.setEstorgdes_fk(origendestino.getEstorgdes_fk());
			orgdesAct.setNomorgdes(origendestino.getNomorgdes());
			
			
			
			orgdesUpdate = orgdeserv.save(orgdesAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el origen o destino en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El origen o destino  ha sido actualizado con éxito!");
		response.put("cargo", orgdesUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
