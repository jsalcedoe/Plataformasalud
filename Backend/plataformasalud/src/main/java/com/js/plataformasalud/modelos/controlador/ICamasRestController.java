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

import com.js.plataformasalud.modelos.entidades.Camas;
import com.js.plataformasalud.modelos.servicios.ICamaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ICamasRestController {
	
	private ICamaServiceImpl camserv;
	
	@GetMapping("/camas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Camas> index(){
		return camserv.findAll();
	}
	
	@GetMapping("camas/{idhab}")
	public ResponseEntity<?> mostrar(@PathVariable Long idhab) {
		Camas cam = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			cam = camserv.findById(idhab);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cam == null) {
			response.put("mensaje", "La cama o camilla con ID: ".concat(idhab.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Camas>(cam, HttpStatus.OK);
	}
	
	@PostMapping("/camas")
	public ResponseEntity<?> save (@Valid @RequestBody Camas cam, BindingResult result) {
		Camas Newcam = null;
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
				Newcam= camserv.save(cam);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la cama o camilla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La cama o camilla ha sido creada con éxito!");
		response.put("Camas", Newcam);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("camas/{idhab}")
	public ResponseEntity <?> actualizar (@PathVariable Long idhab,@RequestBody Camas cam, BindingResult result) {
		
		Camas camUpdate = null;
		Camas camAct = camserv.findById(idhab);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (camAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la cama o camilla con  ID: "
					.concat(idhab.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			camAct.setDethab(cam.getDethab());
			camAct.setEsthab_fk(cam.getEsthab_fk());
			camAct.setFechacreahab(cam.getFechacreahab());
			camAct.setNomhab(cam.getNomhab());
			camAct.setUbicahab(cam.getUbicahab());
			
			camUpdate = camserv.save(camAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la cama o camilla en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La cama o camilla ha sido actualizada con éxito!");
		response.put("Camas", camUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
