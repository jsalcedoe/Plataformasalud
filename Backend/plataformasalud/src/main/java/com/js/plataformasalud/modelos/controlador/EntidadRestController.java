package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Entidad;
import com.js.plataformasalud.modelos.servicios.IEntidadServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class EntidadRestController {
	
	private IEntidadServiceImpl entserv;

	public EntidadRestController(IEntidadServiceImpl entserv) {
		super();
		this.entserv = entserv;
	}
	
	@GetMapping("/entidades")
	public List<Entidad> index(){
		return entserv.findAll();
	}
	
	@GetMapping("/entidades/{ideapb}")
	public Entidad mostrar (@PathVariable Long ideapb) {
		return entserv.findById(ideapb);
	}
	
	@PostMapping("/entidades")
	public Entidad crear (@RequestBody Entidad eapb) {
		return entserv.save(eapb);
	}
	
	@PutMapping("/entidades/{ideapb}")
	public Entidad actualizar(@PathVariable Long ideapb,@RequestBody Entidad eapb){
		Entidad entAct = entserv.findById(ideapb);
		entAct.setContaceapb(eapb.getContaceapb());
		entAct.setDireapb(eapb.getDireapb());
		entAct.setEmaileapb(eapb.getEmaileapb());
		entAct.setGerenteapb(eapb.getGerenteapb());
		entAct.setNomeapb(eapb.getNomeapb());
		entAct.setTipent(eapb.getTipent());
		
		return entserv.save(entAct);
		
	}

}