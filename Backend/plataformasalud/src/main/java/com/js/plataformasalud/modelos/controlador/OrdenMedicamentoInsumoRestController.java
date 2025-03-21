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
import com.js.plataformasalud.modelos.entidades.OrdenMedicamentoInsumo;
import com.js.plataformasalud.modelos.servicios.IOrdenMedicamentoInsumoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class OrdenMedicamentoInsumoRestController {
	
	private IOrdenMedicamentoInsumoServiceImpl ordmedinserv;
	
	@GetMapping("/ordenmedicamentoinsumo")
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrdenMedicamentoInsumo> index(){
		return ordmedinserv.findAll();
	}
	
	@GetMapping("/ordenmedicamentoinsumo/{idordmedins}")
	public ResponseEntity<?> mostrar (@PathVariable Long idordmedins){
		OrdenMedicamentoInsumo ordmedins = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				ordmedins = ordmedinserv.findById(idordmedins);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(ordmedins == null) {
		response.put("mensaje", "La orden ID: ".concat(idordmedins.toString().concat(" no existe en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<OrdenMedicamentoInsumo>(ordmedins, HttpStatus.OK);
	
}
	
	@GetMapping("/ordenmedicamentoinsumo/listar/{idevent}")
	public ResponseEntity<?> ListarOrdenes (@PathVariable Long idevent){
		List<OrdenMedicamentoInsumo> ordmedins = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				ordmedins = ordmedinserv.findByEventordmedins_fk(idevent);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(ordmedins == null) {
		response.put("mensaje", "El evento ID: ".concat(idevent.toString().concat(" no tiene ordenes en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<>(ordmedins, HttpStatus.OK);
	
}
	
	@PostMapping("/ordenmedicamentoinsumo")
	public ResponseEntity<?> crear (@Valid @RequestBody OrdenMedicamentoInsumo ordmedins, BindingResult validacion){
		OrdenMedicamentoInsumo ordmedinsnew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(validacion.hasErrors()) {

			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			ordmedinsnew = ordmedinserv.save(ordmedins);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La orden ha sido creada con éxito!");
		response.put("OrdenMedicamentoInsumo", ordmedinsnew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("ordenmedicamentoinsumo/{idordmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idordmedins,@RequestBody OrdenMedicamentoInsumo ordmedins, BindingResult result) {
		
		OrdenMedicamentoInsumo ordmedinsUpdate = null;
		OrdenMedicamentoInsumo ordmedinsAct = ordmedinserv.findById(idordmedins);
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
			ordmedinsAct.setCantmedins(ordmedins.getCantmedins());
			ordmedinsAct.setDatecreatordmedins(ordmedins.getDatecreatordmedins());
			ordmedinsAct.setDateeditordmedins(ordmedins.getDateeditordmedins());
			ordmedinsAct.setDosismedins(ordmedins.getDosismedins());
			ordmedinsAct.setEstordmedins_fk(ordmedins.getEstordmedins_fk());
			ordmedinsAct.setObsordmedins(ordmedins.getObsordmedins());
			ordmedinsAct.setOrdmedins_fk(ordmedins.getOrdmedins_fk());
			ordmedinsAct.setPordmedins_fk(ordmedins.getPordmedins_fk());
			ordmedinsAct.setUniordmedins_fk(ordmedins.getUniordmedins_fk());
			
			ordmedinsUpdate = ordmedinserv.save(ordmedinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la orden en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La orden ha sido actualizada con éxito!");
		response.put("OrdenMedicamentoInsumo", ordmedinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	

}
