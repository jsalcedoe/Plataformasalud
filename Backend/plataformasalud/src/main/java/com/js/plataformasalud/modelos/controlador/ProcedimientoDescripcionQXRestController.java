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

import com.js.plataformasalud.modelos.entidades.ProcedimientoDescripcionQX;
import com.js.plataformasalud.modelos.servicios.IProcedimientoDescripcionQXServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ProcedimientoDescripcionQXRestController {
	
	private IProcedimientoDescripcionQXServiceImpl procdescserv;
	
	@GetMapping("/procedimientosdescripcion")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProcedimientoDescripcionQX> index(){
		return procdescserv.findAll();
	}
	
	@GetMapping("/procedimientosdescripcion/{idprocqx}")
	public ResponseEntity<?> mostrar(@PathVariable Long idprocqx) {
		ProcedimientoDescripcionQX procdescqx = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			procdescqx = procdescserv.findById(idprocqx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (procdescqx == null) {
			response.put("mensaje", "El Procedimiento de la descripción ID: ".concat(idprocqx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProcedimientoDescripcionQX>(procdescqx, HttpStatus.OK);
	}
	
	@PostMapping("/procedimientosdescripcion")
	public ResponseEntity<?> save (@Valid @RequestBody ProcedimientoDescripcionQX procdescqx, BindingResult result) {
		ProcedimientoDescripcionQX Newprocdescqx = null;
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
				Newprocdescqx= procdescserv.save(procdescqx);
			}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el procedimiento de la descripción en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El procedimiento de la descripción ha sido creado con éxito!");
		response.put("cargo", Newprocdescqx);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/procedimientosdescripcion/{idprocqx}")
	public ResponseEntity <?> actualizar (@PathVariable Long idprocqx,@RequestBody ProcedimientoDescripcionQX procdescqx, BindingResult result) {
		
		ProcedimientoDescripcionQX procdescqxUpdate = null;
		ProcedimientoDescripcionQX procdescqxAct = procdescserv.findById(idprocqx);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (procdescqxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el procedimiento de la descripción ID: "
					.concat(idprocqx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			procdescqxAct.setDescqx_fk(procdescqx.getDescqx_fk());
			procdescqxAct.setEstadopxdesqx_fk(procdescqx.getEstadopxdesqx_fk());
			procdescqxAct.setProcqx_fk(procdescqx.getProcqx_fk());
			
			
			procdescqxUpdate = procdescserv.save(procdescqxAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el procedimiento de la descripción en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El procedimiento de la descripción ha sido actualizado con éxito!");
		response.put("procedimientodescripcion", procdescqxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
