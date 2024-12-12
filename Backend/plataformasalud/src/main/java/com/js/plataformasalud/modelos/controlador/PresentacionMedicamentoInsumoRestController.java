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

import com.js.plataformasalud.modelos.entidades.PresentacionMedicamentoInsumo;
import com.js.plataformasalud.modelos.servicios.IPresentacionMedicamentoInsumoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class PresentacionMedicamentoInsumoRestController {
	
	private IPresentacionMedicamentoInsumoServiceImpl pmedinsserv;
	
	@GetMapping("/pmedins")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PresentacionMedicamentoInsumo> index(){
		return pmedinsserv.findAll();
	}
	
	@GetMapping("/pmedins/{idpmedins}")
	public ResponseEntity<?> mostrar(@PathVariable Long idpmedins) {
		PresentacionMedicamentoInsumo pmedins = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			pmedins = pmedinsserv.FindById(idpmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (pmedins == null) {
			response.put("mensaje", "La presentacion del medicamento o insumo ID: ".concat(idpmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PresentacionMedicamentoInsumo>(pmedins, HttpStatus.OK);
	}
	
	@PostMapping("/pmedins")
	public ResponseEntity<?> save (@Valid @RequestBody PresentacionMedicamentoInsumo pmedins, BindingResult result) {
		PresentacionMedicamentoInsumo Newpmedins = null;
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
				Newpmedins= pmedinsserv.save(pmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la presentacion del medicamento o insumo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "La presentacion del medicamento o insumo ha sido creado con éxito!");
		response.put("PresentacionMedicamentoInsumo", Newpmedins);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/pmedins/{idpmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idpmedins,@RequestBody PresentacionMedicamentoInsumo pmedins, BindingResult result) {
		
		PresentacionMedicamentoInsumo pmedinsUpdate = null;
		PresentacionMedicamentoInsumo pmedinsAct = pmedinsserv.FindById(idpmedins);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (pmedinsAct == null) {
			response.put("mensaje", "Error: no se pudo editar, la presentacion del medicamento o insumo ID: "
					.concat(idpmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			pmedinsAct.setDatecreapmedins(pmedins.getDatecreapmedins());
			pmedinsAct.setDateditpmedins(pmedins.getDateditpmedins());
			pmedinsAct.setDetpmedins(pmedins.getDetpmedins());
			pmedinsAct.setEstpmedins_fk(pmedins.getEstpmedins_fk());
			pmedinsAct.setPmedins(pmedins.getPmedins());
			
			pmedinsUpdate = pmedinsserv.save(pmedinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar la presentacion del medicamento o insumo en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La presentacion del medicamento o insumo ha sido actualizado con éxito!");
		response.put("PresentacionMedicamentoInsumo", pmedinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
