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


import com.js.plataformasalud.modelos.entidades.TipoNota;
import com.js.plataformasalud.modelos.servicios.ITipoNotaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoNotaRestController {
	
	private ITipoNotaServiceImpl typnotserv;
	
	@GetMapping("/tiponotas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoNota> index(){
		return typnotserv.findAll();
	}
	
	@GetMapping("tiponotas/{idtypnot}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtypnot) {
		TipoNota tipnot = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tipnot = typnotserv.findById(idtypnot);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tipnot == null) {
			response.put("mensaje", "El tipo de nota ID: ".concat(idtypnot.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoNota>(tipnot, HttpStatus.OK);
	}
	
	@GetMapping("/tiponotas/search/{dettypnot}")
	public ResponseEntity<?> buscarPorTipoNota(@PathVariable String dettypnot) {
	    List<TipoNota> buscadettypnot = null;
	    Map<String, Object> response = new HashMap<>();

	    try {
	    	buscadettypnot = typnotserv.findByTipoNota(dettypnot);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al realizar la consulta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (buscadettypnot.isEmpty()) {
	        response.put("mensaje", "No se encontraron tipos de notas que  coincidan con: ".concat(dettypnot));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<List<TipoNota>>(buscadettypnot, HttpStatus.OK);
	}
	
	@PostMapping("/tiponotas")
	public ResponseEntity<?> save (@Valid @RequestBody TipoNota tiponota, BindingResult result) {
		TipoNota Newtipnot = null;
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
				Newtipnot= typnotserv.save(tiponota);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de nota en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de nota ha sido creado con éxito!");
		response.put("TipoNota", Newtipnot);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("tiponotas/{idtypnot}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtypnot,@RequestBody TipoNota tiponota, BindingResult result) {
		
		TipoNota tiponotaUpdate = null;
		TipoNota tiponotaAct = typnotserv.findById(idtypnot);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tiponotaAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de nota ID: "
					.concat(idtypnot.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tiponotaAct.setDatecreatypnot(tiponota.getDatecreatypnot());
			tiponotaAct.setDettypnot(tiponota.getDettypnot());
			tiponotaAct.setEstypnot_fk(tiponota.getEstypnot_fk());
			tiponotaAct.setNametypnot(tiponota.getNametypnot());
			
			
			tiponotaUpdate = typnotserv.save(tiponotaAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de nota en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El cargo ha sido actualizado con éxito!");
		response.put("TipoNota", tiponotaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


}
