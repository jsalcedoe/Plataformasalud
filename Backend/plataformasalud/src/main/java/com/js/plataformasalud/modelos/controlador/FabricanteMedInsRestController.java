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

import com.js.plataformasalud.modelos.entidades.FabricanteMedIns;
import com.js.plataformasalud.modelos.servicios.IFabricanteMedInsServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class FabricanteMedInsRestController {
	
	@Autowired
	private IFabricanteMedInsServiceImpl fabmedinsservice;
	
	@GetMapping("/fabricantes")
	public List<FabricanteMedIns> Index(){
		return (List<FabricanteMedIns>) fabmedinsservice.findAll();
	}
	
	@GetMapping("/fabricantes/{nitfabmedins}")
	public FabricanteMedIns mostrar (@PathVariable Long nitfabmedins) {
		return fabmedinsservice.findById(nitfabmedins);
	}
	
	@PostMapping("/fabricantes")
	public FabricanteMedIns crear (@RequestBody FabricanteMedIns fmi) {
		return fabmedinsservice.save(fmi);
	}
	
	@PutMapping("/fabricantes/{nitfabmedins}")
	public FabricanteMedIns actualizar (@RequestBody FabricanteMedIns fmi,@PathVariable Long nitfabmedins) {
		FabricanteMedIns fabmedinsAct = fabmedinsservice.findById(nitfabmedins);
		
		fabmedinsAct.setContactfabmedins(fmi.getContactfabmedins());
		fabmedinsAct.setDirfabmedins(fmi.getDirfabmedins());
		fabmedinsAct.setFechacreafabmedins(fmi.getFechacreafabmedins());
		fabmedinsAct.setNomfabmedins(fmi.getNomfabmedins());
		
		return fabmedinsservice.save(fabmedinsAct);
	}

}