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

import com.js.plataformasalud.modelos.entidades.DiagnosticoHistoriaClinica;
import com.js.plataformasalud.modelos.servicios.IDiagnosticoHistoriaClinicaServices;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class DiagnosticoHistoriaClinicaRestController {
	
	private IDiagnosticoHistoriaClinicaServices dxhcpacserv;
	
	@GetMapping("/diagnosticohcpac")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DiagnosticoHistoriaClinica> index(){
		return dxhcpacserv.findAll();
	}
	
	@GetMapping("/diagnosticohcpac/{iddxhcpac}")
	public ResponseEntity<?> mostrar(@PathVariable Long iddxhcpac) {
		DiagnosticoHistoriaClinica dxhcpac = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			dxhcpac = dxhcpacserv.findById(iddxhcpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (dxhcpac == null) {
			response.put("mensaje", "El Diagnostico ID: ".concat(iddxhcpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DiagnosticoHistoriaClinica>(dxhcpac, HttpStatus.OK);
	}
	@PostMapping("/diagnosticohcpac")
	public ResponseEntity<?> save (@Valid @RequestBody DiagnosticoHistoriaClinica dxhcpac, BindingResult result) {
		DiagnosticoHistoriaClinica Newdxhcpac = null;
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
			Newdxhcpac= dxhcpacserv.save(dxhcpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el diagnostico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	
		}
		response.put("mensaje", "El diagnostico ha sido registrado con éxito!");
		response.put("dxate", Newdxhcpac);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@PutMapping("/diagnosticohcpac/{iddxhcpac}")
	public ResponseEntity <?> actualizar (@PathVariable Long iddxhcpac,@RequestBody DiagnosticoHistoriaClinica dxhcpac, BindingResult result) {
		
		DiagnosticoHistoriaClinica dxhcpacUpdate = null;
		DiagnosticoHistoriaClinica dxhcpacAct = dxhcpacserv.findById(iddxhcpac);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (dxhcpacAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el diagnostico ID: "
					.concat(iddxhcpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			
			dxhcpacAct.setDatecreatdxhcpac(dxhcpac.getDatecreatdxhcpac());
			dxhcpacAct.setEditdatedxhcpac(dxhcpac.getEditdatedxhcpac());
			dxhcpacAct.setEstdxhcpac(dxhcpac.getEstdxhcpac());
			dxhcpacAct.setTypdxhcpac_fk(dxhcpac.getTypdxhcpac_fk());
			
			
			dxhcpacUpdate = dxhcpacserv.save(dxhcpacAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el registro del diagnostico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El registro de diagnostico ha sido actualizado con éxito!");
		response.put("cargo", dxhcpacUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
