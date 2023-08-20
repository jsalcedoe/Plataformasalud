package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.PresentacionMedIns;
import com.js.plataformasalud.modelos.servicios.IPresentacionMedInsServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class PresentacionMedInsRestController {
	
	@Autowired
	private IPresentacionMedInsServiceImpl pmedinsdao;
	
	@GetMapping("/presentaciones")
	public List<PresentacionMedIns> idex(){
		return pmedinsdao.findAll();
	}
	
	@GetMapping("/presentaciones/{presmedins}")
	public PresentacionMedIns mostrar(@PathVariable Long presmedins) {
		return pmedinsdao.findById(presmedins);
	}
	
	@PostMapping("/presentaciones")
	public PresentacionMedIns crear (@RequestBody PresentacionMedIns pmedins) {
		return pmedinsdao.save(pmedins);
	}
	
	@PutMapping("/presentaciones/{presmedins}")
	public PresentacionMedIns update (@RequestBody PresentacionMedIns pmedins,@PathVariable Long presmedins ) {
		PresentacionMedIns pmedinsActual = pmedinsdao.findById(presmedins);
		
		pmedinsActual.setPresmedins(pmedins.getPresmedins());
		
		return pmedinsdao.save(pmedinsActual);
	}
}
