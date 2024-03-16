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
import com.js.plataformasalud.modelos.entidades.Plantillas;
import com.js.plataformasalud.modelos.servicios.IPlantillasServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PlantillasRestController {
	
	private IPlantillasServiceImpl tempserv;
	
	@GetMapping("/plantillas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Plantillas> index(){
		return tempserv.findAll();
	}
	
	@GetMapping("/plantillas/{idtemp}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtemp) {
		Plantillas temp = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			temp = tempserv.findById(idtemp);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (temp == null) {
			response.put("mensaje", "El equipo quirurgico ID: ".concat(idtemp.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Plantillas>(temp, HttpStatus.OK);
	}
	
	@PostMapping("/plantillas")
	public ResponseEntity<?> save (@Valid @RequestBody Plantillas temp, BindingResult result) {
		Plantillas Newtemp = null;
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
				Newtemp= tempserv.save(temp);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la plantilla en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La plantilla ha sido registrada con éxito!");
		response.put("Plantillas", Newtemp);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/plantillas/{idtemp}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtemp,@RequestBody Plantillas temp, BindingResult result) {
		
		Plantillas tempUpdate = null;
		Plantillas tempAct = tempserv.findById(idtemp);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tempAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la plantilla ID: "
					.concat(idtemp.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tempAct.setCreatdatetemp(temp.getCreatdatetemp());
			tempAct.setDettemp(temp.getDettemp());
			tempAct.setEdittemp(temp.getEdittemp());
			tempAct.setEsttemp_fk(temp.getEsttemp_fk());
			tempAct.setNametemp(temp.getNametemp());
			tempAct.setTyptemp_fk(temp.getTyptemp_fk());
			
			
			tempUpdate = tempserv.save(tempAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la plantilla en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La plantilla ha sido actualizada con éxito!");
		response.put("Palntillas", tempUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


}
