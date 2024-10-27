package com.js.plataformasalud.modelos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;
import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgicaDTO;
import com.js.plataformasalud.modelos.servicios.IDescripcionQuirurgicaDTOServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class DescripcionQuirurgicaDTORestController {
	
	private final IDescripcionQuirurgicaDTOServiceImpl procedimientoService;
	
	@PostMapping("/descripcionesquirurgicas")
	public ResponseEntity<?> saveProcedimientosCentralizados(@Valid @RequestBody List<DescripcionQuirurgicaDTO> dtoList, BindingResult result) {
	    Map<String, Object> response = new HashMap<>();
	    
	    if(result.hasErrors()) {
	        List<String> errors = result.getFieldErrors()
	                .stream()
	                .map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
	                .collect(Collectors.toList());
	        
	        response.put("errors", errors);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	    try {
	        List<DescripcionQuirurgica> savedDescripciones = dtoList.stream()
	                .map(dto -> procedimientoService.save(dto))
	                .collect(Collectors.toList());
	        
	        response.put("mensaje", "Los procedimientos quirúrgicos han sido creados con éxito!");
	        response.put("Descripciones Quirurgicas", savedDescripciones);
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } catch (Exception e) {
	        response.put("mensaje", "Error al registrar los procedimientos quirúrgicos en la base de datos");
	        response.put("error", e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
    
}
