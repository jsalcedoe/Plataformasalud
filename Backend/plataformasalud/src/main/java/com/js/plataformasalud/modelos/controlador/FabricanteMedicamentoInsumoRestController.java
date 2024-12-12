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


import com.js.plataformasalud.modelos.entidades.FabricanteMedicamentoInsumo;
import com.js.plataformasalud.modelos.servicios.IFabricanteMedicamentoInsumoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class FabricanteMedicamentoInsumoRestController {
	
	
	private IFabricanteMedicamentoInsumoServiceImpl fabmedinsserv;
	
	@GetMapping("/fabricantes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<FabricanteMedicamentoInsumo> index(){
		return fabmedinsserv.findAll();
	}
	
	@GetMapping("/fabricantes/{idfabmedins}")
	public ResponseEntity<?> mostrar(@PathVariable Long idfabmedins) {
		FabricanteMedicamentoInsumo fabmedins = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			fabmedins = fabmedinsserv.findById(idfabmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (fabmedins == null) {
			response.put("mensaje", "El fabricante ID: ".concat(idfabmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FabricanteMedicamentoInsumo>(fabmedins, HttpStatus.OK);
	}
	
	@PostMapping("/fabricantes")
	public ResponseEntity<?> save (@Valid @RequestBody FabricanteMedicamentoInsumo fabmedins, BindingResult result) {
		FabricanteMedicamentoInsumo Newfabmedins = null;
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
				Newfabmedins= fabmedinsserv.save(fabmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el fabricante en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El fabricante ha sido creado con éxito!");
		response.put("FabricanteMedicamentoInsumo", Newfabmedins);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/fabricantes/{idfabmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idfabmedins,@RequestBody FabricanteMedicamentoInsumo fabmedins, BindingResult result) {
		
		FabricanteMedicamentoInsumo fabmedinsUpdate = null;
		FabricanteMedicamentoInsumo fabmedinsAct = fabmedinsserv.findById(idfabmedins);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (fabmedinsAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el fabricante ID: "
					.concat(idfabmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			fabmedinsAct.setCiufabmedins_fk(fabmedins.getCiufabmedins_fk());
			fabmedinsAct.setDatecreatfabmedins(fabmedins.getDatecreatfabmedins());
			fabmedinsAct.setDateditfabmedins(fabmedins.getDateditfabmedins());
			fabmedinsAct.setDirfabmedins(fabmedins.getDirfabmedins());
			fabmedinsAct.setEstfabmedins_fk(fabmedins.getEstfabmedins_fk());
			fabmedinsAct.setNomfabmedins(fabmedins.getNomfabmedins());
			
			fabmedinsUpdate = fabmedinsserv.save(fabmedinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el fabricante en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El fabricante ha sido actualizado con éxito!");
		response.put("FabricanteMedicamentoInsumo", fabmedinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
