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


import com.js.plataformasalud.modelos.entidades.TipoDocumento;
import com.js.plataformasalud.modelos.servicios.ITipoDocumentoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoDocumentoRestController {
	
	private ITipoDocumentoService tipdocserv;
	
	@GetMapping("/tipodocumento")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoDocumento> index(){
		return tipdocserv.findAll();
	}
	
	@GetMapping("tipodocumento/{idtipdoc}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtipdoc) {
		TipoDocumento tipdoc = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tipdoc = tipdocserv.FindById(idtipdoc);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tipdoc == null) {
			response.put("mensaje", "El tipo de documento ID: ".concat(idtipdoc.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoDocumento>(tipdoc, HttpStatus.OK);
	}
	
	@PostMapping("/tipodocumento")
	public ResponseEntity<?> save (@Valid @RequestBody TipoDocumento tipdoc, BindingResult result) {
		TipoDocumento Newtipdoc = null;
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
				Newtipdoc= tipdocserv.save(tipdoc);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de documento en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de documento ha sido creado con éxito!");
		response.put("tipdoc", Newtipdoc);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipodocumento/{idtipdoc}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtipdoc,@RequestBody TipoDocumento tipdoc, BindingResult result) {
		
		TipoDocumento tipdocUpdate = null;
		TipoDocumento tipdocAct = tipdocserv.FindById(idtipdoc);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tipdocAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de documento ID: "
					.concat(idtipdoc.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tipdocAct.setDatecreatetipdoc(tipdoc.getDatecreatetipdoc());
			tipdocAct.setDetatipdoc(tipdoc.getDetatipdoc());
			tipdocAct.setTipdoc(tipdoc.getTipdoc());
			
			
			tipdocUpdate = tipdocserv.save(tipdocAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de documento en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de documento ha sido actualizado con éxito!");
		response.put("tipdoc", tipdocUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
