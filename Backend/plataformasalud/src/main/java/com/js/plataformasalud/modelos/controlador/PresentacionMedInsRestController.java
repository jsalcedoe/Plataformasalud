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

import com.js.plataformasalud.modelos.entidades.PresentacionMedIns;
import com.js.plataformasalud.modelos.servicios.IPresentacionMedInsServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class PresentacionMedInsRestController {
	
	
	private IPresentacionMedInsServiceImpl pmedinsdao;
	
	@GetMapping("/presentaciones")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PresentacionMedIns> idex(){
		return pmedinsdao.findAll();
	}
	
	@GetMapping("/presentaciones/{presmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public PresentacionMedIns mostrar(@PathVariable Long presmedins) {
		return pmedinsdao.findById(presmedins);
	}
	
	@PostMapping("/presentaciones")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PresentacionMedIns crear (@RequestBody PresentacionMedIns pmedins) {
		return pmedinsdao.save(pmedins);
	}
	
	@PutMapping("/presentaciones/{presmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public PresentacionMedIns update (@RequestBody PresentacionMedIns pmedins,@PathVariable Long presmedins ) {
		PresentacionMedIns pmedinsActual = pmedinsdao.findById(presmedins);
		
		pmedinsActual.setPresmedins(pmedins.getPresmedins());
		
		return pmedinsdao.save(pmedinsActual);
	}
}
