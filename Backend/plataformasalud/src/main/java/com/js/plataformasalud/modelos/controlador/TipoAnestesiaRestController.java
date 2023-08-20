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

import com.js.plataformasalud.modelos.entidades.TipoAnestesia;
import com.js.plataformasalud.modelos.servicios.ITipoAnestesiaServiceImpl;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")

public class TipoAnestesiaRestController {
	
	@Autowired
	private ITipoAnestesiaServiceImpl tipanestservice;
	
	@GetMapping("/tipoanestesias")
	public List<TipoAnestesia> index(){
		return tipanestservice.findAll();
	}
	
	@GetMapping("/tipoanestesias/{idtipanest}")
	public TipoAnestesia mostrar (@PathVariable Long idtipanest ) {
		return tipanestservice.findById(idtipanest);
	}
	
	@PostMapping("/tipoanestesias")
	public TipoAnestesia crear (@RequestBody TipoAnestesia anest) {
		return tipanestservice.save(anest);
	}
	
	@PutMapping("/tipoanestesias/{idtipanest}")
	public TipoAnestesia actualizar (@RequestBody TipoAnestesia anest,@PathVariable Long idtipanest) {
		TipoAnestesia tipanestAct = tipanestservice.findById(idtipanest);
		
		tipanestAct.setDesctipanest(anest.getDesctipanest());
		tipanestAct.setNomtipanest(anest.getNomtipanest());
		
		return tipanestservice.save(tipanestAct);
	}

}
