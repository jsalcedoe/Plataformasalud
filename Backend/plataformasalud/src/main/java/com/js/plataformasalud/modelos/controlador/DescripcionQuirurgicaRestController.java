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
import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
import com.js.plataformasalud.modelos.servicios.IDescripcionQuirurgicaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class DescripcionQuirurgicaRestController {
	
	
	private IDescripcionQuirurgicaServiceImpl qxserv;
	
	@GetMapping("/procedimientosqx")
	@ResponseStatus(code = HttpStatus.OK)
	public List<DescripcionQuirurgica> index(){
		return qxserv.findAll();
	}
	
	@GetMapping("/procedimientosqx/{idqx}")
	public ResponseEntity<?> mostrar(@PathVariable Long idqx) {
		DescripcionQuirurgica qx = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			qx = qxserv.findById(idqx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (qx == null) {
			response.put("mensaje", "El procedimiento quirurgico ID: ".concat(idqx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DescripcionQuirurgica>(qx, HttpStatus.OK);
	}
	
	@PostMapping("/procedimientosqx")
	public ResponseEntity<?> save (@Valid @RequestBody DescripcionQuirurgica qx, BindingResult result) {
		DescripcionQuirurgica Newqx = null;
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
				Newqx= qxserv.save(qx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el procedimiento quirurgico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El procedimiento quirurgico ha sido creado con éxito!");
		response.put("Descripcion Quirurgica", Newqx);
		response.put("idqx", Newqx.getIdqx());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/procedimientosqx/{idqx}")
	public ResponseEntity <?> actualizar (@PathVariable Long idqx,@RequestBody DescripcionQuirurgica qx, BindingResult result) {
		
		DescripcionQuirurgica qxUpdate = null;
		DescripcionQuirurgica qxAct = qxserv.findById(idqx);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (qxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el procedimiento quirurgico ID: "
					.concat(idqx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			qxAct.setAnestesia_fk(qx.getAnestesia_fk());
			qxAct.setComplicqx(qx.getComplicqx());
			qxAct.setConducqx_fk(qx.getConducqx_fk());
			qxAct.setDescqx(qx.getDescqx());
			qxAct.setFechaprocqx(qx.getFechaprocqx());
			qxAct.setHallaqx(qx.getHallaqx());
			qxAct.setHorafinprocqx(qx.getHorafinprocqx());
			qxAct.setHorainicioprocqx(qx.getHorainicioprocqx());
			qxAct.setMatprot(qx.getMatprot());
			qxAct.setMuespato(qx.getMuespato());
			
			
			qxUpdate = qxserv.save(qxAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el procedimiento quirurgico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El procedimiento quirigico ha sido actualizado con éxito!");
		response.put("ProcedimientoQuirurgico", qxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
