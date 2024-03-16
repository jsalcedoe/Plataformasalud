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

import com.js.plataformasalud.modelos.entidades.Diagnostico;
import com.js.plataformasalud.modelos.servicios.IDxServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class dxRestController {
	
	
	private IDxServiceImpl dxserv;
	
	@GetMapping("/diagnosticos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Diagnostico> index(){
		return dxserv.findAll();
	}
	
	@GetMapping("/diagnosticos/{clavedx}")
	public ResponseEntity<?> mostrar(@PathVariable Long clavedx) {
		Diagnostico diagnostico = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			diagnostico = dxserv.findById(clavedx);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (diagnostico == null) {
			response.put("mensaje", "El diagnostico ID: ".concat(clavedx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Diagnostico>(diagnostico, HttpStatus.OK);
	}
	
	@PostMapping("/diagnosticos")
	public ResponseEntity<?> save (@Valid @RequestBody Diagnostico diagnostico, BindingResult result) {
		Diagnostico Nuevodx = null;
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
				Nuevodx = dxserv.save(diagnostico);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el diagnostico en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El diagnostivo ha sido creado con éxito!");
		response.put("cliente", Nuevodx);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/diagnosticos/{clavedx}")
	public ResponseEntity <?> actualizar (@PathVariable Long clavedx,@RequestBody Diagnostico diagnostico, BindingResult result) {
		
		Diagnostico dxUpdate = null;
		Diagnostico dxAct = dxserv.findById(clavedx);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (dxAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el diagnostico ID: "
					.concat(clavedx.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			dxAct.setCapdx(diagnostico.getCapdx());
			dxAct.setDescdx(diagnostico.getDescdx());
			dxAct.setEdadmaxdx(diagnostico.getEdadmaxdx());
			dxAct.setEdadmindx(diagnostico.getEdadmindx());
			dxAct.setEstdx_fk(diagnostico.getEstdx_fk());
			dxAct.setNomdx(diagnostico.getNomdx());
			dxAct.setSexdx(diagnostico.getSexdx());
						
			dxUpdate = dxserv.save(dxAct);
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el diagnostico en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El diagnostico ha sido actualizada con éxito!");
		response.put("diagnostico", dxUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
