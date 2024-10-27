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
import com.js.plataformasalud.modelos.entidades.EquipoQx;
import com.js.plataformasalud.modelos.servicios.IEquipoQxServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor


public class EquipoQxRestController {
	
	private IEquipoQxServiceImpl eqqxserv;
	
	@GetMapping("/equipoqx")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EquipoQx> index(){
		return eqqxserv.findAll();
	}
	
	@GetMapping("/equipoqx/{ideqqx}")
	public ResponseEntity<?> mostrar(@PathVariable Long ideqqx) {
		EquipoQx eqqx = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			eqqx = eqqxserv.findById(ideqqx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (eqqx == null) {
			response.put("mensaje", "El equipo quirurgico ID: ".concat(ideqqx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EquipoQx>(eqqx, HttpStatus.OK);
	}
	
	@PostMapping("/equipoqx")
	public ResponseEntity<?> save (@Valid @RequestBody EquipoQx eqqx, BindingResult result) {
		EquipoQx Neweqqx = null;
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
				Neweqqx= eqqxserv.save(eqqx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el equipo quirurgico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El equipo quirurgico ha sido registrado con éxito!");
		response.put("EquipoQuirurgico", Neweqqx);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/equipoqx/{ideqqx}")
	public ResponseEntity <?> actualizar (@PathVariable Long ideqqx,@RequestBody EquipoQx eqqx, BindingResult result) {
		
		EquipoQx eqqxUpdate = null;
		EquipoQx eqqxAct = eqqxserv.findById(ideqqx);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (eqqxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el equipo quirurgico ID: "
					.concat(ideqqx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			eqqxAct.setDatecreateqqx(eqqx.getDatecreateqqx());
			eqqxAct.setDateediteqqx(eqqx.getDateediteqqx());
			eqqxAct.setInteqqx(eqqx.getInteqqx());
			eqqxAct.setDesqx_fk(eqqx.getDesqx_fk());
			
			eqqxUpdate = eqqxserv.save(eqqxAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el equipo quirurgico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El registro de equip quirurgico ha sido actualizado con éxito!");
		response.put("EquipoQx", eqqxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
