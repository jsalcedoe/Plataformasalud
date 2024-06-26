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
import com.js.plataformasalud.modelos.entidades.TipoHerida;
import com.js.plataformasalud.modelos.servicios.ITipoHeridaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoHeridaRestController {
	
	private ITipoHeridaServiceImpl thxservice;
	
	@GetMapping("/tipoherida")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoHerida> index(){
		return thxservice.findAll();
	}
	
	@GetMapping("tipoherida/{idthx}")
	public ResponseEntity<?> mostrar(@PathVariable Long idthx) {
		TipoHerida thx = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			thx = thxservice.findById(idthx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (thx == null) {
			response.put("mensaje", "El tipo de herida ID: ".concat(idthx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoHerida>(thx, HttpStatus.OK);
	}
	
	@PostMapping("/tipoherida")
	public ResponseEntity<?> save (@Valid @RequestBody TipoHerida thx, BindingResult result) {
		TipoHerida Newthx = null;
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
				Newthx= thxservice.save(thx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de herida en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de herida ha sido creada con éxito!");
		response.put("Tipo Herida", Newthx);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipoherida/{idthx}")
	public ResponseEntity <?> actualizar (@PathVariable Long idthx,@RequestBody TipoHerida thx, BindingResult result) {
		
		TipoHerida thxUpdate = null;
		TipoHerida thxAct = thxservice.findById(idthx);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (thxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de herida ID: "
					.concat(idthx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			thxAct.setDatecreathx(thx.getDatecreathx());
			thxAct.setDatedithx(thx.getDatedithx());
			thxAct.setDethx(thx.getDethx());
			thxAct.setEstadothx_fk(thx.getEstadothx_fk());
			thxAct.setNomthx(thx.getNomthx());
			
			thxUpdate = thxservice.save(thxAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de herida en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de herida ha sido actualizado con éxito!");
		response.put("Tipo Herida", thxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
