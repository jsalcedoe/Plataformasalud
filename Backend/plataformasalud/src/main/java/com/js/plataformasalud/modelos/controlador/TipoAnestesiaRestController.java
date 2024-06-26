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

import com.js.plataformasalud.modelos.entidades.TipoAnestesia;
import com.js.plataformasalud.modelos.servicios.ITipoAnestesiaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoAnestesiaRestController {
	
	
	private ITipoAnestesiaServiceImpl tanestserv;
	
	@GetMapping("/tipoanestesia")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoAnestesia> index(){
		return tanestserv.findAll();
	}
	
	@GetMapping("/tipoanestesia/{idtipanest}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtipanest) {
		TipoAnestesia tanest = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tanest = tanestserv.findById(idtipanest);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tanest == null) {
			response.put("mensaje", "El tipo de anestesia ID: ".concat(idtipanest.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoAnestesia>(tanest, HttpStatus.OK);
	}
	
	@PostMapping("/tipoanestesia")
	public ResponseEntity<?> save (@Valid @RequestBody TipoAnestesia tanest, BindingResult result) {
		TipoAnestesia Newtanest = null;
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
				Newtanest= tanestserv.save(tanest);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de anestesia en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El tipo de anestesia ha sido creada con éxito!");
		response.put("TipoAnestesia", Newtanest);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipoanestesia/{idtipanest}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtipanest,@RequestBody TipoAnestesia tanest, BindingResult result) {
		
		TipoAnestesia tanestUpdate = null;
		TipoAnestesia tanestAct = tanestserv.findById(idtipanest);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tanestAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de anestesia ID: "
					.concat(idtipanest.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tanestAct.setDatecreatanest(tanest.getDatecreatanest());
			tanestAct.setDateeditanest(tanest.getDateeditanest());
			tanestAct.setDesctipanest(tanest.getDesctipanest());
			tanestAct.setEsttypanest(tanest.getEsttypanest());
			tanestAct.setNomtipanest(tanest.getNomtipanest());
			
			tanestUpdate = tanestserv.save(tanestAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el tipo de anestesia en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de anestesia ha sido actualizado con éxito!");
		response.put("TipoAnestesia", tanestUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
