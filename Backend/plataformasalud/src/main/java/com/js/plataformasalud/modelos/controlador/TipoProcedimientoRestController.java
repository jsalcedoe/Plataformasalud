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

import com.js.plataformasalud.modelos.entidades.TipoProcedimiento;
import com.js.plataformasalud.modelos.servicios.ITipoProcServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoProcedimientoRestController {
	
	private ITipoProcServiceImpl typxserv;
	
	@GetMapping("/tipoprocedimiento")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoProcedimiento> index(){
		return typxserv.findAll();
	}
	
	@GetMapping("/tipoprocedimiento/idtproc")
	public ResponseEntity<?> mostrar(@PathVariable Long idtproc) {
		TipoProcedimiento tpx = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tpx = typxserv.findById(idtproc);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tpx == null) {
			response.put("mensaje", "El tipo de procedimiento ID: ".concat(idtproc.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoProcedimiento>(tpx, HttpStatus.OK);
	}
	
	@GetMapping("/tipoprocedimiento/search/{detproc}")
	public ResponseEntity<?> buscarPorprocexam(@PathVariable String detproc) {
	    List<TipoProcedimiento> detprocexam = null;
	    Map<String, Object> response = new HashMap<>();

	    try {
	    	detprocexam = typxserv.findByTipoProcexam(detproc);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al realizar la consulta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (detprocexam.isEmpty()) {
	        response.put("mensaje", "No se encontraron tipos de procedimientos o examenes que coincidan con: ".concat(detproc));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<List<TipoProcedimiento>>(detprocexam, HttpStatus.OK);
	}
	
	@PostMapping("/tipoprocedimiento")
	public ResponseEntity<?> save (@Valid @RequestBody TipoProcedimiento tpx, BindingResult result) {
		TipoProcedimiento Newtpx = null;
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
				Newtpx= typxserv.save(tpx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de procedimiento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de procedimiento ha sido creado con éxito!");
		response.put("typepx", Newtpx);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipoprocedimiento/idtproc")
	public ResponseEntity <?> actualizar (@PathVariable Long idtproc,@RequestBody TipoProcedimiento tpx, BindingResult result) {
		
		TipoProcedimiento tpxUpdate = null;
		TipoProcedimiento tpxAct = typxserv.findById(idtproc);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tpxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de diagnostico ID: "
					.concat(idtproc.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tpxAct.setDatecreatypx(tpx.getDatecreatypx());
			tpxAct.setDateeditypx(tpx.getDateeditypx());
			tpxAct.setDetproc(tpx.getDetproc());
			tpxAct.setEstypx_fk(tpx.getEstypx_fk());
			tpxAct.setNomtproc(tpx.getNomtproc());
			
			tpxUpdate = typxserv.save(tpxAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de diagnostico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de diagnostico ha sido actualizado con éxito!");
		response.put("TipoDiagnostico", tpxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
