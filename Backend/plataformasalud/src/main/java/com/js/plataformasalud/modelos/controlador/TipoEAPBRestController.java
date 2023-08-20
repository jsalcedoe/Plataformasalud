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

import com.js.plataformasalud.modelos.entidades.TipoEAPB;
import com.js.plataformasalud.modelos.servicios.ITipoEAPBServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class TipoEAPBRestController {
	
	private ITipoEAPBServiceImpl tipentserv;

	public TipoEAPBRestController(ITipoEAPBServiceImpl tipentserv) {
		super();
		this.tipentserv = tipentserv;
	}
	
	@GetMapping("/tipoentidades")
	public List<TipoEAPB> index(){
		return tipentserv.findAll();
	}
	
	@GetMapping("/tipoentidades/{idtipeapb}")
	public TipoEAPB mostrar (@PathVariable long idtipeapb) {
		return tipentserv.findById(idtipeapb);
	}
	
	@PostMapping("/tipoentidades")
	public TipoEAPB crear (@RequestBody TipoEAPB tipoentidad) {
		return tipentserv.save(tipoentidad);
	}
	
	@PutMapping("/tipoentidades")
	public TipoEAPB actualizar (@PathVariable long idtipeapb, @RequestBody TipoEAPB tipoentidad) {
		
		TipoEAPB tipoentAct = tipentserv.findById(idtipeapb);
		
		tipoentAct.setNomtipeapb(tipoentidad.getNomtipeapb());
		
		return tipentserv.save(tipoentAct);
	}

}
