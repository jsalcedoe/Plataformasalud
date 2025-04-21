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
import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;
import com.js.plataformasalud.modelos.servicios.IProcedimientoExamenesImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ProcedimientoExamenesRestController {
	
	private IProcedimientoExamenesImpl pxexserv;
	
	@GetMapping("/procedimientosyexamenes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProcedimientosExamenes> index(){
		return pxexserv.findAll();
	}
	
	@GetMapping("/procedimientosyexamenes/{idpxex}")
	public ResponseEntity<?> mostrar(@PathVariable Long idpxex) {
		ProcedimientosExamenes pxex = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			pxex = pxexserv.findById(idpxex);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pxex == null) {
			response.put("mensaje", "El procedimiento o examen ID: ".concat(idpxex.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProcedimientosExamenes>(pxex, HttpStatus.OK);
	}
	@GetMapping("/procedimientosyexamenes/search/{nompxex}")
	public ResponseEntity<?> buscarPornompxex(@PathVariable String nompxex) {
	    List<ProcedimientosExamenes> buscanompxex = null;
	    Map<String, Object> response = new HashMap<>();

	    try {
	    	buscanompxex = pxexserv.findBynompxex(nompxex);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al realizar la consulta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (buscanompxex.isEmpty()) {
	        response.put("mensaje", "No se encontraron procedimientos ni examenes que coincidan con: ".concat(nompxex));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<List<ProcedimientosExamenes>>(buscanompxex, HttpStatus.OK);
	}
	
	@PostMapping("/procedimientosyexamenes")
	public ResponseEntity<?> save (@Valid @RequestBody ProcedimientosExamenes pxex, BindingResult result) {
		ProcedimientosExamenes Newpxex = null;
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
				Newpxex= pxexserv.save(pxex);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el procedimiento o examen en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El procedimiento o examen ha sido creado con éxito!");
		response.put("ProcedimientosExamenes", Newpxex);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/procedimientosyexamenes/{idpxex}")
	public ResponseEntity <?> actualizar (@PathVariable Long idpxex,@RequestBody ProcedimientosExamenes pxex, BindingResult result) {
		
		ProcedimientosExamenes pxexUpdate = null;
		ProcedimientosExamenes pxexAct = pxexserv.findById(idpxex);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (pxexAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el procedimiento o examen ID: "
					.concat(idpxex.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			pxexAct.setCodpxex(pxex.getCodpxex());
			pxexAct.setDatecreatpxex(pxex.getDatecreatpxex());
			pxexAct.setDateeditpxex(pxex.getDateeditpxex());
			pxexAct.setEstadopxex_fk(pxex.getEstadopxex_fk());
			pxexAct.setNompxex(pxex.getNompxex());
			pxexAct.setSexopxex(pxex.getSexopxex());
			pxexAct.setTpxex(pxex.getTpxex());
			
			pxexUpdate = pxexserv.save(pxexAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el procedimiento o examen en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El procedimiento o examen ha sido actualizado con éxito!");
		response.put("ProcedimientoExamen", pxexUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
