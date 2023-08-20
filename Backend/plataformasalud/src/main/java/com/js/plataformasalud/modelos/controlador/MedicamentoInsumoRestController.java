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

import com.js.plataformasalud.modelos.entidades.MedicamentoInsumo;
import com.js.plataformasalud.modelos.servicios.IMedicamentoInsumoServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class MedicamentoInsumoRestController {
	
	@Autowired
	private IMedicamentoInsumoServiceImpl medinsuserv;
	
	@GetMapping("/medicamentosinsumos")
	public List<MedicamentoInsumo> index(){
		return medinsuserv.findAll();
	}
	
	@GetMapping("/medicamentosinsumos/{idmedins}")
	public MedicamentoInsumo mostrar(@PathVariable Long idmedins) {
		return medinsuserv.findById(idmedins);
	}
	
	@PostMapping("/medicamentosinsumos")
	public MedicamentoInsumo crear(@RequestBody MedicamentoInsumo medins) {
		return medinsuserv.save(medins);
	}
	
	@PutMapping("/medicamentosinsumos/{idmedins}")
	public MedicamentoInsumo actualiza (@RequestBody MedicamentoInsumo medins,@PathVariable Long idmedins ) {
		MedicamentoInsumo medinsAct = medinsuserv.findById(idmedins);
		
		medinsAct.setFabricante(medins.getFabricante());
		medinsAct.setFechacreamedins(medins.getFechacreamedins());
		medinsAct.setGrupo(medins.getGrupo());
		medinsAct.setLotemedins(medins.getLotemedins());
		medinsAct.setNominsmed(medins.getNominsmed());
		medinsAct.setPresmedins(medins.getPresmedins());
		medinsAct.setPresmedins(medins.getPresmedins());
		medinsAct.setReginvimamedins(medins.getReginvimamedins());
		medinsAct.setUnidades(medins.getUnidades());
		
		return medinsuserv.save(medinsAct);
	}

}
