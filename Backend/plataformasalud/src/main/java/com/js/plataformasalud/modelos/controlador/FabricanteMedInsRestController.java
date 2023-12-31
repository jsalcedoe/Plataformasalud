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

import com.js.plataformasalud.modelos.entidades.FabricanteMedIns;
import com.js.plataformasalud.modelos.servicios.IFabricanteMedInsServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class FabricanteMedInsRestController {
	
	
	private IFabricanteMedInsServiceImpl fabmedinsservice;
	
	@GetMapping("/fabricantes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<FabricanteMedIns> Index(){
		return (List<FabricanteMedIns>) fabmedinsservice.findAll();
	}
	
	@GetMapping("/fabricantes/{nitfabmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public FabricanteMedIns mostrar (@PathVariable Long nitfabmedins) {
		return fabmedinsservice.findById(nitfabmedins);
	}
	
	@PostMapping("/fabricantes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public FabricanteMedIns crear (@RequestBody FabricanteMedIns fmi) {
		return fabmedinsservice.save(fmi);
	}
	
	@PutMapping("/fabricantes/{nitfabmedins}")
	@ResponseStatus(code = HttpStatus.OK)
	public FabricanteMedIns actualizar (@RequestBody FabricanteMedIns fmi,@PathVariable Long nitfabmedins) {
		FabricanteMedIns fabmedinsAct = fabmedinsservice.findById(nitfabmedins);
		
		fabmedinsAct.setContactfabmedins(fmi.getContactfabmedins());
		fabmedinsAct.setDirfabmedins(fmi.getDirfabmedins());
		fabmedinsAct.setFechacreafabmedins(fmi.getFechacreafabmedins());
		fabmedinsAct.setNomfabmedins(fmi.getNomfabmedins());
		
		return fabmedinsservice.save(fabmedinsAct);
	}

}
