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
import com.js.plataformasalud.modelos.entidades.TipoEAPB;
import com.js.plataformasalud.modelos.servicios.ITipoEAPBServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoEAPBRestController {
	
	private ITipoEAPBServiceImpl tipentserv;

		
	@GetMapping("/tipoentidades")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoEAPB> index(){
		return tipentserv.findAll();
	}
	
	@GetMapping("tipoentidades/{idtipeapb}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtipeapb) {
		TipoEAPB tipoeapb = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tipoeapb = tipentserv.findById(idtipeapb);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tipoeapb == null) {
			response.put("mensaje", "El tipo de entidad ID: ".concat(idtipeapb.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoEAPB>(tipoeapb, HttpStatus.OK);
	}
	
	@PostMapping("/tipoentidades")
	public ResponseEntity<?> save (@Valid @RequestBody TipoEAPB tipoeapb, BindingResult result) {
		TipoEAPB newtipeapb = null;
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
				newtipeapb = tipentserv.save(tipoeapb);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el tipo de entidad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El cargo ha sido creado con éxito!");
		response.put("tipeapb", newtipeapb);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipoentidades/{idtipeapb}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtipeapb,@RequestBody TipoEAPB tipeapb, BindingResult result) {
		
		TipoEAPB tipeapbUpdate = null;
		TipoEAPB tipeapbAct = tipentserv.findById(idtipeapb);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tipeapbAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el tipo de entidad ID: "
					.concat(idtipeapb.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tipeapbAct.setDatecreatypeapb(tipeapb.getDatecreatypeapb());
			tipeapbAct.setEstyeapb_fk(tipeapb.getEstyeapb_fk());
			tipeapbAct.setNomtipeapb(tipeapb.getNomtipeapb());
			tipeapbAct.setDetipeapb(tipeapb.getDetipeapb());
			
			tipeapbUpdate = tipentserv.save(tipeapbAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el cargo en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El tipo de entidad ha sido actualizado con éxito!");
		response.put("cargo", tipeapbUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
