package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.UnidadMedidaMedIns;
import com.js.plataformasalud.modelos.servicios.IUnidadMedidaServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class UnidadMedidaMedInsRestController {
	
	
	private IUnidadMedidaServiceImpl uniservice;
	
	@GetMapping("/unidades")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UnidadMedidaMedIns> index(){
		return uniservice.findAll();
	}
	
	@GetMapping("/unidades/{idmed}")
	@ResponseStatus(code = HttpStatus.OK)
	public UnidadMedidaMedIns mostrar (@PathVariable Long idmed) {
		
		return uniservice.findById(idmed);
		
	}
	
	@PostMapping("/unidades")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UnidadMedidaMedIns crear(@RequestBody UnidadMedidaMedIns unimed) {
		
		return uniservice.save(unimed);
	}
	
	@PutMapping("/unidades/{idmed}")
	@ResponseStatus(code = HttpStatus.OK)
	public UnidadMedidaMedIns actualiza (@RequestBody UnidadMedidaMedIns unimed,@PathVariable Long idmed) {
		UnidadMedidaMedIns unimedAct = uniservice.findById(idmed);
		
		unimedAct.setDetallnommed(unimed.getDetallnommed());
		unimedAct.setEstado(unimed.getEstado());
		unimedAct.setFechcreaunid(unimed.getFechcreaunid());
		unimedAct.setNommed(unimed.getNommed());
		
		return uniservice.save(unimedAct);
	}

}
