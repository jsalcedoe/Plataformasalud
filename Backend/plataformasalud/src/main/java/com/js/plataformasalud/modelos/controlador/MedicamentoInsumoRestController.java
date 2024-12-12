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

import com.js.plataformasalud.modelos.entidades.MedicamentoInsumo;
import com.js.plataformasalud.modelos.servicios.IMedicamentoInsumoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class MedicamentoInsumoRestController {
	
	
	private IMedicamentoInsumoServiceImpl medinsserv;
	
	@GetMapping("/medicamentosinsumos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<MedicamentoInsumo> index(){
		return medinsserv.findAll();
	}
	
	@GetMapping("medicamentosinsumos/{idmedins}")
	public ResponseEntity<?> mostrar(@PathVariable Long idmedins) {
		MedicamentoInsumo medins = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			medins = medinsserv.findById(idmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (medins == null) {
			response.put("mensaje", "El medicamento o insumo ID: ".concat(idmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MedicamentoInsumo>(medins, HttpStatus.OK);
	}
	
	@PostMapping("/medicamentosinsumos")
	public ResponseEntity<?> save (@Valid @RequestBody MedicamentoInsumo medins, BindingResult result) {
		MedicamentoInsumo Newmedins = null;
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
				Newmedins= medinsserv.save(medins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el medicamento o insumo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El medicamento o insumo ha sido creado con éxito!");
		response.put("MedicamentoInsumo", Newmedins);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/medicamentosinsumos/{idmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idmedins,@RequestBody MedicamentoInsumo medins, BindingResult result) {
		
		MedicamentoInsumo medinsUpdate = null;
		MedicamentoInsumo medinsAct = medinsserv.findById(idmedins);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (medinsAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el medicamento o insumo ID: "
					.concat(idmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			medinsAct.setDatecreamedins(medins.getDatecreamedins());
			medinsAct.setDatedimedins(medins.getDatedimedins());
			medinsAct.setEstmedins_fk(medins.getEstmedins_fk());
			medinsAct.setFmedins_fk(medins.getFmedins_fk());
			medinsAct.setMedins(medins.getMedins());
			medinsAct.setPmedins_fk(medins.getPmedins_fk());
			medinsAct.setReginvmedinv(medins.getReginvmedinv());
			medinsAct.setTmedins_fk(medins.getTmedins_fk());
			medinsAct.setUmedins_fk(medins.getUmedins_fk());
			medinsAct.setCummedins(medins.getCummedins());
			
			medinsUpdate = medinsserv.save(medinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el medicamento o insumo en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El medicamento o insumo ha sido actualizado con éxito!");
		response.put("MedicamentoInsumo", medinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
