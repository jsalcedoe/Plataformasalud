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

import com.js.plataformasalud.modelos.entidades.UnidadMedida;
import com.js.plataformasalud.modelos.servicios.IUnidadMedidaServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class UnidadMedidaRestController {
	
	private IUnidadMedidaServiceImpl unimediserv;
	
	@GetMapping("/unidadesmedida")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UnidadMedida> index(){
		return unimediserv.findAll();
	}
	
	@GetMapping("/unidadesmedida/{idunimed}")
	public ResponseEntity<?> mostrar(@PathVariable Long idunimed) {
		UnidadMedida unimedi = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			unimedi = unimediserv.FindById(idunimed);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (unimedi == null) {
			response.put("mensaje", "La unidad de medida ID: ".concat(idunimed.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UnidadMedida>(unimedi, HttpStatus.OK);
	}
	
	@GetMapping("/unidadesmedida/search/{detunimedi}")
	public ResponseEntity<?> buscarPorUmedins(@PathVariable String detunimedi) {
	    List<UnidadMedida> unidadmedins = null;
	    Map<String, Object> response = new HashMap<>();

	    try {
	        unidadmedins = unimediserv.findByUmedins(detunimedi);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al realizar la consulta en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    if (unidadmedins.isEmpty()) {
	        response.put("mensaje", "No se encontraron unidades de medida que coincidan con: ".concat(detunimedi));
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    return new ResponseEntity<List<UnidadMedida>>(unidadmedins, HttpStatus.OK);
	}
	
	@PostMapping("/unidadesmedida")
	public ResponseEntity<?> save (@Valid @RequestBody UnidadMedida unimedi, BindingResult result) {
		UnidadMedida Newunimedi = null;
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
				Newunimedi= unimediserv.save(unimedi);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la unidad de medida en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La unidad de medida ha sido creada con éxito!");
		response.put("UnidadMedida", Newunimedi);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/unidadesmedida/{idunimed}")
	public ResponseEntity <?> actualizar (@PathVariable Long idunimed,@RequestBody UnidadMedida unimedi, BindingResult result) {
		
		UnidadMedida unimediUpdate = null;
		UnidadMedida unimediAct = unimediserv.FindById(idunimed);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (unimediAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la unidad de medida ID: "
					.concat(idunimed.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			unimediAct.setDatecreaunimedi(unimedi.getDatecreaunimedi());
			unimediAct.setDatediunimedi(unimedi.getDatediunimedi());
			unimediAct.setDetunimedi(unimedi.getDetunimedi());
			unimediAct.setEstunimed_fk(unimedi.getEstunimed_fk());
			unimediAct.setUnimedi(unimedi.getUnimedi());
			
			
			unimediUpdate = unimediserv.save(unimediAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la unidad de medida en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La unidad de medida ha sido actualizado con éxito!");
		response.put("UnidadMedida", unimediUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
