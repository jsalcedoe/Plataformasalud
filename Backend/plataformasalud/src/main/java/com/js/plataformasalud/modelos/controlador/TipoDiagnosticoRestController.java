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
import com.js.plataformasalud.modelos.entidades.TipoDiagnostico;
import com.js.plataformasalud.modelos.servicios.ITipoDiagnosticoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoDiagnosticoRestController {
	
	private ITipoDiagnosticoServiceImpl typdxserv;
	
	@GetMapping("/tipodx")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoDiagnostico> index(){
		return typdxserv.findAll();
	}
	
	@GetMapping("tipodx/{idtypdx}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtypdx) {
		TipoDiagnostico typdx = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			typdx = typdxserv.findById(idtypdx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (typdx == null) {
			response.put("mensaje", "El tipo de diagnostico ID: ".concat(idtypdx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoDiagnostico>(typdx, HttpStatus.OK);
	}
	@GetMapping("tipodx/searchxtipo/{term}")
	public ResponseEntity<?> buscarPorTerm(@PathVariable String term) {
	    List<TipoDiagnostico> tydx = null;
	    Map<String, Object> response = new HashMap<>();

	    try {
	        tydx = typdxserv.findByDetypdx(term);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al realizar la consulta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (tydx.isEmpty()) {
	        response.put("mensaje", "No se encontraron tipos de diagnosticos que coincidan con: ".concat(term));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<List<TipoDiagnostico>>(tydx, HttpStatus.OK);
	}
	
	@PostMapping("/tipodx")
	public ResponseEntity<?> save (@Valid @RequestBody TipoDiagnostico typdx, BindingResult result) {
		TipoDiagnostico Newtypdx = null;
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
				Newtypdx= typdxserv.save(typdx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de diagnostico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de diagnostico ha sido creado con éxito!");
		response.put("cargo", Newtypdx);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipodx/{idtypdx}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtypdx,@RequestBody TipoDiagnostico typdx, BindingResult result) {
		
		TipoDiagnostico typdxUpdate = null;
		TipoDiagnostico typdxAct = typdxserv.findById(idtypdx);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (typdxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de diagnostico ID: "
					.concat(idtypdx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			typdxAct.setDatecreatypdx(typdx.getDatecreatypdx());
			typdxAct.setDetypdx(typdx.getDetypdx());
			typdxAct.setEstyodx_fx(typdx.getEstyodx_fx());
			typdxAct.setNamtypdx(typdx.getNamtypdx());
			
			typdxUpdate = typdxserv.save(typdxAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de diagnostico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de diagnostico ha sido actualizado con éxito!");
		response.put("cargo", typdxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
