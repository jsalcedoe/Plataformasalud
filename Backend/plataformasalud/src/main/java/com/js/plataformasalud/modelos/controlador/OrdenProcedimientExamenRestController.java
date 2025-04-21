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
import com.js.plataformasalud.modelos.entidades.OrdenProcedimientoExamen;
import com.js.plataformasalud.modelos.servicios.IOrdenProcedimientoExamenServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class OrdenProcedimientExamenRestController {
	
	private IOrdenProcedimientoExamenServiceImpl ordprocexamserv;
	
	@GetMapping("/ordenesprocedimientos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrdenProcedimientoExamen> index(){
		return ordprocexamserv.findAll();
	}
	
	@GetMapping("/ordenesprocedimientos/{idordprocexam}")
	public ResponseEntity<?> mostrar(@PathVariable Long idordprocexam) {
		OrdenProcedimientoExamen ordprocexam = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			ordprocexam = ordprocexamserv.findById(idordprocexam);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (ordprocexam == null) {
			response.put("mensaje", "La orden ID: ".concat(idordprocexam.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrdenProcedimientoExamen>(ordprocexam, HttpStatus.OK);
	}
	
	@GetMapping("/ordenesprocedimientos/listar/{idevent}")
	public ResponseEntity<?> ListarOrdenes (@PathVariable Long idevent){
		List<OrdenProcedimientoExamen> ordprocexam = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ordprocexam = ordprocexamserv.findByEventpordprocexam_fk(idevent);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(ordprocexam == null) {
		response.put("mensaje", "El evento ID: ".concat(idevent.toString().concat(" no tiene ordenes en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<>(ordprocexam, HttpStatus.OK);
	
}
	
	@PostMapping("/ordenesprocedimientos")
	public ResponseEntity<?> save (@Valid @RequestBody OrdenProcedimientoExamen ordprocexam, BindingResult result) {
		OrdenProcedimientoExamen Newordprocexam = null;
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
				Newordprocexam= ordprocexamserv.save(ordprocexam);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la orden en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La orden ha sido registrada con éxito!");
		response.put("OrdenProcedimientoExamen", Newordprocexam);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/ordenesprocedimientos/{idordprocexam}")
	public ResponseEntity <?> actualizar (@PathVariable Long idordprocexam,@RequestBody OrdenProcedimientoExamen ordprocexam, BindingResult result) {
		
		OrdenProcedimientoExamen ordprocexamUpdate = null;
		OrdenProcedimientoExamen ordprocexamAct = ordprocexamserv.findById(idordprocexam);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (ordprocexamAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la orden ID: "
					.concat(idordprocexam.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			ordprocexamAct.setCanordprocexam(ordprocexam.getCanordprocexam());
			ordprocexamAct.setDatecreatordprocexam(ordprocexam.getDatecreatordprocexam());
			ordprocexamAct.setDateeditordprocexam(ordprocexam.getDateeditordprocexam());
			ordprocexamAct.setEstordprocexam_fk(ordprocexam.getEstordprocexam_fk());
			ordprocexamAct.setObsordprocexam(ordprocexam.getObsordprocexam());
			ordprocexamAct.setPordprocexam_fk(ordprocexam.getPordprocexam_fk());
			ordprocexamAct.setTordprocexam_fk(ordprocexam.getTordprocexam_fk());			
			
			ordprocexamUpdate = ordprocexamserv.save(ordprocexamAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la orden en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La orden ha sido actualizada con éxito!");
		response.put("OrdenProcedimientoExamen", ordprocexamUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
