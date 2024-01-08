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


import com.js.plataformasalud.modelos.entidades.PrestadorServicio;
import com.js.plataformasalud.modelos.servicios.IPrestadorServicioServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class PrestadorServicioRestController {
	
	private IPrestadorServicioServiceImpl preservserv;

	@GetMapping("/prestadorservicio")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PrestadorServicio> index(){
		return preservserv.findAll();
	}
	
	@GetMapping("/prestadorservicio/{idprestserv}")
	public ResponseEntity<?> mostrar (@PathVariable Long idprestserv){
		
		PrestadorServicio prestserv = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
				prestserv = preservserv.findById(idprestserv);
			} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(prestserv == null) {
			response.put("mensaje", "El prestador del servicio ID: ".concat(idprestserv.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<PrestadorServicio>(prestserv, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/prestadorservicio")
	public ResponseEntity<?> crear (@Valid @RequestBody PrestadorServicio prestserv, BindingResult validacion){
		
		PrestadorServicio prestser = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(validacion.hasErrors()) {

			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			prestser = preservserv.save(prestserv);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El prestador de servicio ha sido creada con éxito!");
		response.put("prestserv", prestser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PutMapping("/prestadorservicio/{idprestserv}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> actualiza (@Valid @RequestBody PrestadorServicio prestserv, @PathVariable Long idprestserv, BindingResult validacion) {
		
		PrestadorServicio prestservActual = preservserv.findById(idprestserv);
		
		PrestadorServicio prestservUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if (validacion.hasErrors()) {
			List<String> errors = validacion.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(prestservActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el prestador ID: "
					.concat(idprestserv.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			prestservActual.setDirprestserv(prestserv.getDirprestserv());
			prestservActual.setDocprestserv(prestserv.getDocprestserv());
			prestservActual.setEmailprestserv(prestserv.getEmailprestserv());
			prestservActual.setEstado_fk(prestserv.getEstado_fk());
			prestservActual.setFechacreaprestserv(prestserv.getFechacreaprestserv());
			prestservActual.setNomprestserv(prestserv.getNomprestserv());
			prestservActual.setTelprestserv(prestserv.getTelprestserv());
			prestservActual.setTipdocprestserv_fk(prestserv.getTipdocprestserv_fk());
			
			
			prestservUpdate = preservserv.save(prestservActual);
			}catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el prestador de servicio en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		response.put("mensaje", "El prestador de servicio ha sido actualizada con éxito!");
		response.put("prestserv", prestservUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}

}
