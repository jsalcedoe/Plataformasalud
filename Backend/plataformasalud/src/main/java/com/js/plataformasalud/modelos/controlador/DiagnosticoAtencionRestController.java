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
import com.js.plataformasalud.modelos.entidades.DiagnosticoAtencion;
import com.js.plataformasalud.modelos.servicios.IDiagnosticoAtencionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor



public class DiagnosticoAtencionRestController {
	
private IDiagnosticoAtencionService dxateserv;
	
	@GetMapping("/diagnosticosatencion")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DiagnosticoAtencion> index(){
		return dxateserv.findAll();
	}
	
	@GetMapping("/diagnosticosatencion/{iddxathcpac}")
	public ResponseEntity<?> mostrar(@PathVariable Long iddxathcpac) {
		DiagnosticoAtencion dxate = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			dxate = dxateserv.findById(iddxathcpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (dxate == null) {
			response.put("mensaje", "El Diagnostico ID: ".concat(iddxathcpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DiagnosticoAtencion>(dxate, HttpStatus.OK);
	}
	
	@PostMapping("/diagnosticosatencion")
	public ResponseEntity<?> save (@Valid @RequestBody DiagnosticoAtencion dxate, BindingResult result) {
		DiagnosticoAtencion Newdxate = null;
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
				Newdxate= dxateserv.save(dxate);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el diagnostico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El diagnostico ha sido registrado con éxito!");
		response.put("dxate", Newdxate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/diagnosticosatencion/{iddxathcpac}")
	public ResponseEntity <?> actualizar (@PathVariable Long iddxathcpac,@RequestBody DiagnosticoAtencion dxate, BindingResult result) {
		
		DiagnosticoAtencion dxateUpdate = null;
		DiagnosticoAtencion dxateAct = dxateserv.findById(iddxathcpac);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (dxateAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el diagnostico ID: "
					.concat(iddxathcpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			dxateAct.setCreatdxathcpac(dxate.getCreatdxathcpac());
			dxateAct.setDxatehcpac_fk(dxate.getDxatehcpac_fk());
			dxateAct.setEstdxatehcpac(dxate.getEstdxatehcpac());
			dxateAct.setTypdxatehcpac_fk(dxate.getTypdxatehcpac_fk());
			dxateAct.setEditdxathcpac(dxate.getEditdxathcpac());
			
			
			dxateUpdate = dxateserv.save(dxateAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el registro del diagnostico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El registro de diagnostico ha sido actualizado con éxito!");
		response.put("cargo", dxateUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
