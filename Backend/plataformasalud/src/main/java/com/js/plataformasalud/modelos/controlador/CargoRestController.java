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

import com.js.plataformasalud.modelos.entidades.Cargo;
import com.js.plataformasalud.modelos.servicios.ICargoServicesImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class CargoRestController {
	
	
	private ICargoServicesImpl cargservices;
	
	@GetMapping("/cargos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Cargo> index(){
		return cargservices.findAll();
	}
	
	@GetMapping("cargos/{idcarguser}")
	public ResponseEntity<?> mostrar(@PathVariable Long idcarguser) {
		Cargo cargo = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			cargo = cargservices.findById(idcarguser);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cargo == null) {
			response.put("mensaje", "El cargo ID: ".concat(idcarguser.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);
	}
	
	@PostMapping("/cargos")
	public ResponseEntity<?> save (@Valid @RequestBody Cargo cargo, BindingResult result) {
		Cargo Newcargo = null;
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
				Newcargo= cargservices.save(cargo);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el cargo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El cargo ha sido creado con éxito!");
		response.put("cargo", Newcargo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/cargos/{idcarguser}")
	public ResponseEntity <?> actualizar (@PathVariable Long idcarguser,@RequestBody Cargo cargo, BindingResult result) {
		
		Cargo cargoUpdate = null;
		Cargo cargoAct = cargservices.findById(idcarguser);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (cargoAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el cargo ID: "
					.concat(idcarguser.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			cargoAct.setNomcarg(cargo.getNomcarg());
			cargoAct.setDatecreatcarguser(cargo.getDatecreatcarguser());
			cargoAct.setDetcarguser(cargo.getDetcarguser());
			cargoAct.setEstado_carguser(cargo.getEstado_carguser());
			
			
			cargoUpdate = cargservices.save(cargoAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el cargo en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El cargo ha sido actualizado con éxito!");
		response.put("cargo", cargoUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
