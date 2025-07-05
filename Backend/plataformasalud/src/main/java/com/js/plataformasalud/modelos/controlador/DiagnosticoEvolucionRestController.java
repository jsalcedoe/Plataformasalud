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
import com.js.plataformasalud.modelos.entidades.DiagnosticoEvolucion;
import com.js.plataformasalud.modelos.servicios.IDiagnosticoEvolucionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class DiagnosticoEvolucionRestController {
	
	private IDiagnosticoEvolucionService dxevoserv;
	

	@GetMapping("/diagnosticoevolucion")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DiagnosticoEvolucion> index(){
		return dxevoserv.findAll();
	}
	
	@GetMapping("/diagnosticoevolucion/{iddxevopac}")
	public ResponseEntity<?> mostrar(@PathVariable Long iddxevopac) {
		DiagnosticoEvolucion dxevo = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			dxevo = dxevoserv.findById(iddxevopac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (dxevo == null) {
			response.put("mensaje", "El Diagnostico ID: ".concat(iddxevopac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DiagnosticoEvolucion>(dxevo, HttpStatus.OK);
	}
	
	@PostMapping("/diagnosticoevolucion")
	public ResponseEntity<?> save (@Valid @RequestBody DiagnosticoEvolucion dxevo, BindingResult result) {
		DiagnosticoEvolucion Newdxevo = null;
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
				Newdxevo= dxevoserv.save(dxevo);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el diagnostico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El diagnostico ha sido registrado con éxito!");
		response.put("dxate", Newdxevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/diagnosticoevolucion/{iddxevopac}")
	public ResponseEntity <?> actualizar (@PathVariable Long iddxevopac,@RequestBody DiagnosticoEvolucion dxevo, BindingResult result) {
		
		DiagnosticoEvolucion dxevoUpdate = null;
		DiagnosticoEvolucion dxevoAct = dxevoserv.findById(iddxevopac);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (dxevoAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el diagnostico ID: "
					.concat(iddxevopac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			
			dxevoAct.setDatecreatdxevopac(dxevo.getDatecreatdxevopac());
			dxevoAct.setDxevopac_fk(dxevo.getDxevopac_fk());
			dxevoAct.setEditdatedxevopac(dxevo.getDatecreatdxevopac());
			dxevoAct.setEstdxevopac(dxevo.getEstdxevopac());
			dxevoAct.setTypdxevopac_fk(dxevo.getTypdxevopac_fk());
			
			
			dxevoUpdate = dxevoserv.save(dxevoAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el registro del diagnostico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El registro de diagnostico ha sido actualizado con éxito!");
		response.put("cargo", dxevoUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
