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

import com.js.plataformasalud.modelos.entidades.RepresentanteLegal;
import com.js.plataformasalud.modelos.servicios.IRepresentanteLegalService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class RepresentanteLegalRestController {
	
	private IRepresentanteLegalService replegserv;
	
	@GetMapping("/representantelegal")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RepresentanteLegal> index(){
		return replegserv.findAll();
	}
	
	@GetMapping("/representantelegal/{idrepleg}")
	public ResponseEntity<?> mostrar(@PathVariable Long idrepleg) {
		RepresentanteLegal replegal = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			replegal = replegserv.findById(idrepleg);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (replegal == null) {
			response.put("mensaje", "El representante legal  ID: ".concat(idrepleg.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RepresentanteLegal>(replegal, HttpStatus.OK);
	}
	
	@PostMapping("/representantelegal")
	public ResponseEntity<?> save (@Valid @RequestBody RepresentanteLegal repleg, BindingResult result) {
		RepresentanteLegal Nuevorepleg = null;
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
				Nuevorepleg = replegserv.save(repleg);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el representante legal en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El representante legal ha sido creado con éxito!");
		response.put("repleg", Nuevorepleg);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/representantelegal/{idrepleg}")
	public ResponseEntity <?> actualizar (@PathVariable Long idrepleg,@RequestBody RepresentanteLegal repleg, BindingResult result) {
		
		RepresentanteLegal replegUpdate = null;
		RepresentanteLegal replegAct = replegserv.findById(idrepleg);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (replegAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el representante legal ID: "
					.concat(idrepleg.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			replegAct.setDatecreaterepleg(repleg.getDatecreaterepleg());
			replegAct.setDocrepleg(repleg.getDocrepleg());
			replegAct.setEmailrepleg(repleg.getEmailrepleg());
			replegAct.setIdprestservrepleg_fk(repleg.getIdprestservrepleg_fk());
			replegAct.setPaperepleg(repleg.getPaperepleg());
			replegAct.setPnomrepleg(repleg.getPnomrepleg());
			replegAct.setSaperepleg(repleg.getSaperepleg());
			replegAct.setSnomrepleg(repleg.getSnomrepleg());
			replegAct.setStatusrepleg_fk(repleg.getStatusrepleg_fk());
			replegAct.setTipdocrepleg_fk(repleg.getTipdocrepleg_fk());
			
			replegUpdate =replegserv.save(replegAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el representante legal en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El representante legal ha sido actualizado con éxito!");
		response.put("repleg", replegUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
