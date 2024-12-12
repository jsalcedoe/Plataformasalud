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

import com.js.plataformasalud.modelos.entidades.OrdenesMedicamentosInsumos;
import com.js.plataformasalud.modelos.servicios.IOrdenMedicamentoInsumoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class OrdenesMedicamentoInsumosRestController {
	
	
	private IOrdenMedicamentoInsumoServiceImpl ordmedinsserv;
	
	@GetMapping("/ordenesmedins")
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrdenesMedicamentosInsumos> index(){
		return ordmedinsserv.findAll();
	}
	
	@GetMapping("/ordenesmedins/{idordmedins}")
	public ResponseEntity<?> mostrar(@PathVariable Long idordmedins) {
		OrdenesMedicamentosInsumos ordmedins = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			ordmedins = ordmedinsserv.findById(idordmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ordmedins == null) {
			response.put("mensaje", "La orden ID: ".concat(idordmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrdenesMedicamentosInsumos>(ordmedins, HttpStatus.OK);
	}
	
	@PostMapping("/ordenesmedins")
	public ResponseEntity<?> save (@Valid @RequestBody OrdenesMedicamentosInsumos ordmedins, BindingResult result) {
		OrdenesMedicamentosInsumos Newordmedins = null;
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
				Newordmedins= ordmedinsserv.save(ordmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la orden en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La orden ha sido creada con éxito!");
		response.put("OrdenesMedicamentosInsumos", Newordmedins);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/ordenesmedins/{idordmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idordmedins,@RequestBody OrdenesMedicamentosInsumos ordmedins, BindingResult result) {
		
		OrdenesMedicamentosInsumos ordmedinsUpdate = null;
		OrdenesMedicamentosInsumos ordmedinsAct = ordmedinsserv.findById(idordmedins);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (ordmedinsAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la orden ID: "
					.concat(idordmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			ordmedinsAct.setCantprocexam(ordmedins.getCantprocexam());
			ordmedinsAct.setEventordmedins_fk(ordmedins.getEventordmedins_fk());
			ordmedinsAct.setFechaeditordmedins(ordmedins.getFechaeditordmedins());
			ordmedinsAct.setFechaordmedins(ordmedins.getFechaordmedins());
			ordmedinsAct.setMedinsord_fk(ordmedins.getMedinsord_fk());
			ordmedinsAct.setObsordmedins(ordmedins.getObsordmedins());
			ordmedinsAct.setPmedinsord_fk(ordmedins.getPmedinsord_fk());
			
			
			
			
			ordmedinsUpdate = ordmedinsserv.save(ordmedinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la orden de medicamento en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "la orden de medicamento o insumo ha sido actualizado con éxito!");
		response.put("OrdenesMedicamentosInsumos", ordmedinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
