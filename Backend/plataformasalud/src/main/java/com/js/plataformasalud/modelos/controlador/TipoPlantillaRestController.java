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

import com.js.plataformasalud.modelos.entidades.TipoPlantilla;
import com.js.plataformasalud.modelos.servicios.ITipoPlantillaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoPlantillaRestController {
	
	private ITipoPlantillaServiceImpl tytempserv;
	
	@GetMapping("/tipoplantilla")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoPlantilla> index(){
		return tytempserv.findAll();
	}
	
	@GetMapping("/tipoplantilla/{idtytemp}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtytemp) {
		TipoPlantilla tytemp = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tytemp = tytempserv.findById(idtytemp);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tytemp == null) {
			response.put("mensaje", "El tipo de plantilla ID: ".concat(idtytemp.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoPlantilla>(tytemp, HttpStatus.OK);
	}
	
	@PostMapping("/tipoplantilla")
	public ResponseEntity<?> save (@Valid @RequestBody TipoPlantilla tytemp, BindingResult result) {
		TipoPlantilla Newtytemp = null;
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
				Newtytemp= tytempserv.save(tytemp);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de plantilla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de plantilla ha sido creada con éxito!");
		response.put("TipoPlantilla", Newtytemp);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipoplantilla/{idtytemp}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtytemp,@RequestBody TipoPlantilla tytemp, BindingResult result) {
		
		TipoPlantilla tytempUpdate = null;
		TipoPlantilla tytempAct = tytempserv.findById(idtytemp);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tytempAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de plantilla ID: "
					.concat(idtytemp.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tytempAct.setCreadatetytemp(tytemp.getCreadatetytemp());
			tytempAct.setDetytemp(tytemp.getDetytemp());
			tytempAct.setEsttytemp_fk(tytemp.getEsttytemp_fk());
			tytempAct.setNomtytemp(tytemp.getNomtytemp());
			
			tytempUpdate = tytempserv.save(tytempAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de plantilla en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de anestesia ha sido actualizado con éxito!");
		response.put("TipoPlantilla", tytempUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
