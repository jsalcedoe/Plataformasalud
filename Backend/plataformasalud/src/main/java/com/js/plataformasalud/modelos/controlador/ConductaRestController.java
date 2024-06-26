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
import com.js.plataformasalud.modelos.entidades.Conducta;
import com.js.plataformasalud.modelos.servicios.IConductaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ConductaRestController {
	
	private IConductaServiceImpl condpacserv;
	
	@GetMapping("/conducta")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Conducta> index(){
		return condpacserv.findAll();
	}
	
	@GetMapping("conducta/{idcondpac}")
	public ResponseEntity<?> mostrar(@PathVariable Long idcondpac) {
		Conducta conducta = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			conducta = condpacserv.FindById(idcondpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (conducta == null) {
			response.put("mensaje", "La conducta ID: ".concat(idcondpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conducta>(conducta, HttpStatus.OK);
	}
	
	@PostMapping("/conducta")
	public ResponseEntity<?> save (@Valid @RequestBody Conducta conducta, BindingResult result) {
		Conducta Newconducta = null;
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
				Newconducta= condpacserv.save(conducta);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la conducta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La conducta ha sido creada con éxito!");
		response.put("Conducta", Newconducta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/conducta/{idcondpac}")
	public ResponseEntity <?> actualizar (@PathVariable Long idcondpac,@RequestBody Conducta conducta, BindingResult result) {
		
		Conducta conductaUpdate = null;
		Conducta conductaAct = condpacserv.FindById(idcondpac);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (conductaAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la conducta ID: "
					.concat(idcondpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			conductaAct.setNomcondpac(conducta.getNomcondpac());
			conductaAct.setDetcondpac(conducta.getDetcondpac());
			conductaAct.setEstcondpac_fk(conducta.getEstcondpac_fk());
			conductaAct.setDatecreatcondpac(conducta.getDatecreatcondpac());
			conductaAct.setEditcondpac(conducta.getEditcondpac());
	
			
			conductaUpdate = condpacserv.save(conductaAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la conducta en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La conducta ha sido actualizada con éxito!");
		response.put("Conducta", conductaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
