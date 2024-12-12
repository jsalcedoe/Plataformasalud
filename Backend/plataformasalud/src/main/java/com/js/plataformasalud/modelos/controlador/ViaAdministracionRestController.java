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

import com.js.plataformasalud.modelos.entidades.ViaAdministracion;
import com.js.plataformasalud.modelos.servicios.IViaAdministracionServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ViaAdministracionRestController {
	
	
	private IViaAdministracionServiceImpl viadmserv;
	
	@GetMapping("/viasadministracion")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ViaAdministracion> index(){
		return viadmserv.findAll();
	}
	
	@GetMapping("/viasadministracion/{idviadm}")
	public ResponseEntity<?> mostrar(@PathVariable Long idviadm) {
		ViaAdministracion viadm = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			viadm = viadmserv.findById(idviadm);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (viadm == null) {
			response.put("mensaje", "La via de administracion ID: ".concat(idviadm.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ViaAdministracion>(viadm, HttpStatus.OK);
	}
	
	@PostMapping("/viasadministracion")
	public ResponseEntity<?> save (@Valid @RequestBody ViaAdministracion viadm, BindingResult result) {
		ViaAdministracion Newviadm = null;
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
				Newviadm= viadmserv.save(viadm);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la via de administracion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La via de administracion ha sido creado con éxito!");
		response.put("ViaAdministracion", Newviadm);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/viasadministracion/{idviadm}")
	public ResponseEntity <?> actualizar (@PathVariable Long idviadm,@RequestBody ViaAdministracion viadm, BindingResult result) {
		
		ViaAdministracion viadmUpdate = null;
		ViaAdministracion viadmAct = viadmserv.findById(idviadm);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (viadmAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la via de administracion ID: "
					.concat(idviadm.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			viadmAct.setDatecreatviadm(viadm.getDatecreatviadm());
			viadmAct.setDateditregmedins(viadm.getDateditregmedins());
			viadmAct.setDetviadm(viadm.getDetviadm());
			viadmAct.setEstviadm_fk(viadm.getEstviadm_fk());
			viadmAct.setNomviadm(viadm.getNomviadm());
			
			
			viadmUpdate = viadmserv.save(viadmAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la via de administracion en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La via de administracion ha sido actualizado con éxito!");
		response.put("ViaAdministracion", viadmUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
