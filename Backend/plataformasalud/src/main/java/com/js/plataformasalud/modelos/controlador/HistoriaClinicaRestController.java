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
import com.js.plataformasalud.modelos.entidades.HistoriaClinica;
import com.js.plataformasalud.modelos.servicios.IHistoriaClinicaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class HistoriaClinicaRestController {
	
	private IHistoriaClinicaServiceImpl hcpacserv;
	
	@GetMapping("/historiaclinica")
	@ResponseStatus(code = HttpStatus.OK)
	public List<HistoriaClinica> index(){
		return hcpacserv.findAll();
	}
	
	@GetMapping("/historiaclinica/{idhcpac}")
	public ResponseEntity<?> mostrar(@PathVariable Long idhcpac) {
		HistoriaClinica hcpac = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			hcpac = hcpacserv.findById(idhcpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (hcpac == null) {
			response.put("mensaje", "La historia clínica ID: ".concat(idhcpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<HistoriaClinica>(hcpac, HttpStatus.OK);
	}
	
	@PostMapping("/historiaclinica")
	public ResponseEntity<?> save (@Valid @RequestBody HistoriaClinica hcpac, BindingResult result) {
		HistoriaClinica Newhcpac = null;
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
				Newhcpac= hcpacserv.save(hcpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la historia clinica en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La historia clinica ha sido creada con éxito!");
		response.put("cargo", Newhcpac);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/historiaclinica/{idhcpac}")
	public ResponseEntity <?> actualizar (@PathVariable Long idhcpac,@RequestBody HistoriaClinica hcpac, BindingResult result) {
		
		HistoriaClinica hcpacUpdate = null;
		HistoriaClinica hcpacAct = hcpacserv.findById(idhcpac);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (hcpacAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la historia clinica ID: "
					.concat(idhcpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			hcpacAct.setAnalisishcpac(hcpac.getAnalisishcpac());
			hcpacAct.setAntalerhcpac(hcpac.getAntalerhcpac());
			hcpacAct.setAntfamyhcpac(hcpac.getAntfamyhcpac());
			hcpacAct.setAntfarmhcpac(hcpac.getAntfarmhcpac());
			hcpacAct.setAntpathcpac(hcpac.getAntpathcpac());
			hcpacAct.setAntqxhcpac(hcpac.getAntqxhcpac());
			hcpacAct.setAnttoxihcpac(hcpac.getAnttoxihcpac());
			hcpacAct.setDatecreathcpac(hcpac.getDatecreathcpac());
			hcpacAct.setDatedithcpac(hcpac.getDatedithcpac());
			hcpacAct.setEnfacthcpac(hcpac.getEnfacthcpac());
			hcpacAct.setEstaturahcpac(hcpac.getEstaturahcpac());
			hcpacAct.setEsthcpac_fk(hcpac.getEsthcpac_fk());
			hcpacAct.setFchcpac(hcpac.getFchcpac());
			hcpacAct.setFrhcpac(hcpac.getFrhcpac());
			hcpacAct.setImchcpac(hcpac.getImchcpac());
			hcpacAct.setMedico(hcpac.getMedico());
			hcpacAct.setMotconshcpac(hcpac.getMotconshcpac());
			hcpacAct.setObjhcpac(hcpac.getObjhcpac());
			hcpacAct.setOrigdeshcpac_fk(hcpac.getOrigdeshcpac_fk());
			hcpacAct.setPachcpac_fk(hcpac.getPachcpac_fk());
			hcpacAct.setPesohcpac(hcpac.getPesohcpac());
			hcpacAct.setPlanmanejhcpac(hcpac.getPlanmanejhcpac());
			hcpacAct.setTahcpac(hcpac.getTahcpac());
			hcpacAct.setTemphcpac(hcpac.getTemphcpac());
			
			hcpacUpdate = hcpacserv.save(hcpacAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la historia clinica en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La historia clinica ha sido actualizada con éxito!");
		response.put("Historia Clinica", hcpacUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
