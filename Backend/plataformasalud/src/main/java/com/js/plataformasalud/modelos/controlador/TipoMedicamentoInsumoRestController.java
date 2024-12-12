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

import com.js.plataformasalud.modelos.entidades.TipoMedicamentoInsumo;
import com.js.plataformasalud.modelos.servicios.ITipoMedicamentoInsumoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class TipoMedicamentoInsumoRestController {
	
	private ITipoMedicamentoInsumoServiceImpl tmedinsserv;
	
	@GetMapping("/tipomedicamentosinsumos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TipoMedicamentoInsumo> index(){
		return tmedinsserv.findAll();
	}
	
	@GetMapping("/tipomedicamentosinsumos/{idtmedins}")
	public ResponseEntity<?> mostrar(@PathVariable Long idtmedins) {
		TipoMedicamentoInsumo tmedins = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			tmedins = tmedinsserv.FindById(idtmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tmedins == null) {
			response.put("mensaje", "El medicamento o insumo ID: ".concat(idtmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoMedicamentoInsumo>(tmedins, HttpStatus.OK);
	}
	
	@PostMapping("/tipomedicamentosinsumos")
	public ResponseEntity<?> save (@Valid @RequestBody TipoMedicamentoInsumo tmedins, BindingResult result) {
		TipoMedicamentoInsumo Newtmedins = null;
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
				Newtmedins= tmedinsserv.save(tmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el medicamento o insumo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El medicamento o insumo ha sido creado con éxito!");
		response.put("cargo", Newtmedins);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipomedicamentosinsumos/{idtmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idtmedins,@RequestBody TipoMedicamentoInsumo tmedins, BindingResult result) {
		
		TipoMedicamentoInsumo tmedinsUpdate = null;
		TipoMedicamentoInsumo tmedinsAct = tmedinsserv.FindById(idtmedins);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (tmedinsAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el medicamento o insumo ID: "
					.concat(idtmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			tmedinsAct.setDatecreatymedins(tmedins.getDatecreatymedins());
			tmedinsAct.setDatedittymedins(tmedins.getDatedittymedins());
			tmedinsAct.setDetypmedins(tmedins.getDetypmedins());
			tmedinsAct.setEsttymedins_fk(tmedins.getEsttymedins_fk());
			tmedinsAct.setTypmedins(tmedins.getTypmedins());
			
			tmedinsUpdate = tmedinsserv.save(tmedinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el medicamento o insumo en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El medicamento o insumo ha sido actualizado con éxito!");
		response.put("cargo", tmedinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
